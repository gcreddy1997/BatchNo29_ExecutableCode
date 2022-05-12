package com.magnitia.Batch29_DbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ConnectDB {
			public static Statement stmnt;
		public static Connection connection = null;
		public static ResultSet rs;
		public static List<HashMap<String, Object>> list;
		public static HashMap<String,Object> row;
		public static List z, z1; 
		//******************************************************************/
		/**
		 * This function will Extablish connection with database
		 */		
		public static void EstablishConnection() {
			System.out.println(" ----------- Establish Oracle JDBC connection  -----");
			try{
				connection = DriverManager.getConnection("Connection String");
			}catch(SQLException e){
				e.printStackTrace();			
			}
			if (connection != null){
				System.out.println("-------Connection established successfully -----------"); }
				else {
					System.out.println("-------Failed to make connection  ------------");
				}
			try{
				stmnt = connection.createStatement();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
					
			}  
		/**
		 * this function will return the data from database as per query
		 * @param query this param will pass raw query
		 * @param parameterValue
		 * @return
		 */
		
		public static List<HashMap<String, Object>> getResult(String query, Map<String, String>parameterValue){
			
				String finalq1 = Query(query, parameterValue);
		try{		
				ResultSetMetaData rsmd = rs.getMetaData();
				int col = rsmd.getColumnCount();
				list = new ArrayList<HashMap<String, Object>>();
			 while (rs.next()){
				row = new HashMap<String, Object>(col);
				for (int i = 1; i<=col; i++){
					row.put(rsmd.getColumnName(i), rs.getObject(i));				
				}
			  list.add(row);			
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		 return list;
	}

			private static String Query(String query, Map<String, String> parameterValue) {
				// TODO Auto-generated method stub
				Map<String, String> parameterValue1 = parameterValue;
				int count = 0;
				
				z = new ArrayList<Object>();
				z1 = new ArrayList<Object>();
				// String x = "select * from <TABLENAME> where <columnName> like (#accNumber/);
				//int a = query.length();			
				for (int i=0; i<query.length(); i++){
					char charAt = query.charAt(i);
				//	System.out.println("Testing2.main()"+charAt);
					if (charAt == '\''){
						for (int j = i; j<query.length(); j++) {
							char charAt1 = query.charAt(j);
							if(charAt1 == ',' || charAt1 == ' '|| charAt1 == '\''){
								String d = query.substring(i+1, j);
								z1.add(d);
								break;						
							}
						}	
						count++; 
						z.add(i);
					}				
				}
			for (int i=0; i<z1.size(); i++)  {
				Iterator it = parameterValue1.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry pair = (Map.Entry)it.next();
					if (z1.get(i).toString().equalsIgnoreCase(pair.getKey().toString())) {
						query = query.replace('#' + z1.get(i).toString(), pair.getValue().toString());
					}
				}
			}
			System.out.println("Final Query  ---------" + query);
			return query;
			}  
		//************************************************************************
		public static Map<String, String> collectdata (String ABCValue) {
			Map<String, String> parameter = new HashMap<String, String>();
			parameter.put("abc", ABCValue);
			return parameter;
		}  
		//************************************************************************	
		



}

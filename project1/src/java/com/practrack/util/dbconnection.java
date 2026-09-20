
package com.practrack.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbconnection {
    
     private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
     private static final String USER = "PRACTRACK";
     private static final String PASSWORD ="Practrack123";
     
   public static Connection getConnection(){
       
       Connection con=null;
         try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            con = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Oracle Connected Successfully");

        } catch(Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
     
     
     
     


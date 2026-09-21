package com.practrack.util;

import java.sql.Connection;

public class dbconnectiontest {

    public static void main(String[] args) {

        Connection con = dbconnection.getConnection();

        if (con != null) {
            System.out.println("DATABASE CONNECTION SUCCESSFUL!");
        } else {
            System.out.println("DATABASE CONNECTION FAILED!");
        }
    }
}
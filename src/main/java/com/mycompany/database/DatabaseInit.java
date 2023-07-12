/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gabri
 */
public class DatabaseInit 
{
    private static final String DATABASE_URL = "jdbc:h2:~/test";

    public static Connection getConnection() throws SQLException 
    {
        return DriverManager.getConnection(DATABASE_URL);
    }
    
    public String getUrl()
    {
        return DATABASE_URL;
    }
}

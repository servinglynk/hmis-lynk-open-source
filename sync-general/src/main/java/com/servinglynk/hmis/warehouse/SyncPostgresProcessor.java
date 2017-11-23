package com.servinglynk.hmis.warehouse;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import com.jcraft.jsch.Logger;

public class SyncPostgresProcessor extends Logging{
    public static int batchSize = 1000;
    private static Connection connection = null;
    static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://" + Properties.POSTGRESQL_DB_HOST + ":" + Properties.POSTGRESQL_DB_PORT + "/" + Properties.POSTGRESQL_DB_DATABASE,
                    Properties.POSTGRESQL_DB_USERNAME,
                    Properties.POSTGRESQL_DB_PASSWORD);
        }
        if (connection.isClosed()) {
            throw new SQLException("connection could not initiated");
        }
        return connection;
    }

    public static List<String> getAllTablesFromPostgres(String schemaName) throws Exception{
        List<String> tables = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("SELECT table_name FROM information_schema.tables WHERE table_schema='"+schemaName+"'");
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                tables.add(resultSet.getString("table_name"));
            }

        }catch (Exception ex){
            throw ex;
        }
        return tables;
    }

    
    public static void hydrateSyncTable(String schemaName,String tableName,String status,String message){
        PreparedStatement statement = null;
        Connection connection = null;
        try{
            connection = getConnection();
            statement = connection.prepareStatement("insert into "+schemaName+".sync values (?,?,?,?,?,?)");
            statement.setObject(1, UUID.randomUUID());
            statement.setString(2, tableName);
            statement.setString(3,status);
            statement.setString(4,message);
            statement.setTimestamp(5, getCUrrentTimestamp());
            statement.setTimestamp(6, getCUrrentTimestamp());
            statement.executeUpdate();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
    private static Timestamp getCUrrentTimestamp() {
        Calendar calendar = Calendar.getInstance();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
        return currentTimestamp;

    }
}

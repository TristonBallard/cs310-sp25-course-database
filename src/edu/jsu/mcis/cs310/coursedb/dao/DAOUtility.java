package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
              
                ResultSetMetaData metaD = rs.getMetaData();
                int colCount = metaD.getColumnCount();
                
                while (rs.next()) {
                    JsonObject rec = new JsonObject();
                    for (int i = 1; i <= colCount; i++) {
                        String colName = metaD.getColumnName(i);
                        Object colValue = rs.getObject(i);
                        rec.put(colName, colValue);
                    }
                    records.add(rec);
                }
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        
        
        return Jsoner.serialize(records);
    }
}
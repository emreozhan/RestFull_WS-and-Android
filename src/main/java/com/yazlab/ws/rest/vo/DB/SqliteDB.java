
package com.yazlab.ws.rest.vo.DB;

import com.yazlab.ws.rest.vo.JsonTip;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteDB {
  
    String DBPath="jdbc:sqlite:E:\\dbEV.sqlite";   
   
    
  public JsonTip SelectHome() throws ClassNotFoundException 
  {
   
    JsonTip rEV=new JsonTip();
    Class.forName("org.sqlite.JDBC");
    Connection connection = null; 
    try
    {
      connection = DriverManager.getConnection(DBPath);
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec
      
      ResultSet rs = statement.executeQuery("select * from tblEV");
      
     

      while(rs.next())
      {
          rEV.setEvID(rs.getInt("evID"));
          rEV.setEvIL(rs.getString("evIL"));
          rEV.setEvEmlakTip(rs.getString("evEmlakTip"));
          rEV.setEvAlan(rs.getInt("evAlan"));
          rEV.setEvOdaSayisi(rs.getString("evOdaSayisi"));
          rEV.setEvBinaYasi(rs.getInt("evBinaYasi"));
          rEV.setEvBulKat(rs.getInt("evBulKat"));
          rEV.setEvFiyat(rs.getInt("evFiyat"));
          rEV.setEvAciklama(rs.getString("evAciklama"));
          
          
      
            
      }
    
    }
    catch(SQLException e)
    {
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
          if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {System.err.println(e);}
    }
    
       return rEV;
  }
    
 
  public void DBCreate() throws ClassNotFoundException
    {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    try
    {
      connection = DriverManager.getConnection(DBPath);
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec
      
      statement.executeUpdate("drop table if exists tblEV");
      statement.executeUpdate("drop table if exists tblRESIM");
      statement.executeUpdate("CREATE TABLE tblEV ( `evID` INTEGER, `evIL` TEXT, `evEmlakTip` TEXT, `evAlan` INTEGER, `evOdaSayisi` TEXT, `evBinaYasi` INTEGER, `evBulKat` INTEGER, `evFiyat` REAL, `evAciklama` VARCHAR(200), PRIMARY KEY(`evID`) )");
      statement.executeUpdate("CREATE TABLE `tblRESIM` ( `resimID` INTEGER, `resimYol` TEXT, `resimEvID` INTEGER, PRIMARY KEY(`resimID`) FOREIGN KEY(resimEvID) REFERENCES tblEV(evID))");
      statement.executeUpdate("insert into tblEV values(1, 'kocaeli','satilik',110,'3+1',12,3,135.000,'yatirim amacli')");
      
      ResultSet rs = statement.executeQuery("select * from person");
      while(rs.next())
      {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory", 
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
    
    
    
  }
    
}

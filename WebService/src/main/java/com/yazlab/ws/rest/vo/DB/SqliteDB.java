
package com.yazlab.ws.rest.vo.DB;

import com.yazlab.ws.rest.vo.JsonTip;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.ws.rs.core.Response;

public class SqliteDB {
  
    String DBPath="jdbc:sqlite:E:\\dbEV.sqlite";   
   
    
  public JsonTip[] SelectHome() throws ClassNotFoundException 
  {
    JsonTip [] Jsondizi=new JsonTip[5];
    int sayac=0;
    
    JsonTip rEv;
    Class.forName("org.sqlite.JDBC");
    Connection connection = null; 
    try
    {
      connection = DriverManager.getConnection(DBPath);
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec
       
      //Satir Sayýsý
        ResultSet count = statement.executeQuery("SELECT * FROM tblEV");
        count = statement.executeQuery("SELECT COUNT(*) FROM tblEV");
        count.next();
        int rowCount = count.getInt(1);
        Jsondizi=new JsonTip[rowCount];
        
         ResultSet rs = statement.executeQuery("select * from tblEV");
      
         while(rs.next())
      {
          rEv=new JsonTip();
          
          rEv.setEvID(rs.getInt("evID"));
          rEv.setEvIL(rs.getString("evIL"));
          rEv.setEvEmlakTip(rs.getString("evEmlakTip"));
          rEv.setEvAlan(rs.getInt("evAlan"));
          rEv.setEvOdaSayisi(rs.getString("evOdaSayisi"));
          rEv.setEvBinaYasi(rs.getInt("evBinaYasi"));
          rEv.setEvBulKat(rs.getInt("evBulKat"));
          rEv.setEvFiyat(rs.getInt("evFiyat"));
          rEv.setEvAciklama(rs.getString("evAciklama"));
          
          Jsondizi[sayac]=rEv;
          sayac++;
            
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
    
       return Jsondizi;
  }
    
  public Response SelectHomePicture(int evID,int resimID) throws ClassNotFoundException, IOException
  {
    Class.forName("org.sqlite.JDBC");
    Connection connection = null; 
    Response res=null;
    try
    {
      connection = DriverManager.getConnection(DBPath);
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec

        //Satir Sayýsý
        ResultSet count = statement.executeQuery("SELECT * FROM tblRESIM WHERE resimEvID=="+evID+"");
        count = statement.executeQuery("SELECT COUNT(*) FROM tblRESIM WHERE resimEvID=="+evID+"");
        count.next();
                int rowCount = count.getInt(1);

          ResultSet rs = statement.executeQuery("select * from tblRESIM WHERE resimEvID=="+evID+"");
         int i=0;
         while(i<resimID)
         { 
          rs.next();
          String adres=rs.getString("resimYol");
        
          File sourceimage=new File(adres);
          BufferedImage image = ImageIO.read(sourceimage);
          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          ImageIO.write(image, "jpg", baos);
          byte[] imageData = baos.toByteArray();     
          res=Response.ok(new ByteArrayInputStream(imageData)).build();
          i++;
         }
      return res;
         
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
    
    return res;
  }
 
  public void DBCreate() throws ClassNotFoundException
    {
    Class.forName("org.sqlite.JDBC");
    Connection connection = null;
    try
    {
      connection = DriverManager.getConnection(DBPath);
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);
      
      statement.executeUpdate("drop table if exists tblEV");
      statement.executeUpdate("drop table if exists tblRESIM");
      statement.executeUpdate("CREATE TABLE tblEV ( `evID` INTEGER, `evIL` TEXT, `evEmlakTip` TEXT, `evAlan` INTEGER, `evOdaSayisi` TEXT, `evBinaYasi` INTEGER, `evBulKat` INTEGER, `evFiyat` REAL, `evAciklama` VARCHAR(200), PRIMARY KEY(`evID`) )");
      //statement.executeUpdate("CREATE TABLE tblRESIM ( `resimID` INTEGER, `resimYol` TEXT, `resimEvID` INTEGER, PRIMARY KEY(`resimID`) FOREIGN KEY(resimEvID) REFERENCES tblEV(evID))");
      statement.executeUpdate("CREATE TABLE tblRESIM (`resimID` INTEGER, `resimYol` TEXT, `resimEvID` INTEGER)");
      
    
     String aa="e://wsfoto/";
      statement.executeUpdate("insert into tblRESIM(resimID,resimYol,resimId) VALUES  (1,'"+aa+"11.jpg',1)");
      statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (2,'"+aa+"12.jpg',1)");
      statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (3,'"+aa+"13.jpg',1)");
      
      
              
      statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (4,'"+aa+"21.jpg',2)");
      statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (5,'"+aa+"22.jpg',2)");
      statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (6,'"+aa+"23.jpg',2)");
      
     statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (7,'"+aa+"31.jpg',3)");
      statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (8,'"+aa+"32.jpg',3)");
      statement.executeUpdate("insert into tblRESIM (resimID,resimYol,resimId) VALUES (9,'"+aa+"33.jpg',3)");
      
      statement.executeUpdate("insert into tblEV values(1, 'Kocaeli','Satilik',110,'3+1',12,3,135.000,'Yatirim amacli')");
      statement.executeUpdate("insert into tblEV values(2, 'Ýstanbul','Kiralik',65,'1+1',5,1,1000,'Ogrenciye Kiralik ada manzaralý')");
      statement.executeUpdate("insert into tblEV values(3, 'Antalya','Kiralik',100,'3+1',25,1,800,'Günlük Kiralik')");
      statement.executeUpdate("insert into tblEV values(3, 'Ankara','Satilik',200,'4+1',5,3,200.000,'Aile için uygun merkezi')");
     
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
      {
        System.err.println(e);
      }
    }
    
    
    
  }
    
}

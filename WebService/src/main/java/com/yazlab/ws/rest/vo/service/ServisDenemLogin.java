package com.yazlab.ws.rest.vo.service;


import com.yazlab.ws.rest.vo.DB.SqliteDB;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.yazlab.ws.rest.vo.JsonTip;
import com.yazlab.ws.rest.vo.TopluDonus;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;



@Path("/yazlab")
public class ServisDenemLogin {
   
    @GET
    @Path("/img/{evID}/{resimID}")
    @Produces("image/jpg")
    public  Response getImage(@PathParam(value = "evID") int evID,@PathParam(value = "resimID") int resimId) throws IOException, ClassNotFoundException {
         
        SqliteDB dbResim=new SqliteDB();
        Response RR=dbResim.SelectHomePicture(evID,resimId);

     return RR;
    }
    
    @GET
    @Path("/tamliste")
    @Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public TopluDonus ButunEvListele() throws ClassNotFoundException {
        
        JsonTip [] ret=new JsonTip[2];   
        SqliteDB db=new SqliteDB();
         
        ret=db.SelectHome();
         
        TopluDonus tekli=new TopluDonus();
        tekli.setJsonList(ret);
        return tekli;
    }
    
 
    @GET
    @Path("/DBCREATE")
    @Produces("text/plain")
    @Consumes("text/plain")
    public String DBKur() throws ClassNotFoundException{
        
        SqliteDB db=new SqliteDB();
        db.DBCreate();
    return "DB SIFIRLANDI";
    }

    private CacheControl getCacheControl(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private Image resize(BufferedImage bufferedImage, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
       
        
}

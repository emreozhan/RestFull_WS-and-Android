package com.yazlab.ws.rest.vo.service;


import com.yazlab.ws.rest.vo.DB.SqliteDB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.yazlab.ws.rest.vo.JsonTip;
import com.yazlab.ws.rest.vo.TopluDonus;
import com.yazlab.ws.rest.vo.forImg;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/yazlab")
public class ServisDenemLogin {
   
    
    @GET
    @Path("/img/{imageId}")
    @Produces("image/*")
    public  Response getImage(@PathParam(value = "imageId") String imageId) throws IOException {
 
         
    File sourceimage = new File("e:\\1j.jpg");
    BufferedImage image = ImageIO.read(sourceimage);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(image, "png", baos);
    byte[] imageData = baos.toByteArray();

    // uncomment line below to send non-streamed
    //return Response.ok(imageData).build();
    // uncomment line below to send streamed
     return Response.ok(new ByteArrayInputStream(imageData)).build();
    }
    

   
    
    
    @GET
    @Path("/tamliste")
    ///RestJR/restJR/Yazlab/m/dummy
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Image resize(BufferedImage bufferedImage, int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    /* çalýþýyor
    @GET
    @Path("/m/{msj}")
   // @Consumes("text/plain")
    //@Produces("text/plain")
    @Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public String ButunEvListele(@PathParam("msj") String userName) {
        
        return "mwsajiniz"+ userName ;
    }
	*/
 /**
        @POST
        @Path("/emre")
	@Consumes({javax.ws.rs.core.MediaType.APPLICATION_JSON})
	@Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
	public Deneme emre(Deneme vo){
	
		vo.setDogru(false);
               if(vo.getAd().equals("a") && vo.getSoyad().equals("e")){
                    vo.setDogru(true);
                }
                vo.setAd("emreozhan set ad");
		return vo;
                
	}
        
 */       
        
}

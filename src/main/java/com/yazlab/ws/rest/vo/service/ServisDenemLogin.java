package com.yazlab.ws.rest.vo.service;


import com.yazlab.ws.rest.vo.DB.SqliteDB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.yazlab.ws.rest.vo.JsonTip;
import java.io.File;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/yazlab")
public class ServisDenemLogin {
    
    
    @GET
    @Path("/tamliste")
    ///RestJR/restJR/Yazlab/m/dummy
    @Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public JsonTip [] ButunEvListele() throws ClassNotFoundException {
        
         JsonTip [] ret=new JsonTip[2];   
         SqliteDB db=new SqliteDB();
         
         ret=db.SelectHome();
         
                 
        return ret;
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

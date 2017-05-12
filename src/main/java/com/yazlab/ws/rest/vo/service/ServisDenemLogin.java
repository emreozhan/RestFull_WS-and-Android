package com.yazlab.ws.rest.vo.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.yazlab.ws.rest.vo.Deneme;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/Yazlab")
public class ServisDenemLogin {
    
    
    @GET
    @Path("/m/{msj}")
    @Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public Deneme getUser(@PathParam("msj") String userName) {
            
            Deneme ee=new Deneme();
            ee.setAd("fb");
            ee.setSoyad("soGS");
            ee.setDogru(true);
        
        return ee ;
    }
    
    
    
    
    
    
    
    
    
    /* çalýþýyor
    @GET
    @Path("/m/{msj}")
   // @Consumes("text/plain")
    //@Produces("text/plain")
    @Produces({javax.ws.rs.core.MediaType.APPLICATION_JSON})
    public String getUser(@PathParam("msj") String userName) {
        
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

package com.betha.projeto_manutencao.api.rs;


import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import com.betha.projeto_manutencao.model.ApiResponse;
import com.betha.projeto_manutencao.model.dto.ClienteResponseDTO;
import com.betha.projeto_manutencao.service.ClienteService;



@Path("/cliente")
public class ClienteRestService {

	private static final Logger logger = Logger.getLogger(ClienteRestService.class);
	
	@Inject
	private ClienteService service;

	@GET
	@Path("/")
	@PermitAll
    @Produces(MediaType.APPLICATION_JSON)
	public Response getAll(@QueryParam("termo") final String termo) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start getAll");
		}
		
		Response.Status status = Response.Status.OK;
		Response response = Response.status(status)
				.entity(new ApiResponse<>(status.getStatusCode(), status.toString(), service.getClientes(termo)))
				.type(MediaType.APPLICATION_JSON)
				.build();

        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End getAll");
        }
        return response;	
	}
	
	@POST
	@Path("/")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
	public Response create(final ClienteResponseDTO dto) throws Exception {
        Response.Status status = Response.Status.OK;
        return Response.status(status)
        		.entity(new ApiResponse<>(status.getStatusCode(), status.toString(), service.create(dto)))
        		.type(MediaType.APPLICATION_JSON)
        		.build();
	}

	/*
	@POST
	@Path("/")
    @Produces(MediaType.TEXT_PLAIN)
	public String postSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {

		if (logger.isDebugEnabled()) {
			logger.debug("Start postSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from RESTEasy Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End postSomething");
        }
        return response;	
	}

	@PUT
	@Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
	public String putSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		if (logger.isDebugEnabled()) {
			logger.debug("Start putSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}

		String response = null;

        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

	                response = "Response from RESTEasy Restful Webservice : " + request;
                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	response = e.getMessage().toString();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("result: '"+response+"'");
            logger.debug("End putSomething");
        }
        return response;	
	}

	@DELETE
	@Path("/{id}")
	public void deleteSomething(@FormParam("request") String request ,  @DefaultValue("1") @FormParam("version") int version) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Start deleteSomething");
			logger.debug("data: '" + request + "'");
			logger.debug("version: '" + version + "'");
		}


        try{			
            switch(version){
	            case 1:
	                if(logger.isDebugEnabled()) logger.debug("in version 1");

                    break;
                default: throw new Exception("Unsupported version: " + version);
            }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        
        if(logger.isDebugEnabled()){
            logger.debug("End deleteSomething");
        }
	}
	*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sdist
 */
@Path("converter")
public class RestConverter {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestConverter
     */
    public RestConverter() {
    }

    /**
     * Retrieves representation of an instance of rest.RestConverter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml(@QueryParam("origin")int origin, @QueryParam("target")int target,@QueryParam("amount")double amount ) {
        //TODO return proper representation object
        return ""+convertCurrency(origin,target, amount);
        
    }

    /**
     * PUT method for updating or creating an instance of RestConverter
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }

    private static double convertCurrency(int originCurrency, int targetCurrency, double amount) {
        ConverterService.CurrencyConvertor_Service service = new ConverterService.CurrencyConvertor_Service();
        ConverterService.CurrencyConvertor port = service.getCurrencyConvertorPort();
        return port.convertCurrency(originCurrency, targetCurrency, amount);
    }
}

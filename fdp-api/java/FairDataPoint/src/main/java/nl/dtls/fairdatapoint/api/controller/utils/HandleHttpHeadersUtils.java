/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.api.controller.utils;

import javax.servlet.http.HttpServletResponse;
import nl.dtls.fairdatapoint.utils.MediaType;
import org.apache.http.HttpHeaders;
import org.openrdf.rio.RDFFormat;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Rajaram Kaliyaperumal
 * @since 2016-01-07
 * @version 0.1
 */
public class HandleHttpHeadersUtils {
    
    public static String setErrorResponseHeader(HttpServletResponse 
            response, Exception ex) {
        String errorMessage = ("Internal server error; Error message : " 
                + ex.getMessage());              
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);                
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN);
        return errorMessage;
    }
    
    public static void setSuccessResponseHeader(String responseBody, 
            HttpServletResponse response, String contentType) {   
        if (responseBody == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN);            
        }
        else {
            response.setStatus(HttpServletResponse.SC_OK);                
            response.setHeader(HttpHeaders.CONTENT_TYPE, contentType); 
        }
    }
    
    public static String setNotAcceptedResponseHeader(HttpServletResponse 
            response, String contentType) {               
        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);                            
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN);        
        return("Currently don't supported '" + contentType + "' content type");
    }
    
    public static void setMandatoryResponseHeader(HttpServletResponse 
            response) { 
        response.setHeader(HttpHeaders.SERVER, "FAIR data point server");             
        response.setHeader(HttpHeaders.ALLOW, RequestMethod.GET.name());
    }
    
    public static RDFFormat requestedContentType(String contentType) {        
        RDFFormat requesetedContentType = null;    
        if (contentType.contentEquals(MediaType.TEXT_TURTLE) ||                         
                contentType == null ||         
                contentType.contains(MediaType.WILDCARD)) {
            requesetedContentType = RDFFormat.TURTLE;
        }
        else if (contentType.contentEquals( MediaType.APPLICATION_JSONLD)) {
            requesetedContentType = RDFFormat.JSONLD;
        }
        return requesetedContentType;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.dtls.fairdatapoint.utils;

import com.google.common.collect.ImmutableList;
import static com.google.common.io.Files.readLines;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.openrdf.rio.RDFFormat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Contains references to the example metadata rdf files which are used in the 
 * Junit tests. This class also has static methods to pre populate inmemory 
 * triple store  
 * 
 * @author Rajaram Kaliyaperumal
 * @since 2015-12-01
 * @version 0.1
 */
public class ExampleTurtleFiles {
    
    public static final String FDP_METADATA = "dtl-fdp.ttl";
    public static final ImmutableList<String>  CATALOG_METADATA = 
        ImmutableList.of("textmining-catalog.ttl");
    public static final ImmutableList<String> DATASET_METADATA = 
            ImmutableList.of("disgenet.ttl", "gda-lumc.ttl");
    public static final ImmutableList<String> DATASET_DISTRIBUTIONS = 
        ImmutableList.of("disgenet-html-page.ttl" , 
                "disgenet-nanopubs-gzip.ttl", "disgenet-textfile-gzip.ttl", 
                "gda-lumc-textfile.ttl", "gda-lumc-sparql.ttl");
    public static final String EXAMPLE_CATALOG_ID = "textmining";
    public static final String EXAMPLE_DATASET_ID = 
            "gene-disease-association_lumc";
    public static final String EXAMPLE_DISTRIBUTION_ID = "textfile";
    public static final RDFFormat FILES_RDF_FORMAT = RDFFormat.TURTLE;
    private final static Logger LOGGER = LogManager.getLogger(
            ExampleTurtleFiles.class.getName());
    public final static String BASE_URI = "http://semlab1.liacs.nl:8080/";
    public final static String FDP_URI = "http://semlab1.liacs.nl:8080/fdp";
    
    public static String getTurtleAsString(String fileName)  {        
        String content = "";        
        URL fileURL = ExampleTurtleFiles.class.getResource(fileName);
        try {
            File npFile;
            npFile = new File(fileURL.toURI());
            StringBuilder buf = new StringBuilder();
            for (String fileLine : readLines(npFile, StandardCharsets.UTF_8)) {
                buf.append(fileLine);
                buf.append("\n");
            }
            content = buf.toString();
        } catch (IOException | URISyntaxException ex) {
            LOGGER.error("Error getting turle file",ex);   
        
        }        
        return content;
    }
    
    public static File getTurtleAsFile(String fileName)  {        
        File npFile = null;       
        URL fileURL = ExampleTurtleFiles.class.getResource(fileName);
        try {
            
            npFile = new File(fileURL.toURI());        
        } catch (URISyntaxException ex) {
            LOGGER.error("Error getting turle file",ex);        
        }        
        return npFile;
    }
    
    
    
}

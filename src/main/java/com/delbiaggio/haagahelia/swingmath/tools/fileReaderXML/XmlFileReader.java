/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delbiaggio.haagahelia.swingmath.tools.fileReaderXML;

import com.delbiaggio.haagahelia.swingmath.domaine.Configuration;
import java.io.File;
import java.io.InputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author delbiaggionicolas
 */
public class XmlFileReader {

    private static String FILEXML = "conf.xml";
    private static XmlFileReader current = null;
    
    private XmlFileReader() {
    }
    
    public static XmlFileReader getCurrent(){
        if (current == null) {
            current = new XmlFileReader();
        }
        return current;
    }
            
    public Configuration unmarshallConfFromResouces(String file) {
        Configuration conf = null;
        try {
            InputStream xml = this.getClass().getClassLoader().getResourceAsStream(FILEXML);
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();            
            conf = (Configuration) jaxbUnmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        } 
        return conf;
    }
    
    public Configuration unmarshallConfFromResouces(){
        return unmarshallConfFromResouces(FILEXML);
    }
    
    public Configuration unmarshallConf(String file){
         Configuration conf = null;
        try {
            File f = new File(file);
            JAXBContext jaxbContext = JAXBContext.newInstance(Configuration.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();            
            conf = (Configuration) jaxbUnmarshaller.unmarshal(f);
        } catch (JAXBException e) {
            e.printStackTrace();
        } 
        return conf;       
    }
    
    public Configuration unmarshallConf(){
        return unmarshallConf(FILEXML);
    }

    public void marshallConf(Configuration conf){                
        JAXBContext jaxbContext = null;
        Marshaller jaxbMarshaller = null;
        try {            
//          SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);             
//          Schema schema = sf.newSchema(new File("conf_validation.xsd"));            
            jaxbContext = JAXBContext.newInstance(Configuration.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(conf, new File(FILEXML));
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
    
    public Configuration getInitialConf(){
        File f = new File(FILEXML);
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getPath());
        Configuration conf;
        if (f.exists()) {
            conf = unmarshallConf();
        }else{
            conf = unmarshallConfFromResouces();
            marshallConf(conf);
        }
        return conf;
    }    
}

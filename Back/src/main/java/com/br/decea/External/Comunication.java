package com.br.decea.External;

import lombok.Getter;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author wilson
 */
@Getter
public class Comunication {
    
    private Long zip_code;
    
    final String address = "https://cdn.apicep.com/file/apicep/";
    
    public Comunication(Long zip_code){
        this.zip_code = zip_code;
    }
    
    public Location getData(){
       RestTemplate restTemplate = new RestTemplate(); 
       String url = address+handleZipCode(Long.toString(this.zip_code))+".json";
       Location location = restTemplate.getForObject(url,Location.class);
       return location;
    }
    
    private String handleZipCode(String zipCode){
        StringBuilder stringBuilder = new StringBuilder(zipCode);
        stringBuilder.insert(5, "-");
        return stringBuilder.toString();
    }
}

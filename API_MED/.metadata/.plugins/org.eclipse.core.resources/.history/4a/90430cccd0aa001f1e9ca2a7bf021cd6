package com.medicals.api;




import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.medicals.api.threads.Handler.MyFilesHandler;
@SpringBootApplication
public class MedicalsApplication {

    public static void main(String[] args) {

        SpringApplication.run(MedicalsApplication.class, args);


        Thread additionalThread = new Thread(() -> {
        	
            MyFilesHandler handler = new MyFilesHandler();
            while(true) {
        	    try {
         	       handler.updateDatabaseFromJson(handler.convertXlsxToJson(handler.downloadFile("https://data.gov.ma/data/fr/dataset/2cdfd9f4-289d-4e9a-8998-50bd03f8e874/resource/094733f5-5434-4163-b837-df0e7b665127/download/ref-des-medicaments-cnops-2014.xlsx", 
 				        "/home/creep/Desktop/API_MED/medicals/src/main/java/com/medicals/api/threads/Handler/xlsx/ref-des-medicaments-cnops-2014.xlsx"),
 		                "/home/creep/Desktop/API_MED/medicals/src/main/java/com/medicals/api/threads/Handler/json/ref-des-medicaments-cnops-2014.json"),
         			    "Pharmacy",
         			    "medicine");
         	      Thread.sleep(1000 * 604800);
 		        } catch (IOException e) {
 			        // TODO Auto-generated catch block
 			        e.printStackTrace();
 		        } catch (InvalidFormatException e) {
 			        // TODO Auto-generated catch block
 			        e.printStackTrace();
 		        } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	    
            }
  
        });

        // Start the additional thread
        additionalThread.start();
    }
}

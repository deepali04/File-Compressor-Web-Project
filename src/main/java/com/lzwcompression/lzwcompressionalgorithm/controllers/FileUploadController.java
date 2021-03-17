package com.lzwcompression.lzwcompressionalgorithm.controllers;
import com.lzwcompression.lzwcompressionalgorithm.helper.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
       try {
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must containe file");
            }
            boolean status = fileUploadHelper.uploadFile(file);
            //if (status){   
                // System.out.println(status);            
                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/").path(file.getOriginalFilename().substring(0,file.getOriginalFilename().indexOf(".")) + ".lzw").toUriString());

                //inputFileName.substring(0,inputFileName.indexOf(".")) + ".lzw"
                //ServletUriComponentsBuilder.fromCurrentContextPath() localhost:8080
                // then /files the /filename
                //then this url will be sent
            //} 

    }catch (Exception e) { 
        e.printStackTrace();
    }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong"); 
    }    
}

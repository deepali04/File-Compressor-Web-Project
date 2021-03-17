package com.lzwcompression.lzwcompressionalgorithm.helper;

import java.io.IOException;
//import java.io.FileOutputStream;
//import java.io.InputStream;
import java.nio.file.Files;
//import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import com.lzwcompression.lzwcompressionalgorithm.compression.Compression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {

    @Autowired
    private Compression compression;

    public final String UPLOAD_DIR= new ClassPathResource("/static/files").getFile().getAbsolutePath();

    public FileUploadHelper() throws IOException{

    }

    public boolean uploadFile(MultipartFile multipartFile){
        boolean status= false;
        try{
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR +"\\"+ multipartFile.getOriginalFilename() ),StandardCopyOption.REPLACE_EXISTING);
            System.out.println(UPLOAD_DIR +"\\"+ multipartFile.getOriginalFilename());
            String outputfilename=compression.compressFile(UPLOAD_DIR +"\\"+ multipartFile.getOriginalFilename());
            System.out.println(outputfilename);
            Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR +"\\"+ outputfilename), StandardCopyOption.REPLACE_EXISTING);
            status =true;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return status;
   }
    
}

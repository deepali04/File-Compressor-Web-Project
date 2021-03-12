package com.lzwcompression.lzwcompressionalgorithm.compression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Compression {

    public String compressFile(String inputFileName) {
	
	//MAX_TABLE_SIZE is based on the input bit length
        double MAX_TABLE_SIZE = Math.pow(2, 16); 
	//current size of the hashtable
        double tableSize = 256; 						
                
        Map<String,Integer> map = new HashMap<>(); //Data structure
        for (int i = 0; i < 256; i++) {
            map.put(Character.toString((char) i), i);//Initialize code for individual characters
        }

        String outputFileName = inputFileName.substring(0,inputFileName.indexOf(".")) + ".lzw";
		String text = null; 
		String line = null;
		BufferedReader bufferedReader = null;
		BufferedWriter output = null; 
		
		try{
			bufferedReader = new BufferedReader(new FileReader(inputFileName));
			//Encoded data stored in 16 Bit format
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName),"UTF-16BE"));
			//Read the input file and store in a string
            		while((line = bufferedReader.readLine()) != null) {
            			text = line;
            		}      

            		String encodeString = ""; // Initialize empty string
			//Condition to check for empty input file
            		if(text!=null){
				for(char c: text.toCharArray()) {  
					String symbol = "" + c;
					if (map.containsKey(encodeString + symbol))
						encodeString = encodeString + symbol;
					else{
						//Write to file the encoded string
						output.write(map.get(encodeString));
						//check if table not full
						if(tableSize < MAX_TABLE_SIZE)	
							//add code to the table and increase tableSize
							map.put(encodeString + symbol, (int) tableSize++);  
						encodeString = "" + symbol;
					}
				}
				//Write to file the encoded string
            			output.write(map.get(encodeString));				
        		}
			
            	bufferedReader.close();   //Close the reader
	        output.flush();		  //Flush the buffer
	        output.close();		  //Close the writer

	        }catch(FileNotFoundException ex) {
	        	System.out.println("Unable to open file '" + inputFileName + "'");                
	    	}catch(IOException ex) {
	            	System.out.println("Error reading file '" + inputFileName + "'");                  
		}catch(InvalidPathException ex){
			System.out.println("bypass");
			return outputFileName;
		}

        return outputFileName;
    }
		
    
}

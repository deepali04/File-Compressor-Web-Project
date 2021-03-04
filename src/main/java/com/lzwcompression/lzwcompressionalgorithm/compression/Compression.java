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

        double MAX_TABLE_SIZE = Math.pow(2, 16);   		//MAX_TABLE_SIZE is based on the input bit length
        double tableSize = 256; 						//current size of the hashtable
                
        Map<String,Integer> map = new HashMap<>(); 				//Data structure
        for (int i = 0; i < 256; i++) {
            map.put(Character.toString((char) i), i); 		//Initialize code for individual characters
        }

        String outputFileName = inputFileName.substring(0,inputFileName.indexOf(".")) + ".lzw";
		String text = null; 
		String line = null;
		BufferedReader bufferedReader = null;
		BufferedWriter output = null; 
		
		try {
            
			bufferedReader = new BufferedReader(new FileReader(inputFileName));
			output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName),"UTF-16BE"));  //Encoded data stored in 16 Bit format
			

			//Read the input file and store in a string
            while((line = bufferedReader.readLine()) != null) {
            	text = line;
            }      

            String encodeString = ""; 							// Initialize empty string
            if(text!=null){										//Condition to check for empty input file
				for(char c: text.toCharArray()) {  
					String symbol = "" + c;
					if (map.containsKey(encodeString + symbol))
						encodeString = encodeString + symbol;
					else {
						output.write(map.get(encodeString));		//Write to file the encoded string
						if(tableSize < MAX_TABLE_SIZE)				//check if table not full
							map.put(encodeString + symbol, (int) tableSize++);  //add code to the table and increase tableSize
						encodeString = "" + symbol;
					}
				}
            	output.write(map.get(encodeString));				//Write to file the encoded string
        	}
            bufferedReader.close();    //Close the reader
	        output.flush();				//Flush the buffer
	        output.close();				//Close the writer

	        }
	    catch(FileNotFoundException ex) {
	        System.out.println("Unable to open file '" + inputFileName + "'");                
	    }
	    
		catch(IOException ex) {
	            System.out.println("Error reading file '" + inputFileName + "'");                  
	    }

		catch(InvalidPathException ex){
			System.out.println("bypass");
			return outputFileName;

		}

        return outputFileName;
    }
		
    
}

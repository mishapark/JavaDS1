package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextFileIO {
	private static File myFile = new File("covid.txt");
	
	public static void writeData(String entry) throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(myFile, true))){
			writer.println(entry);
		} 
	}
	
	public static String ReadFile(){
		String fileContent = "";
	
		try{
			FileReader filereader = new FileReader(myFile);
        	Scanner scan = new Scanner(myFile);
            while(scan.hasNextLine()){
            	fileContent = fileContent.concat(scan.nextLine() + "\n");
            }
            scan.close();
        }catch(Exception e){
            e.printStackTrace();
        }
	  return fileContent;
    }
}

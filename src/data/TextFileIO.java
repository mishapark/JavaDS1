package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileIO {
	private static File myFile = new File("covid.txt");
	
	public static void writeData(String entry) throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(myFile, true))){
			writer.println(entry);
		} 
	}
	
	public static Object[] readStudents() throws IOException {
		Scanner scanner = new Scanner(myFile);
		ArrayList<String> names = new ArrayList<String>();

		while(scanner.hasNext()) {
			String name = scanner.nextLine();
			names.add(name);
		}
		scanner.close();
		return names.toArray();
	}
}

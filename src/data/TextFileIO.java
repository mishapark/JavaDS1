package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileIO {
	//the file we're working with
	private static File myFile = new File("covid.txt");

	//writing data into the file
	public static void writeData(String entry) throws IOException {
		try (PrintWriter writer = new PrintWriter(new FileWriter(myFile, true))){
			writer.println(entry);
		} 
	}

	//Get all the cases from the file into the array
    public static Object[] findAll() throws IOException{
		Scanner scan = new Scanner(myFile);
		ArrayList<String> cases = new ArrayList<String>();
		while (scan.hasNext()) {
			String covidCase = scan.nextLine();
			cases.add(covidCase);
		}
		scan.close();
		return cases.toArray();
	}

	//Put all the cases from the specific city to the array
	public static Object[] findCity(String city) throws IOException {
		Scanner scan = new Scanner(myFile);
		ArrayList<String> cases = new ArrayList<String>();
		while (scan.hasNext()) {
			String rec = scan.nextLine();
			String[] fields = rec.split(",");
			if (fields[1].equalsIgnoreCase(city)) {
				cases.add(rec);
			}
		}
		scan.close();
		return cases.toArray();
	}

	//Put all the cases from the specific date to the array
	public static Object[] findDate(String date) throws  IOException {
		Scanner scan = new Scanner(myFile);
		ArrayList<String> cases = new ArrayList<String>();
		while (scan.hasNext()) {
			String rec = scan.nextLine();
			String[] fields = rec.split(",");
			if (fields[0].equalsIgnoreCase(date)) {
				cases.add(rec);
			}
		}
		scan.close();
		return cases.toArray();
	}

	//Put all the cases from the specific date and city to the array
	public static Object[] findDateAndCity(String line) throws IOException {
		Scanner scan = new Scanner(myFile);
		ArrayList<String> cases = new ArrayList<String>();
		Object[] lines = line.split(",");
		while (scan.hasNext()) {
			String rec = scan.nextLine();
			String[] fields = rec.split(",");
			if (fields[0].equalsIgnoreCase(lines[0].toString()) && fields[1].equalsIgnoreCase((lines[1].toString()))) {
				cases.add(rec);
			}
		}
		scan.close();
		return cases.toArray();
	}
}

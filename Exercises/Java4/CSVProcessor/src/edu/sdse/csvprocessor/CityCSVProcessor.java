package edu.sdse.csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CityCSVProcessor {
	Map<String, List<CityRecord>> log;
	List<CityRecord> records;
	
	private void cityInfo(String name, List<CityRecord> records) {
		int sYear = 40000, bYear = 0, averageP = 0, count = 0;
		for (CityRecord cr : records) {
			if(cr.year < sYear) sYear = cr.year;
			if(cr.year > bYear) bYear = cr.year;
			averageP += cr.population;
			count++;
		}
		averageP = averageP/count;
		System.out.printf("Average population of %s (%d-%d; %d entries): %d\n", name, sYear, bYear, count, averageP);
	}
	
	public void readAndProcess(File file) {
		//Try with resource statement (as of Java 8)
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			//Discard header row
			br.readLine();
			
			String line;
			records = new ArrayList<CityRecord>();
			log = new HashMap<String, List<CityRecord>>();
			while ((line = br.readLine()) != null) {
				// Parse each line
				String[] rawValues = line.split(",");
				
				int id = convertToInt(rawValues[0]);
				int year = convertToInt(rawValues[1]);
				String city = convertToString(rawValues[2]);
				int population = convertToInt(rawValues[3]);
				CityRecord cr = new CityRecord(id, year, city, population); 
				records.add(cr);
				if(log.get(city) == null) log.put(city, new ArrayList<CityRecord>());
				log.get(city).add(cr);
				System.out.println(cr);
				//TODO: Extend the program to process entries!
			}
			for(Entry<String, List<CityRecord>> entry : log.entrySet()) {
				cityInfo(entry.getKey(), entry.getValue());
			}
			records = null;
			log = null;
			
		} catch (Exception e) {
			System.err.println("An error occurred:");
			e.printStackTrace();
		}
	}
	
	private String cleanRawValue(String rawValue) {
		return rawValue.trim();
	}
	
	private int convertToInt(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		return Integer.parseInt(rawValue);
	}
	
	private String convertToString(String rawValue) {
		rawValue = cleanRawValue(rawValue);
		
		if (rawValue.startsWith("\"") && rawValue.endsWith("\"")) {
			return rawValue.substring(1, rawValue.length() - 1);
		}
		
		return rawValue;
	}
	
	public static final void main(String[] args) {
		CityCSVProcessor reader = new CityCSVProcessor();
		
		File dataDirectory = new File("data/");
		File csvFile = new File(dataDirectory, "Cities.csv");
		
		reader.readAndProcess(csvFile);
	}
}

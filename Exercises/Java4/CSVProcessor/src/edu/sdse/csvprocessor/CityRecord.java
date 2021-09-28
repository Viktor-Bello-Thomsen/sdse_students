package edu.sdse.csvprocessor;

public class CityRecord {
	
	int id, year, population;
	String city;
		
	CityRecord(int id, int year, String city, int population){
		this.id = id;
		this.year = year;
		this.city = city;
		this.population = population;
	}
	
	public String toString() {
		return "id: " + id + ", year: " + year + ", city: " + city + ", population: " + population;
		
		
	}
}

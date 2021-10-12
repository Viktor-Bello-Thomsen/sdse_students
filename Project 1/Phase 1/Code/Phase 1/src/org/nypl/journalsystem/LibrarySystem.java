package org.nypl.journalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import edu.sdse.csvprocessor.CityRecord;

public class LibrarySystem {
	
	Map<String, Journal> journals;
	Map<Integer, Author> authors;
	List<Article> articles;
	
	public LibrarySystem() {
		//TODO: Initialize system with default journals.
		journals = new HashMap<String, Journal>();
		journals.put("0018-1560",new Journal("Higher Education", "Springer", "Germany", "0018-1560"));
		journals.put("0346-2511", new Journal("System", "Elsevier", "Netherlands", "0346-2511"));
		journals.put("2451-9294",new Journal("Chem", "Elsevier", "Netherlands", "2451-9294"));
		journals.put("1476-4687",new Journal("Nature", "Nature Research", "Great Britain", "1476-4687"));
		journals.put("0147-2011",new Journal("Society", "Springer", "Germnay", "0147-2011"));
	}
	
	public void load() throws FileNotFoundException, IOException {
		loadAuthors();
		loadArticles();
	}
	
	protected void loadAuthors() throws FileNotFoundException, IOException {
		authors = new HashMap<Integer, Author>();
		File file = new File("data/Authors.csv");
		FileReader in = new FileReader(file);
		//TODO: Load authors from file
		Iterable<CSVRecord> records = CSVFormat.TDF.withFirstRecordAsHeader().withDelimiter(',').parse(in);
		for (CSVRecord record : records) {
		    int id = Integer.parseInt(record.get("ID"));
		    String name = record.get("Name");
			if(authors.get(id) == null)authors.put(id, new Author(id,name));
		}
	}
	
	protected void loadArticles() throws FileNotFoundException, IOException {
		File file = new File("data/Articles.csv");
		articles = new ArrayList<Article>();
		//TODO: Load articles from file and assign them to appropriate journal
		FileReader in = new FileReader(file);
		//TODO: Load authors from file
		Iterable<CSVRecord> records = CSVFormat.TDF.withFirstRecordAsHeader().withDelimiter(',').parse(in);
		for (CSVRecord record : records) {
		    int id = Integer.parseInt(record.get("ID"));
		    String name = record.get("Title");
		    String authorids[] = record.get("AuthorIDs").substring(1, record.get("AuthorIDs").length()-1).split("; ");
		    String ISSN = record.get("ISSN");
		    Article article = new Article(id, name, ISSN);
		    for(String authorID : authorids) {
		    	if(authors.get(Integer.parseInt(authorID)) != null) article.putAuthors(authors.get(Integer.parseInt(authorID)));
		    }
		    articles.add(article);
		    if(journals.get(ISSN) != null) journals.get(ISSN).putArticles(article);
		}
	}
	
	
	public void listContents() {
		//TODO: Print all journals with their respective articles and authors to the console.
		for(Entry<String, Journal> entry : journals.entrySet()) {
			System.out.println(entry.getValue());
		}
	}
	
	public static final void main(String[] args) throws Exception {
		LibrarySystem librarySystem = new LibrarySystem();
		librarySystem.load();
		librarySystem.listContents();
	}
}

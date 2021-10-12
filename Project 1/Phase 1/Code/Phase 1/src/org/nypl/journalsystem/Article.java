package org.nypl.journalsystem;

import java.util.ArrayList;
import java.util.List;

public class Article {
	int id;
	String title, ISSN;
	List<Author> authors;
	Article(int id, String title, String ISSN){
		authors = new ArrayList<Author>();
		this.id = id;
		this.title = title;
		this.ISSN = ISSN;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void putAuthors(Author author) {
		this.authors.add(author);
	}
	public String toString() {
		String output =  "ID: " + id + " title: " + title + " ISSN: " + ISSN + "\nMade by following authors: \n\t";
		for(Author author: authors) {
			output += author.toString();
			output += "\t";
		}
		return output + "\n";
	}
}

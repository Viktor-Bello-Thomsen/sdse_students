package org.nypl.journalsystem;

import java.util.ArrayList;
import java.util.List;

public class Journal {
	String name, publisher, location, ISSN;
	List<Article> articles;
	Journal(String name,String publisher,String location, String ISSN){
		articles = new ArrayList<Article>();
		this.name = name;
		this.publisher= publisher;
		this.location = location;
		this.ISSN = ISSN;
	}
	public List<Article> getArticles() {
		return articles;
	}
	public void putArticles(Article article) {
		this.articles.add(article);
	}
	public String toString() {
		String output = "Name: " + name + " publicer: " + publisher + " ISSN: " + ISSN + "\n" + "Contains following articles:\n\t";
		for(Article article: articles) {
			output += article.toString();
			output += "\t";
		}
		return output + "\n";
	}
}

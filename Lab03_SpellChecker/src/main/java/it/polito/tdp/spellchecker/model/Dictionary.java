package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dictionary {
	private List<String> english;
	private List<String> italian;

	public Dictionary() {
		this.english = new ArrayList<>();
		this.italian = new ArrayList<>();
	}

	public void loadDictionary(String language) {
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					english.add(word.toLowerCase());
				}
				br.close();
				} catch (IOException e){
					throw new RuntimeException("Errore nella lettura del file",e);				
				}			
		}
		else {
			try {
				FileReader fr = new FileReader("src/main/resources/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					italian.add(word.toLowerCase());
				}
				br.close();
				} catch (IOException e){
					throw new RuntimeException("Errore nella lettura del file",e);				
				}			
		}		
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList,String language){
		List<RichWord> risultato= new ArrayList<>();
		if(language.equals("English")) {
			for(String input:inputTextList) {
				if(!this.english.contains(input)) {
						RichWord parola=new RichWord(input);
						parola.setCorrect(false);
						risultato.add(parola);	
					}					
				}				
			}
		else {
			for(String input:inputTextList) {
				if(!italian.contains(input)) {
					RichWord parola=new RichWord(input);
					parola.setCorrect(false);
					risultato.add(parola);
				}			
			}	
		}
		return risultato;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList, String language){
		List<RichWord> risultato= new ArrayList<>();
		Collections.sort(this.english);
		Collections.sort(this.italian);
		if(language.equals("English")) {
			for(String input: inputTextList) {
				int index = Collections.binarySearch(this.english, input);		        
		        if(index < 0) {
		        	RichWord parola=new RichWord(input);
					parola.setCorrect(false);
					risultato.add(parola);		        	
		        }	               
			}		
		}
		else {
			for(String input: inputTextList) {
				int index = Collections.binarySearch(this.italian, input);		        
			    if(index < 0) {
			    	RichWord parola=new RichWord(input);
					parola.setCorrect(false);
					risultato.add(parola);		        	
			        }	               
			}		
		}		
		return risultato;
		
	}

	public List<String> getEnglish() {
		return english;
	}

	public List<String> getItalian() {
		return italian;
	}
	
	
}

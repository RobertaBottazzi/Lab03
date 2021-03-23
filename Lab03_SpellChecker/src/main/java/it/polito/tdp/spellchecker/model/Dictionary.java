package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
				FileReader fr = new FileReader("English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					english.add(word);
				}
				br.close();
				} catch (IOException e){
					throw new RuntimeException("Errore nella lettura del file",e);				
				}			
		}
		else {
			try {
				FileReader fr = new FileReader("Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					italian.add(word);
				}
				br.close();
				} catch (IOException e){
					throw new RuntimeException("Errore nella lettura del file",e);				
				}			
		}		
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		List<RichWord> risultato= new ArrayList<>();
		for(String input:inputTextList) {
			if(english.contains(input) || italian.contains(input)) 
				break;
			else {
				RichWord parola=new RichWord(input);
				parola.setCorrect(false);
				risultato.add(parola);				
			}
		}
		return risultato;
		
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		return null;
		
	}

	public List<String> getEnglish() {
		return english;
	}

	public List<String> getItalian() {
		return italian;
	}
	
	
}

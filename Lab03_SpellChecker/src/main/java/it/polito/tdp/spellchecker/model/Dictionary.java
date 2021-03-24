package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	private List<String> dizionario;

	public Dictionary() {
		this.dizionario = new ArrayList<>();
	}

	public void loadDictionary(String language) {
		if(language.equals("English")) {
			try {
				FileReader fr = new FileReader("src/main/resources/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {
					this.dizionario.add(word.toLowerCase());
				}
				br.close();
				fr.close();
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
					this.dizionario.add(word.toLowerCase());
				}
				br.close();
				fr.close();
				} catch (IOException e){
					throw new RuntimeException("Errore nella lettura del file",e);				
				}			
		}		
	}
	
	public List<RichWord> spellCheckTextLinear(List<String> inputTextList){
		List<RichWord> risultato= new ArrayList<>();
		for(String input:inputTextList) {
				if(!this.dizionario.contains(input)) {
					RichWord parola=new RichWord(input);
					parola.setCorrect(false);
					risultato.add(parola);		
				}
		}
		return risultato;				
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList){
		List<RichWord> risultato= new ArrayList<>();
		Collections.sort(this.dizionario);
		for(String input: inputTextList) {
			int index = Collections.binarySearch(this.dizionario, input);
			if(index < 0) {
				RichWord parola=new RichWord(input);
				parola.setCorrect(false);
				risultato.add(parola);	
		    }
		}				
		return risultato;
		
	}

	public List<String> getDizionario() {
		return dizionario;
	}

	
	
	
}

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Dictionary dictionary;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxLanguage;

    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtErrors;

    @FXML
    private Label numberErrors;

    @FXML
    private Button btnClearText;

    @FXML
    private Label txtTime;

    @FXML
    void doClearText(ActionEvent event) {
    	this.txtInput.clear();
    	this.txtErrors.clear();
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	//sistemo input
    	List<String> words= new ArrayList<>(); 
    	String parole = this.txtInput.getText();
    	parole = parole.toLowerCase().replaceAll("[^a-zA-Z]+"," ");
    	String[] arrayParole=parole.split(" ");
    	for(String s: arrayParole) 
    		words.add(s);
    	//carico il dizionario giusto
    	dictionary.loadDictionary(boxLanguage.getValue());
    	//controllo parole sbagliate
    	List<RichWord> wrongWords= dictionary.spellCheckTextLinear(words,boxLanguage.getValue());
    	this.txtErrors.setText(wrongWords.toString());
    	this.numberErrors.setText("The text contains "+wrongWords.size()+" errors");
    	System.out.println("SIZE: "+dictionary.getEnglish().size());
    }

	public void setModel(Dictionary model) {
    	this.dictionary=model;   
    	boxLanguage.getItems().addAll("English","Italian");
    }
    @FXML
    void initialize() {
        assert boxLanguage != null : "fx:id=\"BoxLanguage\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert numberErrors != null : "fx:id=\"numberErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}




import java.util.ArrayList;
import java.util.Iterator;
import java.io.*;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import CYL.XMLReader;

/**
 * This class implements a glossary with functionality to support the
 * search for terms. If a term is not in the glossary alternatives
 * (similar terms) can be suggested.
 * 
 * @author Pablo Romero
 * @version 2004.08.28
 */
public class Glossary
{
    // Number of similar words to be retrieved from the lexicon
    private static final int N_ALTERNATIVES = 5;
    private ArrayList<Term> termsList;
    private Lexicon lexicon;

 	/**
	 * Create a new Glossary
     **/
  	public Glossary()
	  {
        termsList = new ArrayList<Term>();
        lexicon = new Lexicon();
    }

    /**
     * This method adds a new term to the termsList field
     * @param label  the new term to be added
     * @param definition  the terms definition
     * @return true if the term didn't exist and could be added,
     *         false otherwise
     */
    protected boolean addTerm (String label, String definition) 
    {
        boolean added = false;
        if (!termExists(label)) {
            termsList.add(new Term(label, definition));
            addTermToLexicon(label);
            added = true;
        }

        return added;
    }
    /*
     * This method adds a group of term stored in an XML file to 
     * the termList field.
     * @param filePath the path of XML file
     * @return true if the file or term didn't exist and could be added,
     * 		   false otherwise
     */
    protected boolean addTermFromXML(String filePath){
    	boolean added = false;
    	String lable;
    	String definition;
    	int lableTag = 0;
    	int definitionTag = 1;
    	
    	XMLReader xmlReader = new XMLReader(filePath);
    	
    	if(xmlReader!=null){
    		NodeList nodeList = xmlReader.read();

    		for(int i=0;i<nodeList.getLength();i++){
        		Node currentNode = nodeList.item(i);
        		NodeList currentNodeList = currentNode.getChildNodes();
        		
        		lable = currentNodeList.item(lableTag).getTextContent();
        		definition = currentNodeList.item(definitionTag).getTextContent();       		
        		added = addTerm(lable, definition);
           		//System.out.println(i+1+". "+lable+":"+added);        			 
        	}
    	}    	
    	return added;
    }

    /**
     * This method returns true if the parameter exists in the list of
     * terms, false otherwise.
     * @param label phrase to look for in the termsList field
     * @return true if the term already exists, false otherwise
     */
    public boolean termExists (String label) 
    {
        boolean found = false;
        Iterator<Term> i = termsList.iterator();
        while (!found && i.hasNext()) {
            if (label.equals(i.next().getLabel()))
                found = true;
        }
        return found;
    }

    /**
     * This method returns the definition of a term
     * @param label  word to look for in the termsList field
     * @return the string containing the term's definition
     */
    public String getTermDefinition (String label) 
    {
        boolean found = false;
        String definition = "";
        Iterator<Term> i = termsList.iterator();
        while (!found && i.hasNext()) {
            Term nextTerm = i.next();
            if (label.equalsIgnoreCase(nextTerm.getLabel()))
               {
                found = true;
                definition = nextTerm.getDefinition();
               }
        }
        return definition;
    }
    
    public ArrayList<Term> getTermList() {
    	return termsList;
    }

    /**
     * This method adds the words from a term to the lexicon field
     * @param label  the label of the term to add
     */
    private void addTermToLexicon (String label)
    {
        String[] labelWords = label.split(" ");

        for (String word : labelWords)
            if (!lexicon.wordExists(word)) {
               lexicon.addWord(word);
            }
        }
    
    

    /*
     * This method checks the inputed ArrayList exists in the ArrayLust "termList" or not
     * @param labels the ArrayList which contains all labels you want to check in termList.
     * @return a returned ArrayList which contains all terms matched with ArrayList "labels"  
     */
    private ArrayList<String> labelsExist(ArrayList<String> labels) {
    	Iterator<String> label = labels.iterator();
    	ArrayList<String> returnTerms = new ArrayList<String>();
    	
    	
    	while(label.hasNext()){
    		String currentLabel = label.next();
			
    		for(Term tempExistTerm : termsList) {
    			if(tempExistTerm.getLabel().contains(currentLabel) && !returnTerms.contains(tempExistTerm.getLabel())) {
    				returnTerms.add(tempExistTerm.getLabel());
    			}
    		}
    	}
    	   	
    	return returnTerms;
    }
    
    /**
     * This method retrieves phrases from the lexicon which are similar
     * to the one specified as parameter
     * @param label the label of the term taken as a base in the  
     *              search for alternative phrases
     * @return an ArrayList containing the alternative phrases
     */
    private ArrayList<String> getSimilarPhrases (String label) 
    {
       	ArrayList<String> alternativePhrases = new ArrayList<String>();
    	ArrayList<String> alternativeWords = new ArrayList<String>();
    	String[] labelWords = label.split(" ");
    		
    	/*
    	 * To split inputed string into separate words to match extension C
    	 */
    	if (labelWords.length < 2) {    		
    		alternativeWords = lexicon.getSimilarWords(label, 5);
    		return labelsExist(alternativeWords);
    	}
    	else {    		
        	// form a list of alternative prhases
        	for(int i=0;i<labelWords.length;i++) {
        		if (lexicon.wordExists(labelWords[i])) {
        			alternativeWords.add(labelWords[i]);
        		}
        		else {
        			ArrayList<String> similarWords = lexicon.getSimilarWords(labelWords[i], 5);
        			
        			for (String similarWord : similarWords) {
        				//System.out.println(similarWord);
        				alternativePhrases.add(similarWord);
        			}
        		}        	 	
        	}
        	
    		// form a similar phrasses by calling includeWords
        	ArrayList<String> includeedWords = includeWords(alternativeWords, alternativePhrases);        	
        	return labelsExist(includeedWords);
    	}    	
    }

    /**
     * This method includes words at the end of phrases. Each element of a
     * list of words is included at the end of a phrase, generating as many
     * phrases as there are words in the list of words. This process is 
     * repeated for all the phrases in the list of phrases.
     * @param words  a list of words to be included into the phrases
     * @param phrases  a list of phrases to be modified by the inclusion of
     *                 new words
     * @return an ArrayList containing the modified phrases
     */
    private ArrayList<String> includeWords (ArrayList<String> words, ArrayList<String> phrases) 
    {
        ArrayList<String> modifiedPhrases = new ArrayList<String>();

        for (String phrase : phrases)
        {
            for (String word: words) 
            {
                modifiedPhrases.add(phrase+word+" ");
            }
        }
        
        return modifiedPhrases;
    }


    /**
     * This method retrieves terms from termList which are similar
     * to the one specified as parameter
     * @param label the label of the term taken as a base in the  
     *              search for alternative phrases
     * @return an ArrayList containing the alternative terms
     */
    public ArrayList<String> getSimilarTerms (String label) 
    {
        ArrayList<String> similarTerms = new ArrayList<String>();
        ArrayList<String> similarPhrases = getSimilarPhrases(label);

        for (String similarPhrase : similarPhrases)
        {
            similarPhrase = similarPhrase.trim();
            if (termExists(similarPhrase))
                similarTerms.add(similarPhrase);
        }
        return similarTerms;
    }

}

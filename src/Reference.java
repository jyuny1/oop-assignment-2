import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public abstract class Reference {
	protected static final String MOVIE_GLOSSARY = "Movie Glossary";
	protected static final String TV_SERIES_GLOSSARY = "TV Series Glossary";
	protected static int numberOfGlossary = 2;
	protected int glossarySelected=2;

	//There are several glossaries
	protected HashMap<String,Glossary> glossaries;

	/**
	 * Includes all the glossaries in the reference
	 */
	private void includeAllGlossaries() {
		glossaries.put(MOVIE_GLOSSARY, new MovieGlossary());
		glossaries.put(TV_SERIES_GLOSSARY, new TVSeriesGlossary());
	}

	/**
	 * This method returns true if the parameter exists in the glossaries, false
	 * otherwise.
	 *  
	 * @param label to look for in the glossaries
	 */
	protected boolean termExists(String label) {
		boolean found = false;
		Iterator<Glossary> glossariesIterator = glossaries.values().iterator();
		while (!found && glossariesIterator.hasNext()) {
			if (glossariesIterator.next().termExists(label))
				found = true;
		}
		return found;
	}

	/*
	 * This method handles the action if the inputed label does not exist.
	 * 
	 * @param label a string name which you are looking for definition.
	 * @param glossarySelected 1: all 2. first glossary 2. second glossary
	 * @return an ArrayList<String> which contains the search result
	 */
	protected ArrayList<String> getSimilarTerms(String label, int glossarySelected) {
		ArrayList<String> suggestedTerms = new ArrayList<String>();

		if (glossarySelected >= 0 && glossarySelected <= numberOfGlossary) {
			if (glossarySelected == 0) {
				Iterator<String> keyIterator = glossaries.keySet().iterator();

				while (keyIterator.hasNext()) {
					String glossaryKey = keyIterator.next();
					suggestedTerms.addAll(getSimilarDefinitions(label, glossaryKey));
				}
			} else if (glossarySelected == 1) {
				suggestedTerms = getSimilarDefinitions(label, MOVIE_GLOSSARY);

			} else if (glossarySelected == 2) {
				suggestedTerms = getSimilarDefinitions(label, TV_SERIES_GLOSSARY);
			}
		}

		return suggestedTerms;
	}

	private ArrayList<String> getSimilarDefinitions(String lable, String glossary){	    	 
	    Glossary currentGlossary = glossaries.get(glossary);
	    return currentGlossary.getSimilarPhrases(lable); 
	}
	
	/**
     * This method returns the definition of a term. If the term exists
     * in several glossaries all the definitions are returned
     * @param label  word to look for in the termsList field
     */
    protected String getTermDefinitions (String label, int glossarySelected) 
    {
        String definition = new String();
        
        if(glossarySelected >=0 && glossarySelected <= numberOfGlossary){
        	if(glossarySelected == 0){
        		Iterator<String> keysIterator = glossaries.keySet().iterator();
                while (keysIterator.hasNext()) {
                    String glossaryKey = keysIterator.next();
                    Glossary glossary = glossaries.get(glossaryKey);
                    if (glossary.termExists(label)) {
                        definition += glossaryKey+"\n"+glossary.getTermDefinition(label)+"\n";
                    }            	
                }		
        	}
        	else if(glossarySelected ==1){
        		definition += MOVIE_GLOSSARY+"\n"+getTermDefinition(label, MOVIE_GLOSSARY);
        	}
        	else if(glossarySelected == 2){
        		definition += TV_SERIES_GLOSSARY+"\n"+getTermDefinition(label, TV_SERIES_GLOSSARY);
        	}
        }    
        return definition;
    }
    
    /**
     * this method shows all definition stored in the golssaries
     */
    protected String showTermList(){
    	Iterator<String> keyIterator = glossaries.keySet().iterator();
    	String terms = new String();
    	int i=1;
    	while(keyIterator.hasNext()){
            String glossaryKey = keyIterator.next();
            Glossary glossary = glossaries.get(glossaryKey);
            ArrayList<Term> termList = glossary.getTermList();
            
            Iterator<Term> iterator = termList.iterator();
            for(;iterator.hasNext();i++){
            	Term currentTerm = iterator.next();
            	terms += i+". "+currentTerm.getLabel()+" - "+glossaryKey+"\n"+currentTerm.getDefinition()+"\n\n";
            }
    	}
    	return terms;  
    }
    
    protected ArrayList<String> getWordList(){
    	return getWordList();
    }
    
    private String getTermDefinition(String label, String glossary){
    	Glossary currentgGlossary = glossaries.get(glossary);
    	return currentgGlossary.getTermDefinition(label);
    }

	public static void setNumberOfGlossary(int numberOfGlossary) {
		Reference.numberOfGlossary = numberOfGlossary;
	}

	public static int getNumberOfGlossary() {
		return numberOfGlossary;
	}
	
	public Reference(){
		glossaries = new HashMap<String,Glossary>();
	    includeAllGlossaries();
	}
	
	abstract protected void setInputGlossary();
	abstract public void interact();
}

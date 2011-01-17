import  java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


/**
 * This class implements a reference book or a group of
 * reference resources such as glossaries, dictionaries
 * thesauri, etc.
 * 
 * @author Pablo Romero
 * @version 2009.06.04
 */

public class Reference
{
    private static final String JAVA_GLOSSARY = "Java Glossary";
    private static final String CPP_GLOSSARY = "c++ Glossary";
    
    // There are several glossaries
    private HashMap<String,Glossary> glossaries;
    
    /**
     * Constructor for objects of class Reference
     */
    public Reference()
    {
        glossaries = new HashMap<String,Glossary>();
        includeAllGlossaries();
    }

    /**
     * Includes all the glossaries in the reference
     */
    private void includeAllGlossaries() 
    {
        glossaries.put(JAVA_GLOSSARY, new JavaGlossary());
        glossaries.put(CPP_GLOSSARY, new CppGlossary());
    }

    /**
     * This method returns true if the parameter exists in the
     * glossaries, false otherwise.
     * @param label  label to look for in the glossaries
     */
    private boolean termExists(String label) 
    {
        boolean found = false;
        Iterator<Glossary> glossariesIterator = glossaries.values().iterator();
        while (!found && glossariesIterator.hasNext()) {
            if (glossariesIterator.next().termExists(label))
                found = true;
        }
        return found;
    }

    /**
     * This method returns the definition of a term. If the term exists
     * in several glossaries all the definitions are returned
     * @param label  word to look for in the termsList field
     */
    private String getTermDefinition (String label) 
    {
        String definition = "";
        Iterator<String> keysIterator = glossaries.keySet().iterator();
        while (keysIterator.hasNext()) {
            String glossaryKey = keysIterator.next();
            Glossary glossary = glossaries.get(glossaryKey);
            if (glossary.termExists(label)) {
                definition += glossaryKey+"\n"+glossary.getTermDefinition(label)+"\n";
            }
        }        
        return definition;
    }
	
    /**
     * This method interacts with the user. It asks the user for terms
     * to search. This interaction finishes when the user types the special
     * term "finish"
     */
     public void interact() 
     {
		 System.out.print("Enter term: ");
         String term = EasyIn.getString();

         while (!term.equals("finish")) 
         {
             if (termExists(term))
                 System.out.println("term exists");
             else
                 System.out.println("term doesn't exist");

             System.out.print("Enter new term: ");
             term = EasyIn.getString();
         }
	}
}
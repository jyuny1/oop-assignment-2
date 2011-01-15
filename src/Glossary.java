import  java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements a glossary with functionality to support the
 * search for terms. If a term is not in the glossary alternatives
 * (similar terms) can be suggested.
 * 
 * @author Pablo Romero, modified by Liu, Chin-Yi
 * @version 2010.11.08
 */
public class Glossary
{

  private ArrayList<Term> termsList;
  private Lexicon lexicon;

 	/**
	 * Create a new Glossary
   **/
  	public Glossary() 
	  {
  		String fileName = "term.txt";
        termsList = new ArrayList<Term>();
        lexicon = new Lexicon();
        addTerms(fileName);
        //addTerms();
    }

    /**
     * This method adds terms to the termsList field. It is a simple 
     * solution. The sophisticated version should add terms from a text
     * file
     */
    private void addTerms () 
    {
    	/*
    	 * the constructor of addTerms. This can be modified for advance points.
    	 */
        addTerm("abstract class","A class that contains one or more abstract methods, and therefore can never be instantiated. Abstract classes are defined so that other classes can extend them and make them concrete by implementing the abstract methods");
        addTerm("abstract method","A method that has no implementation");
        addTerm("Abstract Window Toolkit (AWT)","A collection of graphical user interface (GUI) components that were implemented using native-platform versions of the components. These components provide that subset of functionality which is common to all native platforms. Largely supplanted by the Project Swing component set. See also Swing");
        addTerm("arithmetic exception","A division by 0 has been perfomed. If it occurred in a paint method, likely this occurred as a result of creating a Font with zero height");
        addTerm("array index out of bounds exception","You have used an array index outside the allowable range. There may be several array references x[i] in the same line. Don't leap to conclusions about which one caused the trouble. Arrays are indexed from 0 to x.length-1, not 1 to x.length");
        addTerm("boolean","Refers to an expression or variable that can have only a true or false value. The Java programming language provides the boolean type and the literal values true and false");
        addTerm("casting","Explicit conversion from one data type to another");
        addTerm("class","In the Java programming language, a type that defines the implementation of a particular kind of object. A class definition defines instance and class variables and methods, as well as specifying the interfaces the class implements and the immediate superclass of the class. If the superclass is not explicitly specified, the superclass will implicitly be Object");
        addTerm("class not found exception","This occurs only at run time. Usually it means a class that was present during compilation has disappeared, or the classpath has changed so it is no longer accessible. It could also happen if you dynamically load a class and the class is not on the classpath");
        addTerm("classpath","An environmental variable which tells the Java virtual machine1 and Java technology-based applications where to find the class libraries, including user-defined class libraries");
        addTerm("compiler","A program to translate source code into code to be executed by a computer. The Java compiler translates source code written in the Java programming language into bytecode for the Java virtual machine1. See also interpreter");
        addTerm("constructor","A pseudo-method that creates an object. In the Java programming language, constructors are instance methods with the same name as their class. Constructors are invoked using the new keyword");
        addTerm("deprecation","Refers to a class, interface, constructor, method or field that is no longer recommended, and may cease to exist in a future version");
        addTerm("encapsulation","The localization of knowledge within a module. Because objects encapsulate data and implementation, the user of an object can view the object as a black box that provides services. Instance variables and methods can be added, deleted, or changed, but as long as the services provided by the object remain the same, code that uses the object can continue to use it without being rewritten. See also instance variable,  instance method");
        addTerm("exception","An event during program execution that prevents the program from continuing normally; generally, an error. The Java programming language supports exceptions with the try, catch, and throw keywords");
        addTerm("extends","Class X extends class Y to add functionality, either by adding fields or methods to class Y, or by overriding methods of class Y. An interface extends another interface by adding methods. Class X is said to be a subclass of class Y");
        addTerm("field","A data member of a class. Unless specified otherwise, a field is not static");
        addTerm("inheritance","The concept of classes automatically containing the variables and methods defined in their supertypes");
        addTerm("instance","An object of a particular class. In programs written in the Java programming language, an instance of a class is created using the new operator followed by the class name");
        addTerm("local variable","A data item known within a block, but inaccessible to code outside the block. For example, any variable defined within a method is a local variable and can't be used outside the method");
        addTerm("method","A function defined in a class. See also instance method, class method. Unless specified otherwise, a method is not static");
        addTerm("object-oriented design","A software design method that models the characteristics of abstract or real objects using classes and objects");
        addTerm("overriding","Providing a different implementation of a method in a subclass of the class that originally defined the method");
        addTerm("out of bounds exception","see array index out of bounds exception");
        addTerm("primitive type","A variable data type in which the variable's value is of the appropriate size and format for its type: a number, a character, or a boolean value");
        addTerm("public","A Java keyword used in a method or variable declaration. It signifies that the method or variable can be accessed by elements residing in other classes");
        addTerm("scope","A characteristic of an identifier that determines where the identifier can be used. Most identifiers in the Java programming environment have either class or local scope. Instance and class variables and methods have class scope; they can be used outside the class and its subclasses only by prefixing them with an instance of the class or (for class variables and methods) with the class name. All other variables are declared within methods and have local scope; they can be used only within the enclosing block");
        addTerm("subclass","A class that is derived from a particular class, perhaps with one or more classes in between. See also superclass");
        addTerm("superclass","A class from which a particular class is derived, perhaps with one or more classes in between. See also subclass");
        addTerm("super","A Java keyword used to access members of a class inherited by the class in which it appears");
    }
    
    /**
     * This method adds a new term to the termsList field
     * @param term  the new term to be added
     * @param definition  the terms definition
     * @return true if the term didn't exist and could be added,
     *         false otherwise
     */
    private boolean addTerm (String label, String definition) 
    {
        boolean added = false;
        if (!labelExists(label)) {
            termsList.add(new Term(label, definition));
            addTermToLexicon(label);
            added = true;
        }

        return added;
    }
    
    /*
     * This method is an overload of addTerms, which reads the file from specific location by user.
     * This part of code is for extension A
     * @param fileName a file name user indicates
     */
    private void addTerms(String fileName) {
    	TermReader termReader = new TermReader(fileName);
    	ArrayList<Term> terms = termReader.getTerms();
    	
    	for (Term tempTerm : terms) {
    		addTerm(tempTerm.getLabel(), tempTerm.getDefinition());
    	}
	}    
    
    /*
     * This method check the input label (String) existed in the ArrayList "termList" or not
     * @param label the label you are looking for in termList.
     * @return true if the inputed string name (label) is matched in termList
     */
    private boolean labelExists (String label) 
    {
        boolean found = false;
        Iterator<Term> i = termsList.iterator();
        while (!found && i.hasNext()) {
        	Term currentTerm = i.next();
        	
            if (label.equals(currentTerm.getLabel())){
            	System.out.println(currentTerm.getDefinition());
            	found = true;
            	break;
            }
        }
        return found;
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
    	
    /*
     * This method check the input label (String) existed in the ArrayList "termList" or not
     * @param label the label you are looking for in termList.
     * @return a returned Term if the inputed string name (label) is matched in termList
     */
    private Term termExist(String label) {
    	Term returnTerm=null;
    	
    	for(Term tempTerm : termsList) {
    		if(label.equals(tempTerm.getLabel())) {
    			returnTerm = tempTerm;
    			break;
    		}
    	}
    	
    	return returnTerm;
    }
        
    /*
     * This method handles the action if the inputed label does not exist.
     * @param label a string name which you are looking for definition.
     */
    private void labelNotExists (String label) {
    	ArrayList<String> guessLabels = getSimilarPhrasses(label);
    	Iterator<String> guessLabel = guessLabels.iterator();
       		
       	if (guessLabel.hasNext()) {
       		System.out.println("what did you mean?");
           	// printing the index to match Extension D.2
       		for(int i=1;guessLabel.hasNext();i++) {
            		System.out.println(i+". "+guessLabel.next());
           	}    		
    
           	continueInteract(guessLabels);
       	}
       	else {
       		System.out.println("the term \""+label+"\" you input cannot be recognised by progromme, please be more specific");
       	}    		   	
    }

    /**
     * This method adds the words from a term to the lexicon field
     * @param label  the added term
     */
    private void addTermToLexicon (String label)
    {
    	// do something here
        String[] labelWords = label.split(" ");
        for (String word : labelWords)
        	lexicon.addWord(word);
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
     * 
     * @modified by Liu, Chun-Yi
     * The origianl code of this function is not complete. 
     * To be able to give a good suggestion (or a good guess as well). The full combination is given in code.
     */
    private ArrayList<String> includeWords (ArrayList<String> words, ArrayList<String> phrases) {		
        if (phrases.isEmpty()) {
        	return words;
        }
        else if (words.isEmpty()) {
			return phrases;
		}
        else if (phrases.isEmpty() && words.isEmpty()) {
			return null;
		}
        else {      	
        	ArrayList<String> modifiedPhrases = new ArrayList<String>();
        	String tmpString = new String();
        	
            for (String phrase : phrases){
                for (String word: words) {
                	for (Term tempTerm : termsList) {
                		// in order to provide a good suggestion to user, four combinations are given below.
                		if (tempTerm.getLabel().equals(phrase+" "+word)) {                			
                			tmpString = phrase+ " " + word;
                			modifiedPhrases.add(tmpString);
                			//break;			
                		}
                		else if (tempTerm.getLabel().equals(word+" "+phrase)) {
                			tmpString = word+" "+phrase;
                			modifiedPhrases.add(tmpString);
                			//break;
                		}
                		else if (tempTerm.getLabel().equals(phrase+word)) {
							tmpString = phrase+word;
							modifiedPhrases.add(tmpString);	
                		}
                		else if (tempTerm.getLabel().equals(word+phrase)) {
							tmpString = word+phrase;
							modifiedPhrases.add(tmpString);	
						}
                	}
                }
            }			
            
            return modifiedPhrases;
        }
    }

    /*
     * This method will try to get the similar phrasses if the inputed label is not matched to termList.
     * @param label a string name that user is looking for the definition
     * @return an ArrayList which contains the phrasses provided by programme. 
     */
	public ArrayList<String> getSimilarPhrasses (String label){
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
	
	/*
     * This method is waiting for the action of pressing button in order to make the output clearer
     */
    private void wait_input() {
    	System.out.print(">>>>> Press enter to continue <<<<<");
    	try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * This method interacts with the user. It asks the user for terms
     * to search. This interaction finishes when the user types the special
     * term "finish"
     */
     public void interact() 
     {
    	 String label = new String();
         
         while (true) 
         {
             System.out.print("Enter new term: ");
             label = EasyIn.getString();

             /*
              * add the function to match add, remove and modify terms. (extension D.3)
              */
             if (label.toLowerCase().equals("addterm")) {
            	 System.out.print("Please add a term name: ");
            	 String termName = EasyIn.getString();
            	 System.out.println("Please add a defination for "+termName+":");
            	 String termDefination = EasyIn.getString();
            	 termsList.add(new Term(termName, termDefination));
            	 label = "";
             }
             else if (label.toLowerCase().equals("delterm")) {
            	 System.out.print("Please enter the term to delete: ");
            	 String termName = EasyIn.getString();
            	 Term term = termExist(termName);
            	 
            	 if(term!=null) {
            		 System.out.println("the term \""+term.getLabel()+"\" deleted");
            		 termsList.remove(term);
            	 }
            	 else {
            		 System.out.println("the term \""+termName+"\" not exists");
            	 }
            	 
            	 label = "";
             }
             else if (label.toLowerCase().equals("modterm")){
            	 System.out.print("Please enter the term to modify: ");
            	 String termName = EasyIn.getString();
            	 Term term = termExist(termName);
            	 
            	 if(term!=null){
            		 int termIndex = termsList.indexOf(term);
            		 System.out.println("The definition of "+term.getLabel()+" is: ");
            		 System.out.println(term.getDefinition());
            		 
            		 System.out.println("Enter the name you want to modify(press enter to skip):");
            		 String newTermName = EasyIn.getString();
            		 System.out.println("Enter the definition you want to modify (pressenter to skip):");
            		 String newTermDefinination = EasyIn.getString();
            		            		 
            		 Term newTerm = new Term(newTermName, newTermDefinination);
            		 termsList.set(termIndex, newTerm);
            		 label = "";
            	 }
            	 else {
            		 System.out.println("the term \""+termName+"\" not exists");
            	 }
             }
             else if (labelExists(label)) {
            	 System.out.println("term you input exists");                 
             }
             else if (label.toLowerCase().equals("finish")) {
            	 break;
             }
            else {
            	labelNotExists(label);
             }
          }            	 
       }

     /*
      * This method takes over the actions after the function "interaction"
      * @param gussesLabels an ArrayList which contains the suggestion generated by programme. 
      */
     private void continueInteract(ArrayList<String> gussesLabels) {
     	System.out.print("Please input the number which programme has gussesed: ");
     	int selected = EasyIn.getInt()-1;
     	String label = gussesLabels.get(selected);
     	Term existTerm = termExist(label);
     
     	if(existTerm!=null){
     		System.out.println(existTerm.getDefinition());
     	}
     	    	
     	wait_input();
     }
}

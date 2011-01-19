import java.util.ArrayList;
import java.util.Iterator;


public class ReferenceTXT extends Reference{
	public ReferenceTXT(){
		super();
	}
		   
	/**
     * This method interacts with the user. It asks the user for terms
     * to search. This interaction finishes when the user types the special
     * term "finish"
     */
    public void interact() {
		int option;
		
    	while(true){
	   		 System.out.print("Welecometo the programming refereice! by Liu, Chun-Yi\n");
	   		 System.out.println("\n1) Glossary settings\n2) Search for terms\n3) Finish");
	   		 System.out.print("Enter option: ");
	   		 option = EasyIn.getInt();
	   		 int i=0;
	   		 
	   		 if(option == 1){
	   			//show glossary	   	   			 		
	   			Iterator<String> keyIterator = glossaries.keySet().iterator();
   	   			
   	   			for(i=1;keyIterator.hasNext();i++){
   	   				String glossaryKey = keyIterator.next();
   	   				System.out.println(i+") "+glossaryKey);
   	   			}
   	   			System.out.println(i+") ALL");
   	   			System.out.print("Enter option: ");
   	   			int glossary = EasyIn.getInt();
   	   			
   	   			setInputGlossary(glossary);
	   		 }
	   		 else if (option == 2) {
	   			 String lable = new String();
				while(!lable.equalsIgnoreCase("goback")){
	   	   			System.out.println("\nGlossary is set to: "+getGlossaryName(glossarySelected));
					System.out.print("Enter term: ");
					lable = EasyIn.getString().toLowerCase();	
					
					if(lable.equalsIgnoreCase("all")){
						System.out.println(showTermList());
					}
					else{
						if(termExists(lable)){
							System.out.println(getTermDefinitions(lable, glossarySelected));
						}
						else{
							ArrayList<String>similarPhrases = getSimilarTerms(lable, glossarySelected);
							if(similarPhrases.size()>0){
								int count = 0;
								System.out.println("Which one of these do you mean?"+"("+similarPhrases.size()+")");
								for(String similarPhrase:similarPhrases){
									similarPhrase = similarPhrase.trim();
									System.out.println(count+1+". "+similarPhrase);
									count++;
								}
								System.out.print("Please press a number: ");
								int select = EasyIn.getInt();
									
								if(select <= similarPhrases.size() && select > 0){
									System.out.println(getTermDefinitions(similarPhrases.get(select-1),glossarySelected));
								}
								else{
									System.out.println(select+": out of index");
								}
							}
							else{
								System.out.println(lable+": nothing related, try another one?");
							}
						}						
					}
				}
			}
	   		else if (option == 3) {
	   			return;
			}
		}
	}    
	
	private String getGlossaryName(int glossary){
		String glossaryName = null;
		if(glossary == 0){
			//movie glossary
			glossaryName = MOVIE_GLOSSARY;
		}
		else if(glossary == 1){
			glossaryName = TV_SERIES_GLOSSARY;
		}
		else if (glossary == 2) {
			glossaryName = "ALL";
		}
		return glossaryName;
	}

	protected void setInputGlossary(int glossary) {
		if(glossary == 1){
			//movie glossary
			glossarySelected = 0;
		}
		else if(glossary == 2){
			//tv series glossary
			glossarySelected = 1;
		}
		else if(glossary == 3){
			//all
			glossarySelected = 2;
		}
		else{
			System.out.println("only "+numberOfGlossary+" can be selected");
			return;
		}
		System.out.println("Glossary has been changed to "+getGlossaryName(glossarySelected));
	}
	@Override
	protected void setInputGlossary() {
		// TODO Auto-generated method stub
		
	}
}

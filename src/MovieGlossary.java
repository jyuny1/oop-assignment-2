/**
 * This class implements a Movie glossary, read from an XML file, with functionality to 
 * support the search for terms. It represents a simple solution
 * to handle a number of different glossaries, it could be eliminated
 * if we use files to store the terms of the glossaries.
 * 
 * @author Liu, Chun-Yi
 * @version 20.01.2011
 */
public class MovieGlossary extends Glossary {
	public MovieGlossary(){
		String filePath = "movieGlossary.xml";
		addTermFromXML(filePath);
	}
}

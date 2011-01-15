import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * This class implements a TermReader with functionality to support read terms in a file. 
 * 
 * @author Liu, Chun-Yi
 * @version 2010.11.01
 */

public class TermReader {
	private int fileSize;
	private ArrayList<Term> terms = new ArrayList<Term>();

	/*
	 * To screate a new TermReader
	 */
	public TermReader (String fileName) {
		File fileSource = new File(fileName);
		fileSize = (int)fileSource.length();
		
		/*
		 * try to read a file from a specific location that user indicated.
		 */
		try {
			// read terms into buffer in order to speed up the programme.
			FileReader fileReader = new FileReader(fileSource);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			
			do {
				String buffer = bufferReader.readLine();
				String data[] = buffer.split("\",\"");
				
				// reform the format from file to ArrayList.
				Term term = new Term(data[0].replace("\"", ""), data[1].replace("\"", ""));
				terms.add(term);
				
			} while (bufferReader.ready());
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// the function to get the ArrayList
	public ArrayList<Term> getTerms() {
		return terms;
	}
	
	// the function to get the size of file currently read.
	public int getFileSize() {
		return fileSize;
	}
}

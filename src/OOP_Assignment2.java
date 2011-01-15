/*
 * The main function for assignment 1.
 * @author Liu, Chun-Yi
 * @version 2010.11.01
 */
public class OOP_Assignment2 {
	private Glossary glossary;
	
	/*
	 * Constructor of OOP_Assignment1
	 */
	public OOP_Assignment2() {
		glossary = new Glossary();
		glossary.interact();
	}
	
	public static void main(String[] args) {
		OOP_Assignment2 assignment1 = new OOP_Assignment2();	
	}
}

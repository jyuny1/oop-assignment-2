import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 * This class implements a reference book or a group of
 * reference resources such as glossaries, dictionaries
 * thesauri, etc. It provides a graphical user interface
 * 
 * @author Pablo Romero
 * @version 2009.06.04
 */
public class ReferenceGUI extends Reference {
    private static final String VERSION = "Version 1.0";

    // GUI fields
    private JFrame mainWindow = new JFrame("Reference");
    private JPanel definitionPane;
    private JPanel resultsPane;
    private JTextField searchField;
    
    //private int glossarySelected;
    
    /**
     * Constructor for objects of class ReferenceGUI
     */
    public ReferenceGUI(){
    	super();
    }

    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(mainWindow, 
                    "Virtual reference\n" + VERSION,
                    "About Virtual Reference", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    /**
     * Search for a specific term
     * @throws BadLocationException 
     * 
     */
    private void termSearch(){
        // First remove all previous content
        definitionPane.removeAll();
        resultsPane.removeAll();
        
        String lable = searchField.getText();
        if(lable.equalsIgnoreCase("all")){
        	displayTermList(showTermList());
        }
        else{
            if(termExists(lable)){
            	displayDefinition(lable);
            }
            else{
            	displayList(getSimilarTerms(lable, glossarySelected));
            }        	
        }
    }
 
    /**
     * Display a list of terms 
     * 
     */
    private void displayList(ArrayList<String> arrayList){
        // Grouping the results in a button group means the user can 
        // only select one at a time (which is what we want)
        ButtonGroup termsGroup = new ButtonGroup();
        
        for (String similarTerm : arrayList) {
            JRadioButton termButton = new JRadioButton(similarTerm);
            // We need to include the terms' label as the action command
            // otherwise it cannot be passed to the inner class (only Final 
            // vars can pass to the inner class)
            termButton.setActionCommand(similarTerm);
            termsGroup.add(termButton);
            termButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) { displayDefinition(e.getActionCommand()); }
                           });
            resultsPane.add(termButton);
        }
 

        mainWindow.setVisible(true);
    }
    
    private void displayTermList(String terms){
    	definitionPane.removeAll();
    	
       	JTextPane textPane = new JTextPane();
    	StyledDocument doc = (StyledDocument) textPane.getDocument();
    	AttributeSet style = doc.addStyle("StyleName", null);
    	
       	try {
			doc.insertString(doc.getLength(), "\n"+terms, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	    
        JScrollPane scrollPane = new JScrollPane(textPane); 
        definitionPane.add(scrollPane);
        mainWindow.setVisible(true);    
    }
    
    /**
     * Display the definition of the term
     * @param termLabel the term's label
     * @throws BadLocationException 
     * 
     */
    private void displayDefinition(String termLabel)
    {
        // Remove the previous definition
        definitionPane.removeAll();

        String definition = getTermDefinitions(termLabel, glossarySelected);
        
    	JTextPane textPane = new JTextPane();
    	StyledDocument doc = (StyledDocument) textPane.getDocument();

    	AttributeSet style = doc.addStyle("StyleName", null);

    	textPane.setCaretPosition(doc.getLength());
    	textPane.insertIcon(new ImageIcon("movieCovers/"+termLabel+".jpg"));
    	    
    	try {
			doc.insertString(doc.getLength(), "\n"+definition, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	    	    
        JScrollPane scrollPane = new JScrollPane(textPane); 
        definitionPane.add(scrollPane);
        mainWindow.setVisible(true);    	    
    }
    
    /**
     * Create the main frame's menu bar.
     */
    private void makeMenuBar()
    {
        final int SHORTCUT_MASK =
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();

        JMenuBar menubar = new JMenuBar();
        mainWindow.setJMenuBar(menubar);
        
        JMenu menu;
        JMenuItem item;
        
        // create the File menu
        menu = new JMenu("File");
        menubar.add(menu);
        
        item = new JMenuItem("Quit");
            item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { quit(); }
                           });
        menu.add(item);

        // put a spacer into the menubar, so the next menu appears to the right
        menubar.add(Box.createHorizontalGlue());

        // create the Help menu
        menu = new JMenu("Help");
        menubar.add(menu);
        
        item = new JMenuItem("About Virtual Reference ...");
            item.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) { showAbout(); }
                           });
        menu.add(item);
    }

    private void setInputGlossary(String input){
    	if(input.equals(MOVIE_GLOSSARY)){
    		glossarySelected = 1;
    	}
    	else if(input.equals(TV_SERIES_GLOSSARY)){
    		glossarySelected = 2;
    	}
    	else{
    		glossarySelected = 0;
    	}
    }
    /**
     * Create the main frame's products bar.
     * @return the created products bar
     */
    private JPanel getReferencesBar()
    {
        JPanel referencesBar = new JPanel();

        referencesBar.setLayout(new BoxLayout(referencesBar, BoxLayout.LINE_AXIS));
  
        JButton button = new JButton("All");
        referencesBar.add(button);
        button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setInputGlossary(((JButton) e.getSource()).getText());
			}
		});
        
        button = new JButton("Movie Glossary");
        referencesBar.add(button);
        button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setInputGlossary(((JButton) e.getSource()).getText());
			}
		});
        
        button = new JButton("TV Series Glossary");
        referencesBar.add(button);
        
        button.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setInputGlossary(((JButton) e.getSource()).getText());
			}
		});
        
       // put a spacer into the menubar, so the next menu appears to the right
        referencesBar.add(Box.createHorizontalStrut(60));
        referencesBar.add(Box.createHorizontalGlue());

        searchField = new JTextField("",15);
        searchField.addActionListener(new ActionListener() {
                               public void actionPerformed(ActionEvent e) {termSearch();}
                               });
        referencesBar.add(searchField);

        return referencesBar;
    }

    /**
     * Interacts with the user
     */
    public void interact()
    {
        
        // makes sure the application exits when the user closes its window
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Container contentPane = mainWindow.getContentPane();
        
        makeMenuBar();
        
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(8, 8));

        // Create the products bar with the buttons
       JPanel referencesBar = getReferencesBar();
       contentPane.add(referencesBar, BorderLayout.NORTH);
       
       // Create the results pane (where similar terms are listed)
       resultsPane = new JPanel();
       resultsPane.setLayout(new GridLayout(0, 1));
       contentPane.add(resultsPane, BorderLayout.WEST);
       
       // Create the center pane (where the definition is displayed)
       definitionPane = new JPanel();
       definitionPane.setLayout(new BoxLayout(definitionPane,BoxLayout.Y_AXIS));
       contentPane.add(definitionPane, BorderLayout.CENTER);
       
        // building is done - arrange the components      
        mainWindow.pack();
        
        // place this frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        mainWindow.setSize(1024, 768);
        mainWindow.setLocation(d.width/2 - mainWindow.getWidth()/2, d.height/2 - mainWindow.getHeight()/2);
        mainWindow.setVisible(true);
    }

	@Override
	protected void setInputGlossary() {
		// TODO Auto-generated method stub
		
	}
}

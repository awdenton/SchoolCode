
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sophie
 */
public class FamilyMember {
    public JFrame myObj;
    
    public FamilyMember(String str){
        myObj = new JFrame(str); //title text at top of window 
        myObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes window and ends program
        myObj.setMinimumSize(new Dimension(600,500));
        myObj.setResizable(false); //prohibits the user from changing window size
        //myObj.setSize(800,600);
        myObj.setLocationRelativeTo(null); //sets location to center of screen
        
        
        /*look at JPanel
        have one panel with info (editable(true)) and
        have 4 panels that are also buttons, each that
        have an action listener, when activated, changes 
        the window to display that button's jframe object's
        interface, which has it's own user interface, and
        its own GUI.
        */
        
        //JPanel for entire window
        final JPanel basePanel = new JPanel(new BorderLayout());
        //Give the panel a black border
        basePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        
        //left side panel for welcome message and static info
        JPanel sidePanel = new JPanel();
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        //sidePanel's layout is BoxLayout, and top-to-bottom oriented
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        
        //label with welcome message
        JLabel welcomeLbl = new JLabel("WELCOME SOPHIE");
        welcomeLbl.setBackground(Color.BLUE);
        welcomeLbl.setBorder(BorderFactory.createLineBorder(Color.PINK, 3));
        //add lbl to sidePanel
        sidePanel.add(welcomeLbl);
        //add sudePabel to basePanel
        basePanel.add(sidePanel, BorderLayout.LINE_START);
        
        //create the menuPanel to hold the user options, with 4 buttons
        JPanel menuPanel = new JPanel(new GridLayout(2,2,10,10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(35,35,10,10));
        menuPanel.setBackground(Color.PINK);
//add the menuPanel to the basePanel
        basePanel.add(menuPanel, BorderLayout.CENTER);
        //set basePanel as myObj's ContentPane
        myObj.setContentPane(basePanel); 
        Border pinkBorder = BorderFactory.createLineBorder(Color.PINK, 3);
        
        ///CODE TO MAKE 4 BUTTONS
        /*
        menuPanel.add(new Button("just"));
        menuPanel.add(new Button("fuck"));
        menuPanel.add(new Button("me"));
        menuPanel.add(new Button("up"));
        */
        
        ///CODE TO MAKE 4 PANELS, EACH OF THEM WITH A BUTTON
        ///*
        JPanel op1 = new JPanel();
        op1.setBorder(pinkBorder);
        op1.setBackground(Color.LIGHT_GRAY);
        op1.add(new Button("just"));
        
        JPanel op2 = new JPanel();
        op2.setBorder(pinkBorder);
        op2.add(new Button("fuck"));
        
        JPanel op3 = new JPanel();
        op3.add(new Button("me"));
        op3.setBorder(pinkBorder);
        
        JPanel op4 = new JPanel();
        op4.add(new Button("up"));
        op4.setBorder(pinkBorder);

        menuPanel.add(op1);
        menuPanel.add(op2);
        menuPanel.add(op3);
        menuPanel.add(op4); 
        //*/

        
        JPanel west = new JPanel(new BorderLayout());
        //west.add(comp, BorderLayout.LEFT);
        
        west.add(new JLabel("Welcome Sophie"));
        west.add(new Button("Hello"));
        //myObj.add(west);
        
        JPanel center = new JPanel();
        center.setBorder(BorderFactory.createLineBorder(Color.PINK, 0));
        //myObj.add(center);
//        myObj.setLayout(new GridLayout(2,2,10,10)); //look for padding
//        myObj.add(new Button("1"));
//        myObj.add(new Button("2"));
//        myObj.add(new Button("3"));
//        myObj.add(new Button("4"));
        
        //JLabel label = new JLabel("Welcome");
        //myObj.add(label); //text in window
        myObj.add(menuPanel);
        //myObj.add(west);
        myObj.pack(); //"compiles" window
        myObj.setVisible(true);

    }

    public void addButton(){
        //might be unecessary...
    }
    
    
    public void go(){
        //for loop to iterate and add buttons
    }
    
    public static void main(String[] args) {
        new FamilyMember("hello");
        
        
    }
    
}
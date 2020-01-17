import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUITest extends JFrame implements ActionListener{
    
    //Components are declared here so that they are visible
    //to the ActionListener
    JButton ptReturn;
    JButton editPlan;
    JButton createEx;
    JButton viewFeedback;
    JButton viewGoals;
    
    JButton temp;
    
    JPanel west;
    JPanel cent;
    
    public GUITest(){
        //Establishes basic outline of the Frame
        super("GUI Test");
        setMinimumSize(new Dimension(600,500));
        setResizable(false);
        //setSize(1422,800);
        setTitle("PT Options");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //Various components to be used in the frame
        JLabel user = new JLabel("Username", SwingConstants.CENTER);
        ptReturn = new JButton("Finish");
        editPlan = new JButton("Edit Exercise Plan");
        createEx = new JButton("Create Exercise");
        viewFeedback = new JButton("View User Feedback");
        viewGoals = new JButton("View User Goals");
        temp = new JButton("temp");
        
        //Panels to hold the components
        west = new JPanel();
        west.setPreferredSize(new Dimension(75,100));
        cent = new JPanel();
        
        //Set layouts for interior panels
        GridLayout westGrid = new GridLayout(3,1);
        GridLayout centGrid = new GridLayout(2,2,10,10);
        cent.setBorder(BorderFactory.createEmptyBorder(35,35,10,10));
        west.setLayout(westGrid);
        cent.setLayout(centGrid);
        
        //Add components to the panels
        west.add(user);
        west.add(temp);
        temp.setVisible(false);
        west.add(ptReturn);
        
        cent.add(editPlan);
        cent.add(viewFeedback);
        cent.add(createEx);
        cent.add(viewGoals);
        
        //Add action listeners
        editPlan.addActionListener(this);
        createEx.addActionListener(this);
        
        //Finally, add panels to the frame
        add(west, BorderLayout.WEST);
        add(cent, BorderLayout.CENTER);
       
        
        pack();
        setVisible(true);
    }
    
    //Sets the look and feel to match the design of the local OS
    private static void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exc) {
            System.err.println("Couldn't use the system look and feel: " + exc);
        }
    }
    
    //Launches the application
    public static void main(String[] args) {
        GUITest.setLookAndFeel();
        GUITest temp = new GUITest();
    }
    
    public void showCreateScreen(){
        JPanel createExPanel = new JPanel();
        
        JLabel blank = new JLabel("Please God Work");
        
        createExPanel.add(blank);
        createExPanel.setVisible(true);
        add(createExPanel, BorderLayout.CENTER);
    }
    
    //ActionListener for the two main functions of the PT screen
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == editPlan){
            JOptionPane.showMessageDialog(null, "Clicked Edit Plan");
        } else if(source == createEx) {
            remove(cent);
            showCreateScreen();
            repaint();
        }
    }
    
}
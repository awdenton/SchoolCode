import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PTV2 extends JFrame implements ActionListener {
    
    //center panel to hold view feedback and create exercise screens
    JPanel center;
    JPanel subCent;
    
    //components for side bar
    JPanel sideBar;
    JLabel welcome;
    JButton blank;
    JButton home;
    
    //components for plan editor
    JPanel editPlanPanel;
    JPanel daysOfWeek;
    JLabel nameSelect;
    String[] exercises = {"-","Push ups","Sit ups","Pilates","Jogging","Rest day"};
    JLabel day[] = {new JLabel("Mon: ", SwingConstants.RIGHT),
        new JLabel("Tue: ", SwingConstants.RIGHT),
        new JLabel("Wed: ", SwingConstants.RIGHT),
        new JLabel("Thu: ", SwingConstants.RIGHT),
        new JLabel("Fri: ", SwingConstants.RIGHT),
        new JLabel("Sat: ", SwingConstants.RIGHT),
        new JLabel("Sun: ", SwingConstants.RIGHT)};
    JComboBox fm;
    JComboBox exChooser[] = {new JComboBox(exercises),
        new JComboBox(exercises),
        new JComboBox(exercises),
        new JComboBox(exercises),
        new JComboBox(exercises),
        new JComboBox(exercises),
        new JComboBox(exercises)};
    
    //components for the family member chooser
    JPanel selectFMPanel;
    String[] famArr = {"-","Aaron","Sophie","Josh"};
    JComboBox famChooser;
    JLabel famLbl;
    
    //components for the view feedback and goals screen
    JButton viewFeedback;
    
    //components for the create exercise screen
    JButton createEx;
    JLabel exNameLbl;
    JLabel repsLbl;
    JLabel calsLbl;
    JTextField exName;
    JTextField repsNum;
    JTextField calsBurn;    
    JPanel createExPanel;

    //optionbar components
    JButton save;
    JButton cancel;
    JPanel optionBar;
    
    
    public PTV2(){
        super("Personal Trainer");
        setMinimumSize(new Dimension(600,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        //build the sidebar
        sideBar = new JPanel(new GridLayout(3,1));
        welcome = new JLabel("Welcome trainer!", SwingConstants.CENTER);
        blank = new JButton("blank");
        blank.setVisible(false);
        home = new JButton("Home");
        sideBar.add(welcome);
        sideBar.add(blank);
        sideBar.add(home);
        //add it to the WEST portion of the JFrame BorderLayout
        add(sideBar, BorderLayout.WEST);
        
        //set up the center panel. This panel will hold the FM chooser bar,
        //view user feedback and goals, and our save and cancel buttons
        center = new JPanel();
                //BoxLayout centerBox = new BoxLayout(center, BoxLayout.Y_AXIS);
        BorderLayout centerBord = new BorderLayout();
        center.setLayout(centerBord);
        //put it in the center of the JFrame BorderLayout
        add(center, BorderLayout.CENTER);
        
        //build the family member select panel
        selectFMPanel = new JPanel();
        BoxLayout famBox = new BoxLayout(selectFMPanel, BoxLayout.Y_AXIS);
        famLbl = new JLabel("Select Family Member: ");
        famChooser = new JComboBox(famArr);
        selectFMPanel.add(famLbl);
        selectFMPanel.add(famChooser);
        //add to the 'center' panel
        center.add(selectFMPanel, BorderLayout.NORTH);
        
        //set up the interior part of the center panel which holds the
        //view feedback and goals window and the create exercise
        //controls
        subCent = new JPanel();
        BoxLayout subCentBox = new BoxLayout(subCent, BoxLayout.Y_AXIS);
        subCent.setLayout(subCentBox);
        viewFeedback = new JButton("View Feedback and Goals");
        createEx = new JButton("Create New Exercise");
        subCent.add(viewFeedback);
        subCent.add(createEx);
        //add this sub-panel to the 'center' panel
        center.add(subCent, BorderLayout.CENTER);
        
        //setup the option bar
        save = new JButton("Save");
        save.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        optionBar = new JPanel();
        BoxLayout optionBox = new BoxLayout(optionBar, BoxLayout.X_AXIS);
        optionBar.setLayout(optionBox);
        optionBar.add(save);
        optionBar.add(cancel);
        //add it to the 'center' panel
        center.add(optionBar, BorderLayout.SOUTH);
        
        //set up the edit plan screen
        editPlanPanel = new JPanel();
        BoxLayout planLayout = new BoxLayout(editPlanPanel, BoxLayout.Y_AXIS);
        editPlanPanel.setLayout(planLayout);
        daysOfWeek = new JPanel(new GridLayout(7,2,0,10));
        for(int i = 0; i < 7; i++){
            daysOfWeek.add(day[i]);
            daysOfWeek.add(exChooser[i]);
        }
        editPlanPanel.add(daysOfWeek);
        center.add(editPlanPanel);
        //add it to the EAST panel of the JFrame BorderLayout
        add(editPlanPanel, BorderLayout.CENTER);
        
        
        
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new PTV2();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PTScreen extends JFrame implements ActionListener {
    
    //a panel to hold all the other screens
    JPanel main;
    
    //components for the initial screen
    JLabel name; 
    JButton ptReturn;
    JButton editPlan;
    JButton createEx;
    JButton viewFeedback;
    JButton viewGoals;    
    JButton spaceHolder;    
    JPanel west;
    JPanel home;
    
    //components for the edit plan screen
    int plans[][] = new int[4][7];
    JPanel editPlanPanel;
    JPanel selectFMBar;
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
    
    
    //components for the view feedback screen
    String feedback[] = {"","I really hated doing yoga","The eliptical was boring",
        "My exercise plan is just fine"};
    JTextArea userFeedback;
    JPanel feedbackPanel;
    
    //components for the create exercise screen
    JLabel exNameLbl;
    JLabel repsLbl;
    JLabel calsLbl;
    JTextField exName;
    JTextField repsNum;
    JTextField calBurn;    
    JPanel createExPanel;
    
    //componentes for the view goals screen
    String goals[] = {"","Gain 75 pounds", "Be able to run a four minute mile",
            "I'm totally ok with my fitness situation"};
    JTextArea userGoals;
    JPanel goalsPanel;
    
    //optionbar components
    JButton save;
    JButton cancel;
    JPanel optionBar;

    public PTScreen(){
        super("PT Options");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(600,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        main = new JPanel();
        BoxLayout mainBox = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(mainBox);
        
        //build the west toolbar
        west = new JPanel(new GridLayout(3,1));
        name = new JLabel("Welcome PT!", SwingConstants.CENTER);
        spaceHolder = new JButton("SpaceHolder");
        ptReturn = new JButton("Return");
        west.add(name);
        west.add(spaceHolder);
        spaceHolder.setVisible(false);
        west.add(ptReturn);
        this.add(west, BorderLayout.WEST);
        
        //build the PT home screen
        home = new JPanel(new GridLayout(2,2,10,10));
        home.setBorder(BorderFactory.createEmptyBorder(35,35,10,10));
        editPlan = new JButton("Edit Plan");
        editPlan.addActionListener(this);
        createEx = new JButton("Create Exercise");
        createEx.addActionListener(this);
        viewFeedback = new JButton("View Feedback");
        viewFeedback.addActionListener(this);
        viewGoals = new JButton("View Goals");
        viewGoals.addActionListener(this);
        home.add(editPlan);
        home.add(viewFeedback);
        home.add(createEx);
        home.add(viewGoals);
        main.add(home);
        
        //make a select family member bar
        String[] fMembers = {"-","Aaron","Sophie","Josh"};
        selectFMBar = new JPanel();
        selectFMBar.setMaximumSize(new Dimension(250,250));
        nameSelect = new JLabel("Select Family Member: ");
        fm = new JComboBox(fMembers);
        fm.addActionListener(this);
        selectFMBar.add(nameSelect);
        selectFMBar.add(fm);
        selectFMBar.setVisible(false);
        main.add(selectFMBar);
        
        //build the edit plan screen
        int count = 1;
        for(int i = 1; i < plans.length; i++){
            for(int j = 0; j < plans[0].length; j++){
                plans[i][j] = (count++ % 5)+1;
            }
        }
        editPlanPanel = new JPanel();
        BoxLayout planLayout = new BoxLayout(editPlanPanel, BoxLayout.Y_AXIS);
        editPlanPanel.setLayout(planLayout);
        daysOfWeek = new JPanel(new GridLayout(7,2,0,10));
        for(int i = 0; i < 7; i++){
            daysOfWeek.add(day[i]);
            daysOfWeek.add(exChooser[i]);
        }
        editPlanPanel.add(daysOfWeek);
        editPlanPanel.setVisible(false);
        main.add(editPlanPanel);
          
        //build the view feedback screen
        userFeedback = new JTextArea("", 20, 40);
        userFeedback.setLineWrap(true);
        userFeedback.setEditable(false);
        feedbackPanel = new JPanel();
        feedbackPanel.add(userFeedback);
        feedbackPanel.setVisible(false);
        main.add(feedbackPanel);
        
        //build the create exercise window
        createExPanel = new JPanel(new GridLayout(4,2,0,10));
        createExPanel.setBorder(BorderFactory.createEmptyBorder(50,10,10,75));
        exNameLbl = new JLabel("Exercise Name: ", SwingConstants.RIGHT);
        repsLbl = new JLabel("Number of Reps: ", SwingConstants.RIGHT);
        calsLbl = new JLabel("Calorie Burn: ", SwingConstants.RIGHT);
        exName = new JTextField();
        repsNum = new JTextField();
        calBurn = new JTextField();
        createExPanel.add(exNameLbl);
        createExPanel.add(exName);
        createExPanel.add(repsLbl);
        createExPanel.add(repsNum);
        createExPanel.add(calsLbl);
        createExPanel.add(calBurn);
        createExPanel.setVisible(false);
        main.add(createExPanel);
        
        //create the panel for user goals
        userGoals = new JTextArea("", 20, 40);
        userGoals.setLineWrap(true);
        userGoals.setEditable(false);
        goalsPanel = new JPanel();
        goalsPanel.add(userGoals);
        goalsPanel.setVisible(false);
        main.add(goalsPanel);
        
        //create the option bar
        save = new JButton("Save");
        save.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        optionBar = new JPanel(new GridLayout(1,2));
        optionBar.add(save);
        optionBar.add(cancel);
        optionBar.setMaximumSize(new Dimension(150, 150));
        optionBar.setVisible(false);
        main.add(optionBar);
        
        
        this.add(main, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        setLookAndFeel();
        PTScreen screen = new PTScreen();
    }
    
    public void showEditPlanScreen(){
        home.setVisible(false);
        optionBar.setVisible(true);
        editPlanPanel.setVisible(true);
        selectFMBar.setVisible(true);
        repaint();
    }
    
    public void showFeedbackScreen(){
        home.setVisible(false);
        optionBar.setVisible(true);
        feedbackPanel.setVisible(true);
        selectFMBar.setVisible(true);
        repaint();
    }
    
    public void showCreateScreen(){
        home.setVisible(false);
        optionBar.setVisible(true);
        createExPanel.setVisible(true);
        repaint();
    }
    
    public void showGoalsScreen(){
        home.setVisible(false);
        optionBar.setVisible(true);
        goalsPanel.setVisible(true);
        selectFMBar.setVisible(true);
        repaint();
    }
    
    public void showMainScreen(){
        home.setVisible(true);
        editPlanPanel.setVisible(false);
        feedbackPanel.setVisible(false);
        createExPanel.setVisible(false);
        goalsPanel.setVisible(false);
        optionBar.setVisible(false);
        fm.setSelectedIndex(0);
        for(int j = 0; j < 7; j++){
            exChooser[j].setSelectedIndex(0);
        }
        userGoals.setText("");
        userFeedback.setText("");
        selectFMBar.setVisible(false);
        repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if(source == editPlan){
            showEditPlanScreen();
        }
        if(source == viewFeedback){
            showFeedbackScreen();
        }
        if(source == createEx){
            showCreateScreen();
        }
        if(source == viewGoals){
            showGoalsScreen();
        }
        if(source == save || source == cancel){
            showMainScreen();
        }
        if(source == fm && goalsPanel.isVisible() == true){
            int i = fm.getSelectedIndex();
            userGoals.setText(goals[i]);
        }
        if(source == fm && feedbackPanel.isVisible() == true){
            int i = fm.getSelectedIndex();
            userFeedback.setText(feedback[i]);
        }
        if(source == fm && editPlanPanel.isVisible() == true){
            int k = fm.getSelectedIndex();
            for(int j = 0; j < 7; j++){
                exChooser[j].setSelectedIndex(plans[k][j]);
            }
        }
    }
    
    //Sets the look and feel to match the design of the local OS
    private static void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exc) {
            System.err.println("Couldn't use the system look and feel: " + exc);
        }
    }
}
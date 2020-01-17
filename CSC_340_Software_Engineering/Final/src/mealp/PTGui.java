package mealp;

import javax.swing.*;
import java.awt.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class PTGui extends JFrame implements ActionListener {
    
    //a panel to hold all the other screens
    JPanel main;
    
    FamilyMemberGUI fmgui;
    ExerManage manager;
    
    //components for the initial screen
    JButton ptReturn;
    JButton editPlan;
    JButton createEx;
    JButton viewFeedback;
    JButton viewGoalsPrefs;        
    JPanel west;
    JPanel home;
    
    //components for the edit plan screen
    JPanel editPlanPanel;
    JPanel selectFMBar;
    JPanel daysOfWeek;
    JTextField[] ratings = new JTextField[7];
    JTextField[] completed = new JTextField[7];
    JLabel day[] = {new JLabel("Mon: ", SwingConstants.RIGHT),
        new JLabel("Tue: ", SwingConstants.RIGHT),
        new JLabel("Wed: ", SwingConstants.RIGHT),
        new JLabel("Thu: ", SwingConstants.RIGHT),
        new JLabel("Fri: ", SwingConstants.RIGHT),
        new JLabel("Sat: ", SwingConstants.RIGHT),
        new JLabel("Sun: ", SwingConstants.RIGHT)};
    JComboBox<String> fm;
    JComboBox<String>[] exChooser = new JComboBox[7];
    
    
    //components for the view feedback screen
    String feedback[] = {"","I really hated doing yoga","The eliptical was boring",
        "My exercise plan is just fine"};
    JTextArea userFeedback;
    JPanel feedbackPanel;
    
    //components for the create exercise screen
    JTextField exName;
    JTextField repsNum;
    JTextField calBurn;    
    JPanel createExPanel;
    
    //componentes for the view goals screen
    String goals[] = {"","Gain 75 pounds", "Be able to run a four minute mile",
            "I'm totally ok with my fitness situation"};
    JTextArea userGoals;
    JTextArea userPrefs;
    JPanel goalsPanel;
    
    //optionbar components
    JButton save;
    JButton cancel;
    JPanel optionBar;

    public PTGui(FamilyMemberGUI f) throws FileNotFoundException {
        super("PT Options");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        fmgui = f;
        manager = new ExerManage();
        
        main = new JPanel();
        BoxLayout mainBox = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(mainBox);
        
        
        //build the west toolbar
        west = new JPanel(new GridLayout(3,1));
        JLabel name = new JLabel("Welcome PT!", SwingConstants.CENTER);
        JButton spaceHolder = new JButton("SpaceHolder");
        ptReturn = new JButton("Return");
        ptReturn.addActionListener(this);
        west.add(name);
        west.add(spaceHolder);
        spaceHolder.setVisible(false);
        west.add(ptReturn);
        add(west, BorderLayout.WEST);
        
        
        //build the PT home screen
        home = new JPanel(new GridLayout(2,2,10,10));
        home.setBorder(BorderFactory.createEmptyBorder(35,35,10,10));
        editPlan = new JButton("Edit Plan");
        editPlan.addActionListener(this);
        createEx = new JButton("Create Exercise");
        createEx.addActionListener(this);
        viewFeedback = new JButton();
        viewFeedback.addActionListener(this);
        viewFeedback.setVisible(false);
        viewGoalsPrefs = new JButton("View Goals");
        viewGoalsPrefs.addActionListener(this);
        home.add(editPlan);
        home.add(viewFeedback);                
        home.add(createEx);
        home.add(viewGoalsPrefs);
        main.add(home);
        
        
        //make a select family member bar
        String[] fMembers = {"-","Sophie","Aaron","Josh"};
        selectFMBar = new JPanel();
        selectFMBar.setMaximumSize(new Dimension(250,250));
        JLabel nameSelect = new JLabel("Select Family Member: ");
        fm = new JComboBox(fMembers);
        fm.addActionListener(this);
        selectFMBar.add(nameSelect);
        selectFMBar.add(fm);
        selectFMBar.setVisible(false);
        main.add(selectFMBar);
        
        
        //build the edit plan screen
        editPlanPanel = new JPanel();
        BoxLayout planLayout = new BoxLayout(editPlanPanel, BoxLayout.Y_AXIS);
        editPlanPanel.setLayout(planLayout);
        daysOfWeek = new JPanel(new GridLayout(7,4,0,10));
        
        for(int k = 0; k < 7; k++){
            exChooser[k] = new JComboBox(manager.getNames());
            exChooser[k].addActionListener(this);
            ratings[k] = new JTextField("Rating: ");
            completed[k] = new JTextField("Completed: ");
        }
        
        for(int i = 0; i < 7; i++){
            daysOfWeek.add(day[i]);
            daysOfWeek.add(exChooser[i]);
            daysOfWeek.add(ratings[i]);
            daysOfWeek.add(completed[i]);
        }
        
        editPlanPanel.add(daysOfWeek);
        editPlanPanel.setVisible(false);
        main.add(editPlanPanel);
          
        
        //build the view feedback screen
        userFeedback = new JTextArea("", 20, 40);
        userFeedback.setLineWrap(true);
        userFeedback.setEditable(false);
        userFeedback.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        feedbackPanel = new JPanel();
        feedbackPanel.add(userFeedback);
        feedbackPanel.setVisible(false);
        main.add(feedbackPanel);
        
        
        //build the create exercise window
        createExPanel = new JPanel(new GridLayout(4,2,0,10));
        createExPanel.setBorder(BorderFactory.createEmptyBorder(50,10,10,75));
        JLabel exNameLbl = new JLabel("Exercise Name: ", SwingConstants.RIGHT);
        JLabel repsLbl = new JLabel("Number of Reps: ", SwingConstants.RIGHT);
        JLabel calsLbl = new JLabel("Calorie Burn: ", SwingConstants.RIGHT);
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
        
        
        //create the panel for user goals and preferences
        JLabel goalsLbl = new JLabel("User Goals");
        JLabel prefsLbl = new JLabel("User Preferences");
        userGoals = new JTextArea("");
        userGoals.setLineWrap(true);
        userGoals.setEditable(false);
        userGoals.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        userPrefs = new JTextArea("");
        userPrefs.setLineWrap(true);
        userPrefs.setEditable(false);
        userPrefs.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        goalsPanel = new JPanel();
        GroupLayout goalsGroup = new GroupLayout(goalsPanel);
        goalsPanel.setLayout(goalsGroup);
        goalsGroup.setAutoCreateGaps(true);
        goalsGroup.setAutoCreateContainerGaps(true);
        GroupLayout.SequentialGroup goalsHGroup = goalsGroup.createSequentialGroup();
        GroupLayout.SequentialGroup goalsVGroup = goalsGroup.createSequentialGroup();
        
        goalsHGroup.addGroup(goalsGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(goalsLbl).addComponent(userGoals));
        goalsHGroup.addGroup(goalsGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(prefsLbl).addComponent(userPrefs));
        
        goalsVGroup.addGroup(goalsGroup.createParallelGroup().addComponent(goalsLbl).addComponent(prefsLbl));
        goalsVGroup.addGroup(goalsGroup.createParallelGroup().addComponent(userGoals).addComponent(userPrefs));
        
        goalsGroup.setHorizontalGroup(goalsHGroup);
        goalsGroup.setVerticalGroup(goalsVGroup);
        
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
        
        
        add(main, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                manager.writeData(fm);
                dispose();
            }
        });
        
        pack();
        setVisible(true);
    }
    
//    public static void main(String[] args) throws FileNotFoundException {
//        setLookAndFeel();        
//        PTGui screen = new PTGui();
//    }
    
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
        save.setVisible(false);
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
        save.setVisible(false);
        repaint();
    }
    
    public void showMain(){
        home.setVisible(true);
        editPlanPanel.setVisible(false);
        feedbackPanel.setVisible(false);
        createExPanel.setVisible(false);
        goalsPanel.setVisible(false);
        optionBar.setVisible(false);
        fm.setSelectedIndex(0);
        for(int j = 0; j < 7; j++){
            exChooser[j].setSelectedIndex(0);
            ratings[j].setText("Rating: ");
            completed[j].setText("Completed: ");
        }
        exName.setText("");
        repsNum.setText("");
        calBurn.setText("");
        userGoals.setText("");
        userFeedback.setText("");
        selectFMBar.setVisible(false);
        save.setVisible(true);
        repaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        //display various action screens
        if(source == editPlan){
            showEditPlanScreen();
        }
        if(source == viewFeedback){
            showFeedbackScreen();
        }
        if(source == createEx){
            showCreateScreen();
        }
        if(source == viewGoalsPrefs){
            showGoalsScreen();
        }
        
        //reset to the main PT screen
        if(source == cancel){
            showMain();
        }
        
        //save an exercise
        if(source == save && createExPanel.isVisible()){
            String name = exName.getText();
            int reps = Integer.parseInt(repsNum.getText());
            int cals = Integer.parseInt(calBurn.getText());
            manager.createExercise(name, reps, cals);
            
            for(int i =0; i < 7; i++){
                exChooser[i].addItem(name);
            }
            
            showMain();
        }
        
        //create an exercise plan
        if(source == save && editPlanPanel.isVisible()){
            int fam = fm.getSelectedIndex();
            int[] exers = new int[7];
            for(int i = 0; i < 7; i++){
                exers[i] = exChooser[i].getSelectedIndex();
            }
            manager.createPlan(fam, exers);
            showMain();
        }
        
        //display current exercise plan for selected fm
        if(editPlanPanel.isVisible() && source == fm){
            for(int i = 0; i < 7; i++){
                exChooser[i].setSelectedIndex(manager.plans[fm.getSelectedIndex()][i]);
                ratings[i].setText("Rating: " + manager.exercises.get(exChooser[i].getSelectedIndex()).getRating(fm.getSelectedIndex()));
                completed[i].setText("Completed: " + manager.exercises.get(exChooser[i].getSelectedIndex()).getCompletion(fm.getSelectedIndex()));
            }
        }
        
        //display ratings of exercises on the fly
        if(source == exChooser[0]){
                ratings[0].setText("Rating: " + manager.exercises.get(exChooser[0].getSelectedIndex()).getRating(fm.getSelectedIndex()));
        }
        if(source == exChooser[1]){
                ratings[1].setText("Rating: " + manager.exercises.get(exChooser[1].getSelectedIndex()).getRating(fm.getSelectedIndex()));
        }
        if(source == exChooser[2]){
                ratings[2].setText("Rating: " + manager.exercises.get(exChooser[2].getSelectedIndex()).getRating(fm.getSelectedIndex()));
        }
        if(source == exChooser[3]){
                ratings[3].setText("Rating: " + manager.exercises.get(exChooser[3].getSelectedIndex()).getRating(fm.getSelectedIndex()));
        }
        if(source == exChooser[4]){
                ratings[4].setText("Rating: " + manager.exercises.get(exChooser[4].getSelectedIndex()).getRating(fm.getSelectedIndex()));
        }
        if(source == exChooser[5]){
                ratings[5].setText("Rating: " + manager.exercises.get(exChooser[5].getSelectedIndex()).getRating(fm.getSelectedIndex()));
        }
        if(source == exChooser[6]){
                ratings[6].setText("Rating: " + manager.exercises.get(exChooser[6].getSelectedIndex()).getRating(fm.getSelectedIndex()));
        }
        
        //display FM goals and preferences
        if(goalsPanel.isVisible() && source == fm){
            userGoals.setText("Current weight: " + fmgui.manager.users.get(fm.getSelectedIndex()-1).getCurrentWeight()
                              + "\nWeight goal: " + fmgui.manager.users.get(fm.getSelectedIndex()-1).getWeightGoal());
            userFeedback.setText(fmgui.manager.users.get(fm.getSelectedIndex()-1).getExPrefs());
        }
        
        if(source == ptReturn){
            manager.writeData(fm);
            try {
                new FamilyMemberGUI("Health and Fitness Family Trainer");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PTGui.class.getName()).log(Level.SEVERE, null, ex);
            }
            dispose();
        }
    }
}
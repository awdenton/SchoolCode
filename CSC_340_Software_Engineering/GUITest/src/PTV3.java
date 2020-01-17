import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class PTV3 extends JFrame implements ActionListener {
    
    
    String[] exercises = {"-","Push ups","Sit ups","Pilates","Jogging","Rest day"};
    JButton home;
    
    
    //components for the family member selector
    JComboBox famChooser;
    
    
    //components for the user goals and feedback
    Box feedBox;
    JTextArea userGoals;
    JTextArea userFeedback;
    
    
    //components for the create/edit exercise screen
    Box exerBox;
    JPanel exerData;
    JLabel chooseLbl;
    JComboBox exerChoose = new JComboBox(exercises);
    JTextField exerName;
    JTextField exerRep;
    JTextField exerCal;
    JButton exerSave;
    JButton exerCancel;
    JButton exerDelete;
    JButton exerCreate;
    JButton exerEdit;
    
    
    //components for exercise plan creator
    JComboBox planChooser;
    JTextField planName;
    JComboBox exPlanner[] = {new JComboBox(exercises),
                             new JComboBox(exercises),
                             new JComboBox(exercises),
                             new JComboBox(exercises),
                             new JComboBox(exercises),
                             new JComboBox(exercises),
                             new JComboBox(exercises)};
    JButton planSave;
    JButton planSaveAs;
    JButton planCancel;
    
    
    public PTV3() {
        super("Personal Trainer v3");
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(600,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        //mainBox arranges the elements to be placed into the center section
            //Box mainBox = new Box(BoxLayout.X_AXIS);
        JPanel mainPanel = new JPanel();
        GroupLayout mainGroup = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainGroup);
        mainGroup.setAutoCreateGaps(true);
        mainGroup.setAutoCreateContainerGaps(true);
        
        
        //create the home button
        Box westBox = new Box(BoxLayout.Y_AXIS);
        westBox.add(Box.createVerticalGlue());
        home = new JButton("Home");
        home.setPreferredSize(new Dimension(70,75));
        westBox.add(home);
        add(westBox, BorderLayout.WEST);
        
        
        //create a Box to hold the elements between the home button
        //and the plan editor
        Box centerBox = new Box(BoxLayout.Y_AXIS);
        
        
        //create the family member selection panel
        String[] famArr = {"-","Aaron","Sophie","Josh"};
        JLabel famLbl = new JLabel("Select FM: ");
        famChooser = new JComboBox(famArr);
        famChooser.addActionListener(this);
        //make a panel to hold the components 
        JPanel famPanel = new JPanel();
        famPanel.add(famLbl);
        famPanel.add(famChooser);
        //add the panel to the centerBox
        centerBox.add(famPanel);
        
        
        //create the text fields to view user weight goals
        //and potentially exercise feedback
        userGoals = new JTextArea("User goals and preferences");
        userGoals.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        userGoals.setEditable(false);
        userGoals.setLineWrap(true);
        userGoals.setWrapStyleWord(true);
        userFeedback = new JTextArea("User feedback and exercise ratings");
        userFeedback.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        userFeedback.setEditable(false);
        userFeedback.setLineWrap(true);
        userFeedback.setWrapStyleWord(true);
        feedBox = new Box(BoxLayout.Y_AXIS);
        feedBox.add(userGoals);
        feedBox.add(Box.createVerticalStrut(25));
        feedBox.add(userFeedback);
        centerBox.add(feedBox);
        
        
        //create the 'Create/Edit Exercise' screen
        chooseLbl = new JLabel("Select exercise: ", SwingConstants.RIGHT);
        JLabel exerNameLbl = new JLabel("Exercise name: ", SwingConstants.RIGHT);
        exerName = new JTextField();
        JLabel exerRepLbl = new JLabel("Number of reps: ");
        exerRep = new JTextField();
        JLabel exerCalLbl = new JLabel("Estimated calorie burn: ");
        exerCal = new JTextField();
        exerSave = new JButton("Save Exercise");
        exerCancel = new JButton("Cancel Changes");
        exerDelete = new JButton("Delete Exercise");
        
        exerData = new JPanel();
        GroupLayout exerGroup = new GroupLayout(exerData);
        exerData.setLayout(exerGroup);
        exerGroup.setAutoCreateGaps(true);
        exerGroup.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup exerHGroup = exerGroup.createSequentialGroup();
        GroupLayout.SequentialGroup exerVGroup = exerGroup.createSequentialGroup();
        
        exerHGroup.addGroup(exerGroup.createParallelGroup(GroupLayout.Alignment.TRAILING).
                addComponent(chooseLbl).addComponent(exerNameLbl).addComponent(exerRepLbl).addComponent(exerCalLbl));
        exerHGroup.addGroup(exerGroup.createParallelGroup().addComponent(exerChoose).addComponent(exerName).
                addComponent(exerRep).addComponent(exerCal));
        exerVGroup.addGroup(exerGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(chooseLbl).
                addComponent(exerChoose));
        exerVGroup.addGroup(exerGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(exerNameLbl).
                addComponent(exerName));
        exerVGroup.addGroup(exerGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(exerRepLbl).
                addComponent(exerRep));
        exerVGroup.addGroup(exerGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(exerCalLbl).
                addComponent(exerCal));
        
        exerGroup.setHorizontalGroup(exerHGroup);
        exerGroup.setVerticalGroup(exerVGroup);
        
        Box exerOpts = new Box(BoxLayout.X_AXIS);
        exerOpts.add(exerSave);
        exerOpts.add(exerCancel);
        exerOpts.add(exerDelete);
        
        exerBox = new Box(BoxLayout.Y_AXIS);
        exerBox.add(exerData);
        exerBox.add(exerOpts);
        centerBox.add(exerBox);
        
        
        //prepare the Box for the exercise planner portion
        Box plannerBox = new Box(BoxLayout.Y_AXIS);
        JLabel planLbl = new JLabel("Select Plan: ", SwingConstants.RIGHT);
        planName = new JTextField("Plan Name");
        planChooser = new JComboBox();
        planSave = new JButton("Save Plan");
        planSaveAs = new JButton("Save Plan As");
        planCancel = new JButton("Cancel");
        
        JPanel planTop = new JPanel();
        planTop.add(planLbl);
        planTop.add(planName);
                
        JPanel planBottom = new JPanel();
        planBottom.add(planSave);
        planBottom.add(planSaveAs);
        planBottom.add(planCancel);
                
        //create the exercise plan screen
        JPanel planPanel = new JPanel();
        planPanel.setMinimumSize(new Dimension(750,600));
        GroupLayout planGroup = new GroupLayout(planPanel);
        planPanel.setLayout(planGroup);
        planGroup.setAutoCreateGaps(true);
        planGroup.setAutoCreateContainerGaps(true);
        
        GroupLayout.SequentialGroup planHGroup = planGroup.createSequentialGroup();
        GroupLayout.SequentialGroup planVGroup = planGroup.createSequentialGroup();
        
        JLabel day[] = {new JLabel("Mon: ", SwingConstants.RIGHT),
                        new JLabel("Tue: ", SwingConstants.RIGHT),
                        new JLabel("Wed: ", SwingConstants.RIGHT),
                        new JLabel("Thu: ", SwingConstants.RIGHT),
                        new JLabel("Fri: ", SwingConstants.RIGHT),
                        new JLabel("Sat: ", SwingConstants.RIGHT),
                        new JLabel("Sun: ", SwingConstants.RIGHT)};
        
        planHGroup.addGroup(planGroup.createParallelGroup().addComponent(day[0]).addComponent(day[1]).
                addComponent(day[2]).addComponent(day[3]).addComponent(day[4]).addComponent(day[5]).
                addComponent(day[6]));
        planHGroup.addGroup(planGroup.createParallelGroup().addComponent(exPlanner[0]).addComponent(exPlanner[1]).
                addComponent(exPlanner[2]).addComponent(exPlanner[3]).addComponent(exPlanner[4]).
                addComponent(exPlanner[5]).addComponent(exPlanner[6]));
        planVGroup.addGroup(planGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(day[0]).addComponent(exPlanner[0]));
        planVGroup.addGroup(planGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(day[1]).addComponent(exPlanner[1]));
        planVGroup.addGroup(planGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(day[2]).addComponent(exPlanner[2]));
        planVGroup.addGroup(planGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(day[3]).addComponent(exPlanner[3]));
        planVGroup.addGroup(planGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(day[4]).addComponent(exPlanner[4]));
        planVGroup.addGroup(planGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(day[5]).addComponent(exPlanner[5]));
        planVGroup.addGroup(planGroup.createParallelGroup(GroupLayout.Alignment.CENTER).addComponent(day[6]).addComponent(exPlanner[6]));
        
        planGroup.setHorizontalGroup(planHGroup);
        planGroup.setVerticalGroup(planVGroup);
        
        plannerBox.add(planTop);
        plannerBox.add(planPanel);
        plannerBox.add(planBottom);
        
        
        //now that all components hacve been created, set the layout for the main panel
        GroupLayout.SequentialGroup mainHGroup = mainGroup.createSequentialGroup();
        GroupLayout.SequentialGroup mainVGroup = mainGroup.createSequentialGroup();
        
        mainHGroup.addGroup(mainGroup.createParallelGroup().addComponent(centerBox));
        mainHGroup.addGroup(mainGroup.createParallelGroup().addComponent(plannerBox));
        mainVGroup.addGroup(mainGroup.createParallelGroup().addComponent(centerBox).addComponent(plannerBox));
        
        mainGroup.setHorizontalGroup(mainHGroup);
        mainGroup.setVerticalGroup(mainVGroup);
        
        
        
        //mainBox.add(centerBox);
        //mainBox.add(planPanel);
        //mainPanel.add(centerBox);
        //mainPanel.add(planPanel);
        
        //add(mainBox);
        add(mainPanel);
        
        pack();
        setVisible(true);
    }
    
    
    public static void main(String[] args) {
        new PTV3();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
    }

    
}
package mealp;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Sophie
 */
public class FamilyMemberGUI extends JFrame implements ActionListener{
    
    public JFrame myObj;
    
    FamilyMemberManager manager;
    PTGui ptgui;
    mealp mpgui;
    
    JComboBox dayListEx;
    JComboBox ratingEx;
    JComboBox finishedEx;
    JTextArea exercise;
    
    JPanel basePanel;
    JPanel mainPanel = new JPanel();
    JPanel sidePanel;
    JPanel menuPanel;
    JPanel loginPanel;
    JPanel viewProgPanel = new JPanel();
    JPanel enterProgPanel = new JPanel();
    JPanel enterPrefsPanel = new JPanel();
    
    JPanel giveFeedbackPanel = new JPanel();
    JPanel rateMealPanel = new JPanel();
    JPanel rateExPanel = new JPanel();
    JPanel foodListPanel = new JPanel();
    JPanel ratingPanel = new JPanel();

    JPanel foodPrefsPanel = new JPanel();
    JPanel prefListPanel = new JPanel();
    JPanel exPrefsPanel = new JPanel();
    JPanel exListPanel = new JPanel();
    JPanel foodChoicesPanel = new JPanel();
    
    
    JPanel selectPanel = new JPanel();
    JPanel foodLikesPanel = new JPanel();
    JPanel exLikesPanel= new JPanel();

    JButton viewProg;
    JButton enterProg;
    JButton enterPrefs;
    JButton giveFdbck;
    JButton ptOptions;
    JButton mpOptions;
    JButton back = new JButton("Back");
    JButton save = new JButton("Save");
    JButton getFeedbackButton = new JButton("Get Feedback");
    JButton login = new JButton("login");
    
    JTextField userNameField = new JTextField("             ");
    JTextField enterWeight = new JTextField();
    JTextField currWeight = new JTextField();
    JTextField startWeight = new JTextField();
    JTextArea weightChange1 = new JTextArea();
    JTextArea weightChange2 = new JTextArea();  
    JTextField weightChange = new JTextField();
    
    JTextArea food1 = new JTextArea();
    JTextArea food2 = new JTextArea();
    JTextArea food3 = new JTextArea();
    JTextArea food4 = new JTextArea();
    JTextArea food5 = new JTextArea();
    JTextArea food6 = new JTextArea();
    JTextArea food7 = new JTextArea();
    JTextArea food8 = new JTextArea();
    JTextArea food9 = new JTextArea();
    JTextArea food10 = new JTextArea();
    JTextArea ex1 = new JTextArea();
    JTextArea ex2 = new JTextArea();
    JTextArea ex3 = new JTextArea();
    JTextArea ex4 = new JTextArea();
    JTextArea ex5 = new JTextArea();
    JTextArea ex6 = new JTextArea();
    JTextArea ex7 = new JTextArea();
    JTextArea ex8 = new JTextArea();
    JTextArea ex9 = new JTextArea();
    JTextArea ex10 = new JTextArea();
    
    JOptionPane confirm = new JOptionPane(); //for window to confirm
    JOptionPane encourage = new JOptionPane(); //for get Feedback button
    JOptionPane loginError = new JOptionPane(); //for incorrect username
    String[][] portionArr = new String[10][3];
    String[] dietaryNeeds = new String[4];
    String foods[];
    
    String[] foodRatings = {"1","2","3"};
    String[] foodRatings2 = {"1","2","3"};
    String[] foodRatings3 = {"1","2","3"};
    String[] foodRatings4 = {"1","2","3"};
            
    JComboBox foodRating = new JComboBox(foodRatings);      
    JComboBox foodRating3 = new JComboBox(foodRatings3);
    JComboBox foodRating4 = new JComboBox(foodRatings4);
    JComboBox foodRating2 = new JComboBox(foodRatings2);
            
    public FamilyMemberGUI(String str) throws FileNotFoundException{
        //new JFrame(str); //title text at top of window 
        super(str);
        manager = new FamilyMemberManager();
        manager.importFile();
        ptgui = new PTGui(this);
        ptgui.setVisible(false);
        //mpgui = new mealp("Meal Planner"); 
        
        back.addActionListener(this);
        save.addActionListener(this);
        login.addActionListener(this); //for login
        
        //setExtendedState(MAXIMIZED_BOTH); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes window and ends program
        setMinimumSize(new Dimension(900,750));
        setResizable(true);             //prohibits the user from changing window size
        setLocationRelativeTo(null);    //sets location to center of screen
        //build the panel for the entire frame
        basePanel = new JPanel(new BorderLayout());
        basePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        
        loginPanel = new JPanel(new FlowLayout());
        JLabel loginMsg = new JLabel("Welcome! Please enter your username.", SwingConstants.CENTER);
        
        //build the login panel
        userNameField.setEditable(true);
        loginPanel.add(loginMsg);
        loginPanel.add(userNameField); //SAVE THIS DATA
        loginPanel.add(login);

        //built the mainPanel: holds all other panels, including menu
        menuPanel = new JPanel(new GridLayout(2,2,10,10));

        ///BORDERS & BACKGROUNDS
        Border pinkB = BorderFactory.createLineBorder(Color.PINK, 3);
        JLabel welcomeLbl = new JLabel("WELCOME ", SwingConstants.CENTER); //change to getName()
        welcomeLbl.setBorder(pinkB);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(35,35,10,10));
        menuPanel.setBackground(Color.PINK);  
        
        //build the left side panel
        sidePanel = new JPanel(); 
        sidePanel.setLayout(new GridLayout(3,1));
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        sidePanel.setPreferredSize(new Dimension(200,200));
        
        //set basePanel as ContentPane
        setContentPane(basePanel); 
        
        ///LAYOUT for mainPanel
        BoxLayout mainbox = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(mainbox);
        
        ///BUTTONS and action listeners
        viewProg = new JButton("View Progress");
        viewProg.addActionListener(this);    
        
        enterProg = new JButton("Enter Progress");
        enterProg.addActionListener(this); 
        
        enterPrefs = new JButton("Enter Preference and Feedback");
        enterPrefs.addActionListener(this);
        
        giveFdbck = new JButton("Get Feedback");
        giveFdbck.addActionListener(this);
        
        ptOptions = new JButton("Personal Trainer Options");
        ptOptions.addActionListener(this);
        
        mpOptions = new JButton("Meal Planner Options");
        mpOptions.addActionListener(this);
        
        //adds 4 buttons to the menu panel
        menuPanel.add(enterProg);
        menuPanel.add(viewProg);
        menuPanel.add(enterPrefs);
        menuPanel.add(giveFdbck);

        mainPanel.add(loginPanel); ///NEW
        mainPanel.add(menuPanel);  //replace menuPanel here with loginPanel
        menuPanel.setVisible(false);

        //add component to sidePanel
        sidePanel.add(welcomeLbl);//add lbl to sidePanel

        //add panels to basePanel
        //basePanel.add(); ///NEW
        basePanel.add(sidePanel, BorderLayout.LINE_START); 
        sidePanel.setVisible(false);
        basePanel.add(mainPanel, BorderLayout.CENTER);  //keep mainPanel here :)
        
        setVisible(true);
        pack(); //"compiles" window
        //manager.importFile();
    }
    
    public void showLoginScreen() throws FileNotFoundException{
        viewProgPanel.setVisible(false);
        enterProgPanel.setVisible(false);
        enterPrefsPanel.setVisible(false);
        giveFeedbackPanel.setVisible(false);
        menuPanel.setVisible(false);
    }

    public void showMainScreen(){
        viewProgPanel.setVisible(false);
        enterProgPanel.setVisible(false);
        enterPrefsPanel.setVisible(false);
        giveFeedbackPanel.setVisible(false);
        mainPanel.setVisible(true);
        sidePanel.setVisible(true);
        menuPanel.setVisible(true);
        loginPanel.setVisible(false);
        if(manager.currentUser.isMealPlanner()){
            sidePanel.add(mpOptions);
        }
        else if(manager.currentUser.isPersonalTrainer()){
             sidePanel.add(ptOptions);
        }
    }

    /**
     * View Progress Panel 
     * allows the user to view their current weight,
     * staring weight, and the weight they have lost/gained.
     */
    public void showViewProgScreen(){               //DONE
        menuPanel.setVisible(false);
        
        //setup layout
        GroupLayout viewProgLayout = new GroupLayout(viewProgPanel);
        viewProgPanel.setLayout(viewProgLayout);
        
        //define and initilize components
        JTextArea startWeightLbl = new JTextArea("Your starting weight was: ");
        startWeight.setText(Double.toString(manager.currentUser.getStartWeight()));
        startWeight.setEditable(false);
        
        JTextArea currWeightLbl = new JTextArea("Your current weight is: ");
        currWeight.setText(Double.toString(manager.currentUser.getCurrentWeight()));
        currWeight.setEditable(false);
        
        weightChange1.setText("You have lost ");
        weightChange2.setText(" lbs."); 
        weightChange.setText(manager.getWeightChange());
        weightChange.setEditable(false);

        //add componoents to horizonal and vertival layout
        viewProgLayout.setHorizontalGroup(
            viewProgLayout.createSequentialGroup()
                .addGroup(viewProgLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(startWeightLbl, 175,175,175)
                    .addComponent(currWeightLbl, 175,175,175)
                    .addComponent(weightChange1, 175,175,175)
                )       
                .addGroup(viewProgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(startWeight, 50,50,50)
                    .addComponent(currWeight, 50,50,50)
                    .addComponent(weightChange, 50,50,50)
                )
                .addGroup(viewProgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(weightChange2, 25,25,25)
                )
                .addComponent(back)
        );
        viewProgLayout.setVerticalGroup(
            viewProgLayout.createSequentialGroup()
                .addGroup(viewProgLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(startWeightLbl, 25,25,25)
                    .addComponent(startWeight, 25,25,25) 
                )       
                .addGroup(viewProgLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(currWeightLbl, 25,25,25)
                    .addComponent(currWeight, 25,25,25)
                )
                .addGroup(viewProgLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(weightChange1, 25,25,25)
                    .addComponent(weightChange, 25,25,25)
                    .addComponent(weightChange2, 25,25,25)
                )
                .addComponent(back)
        );  
        mainPanel.add(viewProgPanel);
        viewProgPanel.setVisible(true);  
        repaint();
    }               
    
    public void showEnterProgScreen(){              //DONE
        menuPanel.setVisible(false);

        GroupLayout enterProgLayout = new GroupLayout(enterProgPanel);
        enterProgPanel.setLayout(enterProgLayout);

        JTextArea enterWeightLbl = new JTextArea("Enter your weight for this week : ");
        enterWeight.setEditable(true);

        enterProgLayout.setHorizontalGroup(
            enterProgLayout.createSequentialGroup()
                .addComponent(enterWeightLbl,200,200,200)
                .addComponent(enterWeight, 75,75,75)
                .addGroup(enterProgLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(save)
                    .addComponent(back)
                )       
        );
        enterProgLayout.setVerticalGroup(
            enterProgLayout.createSequentialGroup()
                .addGroup(enterProgLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(enterWeightLbl, 25,25,25)
                    .addComponent(enterWeight, 25,25,25)
                    .addComponent(save)
                )
                .addComponent(back)
        );
        mainPanel.add(enterProgPanel);
        enterProgPanel.setVisible(true);
        repaint();
    }              

    public void showEnterPrefScreen(){
        menuPanel.setVisible(false);
        enterPrefsPanel.setLayout(new GridLayout(3,1,10,10));
        
        //set layouts of enterPrefs's 3 internal panels
        GroupLayout foodChoicesLayout = new GroupLayout(foodChoicesPanel);
        foodChoicesPanel.setLayout(foodChoicesLayout);
        GroupLayout foodPrefsLayout = new GroupLayout(foodPrefsPanel);
        foodPrefsPanel.setLayout(foodPrefsLayout);
        GroupLayout exPrefsLayout = new GroupLayout(exPrefsPanel);
        exPrefsPanel.setLayout(exPrefsLayout);
        
        foodChoicesPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        foodPrefsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        exPrefsPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        
        //panel to display the list of dietary restrictions/preferences
        prefListPanel.setLayout(new BoxLayout(prefListPanel, BoxLayout.PAGE_AXIS));
        //panel to display the list of foods that will be rated...
        foodListPanel.setLayout(new BoxLayout(foodListPanel, BoxLayout.PAGE_AXIS));
        //panel to display the list of exercises that will be rated...
        exListPanel.setLayout(new BoxLayout(exListPanel, BoxLayout.PAGE_AXIS));
        
        //panel to select yes/no for dietary restrictions
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.PAGE_AXIS));
        //panel to rate like/dislike food
        foodLikesPanel.setLayout(new BoxLayout(foodLikesPanel, BoxLayout.PAGE_AXIS));
        //panel to rate like/dislike exercise
        exLikesPanel.setLayout(new BoxLayout(exLikesPanel, BoxLayout.PAGE_AXIS));
        
        //COMPONENTS FOR FoodChoicePanel
        JTextArea msg1 = new JTextArea("Please enter any dietary restrictions"
                + "and or food preferences that apply.");
        msg1.setEditable(false);
        
        //components for prefListPanel
        JTextArea op1 = new JTextArea("vegetarian");
        op1.setEditable(false);
        JTextArea op2 = new JTextArea("vegan");
        op2.setEditable(false);
        JTextArea op3 = new JTextArea("gluten free");
        op3.setEditable(false);
        JTextArea op4 = new JTextArea("allergy");
        op4.setEditable(false);
        
        //build prefListPanel
        prefListPanel.add(op1);
        prefListPanel.add(op2);
        prefListPanel.add(op3);
        prefListPanel.add(op4);
        foodListPanel.setVisible(true);
        
        //components for selectPanel
        String[] yn1 = {"yes","no"};
        JComboBox select1 = new JComboBox(yn1);
        select1.setSelectedIndex(0);
        select1.setEditable(false);
        dietaryNeeds[0] = select1.getSelectedItem().toString();
        
        String[] yn2 = {"yes","no"};
        JComboBox select2 = new JComboBox(yn2);
        select2.setSelectedIndex(0);
        select2.setEditable(false);
        dietaryNeeds[1] = select2.getSelectedItem().toString();
        
        String[] yn3 = {"yes","no"};
        JComboBox select3 = new JComboBox(yn3);
        select3.setSelectedIndex(0);
        select3.setEditable(false);
        dietaryNeeds[2] = select3.getSelectedItem().toString();
        
        String[] yn4 = {"yes","no"};
        JComboBox select4 = new JComboBox(yn4);
        select4.setSelectedIndex(0);
        select4.setEditable(false);
        dietaryNeeds[3] = select4.getSelectedItem().toString();
        
        //build selectPanel
        selectPanel.add(select1);
        selectPanel.add(select2);
        selectPanel.add(select3);
        selectPanel.add(select4);
        selectPanel.setVisible(true);
        
        //BUILD foodchoicespanel
        foodChoicesLayout.setHorizontalGroup(
            foodChoicesLayout.createSequentialGroup()
                .addGroup(foodChoicesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(msg1, 450,450,450)
                    .addComponent(prefListPanel, 75,75,75)
                ) 
                .addComponent(selectPanel, 75,75,75)
        );
        foodChoicesLayout.setVerticalGroup(
            foodChoicesLayout.createSequentialGroup()
                .addComponent(msg1, 25,25,25)
                .addGroup(foodChoicesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(prefListPanel, 100,100,100)
                    .addComponent(selectPanel, 110,110,110)
                )
        );
        foodChoicesPanel.setVisible(true);
        
        
        //COMPONENTS FOR foodPrefsPanel
        JTextArea msg2 = new JTextArea("Please mark if you dislike (1), like (3)"
                + "or are Ok (2) with these foods");
        msg2.setEditable(false);

        //components for foodListPanel
        
        foods = manager.getFoodList();
        
        food1 .setText(foods[0]);
        food1.setEditable(false);
        food2.setText(foods[1]);
        food2.setEditable(false);
        food3.setText(foods[2]);
        food3.setEditable(false);
        food4.setText(foods[3]);
        food4.setEditable(false);
        food5.setText(foods[4]);
        food5.setEditable(false);
        food6 .setText(foods[5]);
        food6.setEditable(false);
        food7.setText(foods[6]);
        food7.setEditable(false);
        food8.setText(foods[7]);
        food8.setEditable(false);
        food9.setText(foods[8]);
        food9.setEditable(false);
        food10.setText(foods[9]);
        food10.setEditable(false);
        
        foodListPanel.add(food1);
        foodListPanel.add(food2);
        foodListPanel.add(food3);
        foodListPanel.add(food4);

        //build foodListPanel
        foodListPanel.add(food1);
        foodListPanel.add(food2);
        foodListPanel.add(food3);
        foodListPanel.add(food4);
        foodListPanel.setVisible(true);

        //components for foodLikesPanel
        //indiviudal ratings for each food
        foodRating.setSelectedIndex(0);
        foodRating.setEditable(false);
        foodRating2.setSelectedIndex(0);
        foodRating2.setEditable(false);
        foodRating3.setSelectedIndex(0);
        foodRating3.setEditable(false);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);

        //build foodLikesPanel
        foodLikesPanel.add(foodRating);
        foodLikesPanel.add(foodRating2);
        foodLikesPanel.add(foodRating3);
        foodLikesPanel.add(foodRating4);
        foodLikesPanel.setVisible(true);
        //BUILD foodPrefsPanel
        foodPrefsLayout.setHorizontalGroup(
            foodPrefsLayout.createSequentialGroup()
                .addGroup(foodPrefsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(msg2, 450,450,450)
                    .addComponent(foodListPanel, 75,75,75)
                ) 
                .addComponent(foodLikesPanel, 75,75,75)
        );
        foodPrefsLayout.setVerticalGroup(
            foodPrefsLayout.createSequentialGroup()
                .addComponent(msg2, 25,25,25)
                .addGroup(foodPrefsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(foodListPanel, 100,100,100)
                    .addComponent(foodLikesPanel, 110,110,110)
                )
        );
        foodPrefsPanel.setVisible(true);

        //COMPONENTS FOR exPrefsPanel
        JTextArea msg3 = new JTextArea("Please mark if you dislike (1), like (3)"
                + "or are Ok (2) with these exercises");
        msg3.setEditable(false);
        
        //components for exListPanel
        ex1.setText("push ups");
        ex1.setEditable(false);
        ex2.setText("sit ups");
        ex2.setEditable(false);
        ex3.setText("jogging");
        ex3.setEditable(false);
        ex4.setText("pilates");
        ex4.setEditable(false);
        
        //build exListPanel
        exListPanel.add(ex1);
        exListPanel.add(ex2);
        exListPanel.add(ex3);
        exListPanel.add(ex4);
        exListPanel.setVisible(true);

        //components for exLikesPanel
        //indiviudal ratings for each exercise
        String[] exRatings = {"1","2","3"};
        JComboBox exRating = new JComboBox(exRatings);
        exRating.setSelectedIndex(0);
        exRating.setEditable(false);
        String[] exRatings2 = {"1","2","3"};
        JComboBox exRating2 = new JComboBox(exRatings2);
        exRating2.setSelectedIndex(0);
        exRating2.setEditable(false);
        String[] exRatings3 = {"1","2","3"};
        JComboBox exRating3 = new JComboBox(exRatings3);
        exRating3.setSelectedIndex(0);
        exRating3.setEditable(false);
        String[] exRatings4 = {"1","2","3"};
        JComboBox exRating4 = new JComboBox(exRatings4);
        exRating4.setSelectedIndex(0);
        exRating4.setEditable(false);
        
        //build exLikesPanel
        exLikesPanel.add(exRating);
        exLikesPanel.add(exRating2);
        exLikesPanel.add(exRating3);
        exLikesPanel.add(exRating4);
        exLikesPanel.setVisible(true);      
 
        //BUILD exPrefsPanel
        exPrefsLayout.setHorizontalGroup(
            exPrefsLayout.createSequentialGroup()
                .addGroup(exPrefsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(msg3, 450,450,450)
                    .addComponent(exListPanel, 75,75,75)
                ) 
                .addComponent(exLikesPanel, 75,75,75)
                .addGroup(exPrefsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(save)
                .addComponent(back)
                )
        );
        exPrefsLayout.setVerticalGroup(
            exPrefsLayout.createSequentialGroup()
                .addComponent(msg3, 25,25,25)
                .addGroup(exPrefsLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(exListPanel, 100,100,100)
                    .addComponent(exLikesPanel, 110,110,110)
                    .addComponent(save)
                )
                .addComponent(back)
        );
        exPrefsPanel.setVisible(true);
        
        enterPrefsPanel.add(foodChoicesPanel);
        enterPrefsPanel.add(foodPrefsPanel);
        enterPrefsPanel.add(exPrefsPanel);
        
        mainPanel.add(enterPrefsPanel);
        enterPrefsPanel.setVisible(true);
        repaint();
    }            
    
    public void showGiveFeedbackScreen(){                        //
        menuPanel.setVisible(false);
        giveFeedbackPanel.setLayout(new GridLayout(2,1,10,10));
        //giveFeedbackPanel.setLayout(new BoxLayout(giveFeedbackPanel, BoxLayout.PAGE_AXIS));
                /**
                 * JPanel listPane = new JPanel();
                    listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
                 */
        //set layouts of giveFeedbackPanel's two interal panels
        GroupLayout rateMealLayout = new GroupLayout(rateMealPanel);
        rateMealPanel.setLayout(rateMealLayout);
        GroupLayout rateExLayout = new GroupLayout(rateExPanel);
        rateExPanel.setLayout(rateExLayout);
        //panel to display the list of foods that will be rated...
        foodListPanel.setLayout(new BoxLayout(foodListPanel, BoxLayout.PAGE_AXIS));
        //panel to enter ratings and portions for each individual food
        GroupLayout ratingLayout = new GroupLayout(ratingPanel);
        ratingPanel.setLayout(ratingLayout);
        
        rateMealPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        rateExPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
        
        //components for rateMealPanel
        JTextArea msg = new JTextArea("Rate A Meal");
        msg.setEditable(false);
        
        String[] dayStrings = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        JComboBox dayList = new JComboBox(dayStrings);
        dayList.setSelectedIndex(0);
        dayList.setEditable(false);
        
        String[] mealStrings = {"Breakfast", "Lunch", "Dinner"};
        JComboBox mealList = new JComboBox(mealStrings);
        mealList.setSelectedIndex(0);
        mealList.setEditable(false);
        
        //overall meal rating drop down menu
        String[] ratings = {"1","2","3","4","5"};
        JComboBox rating = new JComboBox(ratings);
        rating.setSelectedIndex(0);
        rating.setEditable(false);
        
        //indiviudal ratings for each food
        String[] foodRatings = {"1","2","3"};
        JComboBox foodRating = new JComboBox(foodRatings);
        foodRating.setSelectedIndex(0);
        foodRating.setEditable(false);
        String[] foodRatings2 = {"1","2","3"};
        JComboBox foodRating2 = new JComboBox(foodRatings2);
        foodRating2.setSelectedIndex(0);
        foodRating2.setEditable(false);
        String[] foodRatings3 = {"1","2","3"};
        JComboBox foodRating3 = new JComboBox(foodRatings3);
        foodRating3.setSelectedIndex(0);
        foodRating3.setEditable(false);
        String[] foodRatings4 = {"1","2","3"};
        JComboBox foodRating4 = new JComboBox(foodRatings4);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);
        String[] foodRatings5 = {"1","2","3"};
        JComboBox foodRating5 = new JComboBox(foodRatings5);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);
        String[] foodRatings6 = {"1","2","3"};
        JComboBox foodRating6 = new JComboBox(foodRatings6);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);
        String[] foodRatings7 = {"1","2","3"};
        JComboBox foodRating7 = new JComboBox(foodRatings7);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);
        String[] foodRatings8 = {"1","2","3"};
        JComboBox foodRating8 = new JComboBox(foodRatings8);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);
        String[] foodRatings9 = {"1","2","3"};
        JComboBox foodRating9 = new JComboBox(foodRatings9);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);
        String[] foodRatings10 = {"1","2","3"};
        JComboBox foodRating10 = new JComboBox(foodRatings10);
        foodRating4.setSelectedIndex(0);
        foodRating4.setEditable(false);
        
        //rating portion size
        String[] portions = {"1","2","3"};
        JComboBox portion = new JComboBox(portions);
        portion.setSelectedIndex(0);
        portion.setEditable(true); //add case to check for user error
        String[] portions2 = {"1","2","3"};
        JComboBox portion2 = new JComboBox(portions2);
        portion2.setSelectedIndex(0);
        portion2.setEditable(true);
        String[] portions3 = {"1","2","3"};
        JComboBox portion3 = new JComboBox(portions3);
        portion3.setSelectedIndex(0);
        portion3.setEditable(true);
        String[] portions4 = {"1","2","3"};
        JComboBox portion4 = new JComboBox(portions4);
        portion4.setSelectedIndex(0);
        portion4.setEditable(true);
        
        //foods
        foods = manager.getFoodList();
        
        food1 .setText(foods[0]);
        food1.setEditable(false);
        food2.setText(foods[1]);
        food2.setEditable(false);
        food3.setText(foods[2]);
        food3.setEditable(false);
        food4.setText(foods[3]);
        food4.setEditable(false);

        //build foodListPanel
        foodListPanel.add(food1);
        foodListPanel.add(food2);
        foodListPanel.add(food3);
        foodListPanel.add(food4);
        
        //build ratingPanel
        ratingLayout.setHorizontalGroup(
            ratingLayout.createSequentialGroup()
                .addGroup(ratingLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(foodRating, 75,75,75)
                    .addComponent(foodRating2, 75,75,75)
                    .addComponent(foodRating3, 75,75,75)
                    .addComponent(foodRating4, 75,75,75)
                )       
                .addGroup(ratingLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(portion, 75,75,75)
                    .addComponent(portion2, 75,75,75)
                    .addComponent(portion3, 75,75,75)
                    .addComponent(portion4, 75,75,75)
                )
        );
        ratingLayout.setVerticalGroup(
            ratingLayout.createSequentialGroup()
                .addGroup(ratingLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(foodRating, 25,25,25)
                    .addComponent(portion, 25,25,25)
                )       
                .addGroup(ratingLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(foodRating2, 25,25,25)
                    .addComponent(portion2, 25,25,25)
                )
                .addGroup(ratingLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(foodRating3, 25,25,25)
                    .addComponent(portion3, 25,25,25)
                )
                .addGroup(ratingLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(foodRating4, 25,25,25)
                    .addComponent(portion4, 25,25,25)
                )
        ); 
        ratingPanel.setVisible(true);
        
        //build rateMealPanel
        rateMealLayout.setHorizontalGroup(
            rateMealLayout.createSequentialGroup()
                .addGroup(rateMealLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(msg, 150,150,150)
                    .addComponent(dayList, 150,150,150)
                    .addComponent(foodListPanel, 150,150,150)
                )
                .addGroup(rateMealLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(mealList, 150,150,150)
                    .addComponent(ratingPanel, 150,150,150)
                )
                .addComponent(rating, 75,75,75)
        );
        rateMealLayout.setVerticalGroup(
            rateMealLayout.createSequentialGroup()
                .addGroup(rateMealLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(msg, 25,25,25)
                )
                .addGroup(rateMealLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(dayList, 25,25,25)
                    .addComponent(mealList, 25,25,25)
                    .addComponent(rating, 25,25,25)
                )
                .addGroup(rateMealLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(foodListPanel, 130,130,130)
                    .addComponent(ratingPanel, 130,130,130)
                )
        );
        rateMealPanel.setVisible(true);

        //components for rateExPanel
        JTextArea msgEx = new JTextArea("Rate an Exercise");
        msgEx.setEditable(false);
        
        String[] dayStringsEx = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        dayListEx = new JComboBox(dayStrings);
        dayListEx.setSelectedIndex(0);
        dayListEx.setEditable(false);
        
        String[] ratingsEx = {"1","2","3"};
        ratingEx = new JComboBox(ratingsEx);
        ratingEx.setSelectedIndex(0);
        ratingEx.setEditable(false);
        
        //feedback for if the exercise was completed
        String[] finishEx = {"yes","no"};
        finishedEx = new JComboBox(finishEx);
        finishedEx.setSelectedIndex(0);
        finishedEx.setEditable(false);
        
        //exercise example
        exercise = new JTextArea("Jogging");
        exercise.setEditable(false);
        
        //build rateExPanel
        rateExLayout.setHorizontalGroup(
            rateExLayout.createSequentialGroup()
                .addGroup(rateExLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(msgEx, 150,150,150)
                    .addComponent(dayListEx, 150,150,150)
                    .addComponent(exercise, 150,150,150)
                )
                .addComponent(finishedEx, 75,75,75)
                .addComponent(ratingEx, 75,75,75)
                .addGroup(rateExLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(save)
                    .addComponent(back)
                )
        );
        rateExLayout.setVerticalGroup(
            rateExLayout.createSequentialGroup()
                .addComponent(msgEx, 25,25,25)
                .addComponent(dayListEx, 25,25,25)   
                .addGroup(rateExLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(exercise, 25,25,25) 
                    .addComponent(finishedEx, 25,25,25)
                    .addComponent(ratingEx, 25,25,25)
                    .addComponent(save)    
                )
                .addComponent(back)
        );
        rateExPanel.setVisible(true);
        
        //add two internal panels to giveFeedbackPanel
        giveFeedbackPanel.add(rateMealPanel);
        giveFeedbackPanel.add(rateExPanel);
        
        mainPanel.add(giveFeedbackPanel);
        giveFeedbackPanel.setVisible(true);
        repaint();
    }
      
    public static void main(String[] args) throws FileNotFoundException {
        new FamilyMemberGUI("Health and Fitness Family Trainer");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == login){        //handles login by checking username.
            String input = userNameField.getText();
            if(manager.checkLogin(input)){
                //login ok
                
                showMainScreen();
            }
            else{
                //message: not a username
                loginError.showMessageDialog(myObj, "Incorrect username",
                        "Please try again.", loginError.PLAIN_MESSAGE);
            }
        }
        if(source == viewProg){     showViewProgScreen();}
        if(source == enterProg){    showEnterProgScreen();}
        if(source == enterPrefs){   showEnterPrefScreen();}
        if(source == giveFdbck){    showGiveFeedbackScreen();}
        if(source == back){         showMainScreen();}
        if(source == mpOptions){    
            //redirect to Meal Planner GUI
            setVisible(false);
            try {
                new mealp("Meal Planner");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
//            mpgui.setVisible(true);
        }
        if(source == ptOptions){
            //redirect to show Personal Trainer GUI
            
            ptgui.setVisible(true);
            dispose();
            
        }
        if(source == dayListEx) {
            String[] exerName = ptgui.manager.getPlan(this.manager.current+1);
            exercise.setText(exerName[dayListEx.getSelectedIndex()]);
        }
        if(source == save){
            confirm.showMessageDialog(myObj, "Your information has been saved",
                    "Information Saved", confirm.PLAIN_MESSAGE);
            //depending on active panels update things...
            if(enterProgPanel.isVisible()){         
                try {
//                update currentWeight, update weight lost/gained, write to file
                manager.recordProgress(enterWeight.getText());
                System.out.println("currentweight"+manager.currentUser.getCurrentWeight());
                manager.saveUsers();
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(enterPrefsPanel.isVisible()){
                try {
                    /**
                     * Enter Preferences and Feedback is open
                     * pt1 -dietary restrictions
                     *      grab and save values in arr
                     *      edit the feedback message and update file.
                     * pt2 -foodPrefs
                     *      grab and save prefs for foods...
                     *          ^ aboutFood.getFoods() returns null...
                     *      display all foods. <- getFoods() returns array of strings
                     * pt3 -exPrefs
                     *      grab and save prefs for exercises...
                     *      display all exercises. <--ArrayList<Exercise> exercises .getName();
                     */
                    for(int i = 0; i < manager.users.size(); i++){
                        manager.foodRatingArr[i][0] = Double.parseDouble((String) foodRating.getSelectedItem());
                        manager.foodRatingArr[i][1] = Double.parseDouble((String) foodRating2.getSelectedItem());
                        manager.foodRatingArr[i][2] = Double.parseDouble((String) foodRating3.getSelectedItem());
                        manager.foodRatingArr[i][3] = Double.parseDouble((String) foodRating4.getSelectedItem());
                        manager.saveFoodRatings();
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    manager.saveExRatings();
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(giveFeedbackPanel.isVisible()){
                ptgui.manager.exercises.get(manager.current+1).setRating((String)ratingEx.getSelectedItem(), manager.current+1);
                ptgui.manager.exercises.get(manager.current+1).setCompletion((String)finishedEx.getSelectedItem(), manager.current+1);
            }
        }
        if(source == getFeedbackButton){
            encourage.showMessageDialog(myObj, "You're doing a great job!",
                    "Keep up the good work", encourage.PLAIN_MESSAGE);
        }
    }
}

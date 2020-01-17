package mealp;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.*;
public class mealp extends JFrame implements ActionListener {
	//Meal
	int day;
	int course;
	//Lists
	JList food=new JList();
	JList meal=new JList();
	JComboBox foodList; 
	JComboBox mealList;
	JComboBox dayList;
    //Buttons
    JButton planWeek;
    JButton viewInfo;
    JButton oldMeal;
    JButton foodNut;
    JButton ptOptions;
    JButton mpOptions;
    JButton backButton=new JButton("Back");
    JButton backPlanButton=new JButton("Back");
    JButton planSubmitButton=new JButton("Submit");
    JButton planSaveButton=new JButton("Save");
    JButton addButton;
    JButton removeButton;
    JButton homeButton;
    //Panels
    JPanel menuPanel;
    JPanel mainPanel;
    JPanel basePanel;
    JPanel planningPanel=new JPanel();
    JPanel infoPanel=new JPanel();
    JPanel oldMealPanel=new JPanel();
    JPanel foodInfoPanel=new JPanel();
    JPanel submitMealPanel=new JPanel();
    //Outside Java class
    mealMiddleLayer control;    
    
    
    public mealp(String str)throws FileNotFoundException{
        //new JFrame(str); //title text at top of window 
        super(str);
        control = new mealMiddleLayer();
        backButton.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //closes window and ends program
        setMinimumSize(new Dimension(500,500));
        setResizable(true); //prohibits the user from changing window size
        setLocationRelativeTo(null); //sets location to center of screen

        //JPanel for entire window
        basePanel = new JPanel(new BorderLayout());

        mainPanel = new JPanel(); //right side panel
        menuPanel = new JPanel(new GridLayout(2,2,2,2));      
        setContentPane(basePanel); 
        
        BoxLayout mainbox = new BoxLayout(mainPanel, BoxLayout.X_AXIS);
        mainPanel.setLayout(mainbox);
        
        planWeek = new JButton("Plan Week of Meals");
        planWeek.addActionListener(this);
        
        viewInfo = new JButton("View Family Members information");
        viewInfo.addActionListener(this);
        oldMeal = new JButton("View Old Meals");
        oldMeal.addActionListener(this);
        
        foodNut = new JButton("View Food Nutrition");
        foodNut.addActionListener(this);
        
        homeButton = new JButton("Back to Family Member");
        homeButton.addActionListener(this);

        menuPanel.setBorder(BorderFactory.createEmptyBorder(35,35,35,35));
        //adds 4 buttons to the menu panel
        menuPanel.add(viewInfo);
        menuPanel.add(planWeek);
        menuPanel.add(homeButton);
        menuPanel.add(oldMeal);
        menuPanel.add(foodNut);
        
        
        mainPanel.add(menuPanel);

        basePanel.add(mainPanel, BorderLayout.CENTER);  
        setVisible(true);
        pack(); //"compiles" window
        
    }    
    public static void main(String[] args) throws FileNotFoundException {
        new mealp("hello");
    }
    public void showPlanWeekScreen(){
    	   //backButton.addActionListener(this);
           mainPanel.setVisible(false);
           planningPanel=new JPanel();
           GroupLayout layout=new GroupLayout(planningPanel);
           planningPanel.setLayout(layout);
           
           String[] dayStrings = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
           dayList = new JComboBox(dayStrings);
           dayList.setSelectedIndex(0);
           dayList.setEditable(false);
           dayList.addActionListener(this);
           
           String[] mealStrings = {"Breakfast", "Lunch", "Dinner"};
           mealList = new JComboBox(mealStrings);
           mealList.setSelectedIndex(0);
           mealList.setEditable(false);
           mealList.addActionListener(this);
           
           planSubmitButton.addActionListener(this);
           
           layout.setHorizontalGroup(
        		   layout.createSequentialGroup()
        		   		.addComponent(dayList, 200, 200, 200)
        		   		.addComponent(mealList, 200, 200, 200)
	        		   	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
	        		   			.addComponent(backButton,50,80,500)
	        		   			.addComponent(planSubmitButton,50,80,500))	        		   		
        	);
           				
           layout.setVerticalGroup(
        		   layout.createSequentialGroup()
        		   		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        		   				.addComponent(dayList,20,20,20)
        		   				.addComponent(mealList,20,20,20)
        		   				.addComponent(backButton,20,20,20))
        		   		.addComponent(planSubmitButton,20,20,20)
        		   		
            );
           
           basePanel.add(planningPanel);
           planningPanel.setVisible(true);
           repaint();
    }   
    public void showViewInfoScreen() throws FileNotFoundException{
    	mainPanel.setVisible(false);
    	infoPanel=new JPanel();
        GroupLayout layout=new GroupLayout(infoPanel);
        infoPanel.setLayout(layout);
        
        FamilyMemberManager soph=new FamilyMemberManager();
        soph.importFile();
        
        String[] family=new String[3];
        int count=0;
        for(FamilyMember x:soph.users)
        {
        	family[count]=x.getName();
        	//System.out.println(x.getName());
        	count++;
        }
        JComboBox familyList = new JComboBox(family);
        familyList.setSelectedIndex(0);
        familyList.setEditable(false);
        familyList.addActionListener(this);
        
        
        System.out.println(soph.users.size());
        JTextArea text=new JTextArea(soph.users.get(0).exercisePrefs+"\n\n"+soph.users.get(0).foodPrefs);
    	//text.append(" test");
    	JScrollPane scrollPane = new JScrollPane(text);
    	text.setEditable(false);
        
        layout.setHorizontalGroup(
        		layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(familyList,200,200,200)
        					.addComponent(text))
        			.addComponent(backButton,100,100,100)        			
        );
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        				.addComponent(familyList,20,20,20)
        				.addComponent(backButton))
        			.addComponent(text)
        );
        basePanel.add(infoPanel);
        infoPanel.setVisible(true);
        repaint();
    }    
    public void showOldMealScreen(int ch){
    	mainPanel.setVisible(false);
    	oldMealPanel=new JPanel();
    	GroupLayout layout=new GroupLayout(oldMealPanel);
    	oldMealPanel.setLayout(layout);
    	String m[]=new String[3];
    	Arrays.fill(m, "");
    	for(int cour=0;cour<3;cour++)
    	{
    		for(int day=0;day<7;day++)
    		{
    			for(String x:control.meals.meals[day][cour])
    			{
    				m[cour]+="\t"+x+"\n";
    			}
    			if(control.meals.meals[day][cour].length>0)
    			{
	    			m[cour]+="Meal Rating: 4.5";
	    			m[cour]+="\n\n\n";
    			}
    		}
    	}
    	JTextArea text=new JTextArea(m[0]);
    	switch(ch)
    	{
    	case 0:text=new JTextArea(m[0]);
    		break;
    	case 1:text=new JTextArea(m[1]);
    		break;
    	case 2:text=new JTextArea(m[2]);
    		break;
    	}
    	
    	JScrollPane scrollPane = new JScrollPane(text);
    	text.setEditable(false);
    	
    	String[] mealStrings = {"Breakfast", "Lunch", "Dinner"};
        
    	mealList = new JComboBox(mealStrings);
        mealList.setSelectedIndex(ch);
        mealList.setEditable(false);
        mealList.addActionListener(this);
    	
    	layout.setHorizontalGroup(
    			layout.createSequentialGroup()
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
    						.addComponent(mealList,200,200,200)
    						.addComponent(text)
    						)
    				.addComponent(backButton)
    	);
    	layout.setVerticalGroup(
    			layout.createSequentialGroup()
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    					.addComponent(mealList,20,20,20)
    					.addComponent(backButton))	
    				.addComponent(text)
    	);
    	basePanel.add(oldMealPanel);
    	oldMealPanel.setVisible(true);
    	repaint();    	
    }
    public void showNutritionScreen(int index){
    	mainPanel.setVisible(false);
    	foodInfoPanel=new JPanel();
    	GroupLayout layout=new GroupLayout(foodInfoPanel);
    	foodInfoPanel.setLayout(layout);
    	
    	//String[] foodStrings = {"Apple", "Orange", "Cucumber", "Lard", "Cake"};
        foodList = new JComboBox(control.info.name);
        foodList.setSelectedIndex(index);
        foodList.setEditable(false);
        foodList.addActionListener(this);

        JTextArea text=new JTextArea(control.info.name[index]+
        		"\nServing Size: "+control.info.servSize[index]+
        		"\nCalories: "+control.info.calories[index]+
        		"\nRating 1: "+control.info.rating[0][index]+
        		"\nRating 2: "+control.info.rating[1][index]+
        		"\nRating 3: "+control.info.rating[2][index]);
    	JScrollPane scrollPane = new JScrollPane(text);
    	text.setEditable(false);
    	
    	//GroupLayout lay=new
        
        layout.setHorizontalGroup(
        		layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        					.addComponent(foodList,200,200,200)
        					.addComponent(text))
        			.addComponent(backButton,100,100,100)
        );
        layout.setVerticalGroup(
        		layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
        				.addComponent(foodList,20,20,20)
        				.addComponent(backButton))
        			.addComponent(text)
        );
        basePanel.add(foodInfoPanel);
        foodInfoPanel.setVisible(true);    
        repaint();
    }
    public void submitMealScreen()
    {
    	mainPanel.setVisible(false);
    	planningPanel.setVisible(false);
    	submitMealPanel=new JPanel();
    	backPlanButton=new JButton("Back");
    	backPlanButton.addActionListener(this);
    	GroupLayout layout=new GroupLayout(submitMealPanel);
    	submitMealPanel.setLayout(layout);
    	
    	//String[] foodStrings = {"Food Options","Apple", "Orange", "Cucumber", "Lard", "Cake"};
    	food=new JList(control.info.name);
    	JScrollPane listPane = new JScrollPane(food);
    	
    	//DefaultListModel listModel=new DefaultListModel();
    	//listModel.addElement("Current Meal");
    	//listModel.addElement("Pizza");
    	meal=new JList(control.meals.meals[day][course]);
    	JScrollPane mealPane = new JScrollPane(meal);
    	
    	addButton=new JButton("Add to meal");
    	addButton.addActionListener(this);
    	
    	removeButton=new JButton("Remove from meal");
    	removeButton.addActionListener(this);
    	
    	planSaveButton.addActionListener(this);
    	
    	JPanel buttonsPanel=new JPanel();
    	GroupLayout lay=new GroupLayout(buttonsPanel);
    	buttonsPanel.setLayout(lay);
    	lay.setHorizontalGroup(
    			lay.createSequentialGroup()
    				.addGroup(lay.createParallelGroup(GroupLayout.Alignment.CENTER)
    						.addComponent(backPlanButton)
    						.addComponent(removeButton)
    						.addComponent(planSaveButton))
    	);
    	lay.setVerticalGroup(
    			lay.createSequentialGroup()
    				.addComponent(backPlanButton)
    				.addComponent(removeButton)
    				.addComponent(planSaveButton)
    	);
    	layout.setHorizontalGroup(
    			layout.createSequentialGroup()
    				.addComponent(listPane)
    				.addComponent(addButton)
    				.addComponent(mealPane,200,200,200)
    				.addComponent(buttonsPanel)
    	);
    	layout.setVerticalGroup(
    			layout.createSequentialGroup()
    				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    						.addComponent(listPane)
    						.addComponent(addButton)
    						.addComponent(mealPane)
    						.addComponent(buttonsPanel))
    	);
    	lay.linkSize(removeButton,backPlanButton,planSaveButton);
    	basePanel.add(submitMealPanel);
    	submitMealPanel.setVisible(true);
    	repaint();
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == planWeek)
            showPlanWeekScreen();
        else if(source == viewInfo)
			try {
				showViewInfoScreen();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		else if(source == oldMeal)
            showOldMealScreen(0);
        else if(source == foodNut)
            showNutritionScreen(0);
        else if(source == planSubmitButton)   
        {
        	submitMealScreen();
        }
        else if(source == backButton)
        {
        	foodInfoPanel.setVisible(false);
        	planningPanel.setVisible(false);
        	infoPanel.setVisible(false);
        	oldMealPanel.setVisible(false);
        	submitMealPanel.setVisible(false);
        	mainPanel.setVisible(true);
        }
        else if(source == backPlanButton)
        {
        	submitMealPanel.setVisible(false);
        	showPlanWeekScreen();
        }
        else if(source == removeButton)
        {
        	//System.out.println(meal.getSelectedValue());
        	ArrayList<String>temp=new ArrayList<String>(Arrays.asList(control.meals.meals[day][course]));
        	temp.remove(meal.getSelectedIndex());
        	control.meals.meals[day][course]=temp.toArray(new String[temp.size()]);
        	submitMealPanel.setVisible(false);
        	submitMealScreen();
        	
        }        
        else if(source == addButton)
        {
        	ArrayList<String>temp=new ArrayList<String>(Arrays.asList(control.meals.meals[day][course]));
        	temp.add(food.getSelectedValue().toString());
        	control.meals.meals[day][course]=temp.toArray(new String[temp.size()]);
        	submitMealPanel.setVisible(false);
        	submitMealScreen();
        	
        }
        else if(source == foodList)
        {
        	//System.out.println(foodList.getSelectedIndex());
        	foodInfoPanel.setVisible(false);
        	showNutritionScreen(foodList.getSelectedIndex());
        }
        else if(source == mealList)
        {
        	course = mealList.getSelectedIndex();
        	//System.out.println(course);
        	if(oldMealPanel.isVisible())
        	{
        		oldMealPanel.setVisible(false);
        		showOldMealScreen(course);
        	}
        }
        else if(source == dayList)
        {
        	day = dayList.getSelectedIndex();
        }
        else if(source == planSaveButton)
        {
        	try {
				control.meals.setMealPlan(control.meals.meals);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.out.println("break");
			}
        }
        else if(source == homeButton)
        {
        	try {
        		setVisible(false);
				new FamilyMemberGUI("Health and Fitness Family Trainer");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
    }
}
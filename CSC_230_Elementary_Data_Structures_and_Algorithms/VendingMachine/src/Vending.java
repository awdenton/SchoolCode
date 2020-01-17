
import java.util.Scanner;

public class Vending{
    
//PROVIDED
    public static void main(String[] args) {
        VendingMachine chaChing = new VendingMachine();
        chaChing.run();
    }
}

/**********************************************************/

class VendingMachine {

    //PROVIDED
    private Dispenser snackMachine;
    private Coinbox moneyBox;

    //PROVIDED
    public VendingMachine() {
        snackMachine = new Dispenser();
        moneyBox = new Coinbox(0, 3, 3);
    }

    //PROVIDED
    public void run() {
        Scanner kybd = new Scanner(System.in);
        boolean quit = false;
        do {
            showUserChoice();
            String choice = kybd.next();
            if (choice.equals("BOSS")) {
                quit = this.bossWork();
            } else {
                serviceCustomer(choice.charAt(0));
            }
        } while (!quit);
    }

    /**
     * Show the user the products, the coinbox menu, and tell the
     * user what to do
     */
    private void showUserChoice() {
        //Display the products
        System.out.println(snackMachine.toString());
        //Display the coinbox menu
        moneyBox.displayCoins();
        //Tell the user what to do
        System.out.print("Enter money first then select product: ");
    }
       
    /**
     * Take money from the user, then process product selection
     * @param choice 
     */
    private void serviceCustomer(char choice) {
        //While user is putting in coins, allow him to put in more coins
        //until the user does something else.
        //First set up a scanner to take in additional inputs if neccesary.
        Scanner kybd = new Scanner(System.in);
        while(moneyBox.option(choice)){
            choice = kybd.next().charAt(0);
        }
        //Try to dispense an item for the user. Make sure enough money has
        //been deposited for the item
        if(moneyBox.getAmount() >= (snackMachine.getPrice(choice)*100)){
            //If an item is succesfully dispensed, returnChange()
            if(snackMachine.dispense(choice) == 1)
                moneyBox.giveChange((int) (moneyBox.getAmount() - (snackMachine.getPrice(choice)*100)));
        }
    }
        
    /**
     * Gives the BOSS menu. Processes boss choices while choice is not
     * Shutdown or Start Machine. Return true for shutdown option, false for
     * start machine option
     * @return 
     */    
    private boolean bossWork() {
        //Setup a scanner to allow BOSS to make a sugestion
        Scanner kybd = new Scanner(System.in);
        int choice;
        //Display the BOSS menu. Run until BOSS chooses (6) to start machine
        do {
            System.out.println("WELCOME BOSS");
            System.out.println("1\tAdd Products");
            System.out.println("2\tRestock Products");
            System.out.println("3\tChange Price");
            System.out.println("4\tRemove Product");
            System.out.println("5\tShutdown");
            System.out.println("6\tStart Machine");
            System.out.print("BOSS - enter your selection: ");
            choice = kybd.nextInt();
            //Perform the function BOSS selects
            switch(choice){
                case 1: snackMachine.setUpDispenser();
                        break;
                case 2: snackMachine.restockProduct();
                        break;
                case 3: snackMachine.changePrice();
                        break;
                case 4: snackMachine.deleteProduct();
                        break;
                case 5: return true;
            }
        } while(choice != 6);
        return false;
    }
        
}


/**********************************************************/


class Product {
    private double price;
    private int quantity;
    private String name;
    
    /**
     * No arg constructor, initializes price and quantity to 0, sets an empty name
     */
    public Product(){
        price = 0;
        quantity = 0;
        name = "";
    }
    
    /**
     * Constructs a product
     * @param n product name (String)
     * @param p product price (double)
     * @param q product quantity (int)
     */
    public Product(String n, double p, int q){
        this.price = p;
        this.quantity = q;
        this.name = n;
    }
    
    /**
     * Returns product name
     * @return 
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Returns product price
     * @return 
     */
    public double getPrice(){
        return this.price;
    }
    
    /**
     * Returns product quantity
     * @return 
     */
    public int getQuantity(){
        return this.quantity;
    }
    
    /**
     * Change product name
     * @param n new name (String)
     */
    public void setName(String n){
        this.name = n;
    }
    
    /**
     * Change product price
     * @param p new price (double)
     */
    public void setPrice(double p){
        this.price = p;
    }
    
    /**
     * Change product quantity
     * @param q new quantity (int)
     */
    public void setQuantity(int q){
        this.quantity = q;
    }
    
    /**
     * Displays product name, price, and quantity
     * @return 
     */
    public String toString(){
        return String.format("%s, %.2f, %d", name, price, quantity);
    }
}


/**********************************************************/


class Coinbox {
    
    //PROVIDED
    private int numQ, numD, numN;
    private int amount;
    
    //PROVIDED
    public Coinbox(int Q, int D, int N){
        this.numQ = Q;
        this.numD = D;
        this.numN = N;
        this.amount = 0;
    }
    
    /**
     * First, make sure machine is not in a correct change situation. Then
     * determine the number of coins to return. Amount is reset to 0,
     * and a message shows the coins returned
     * @param change 
     */
    public void giveChange(int change){
        //intialize values for coin return
        int q = 0, d = 0, n = 0;
        //see if correct change is needed
        if(!this.correctChange()){
            //can make change. determine number of quarters needed
            q = change/25;
            //make sure you have enough quarters
            if(q > numQ){
                //if you need more quarters then you have, return all quarters
                //currently in machine
                q = numQ;
            }
            //decrease number of quarters
            //decrease amount of change left to return
            numQ -= q;
            change -= q*25;
            
            //determine number of dimes needed
            d = change/10;
            //make sure you have enough dimes
            if(d > numD){
                d = numD;
            }
            //decrease dimes and anmount of change
            numD -= d;
            change -= d*10;
            
            //determine number of nickels needed. no need to check if there are
            //enough nickels since that check is performed in correctChange
            n = change/5;
            numN -= n;
        }
        //reset amount
        this.amount = 0;
        //display coins to be returned
        System.out.println("Now returning " + q + " quarters, " + d + " dimes, and " + n + " nickels.\n");        
    }
    
    /**
     * If correct change is required, returns true. Otherwise returns false
     * @return 
     */
    public boolean correctChange(){
        //Check if you can return change for a quarter. Needs at least
        //one nickel, and also at least 20 cents in dimes and nickels
        return (numN < 1 || (numD*10 + numN*5) < 20);
    }
    
    /**
     * Displays the coinbox menu
     */
    public void displayCoins(){
        System.out.println("This machine takes quarters, dimes, or nickels.");
        System.out.println("Enter Q, D, N, or R for coin return.");
    }
    
    /**
     * Returns the amount deposited for the current transaction
     * @return 
     */
    public int getAmount(){
        return this.amount;
    }
    
    /**
     * Evaluates user input. If it is a coin (Q, D, N) then takeCoin(),
     * displayAmount(), and returns true. If choice is (R), giveChange()
     * and return false. All other inputs returns false
     * @param choice
     * @return 
     */
    public boolean option(char choice){
        //Check if the input is a coin
        if(choice == 'Q' || choice == 'D' || choice == 'N'){
            //Take in the coin
            this.takeCoin(choice);
            //Display the amount
            this.displayAmount();
            //return true
            return true;
        }
        //see if input if R
        else if(choice == 'R'){
            //give back change, return false
            this.giveChange(amount);
            return false;
        }
        //all other inputs return false
        else
            return false;
    }
    
    /**
     * If user input is (Q, D, N), increments the appropriate coin in the box,
     * and the total amount of the current transaction
     * @param coin 
     */    
    private void takeCoin(char coin){
        //see if input is a quarter
        if(coin == 'Q'){
            //increment number of quarters, update current amount
            numQ++;
            amount += 25;
        }
        //see if input is a dime
        if(coin == 'D'){
            //increment number of dimes, update current amount
            numD++;
            amount += 10;
        }
        //see if input is a nickel
        if(coin == 'N'){
            numN++;
            amount += 5;
        }
    }
    
    /**
     * Displays the total amount deposited for the current transaction
     */
    private void displayAmount(){
        System.out.println("Total deposited = " + amount + " cents.");
    }
}


/**********************************************************/


class Dispenser {
    private Product[] items;
    private int numItems;
    
    /**
     * Creates a new Dispenser, sets product array size to 5 and numItems to 0
     */
    public Dispenser(){
        items = new Product[5];
        numItems = 0;
    }
    
    /**
     * Displays the items in the product array in a numbered list
     * @return 
     */
    public String toString(){
        String result = "";
        //iterate through the array
        for(int i = 0; i < numItems; i++){
            result += (i+1) + ") (" + items[i].toString() + ")\n";
        }
        return result;
    }
    
    /**
     * Returns true if input is is a valid item choice, false if not
     * @param choice
     * @return 
     */
    public boolean option(char choice){
        //determine if the input is a valid option. 49 is the unicode value
        //for 1, so we check around that value for the choice char. This usage
        //of the char unicode values is seen throughout the class
        return choice >= 49 && choice <= (48+numItems);
    }
    
    /**
     * If there is room for more products, allows the BOSS to enter a new
     * product, places it in the dispenser, and increments numItems
     */
    public void setUpDispenser(){
        //check if their is room in the dispenser
        if(numItems < items.length){
            //Ask for the product details
            System.out.print("Enter product name price quantity: ");
            //Setup a scanner to read in the product details
            Scanner kybd = new Scanner(System.in);
            String name = kybd.next();
            double price = kybd.nextDouble();
            int quantity = kybd.nextInt();
            //create new Prodcut in last spot in the product array
            items[numItems] = new Product(name, price, quantity);
            //increase number of items
            numItems++;
        }
    }
    
    /**
     * Display the products, and allow BOSS to change the price of any item
     * in the dispenser
     */
    public void changePrice(){
        //display the products
        System.out.println(this.toString());
        //setup a scanner to allow BOSS to choose an item and input a new price
        Scanner kybd = new Scanner(System.in);
        System.out.print("Enter the item #: ");
        char choice = kybd.next().charAt(0);
        //make sure BOSS chose a valid item. if he didn't, do nothing
        if(this.option(choice)){
            //take in new price
            System.out.print("Enter new price: ");
            double price = kybd.nextDouble();
            //update price of the item
            items[(int)choice - 49].setPrice(price);
        }
    }
    
    /**
     * Display products, and allow BOSS to add more stock to any item in the
     * dispenser
     */
    public void restockProduct(){
        //display the prodcuts
        System.out.println(this.toString());
        //setup a scanner to allow BOSS to chose an item and add stock
        Scanner kybd = new Scanner(System.in);
        System.out.print("Ener the item #: ");
        char choice = kybd.next().charAt(0);
        //make sure BOSS chose a valid item. if he didn't, do nothing
        if(this.option(choice)){
            //take in quantity to add
            System.out.print("Enter quantity to add: ");
            int quant = kybd.nextInt();
            //update the quantity
            items[(int)choice - 49].setQuantity(items[(int)choice - 49].getQuantity() + quant);
        }
    }
    
    /**
     * Returns the price of the selected product. If choice is invalid,
     * return -1
     * @param choice
     * @return 
     */
    public double getPrice(char choice){
        //check if choice is valid. if valid return item price
        if(this.option(choice))
            return items[(int)choice - 49].getPrice();
        //if choice is invalid return -1
        else
            return -1;
    }
    
    /**
     * If choice is valid and item is in stock, returns true.
     * Otherwise returns false
     * @param choice
     * @return 
     */
    public boolean inStock(char choice){
        return this.option(choice) && items[(int)choice - 49].getQuantity() > 0;
    }
    
    /**
     * If item is a valid choice and in stock, one piece is removed from 
     * dispenser and given to the user, returns 1. If item is out of stock, 
     * or an invalid selection, returns 0
     * @param choice
     * @return 
     */
    public int dispense(char choice){
        //see if choice is valid and in stock
        if(this.inStock(choice)){
            //if it is, give the item to the user
            System.out.println("Here is your " + items[(int)choice - 49].getName());
            //decrease quantity of item
            items[(int)choice - 49].setQuantity(items[(int)choice - 49].getQuantity() - 1);
            return 1;
        }
        //if choice is invalid, or not in stock, return 0
        else
            return 0;
    }
    
    /**
     * Display product and all BOSS to select and remove a product from the
     * dispenser, then decrement numItems
     */
    public void deleteProduct(){
        //display the products
        System.out.println(this.toString());
        //setup a scanner to allow BOSS to choose an item
        Scanner kybd = new Scanner(System.in);
        System.out.print("Enter the item #: ");
        char choice = kybd.next().charAt(0);
        //make sure choice is valid. if it isnt do nothing
        if(this.option(choice)){
            //Use a for loop to slide remaining items over in the array
            //in order to avoid a null pointer exception
            for(int i = (int)choice - 49; i < numItems - 1; i++){
                items[i] = items[i+1];
            }
            //decrement numItems
            numItems--;
        }
    }
}
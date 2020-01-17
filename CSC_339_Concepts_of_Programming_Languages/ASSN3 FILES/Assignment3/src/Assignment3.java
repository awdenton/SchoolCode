import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Assignment3 {
    
    public ArrayList<Student> students;
    private static int choice = 0;
    private static Scanner kybd = new Scanner(System.in);
    
    public Assignment3(){
        students = new ArrayList<>();
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Assignment3 main = new Assignment3();
        kybd.useDelimiter("\\n");
        main.readFile();
        while(choice != 7){
            main.showOptions();
            choice = kybd.nextInt();
            switch(choice){
                case 1: main.createStudent();
                        break;
                case 2: main.modifyStudent();
                        break;
                case 3: Student st = main.getStudent();
                        if(st != null)
                            System.out.println(st.toString());
                        break;
                case 4: main.showStudents();
                        break;
                case 5: main.showStudentsName();
                        break;
                case 6: main.showStudentsGPA();
                        break;
            }
        }
    }
    
    private void showOptions(){
        System.out.println("\nSelect Option");
        System.out.println("1 - Enter Student Information");
        System.out.println("2 - Modify student information");
        System.out.println("3 - Lookup student by ID\n");
        
        System.out.println("4 - Display all students");
        System.out.println("5 - Display all student sorted by Name");
        System.out.println("6 - Display all student sorted by GPA\n");
        
        System.out.println("7 - Exit\n");
    }
    
    public void createStudent(){
        System.out.println("\nInput nine digit ID number:");
        int id = kybd.nextInt();
        System.out.println("Input student name:");
        String name = kybd.next();
        System.out.println("Input student major:");
        String major = kybd.next();
        System.out.println("Input student level:");
        String level = kybd.next();
        System.out.println("Input student GPA:");
        float gpa = kybd.nextFloat();
        System.out.println("Input student credit hours:");
        int creds = kybd.nextInt();
        System.out.println("Input major advisor name:");
        String adv = kybd.next();
        students.add(new Student(id, name, major, level, gpa, creds, adv));        
    }
    
    public void modifyStudent(){
        Student st = getStudent();
        if(st == null)
            return;
        System.out.println("Current Student info:");
        System.out.println(st.toString());
        System.out.println("Input new student major:");
        st.setMajor(kybd.next());
        System.out.println("Input new student level:");
        st.setLevel(kybd.next());
        System.out.println("Input new student GPA:");
        st.setGPA(kybd.nextFloat());
        System.out.println("Input new student credit hours:");
        st.setCredits(kybd.nextInt());
        System.out.println("Input new major advisor name:");
        st.setAdvisor(kybd.next());        
    }
    
    public Student getStudent(){
        System.out.println("\nEnter nine digit Student ID");
        int ident = kybd.nextInt();
        for(int i = 0; i < students.size(); i++){
            if(students.get(i).getID() == ident){
                Student stu = students.get(i);
                return stu;
            }
        }
        System.out.println("Student not found");
        return null;
    }
    
    public void printStudents(){
        for(int i = 0; i < students.size(); i++){
            System.out.println(students.get(i).toString());
        }
    }
    
    public void showStudents(){
        Collections.sort(students, Student.IDComp);
        printStudents();
    }
    
    public void showStudentsName(){
        Collections.sort(students, Student.NameComp);
        printStudents();
    }
    
    public void showStudentsGPA(){
        Collections.sort(students, Student.GPAComp);
        printStudents();
    }
    
    private void readFile() throws FileNotFoundException{
        Scanner read = new Scanner(new File("Student_Record.csv"));
        read.useDelimiter(",|\\n");
        while(read.hasNext()){
            this.students.add(new Student(read.nextInt(), read.next(), read.next(), read.next(), read.nextFloat(), read.nextInt(), read.next()));            
        }
    }
        
}

class Student {
    
    private int id;
    private String name;
    private String major;
    private String level;
    private Float gpa;
    private int credits;
    private String advisor;
    
    public Student(){
        id = 000000000;
        name = "";
        major = "";
        level = "";
        gpa = new Float(0.0);
        credits = 0;
        advisor = "";
    }
    
    public Student(int i, String n, String m, String l, Float g, int c, String a){
        id = i;
        name = n;
        major = m;
        level = l;
        gpa = g;
        credits = c;
        advisor = a;
    }
    
    public void setID(int i){
        this.id = i;
    }
    
    public void setName(String n){
        this.name = name;
    }
    
    public void setMajor(String m){
        this.major = m;
    }
    
    public void setLevel(String l){
        this.level = l;
    }
    
    public void setGPA(float g){
        this.gpa = g;
    }
    
    public void setCredits(int c){
        this.credits = c;
    }
    
    public void setAdvisor(String a){
        this.advisor = a;
    }
    
    public int getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public String getMajor(){
        return major;
    }
    
    public String getLevel(){
        return level;
    }
    
    public float getGPA(){
        return gpa;
    }
    
    public int getCredits(){
        return credits;
    }
    
    public String getAdvisor(){
        return advisor;
    }
    
    public String toString(){
        String result = "ID: " + id + "\nName: " + name + "\nMajor: " + major + 
                "\nLevel: " + level + "\nGPA: " + gpa + "\nCredits: " + credits + 
                "\nAdvisor: " + advisor + "\n";
        return result;
    }    

    public static Comparator<Student> IDComp = new Comparator<Student>() {
        @Override
        public int compare(Student t, Student t1) {
            return t.id - t1.id;
        }        
    };
    
    public static Comparator<Student> NameComp = new Comparator<Student>() {
        @Override
        public int compare(Student t, Student t1) {
            return t.name.compareToIgnoreCase(t1.name);
        }        
    };
    
    public static Comparator<Student> GPAComp = new Comparator<Student>() {
        @Override
        public int compare(Student t, Student t1) {
            return t1.gpa.compareTo(t.gpa);
        }        
    };
}
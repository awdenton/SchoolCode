#include <iostream>
#include <fstream>
#include <string>
using namespace std;

/*
	Define the student data class. Methods are public, fields are private
*/
class Student {
	
	public:
		void setMajor(string m);
		void setLevel(string l);
		void setGPA(string g);
		void setCredits(string c);
		void setAdvisor(string a);
		string getID(void);
		string getName(void);
		string getMajor(void);
		string getLevel(void);
		string getGPA(void);
		string getCredits(void);
		string getAdvisor(void);
		void toString(void);
		Student();
		Student(string i, string n, string m, string l, string g, string c, string a);
	
	private:
		string ident;
		string name;
		string major;
		string level;
		string gpa;
		string credits;
		string advisor;
	
};

/*
	The constructors for Student.
*/
Student::Student(void) {}

Student::Student(string i, string n, string m, string l, string g, string c, string a){
	
	ident = i;
	name = n;
	major = m;
	level = l;
	gpa = g;
	credits = c;
	advisor = a;
	
}

/*
	The next several methods are simply getters and setters for the Student fields
*/
void Student::setMajor(string m) {
	major = m;
}

void Student::setLevel(string l) {
	level = l;
}

void Student::setGPA(string g) {
	gpa = g;
}

void Student::setCredits(string c) {
	credits = c;
}

void Student::setAdvisor(string a) {
	advisor = a;
}

string Student::getID(void) {
	return ident;
}

string Student::getName(void) {
	return name;
}

string Student::getMajor(void) {
	return major;
}

string Student::getLevel(void) {
	return level;
}

string Student::getGPA(void) {
	return gpa;
}

string Student::getCredits(void) {
	return credits;
}

string Student::getAdvisor(void) {
	return advisor;
}

void Student::toString(void) {
	cout << "\nID: " << ident << "\nName: " << name << "\nMajor: " << major << "\nLevel: " << level
		 << "\nGPA: " << gpa << "\nCredits: " << credits << "\nAdvisor: " << advisor << "\n";
}

/*
	Declare an array to hold students, as well as a counter to keep track
	of the current number of students
*/
Student students[10];
int stuCount = 0;

//Simply a method to print out all students
void printStudents(void) {
	for(int i = 0; i < stuCount; i++){
		students[i].toString();
		cout << "\n";
	}
}

/*
	Reads in student data from a .csv and populates it into an array
*/
void readIn(void) {
	ifstream records ("Student_Record.csv");
	string i, n, m, l, g, c, a;
	while(records.good()) {
		getline(records, i, ',');
		getline(records, n, ',');
		getline(records, m, ',');
		getline(records, l, ',');
		getline(records, g, ',');
		getline(records, c, ',');
		getline(records, a, '\n');
		
		Student stu(i, n, m, l, g, c, a);
		students[stuCount] = stu;
		stuCount++;
	}
	records.close();
}

/*
	Allows the user to create a new student
*/
void createStudent(void) {
	cin.ignore();
	string i, n, m, l, g, c, a;
	cout << "Input 9 digit ID number: ";
	getline(cin,i);
	cout << "Input name: ";
	getline(cin,n);
	cout << "Input major: ";
	getline(cin,m);
	cout << "Input level: ";
	getline(cin,l);
	cout << "Input GPA: ";
	getline(cin,g);
	cout << "Input credits: ";
	getline(cin,c);
	cout << "Input advisor: ";
	getline(cin,a);
	
	Student stu(i, n, m, l, g, c, a);
	students[stuCount] = stu;
	stuCount++;
}

/*
	Allows the user to find a specific student by ID number
*/
int getStudent(void){
	cin.ignore();
	string idnum;
	cout << "Input nine digit student ID: ";
	getline(cin, idnum);
	for(int i = 0; i < stuCount; i++){
		if(students[i].getID() == idnum)
			return i;
	}
	cout << "Student not found\n";
	return -1;
}

/*
	Allow a user to modify student info
*/
void modifyStudent(void){
	int pos;
	string m,l,g,c,a;
	pos = getStudent();
	if(pos == -1)
		return;
	cout << "Current student info:";
	students[pos].toString();
	cout << "Input new major: ";
	getline(cin, m);
	students[pos].setMajor(m);
	cout << "Input new level: ";
	getline(cin, l);
	students[pos].setLevel(l);
	cout << "Input new GPA: ";
	getline(cin, g);
	students[pos].setGPA(g);
	cout << "Input new credits: ";
	getline(cin, c);
	students[pos].setCredits(c);
	cout << "Input new advisor: ";
	getline(cin, a);
	students[pos].setAdvisor(a);
}

/*
	A method to sort students by ID number
	I considered using quick sort but in the end went with bubble sort
	mainly because its a lot easier to implement and I'm not overly
	worried about efficiency in this program
*/
void idSort(void){
	for(int i = 1; i < stuCount; i++){
		for(int j = 0; j < stuCount-1; j++){
			if(students[j].getID().compare(students[j+1].getID()) > 0){
				Student temp = students[j];
				students[j] = students[j+1];
				students[j+1] = temp;
			}
		}
	}
}

/*
	Sorts by student name
*/
void nameSort(void){
	for(int i = 1; i < stuCount; i++){
		for(int j = 0; j < stuCount-1; j++){
			if(students[j].getName().compare(students[j+1].getName()) > 0){
				Student temp = students[j];
				students[j] = students[j+1];
				students[j+1] = temp;
			}
		}
	}
}

/*
	Sorts by student GPA
*/
void gpaSort(void){
	for(int i = 1; i < stuCount; i++){
		for(int j = 0; j < stuCount-1; j++){
			if(students[j].getGPA().compare(students[j+1].getGPA()) < 0){
				Student temp = students[j];
				students[j] = students[j+1];
				students[j+1] = temp;
			}
		}
	}
}

/*
	A menu of available options for the user
*/
void showOptions(void) {
	cout << "\nSelect Option";
	cout << "\n1 - Enter student information";
	cout << "\n2 - Modify student information";
	cout << "\n3 - Lookup student by ID";
	
	cout << "\n4 - Display all students";
	cout << "\n5 - Display all students by Name";
	cout << "\n6 - Display all students by GPA";
	
	cout << "\n7 - Exit\n";
}

int main(){
	//Reads in data from a .csv file
	readIn();
	
	int choice = 0;
	
	//Show the options to the user, and continue to display the menu
	//untill the 'Exit' option is selected
	while(choice != 7){
		showOptions();
		cin >> choice;
		switch(choice){
			case 1:
				createStudent();
				break;
			case 2:
				modifyStudent();
				break;
			case 3:
				int pos;
				pos = getStudent();
				if(pos != -1) {
					students[pos].toString();
				}
				break;
			case 4:
				idSort();
				printStudents();
				break;
			case 5:
				nameSort();
				printStudents();
				break;
			case 6:
				gpaSort();
				printStudents();
				break;
		}
	}	
	return 0;
}
import csv

"""
Define a structure for the student data class. Has one constructor and a toString method
"""
class Student:
	def __init__(self, ident, name, major, level, gpa, credit, advisor):
        	self.ident = ident
	        self.name = name
	        self.major = major
	        self.level = level
	        self.gpa = gpa
	        self.credits = credit
	        self.advisor = advisor
	
	def __str__(self):
		return "\nID: " + self.ident + "\nName: " + self.name + "\nMajor: " + self.major + "\nLevel: " + self.level + "\nGPA: " + self.gpa + "\nCredits: " + self.credits + "\nAdvisor: " + self.advisor

#Create a list to hold Students
students = []

#A method to print all Students
def printStudents(stu):
	for x in stu:
		print x

#A lambda expression to sort students by ID
def sortID():
	printStudents(sorted(students, key=lambda student: student.ident))

#A method to sort by name
def sortName():
	printStudents(sorted(students, key=lambda student: student.name))

#A method to sort by GPA
def sortGPA():
	printStudents(sorted(students, key=lambda student: student.gpa))

#Allow a user to create a new student and add it to the student list
def createStudent():
	i = raw_input("Enter nine digit ID number: ")
	n = raw_input("Enter name: ")
	m = raw_input("Enter major: ")
	l = raw_input("Enter level: ")
	g = raw_input("Enter GPA: ")
	c = raw_input("Enter credits: ")
	a = raw_input("Enter advisor: ")
	st = Student(i,n,m,l,g,c,a,)
	students.append(st)

#Lookup a student by ID number	
def lookupStudent():
	i = raw_input("Please enter nine digit ID number: ")
	for x in students:
		if i == x.ident:
			return x
	return "Student not found"

#Allow a user to modify a students info
def modifyStudent():
	stu = lookupStudent()
	print "Current student info:"
	print stu
	stu.major = raw_input("Enter new major: ")
	stu.level = raw_input("Enter new level: ")
	stu.gpa = raw_input("Enter new GPA: ")
	stu.credits = raw_input("Enter new credits: ")
	stu.advisor = raw_input("Enter new advisor: ")
	
#Read in data from a .csv file and populate the Student list	
with open('Student_Record.csv', 'r') as csvfile:
	recordReader = csv.reader(csvfile)
	for row in recordReader:
		stu = Student(row[0], row[1], row[2], row[3], row[4], row[5], row[6])
		students.append(stu)

#Show available options to the user
def showOptions():
	print "\n1 - Enter student information"
	print "2 - Modify student information"
	print "3 - Lookup student by ID"
	print "4 - Display all students"
	print "5 - Display students by name"
	print "6 - Display students by GPA"
	print "7 - Exit"

#Ask the user to select an option, and return to this screen
#untill the 'Exit' option is selected
choice = 0
while int(choice) <> 7:
	showOptions()
	choice = raw_input("Select option: ")
	if choice == '1':
		createStudent()
	elif choice == '2':
		modifyStudent()
	elif choice == '3':
		print lookupStudent()
	elif choice == '4':
		sortID()
	elif choice == '5':
		sortName()
	elif choice == '6':
		sortGPA()

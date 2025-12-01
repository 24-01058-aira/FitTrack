# FitTrack
A User-Centered Fitness and Progress Monitoring System
Overview
FitTrack is a console-based fitness and meal tracking system designed to help users manage their workout routines and dietary plans according to their personal fitness goals. The system allows users to register, log in, input their weight and fitness goals, and receive personalized workout and meal suggestions. Users can select workouts and foods they prefer, creating a customized routine. This system solves the problem of unorganized fitness tracking by providing a structured approach to monitor progress and maintain a balanced exercise and nutrition plan.

OOP Concepts Applied
a. Encapsulation - sensitive data like username, password, weight, and goal in the User class are marked private and access is controlled through getter and setter methods, e.g., checkPassword(), setWeight(), setGoal(). The Food class also encapsulates fields name and type with getters for controlled access.
b. Inheritance - the abstract Workout class serves as a superclass. Cardio, Strength, and Flexibility are subclasses, inheriting common fields (name, duration) and methods from Workout. This allows code reuse and avoids redundancy for shared workout properties.
c. Polymorphism - method overriding is used in each subclass of Workout to implement showDetails() differently according to the type of workout. Dynamic polymorphism occurs when a Workout reference is used to store any subclass object: Workout w = new Cardio("Running", 30); w.showDetails(); // Calls Cardio's version at runtime
d. Abstraction - workout is an abstract class with the abstract method showDetails(). It provides a blueprint for all types of workouts, enforcing that subclasses implement their own showDetails() method. It hides internal implementation details of specific workouts from the user interface.
e. Exception Handling - custom exception InvalidInputException is created to handle invalid fitness goals. Try-catch blocks are used to handle invalid user inputs like non-numeric weight or invalid goal entries, preventing program crashes.

Program Structure
Class - Role
Main - Main program entry point, handles user interaction, registration, login, and routine setup.
User - Represents a user with encapsulated attributes like username, password, weight, goal, and selected workouts/foods.
Workout (abstract) - Blueprint for all workouts, enforces implementation of showDetails() in subclasses.
Cardio, Strength, Flexibility - Specific types of workouts (Inheritance + Polymorphism) with their own showDetails() implementations.
Food - Represents food recommendations with encapsulated name and type attributes.
InvalidInputException	Custom exception for handling invalid user inputs.
Text-Based Class Relationships
                Workout (abstract)
                     ↑
        -----------------------------
        |            |              |
     Cardio       Strength     Flexibility

Main --> uses --> User --> contains --> List<Workout>, List<Food>
Main --> throws --> InvalidInputException
Main --> uses --> Food

 How to Run the Program
Save the program file
Save the code in a file named Main.java.
Open Command Prompt / Terminal
Navigate to the directory where Main.java is saved.
Compile the program (javac Main.java) This will generate a Main.class file if there are no syntax errors.
Run the program (java Main)
Interact with the program
You will have to register and login to select the goal that you want to achieve 
The system will suggest workouts and food that fits to your specific needs
The program will display your saved workout and meal plan.

Sample Output
--- Welcome to FitTrack ---
1. Register
2. Login
3. Exit
Choose an option: 1
Enter username: aira camino
Enter password: *****
Registration successful!

--- Welcome to FitTrack ---
1. Register
2. Login
3. Exit
Choose an option: 2
Enter username: aira camino
Enter password: *****
Login successful!
Enter your current weight (kg): 40
Enter your goal (lose/gain/build): gain

Suggested Workouts:
1. Strength Workout: Weightlifting | Duration: 40 mins
2. Strength Workout: Push-ups | Duration: 20 mins

Suggested Foods:
1. Supplement: Protein Shake
2. Meal: Rice & Chicken

Select workout numbers (comma separated): 1
Select food numbers (comma separated): 2

Routine saved successfully!

Your Workout Routine:
Strength Workout: Weightlifting | Duration: 40 mins

Your Meal Plan:
Meal: Rice & Chicken

Logging out...

Acknowledgement
	 We would like to express our deepest appreciation to our instructor for her guidance and support throughout this semester and the completion of this project. We would also like extend our gratitude to our classmates and peers for their unwavering support that brought us motivation and encouragement during the progress of this project.

Disclaimer
	This project and its contents are provided for example and learning purposes only. Students are encouraged to use it as a reference and not copy it in its entirety.

Contributors
Jermae Balmes
France Javie
Aira Camino



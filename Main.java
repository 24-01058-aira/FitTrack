import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class RoutinePlan {
    protected String goal;

    public RoutinePlan(String goal) {
        this.goal = goal;
    }

    public abstract List<String> suggestWorkouts();
    public abstract List<String> suggestFoods();

    public String getGoal() {
        return goal;
    }
}

class WeightLossPlan extends RoutinePlan {
    public WeightLossPlan() { super("Lose Weight"); }

    @Override
    public List<String> suggestWorkouts() {
        return Arrays.asList("Cardio Training (30-45 min)", "HIIT Workout (20-25 min)");
    }

    @Override
    public List<String> suggestFoods() {
        return Arrays.asList("Vegetable Salad", "Grilled Chicken", "Oatmeal", "Steamed Fish");
    }
}

class MuscleGainPlan extends RoutinePlan {
    public MuscleGainPlan() { super("Build Muscle"); }

    @Override
    public List<String> suggestWorkouts() {
        return Arrays.asList("Weight Lifting (Compound lifts)", "Resistance Training (3 sets)");
    }

    @Override
    public List<String> suggestFoods() {
        return Arrays.asList("Steak / Lean Beef", "Eggs", "Protein Shake", "Brown Rice");
    }
}

class WeightGainPlan extends RoutinePlan {
    public WeightGainPlan() { super("Gain Weight"); }

    @Override
    public List<String> suggestWorkouts() {
        return Arrays.asList("Light Strength Training", "Low-Intensity Full Body Workout");
    }

    @Override
    public List<String> suggestFoods() {
        return Arrays.asList("High-Calorie Rice Meals", "Pasta", "Healthy Fats (nuts, avocado)", "Full-fat Dairy");
    }
}

// Encapsulation: User class with private fields and accessors
class User {
    private String username;
    private String password;
    private boolean loggedIn;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public String getUsername() { return username; }
    public boolean isLoggedIn() { return loggedIn; }

    // return true if login successful
    public boolean login(String user, String pass) {
        if (this.username.equals(user) && this.password.equals(pass)) {
            loggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        loggedIn = false;
    }
}

class RoutineRecord {
    private String workout;
    private String food;
    private String goal;
    private String timestamp;
    private int days;

    public RoutineRecord(String workout, String food, String goal, int days) {
        this.workout = workout;
        this.food = food;
        this.goal = goal;
        this.days = days;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public String toString() {
        return "Goal: " + goal +
                "\nWorkout: " + workout +
                "\nFood: " + food +
                "\nDays to Perform: " + days +
                "\nSaved On: " + timestamp;
    }
}

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static User user = null;
    private static final ArrayList<RoutineRecord> history = new ArrayList<>();

    public static void main(String[] args) {
        int choice = -1;

        while (true) {
            printMenu();
            try {
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer choice.");
                continue;
            }

            switch (choice) {
                case 1 -> register();
                case 2 -> loginFlow();
                case 3 -> addRoutine();
                case 4 -> viewRoutines();
                case 5 -> deleteRoutine();
                case 6 -> logoutOrExit();
                case 7 -> {
                    System.out.println("Program terminated. Goodbye!");
                    return; // fully exit program
                }
                default -> System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== FITTRACK SYSTEM =====");
        System.out.println("[1] Register");
        System.out.println("[2] Login & Generate Routine");
        System.out.println("[3] Add Routine (must be logged in)");
        System.out.println("[4] View Saved Routines");
        System.out.println("[5] Delete Routine");
        System.out.println("[6] Logout");
        System.out.println("[7] Exit Program");
    }

    private static void register() {
        System.out.print("Enter username: ");
        String u = sc.nextLine().trim();
        if (u.isEmpty()) {
            System.out.println("Username cannot be empty.");
            return;
        }

        System.out.print("Enter password: ");
        String p = sc.nextLine();
        if (p.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }

        user = new User(u, p);
        System.out.println("Registration successful! You may now log in.");
    }

    private static void loginFlow() {
        if (user == null) {
            System.out.println("No registered user found. Please register first.");
            return;
        }

        if (user.isLoggedIn()) {
            System.out.println("Already logged in as " + user.getUsername() + ".");
            return;
        }

        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        if (user.login(u, p)) {
            System.out.println("Login successful! Welcome, " + user.getUsername() + ".");
            startRoutineCreation();
        } else {
            System.out.println("Invalid credentials. Login failed.");
        }
    }

    // Shared routine creation logic (used by loginFlow and addRoutine)
    private static void startRoutineCreation() {
        Integer age = null;
        Double weight = null;

        // get age
        while (true) {
            System.out.print("Enter your age (years): ");
            String s = sc.nextLine().trim();
            try {
                age = Integer.parseInt(s);
                if (age <= 0) {
                    System.out.println("Age must be a positive integer. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input for age. Enter a whole number.");
            }
        }

        // get weight
        while (true) {
            System.out.print("Enter your weight (kg): ");
            String s = sc.nextLine().trim();
            try {
                weight = Double.parseDouble(s);
                if (weight <= 0) {
                    System.out.println("Weight must be a positive number. Try again.");
                    continue;
                }
                break;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input for weight. Enter a number.");
            }
        }

        // choose goal
        RoutinePlan plan = null;
        while (true) {
            System.out.println("\nChoose Goal:");
            System.out.println("[1] Lose Weight");
            System.out.println("[2] Gain Weight");
            System.out.println("[3] Build Muscle");
            System.out.print("Select (1-3): ");
            String s = sc.nextLine().trim();
            if (s.equals("1")) { plan = new WeightLossPlan(); break; }
            if (s.equals("2")) { plan = new WeightGainPlan(); break; }
            if (s.equals("3")) { plan = new MuscleGainPlan(); break; }
            System.out.println("Invalid selection. Choose 1, 2, or 3.");
        }

        // show recommended workouts (at least 2)
        List<String> workouts = plan.suggestWorkouts();
        System.out.println("\n--- Recommended Workouts ---");
        for (int i = 0; i < workouts.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, workouts.get(i));
        }

        int wChoice = -1;
        while (true) {
            System.out.print("Choose workout by number: ");
            try {
                wChoice = Integer.parseInt(sc.nextLine().trim());
                if (wChoice < 1 || wChoice > workouts.size()) {
                    System.out.println("Select a valid workout number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }

        // show recommended foods (2 or more)
        List<String> foods = plan.suggestFoods();
        System.out.println("\n--- Recommended Foods ---");
        for (int i = 0; i < foods.size(); i++) {
            System.out.printf("[%d] %s%n", i + 1, foods.get(i));
        }

        int fChoice = -1;
        while (true) {
            System.out.print("Choose food by number: ");
            try {
                fChoice = Integer.parseInt(sc.nextLine().trim());
                if (fChoice < 1 || fChoice > foods.size()) {
                    System.out.println("Select a valid food number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }

        int days = -1;
        while (true) {
            System.out.print("How many days should this routine be performed? ");
            try {
                days = Integer.parseInt(sc.nextLine().trim());
                if (days <= 0) {
                    System.out.println("Days must be positive.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number of days.");
            }
        }

        RoutineRecord record = new RoutineRecord(
                workouts.get(wChoice - 1),
                foods.get(fChoice - 1),
                plan.getGoal(),
                days
        );
        history.add(record);

        System.out.println("\nRoutine saved successfully!");
        System.out.println(record);
    }

    private static void addRoutine() {
        if (user == null || !user.isLoggedIn()) {
            System.out.println("You must be logged in to add a routine. Please login first.");
            return;
        }
        System.out.println("Add another routine:");
        startRoutineCreation();
    }

    private static void viewRoutines() {
        if (history.isEmpty()) {
            System.out.println("No routines saved.");
            return;
        }
        System.out.println("\n=== Saved Routines ===");
        for (int i = 0; i < history.size(); i++) {
            System.out.println("\nRecord #" + (i + 1));
            System.out.println(history.get(i).toString());
        }
    }

    private static void deleteRoutine() {
        if (history.isEmpty()) {
            System.out.println("No records to delete.");
            return;
        }
        viewRoutines();
        int num = -1;
        while (true) {
            System.out.print("Enter record number to delete (or 0 to cancel): ");
            try {
                num = Integer.parseInt(sc.nextLine().trim());
                if (num == 0) {
                    System.out.println("Deletion cancelled.");
                    return;
                }
                if (num < 1 || num > history.size()) {
                    System.out.println("Invalid record number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer.");
            }
        }
        history.remove(num - 1);
        System.out.println("Record deleted.");
    }

    private static void logoutOrExit() {
        if (user == null || !user.isLoggedIn()) {
            System.out.println("No user is currently logged in.");
            return;
        }
        user.logout();
        System.out.println("You have been logged out.");
    }
}


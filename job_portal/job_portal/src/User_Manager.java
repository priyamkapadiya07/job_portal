import java.sql.*;
import java.util.*;

public class User_Manager {
    static Scanner scanner = new Scanner(System.in);
    static User_DLL users;
    static User_DAO user_data;
    static int id = 0;

    public User_Manager() throws Exception {
        this.users = new User_DLL();
        this.user_data = new User_DAO();
    }

    boolean copyData() throws Exception {
        boolean f = true;
        try {
            String q = "select * from user;";
            ResultSet rs = user_data.st.executeQuery(q);
            while (rs.next()) {
                id = rs.getInt(1);
                User temp = new User(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), getPoints(rs.getString(6), rs.getString(4), rs.getString(5)));
                users.insertInOrder(temp);
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            f = false;
        }
        return f;
    }

    public static int getPoints(String Experience, String Education, String Preferences) {
        int ponts = 0;
        String experience = Experience;
        String education = Education;
        String preferences = Preferences;
        String[] edu = education.split(" ");
        String degree = edu[0];
        switch (degree) {
            case "Doctoral":
                ponts += 20;
                break;
            case "Master's":
                ponts += 15;
                break;
            case "Bachelor's":
                ponts += 10;
                break;
            case "Associate's":
                ponts += 5;
                break;
            default:
                ponts += 0;
                // System.out.println(degree);
                break;
        }
        if (experience.equalsIgnoreCase("no experience") || experience.equalsIgnoreCase("fresher")) {
            ponts += 0;
        } else {
            int experience_years = experience.charAt(0);
            if (experience_years >= 2 && experience_years < 5) {
                ponts += 10;
            } else if (experience_years >= 5) {
                ponts += 20;
            }
        }
        if (preferences.equalsIgnoreCase("flexible")) {
            ponts += 10;
        } else if (preferences.equalsIgnoreCase("office work")) {
            ponts += 5;
        } else {
            ponts += 0;
        }
        return ponts;
    }

    public void register() throws Exception {
        id++;
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        String education = "";
        boolean b1 = false;
        try {
            do {
                System.out.println("provide information about your highest education education: ");
                System.out.println("""
                        1:No formal education
                        2:Primary school
                        3:Middel school
                        4:High School Graduate
                        5:Associate's degree
                        6:Bachelor's degree
                        7:Master's degree
                        8:Doctoral degree
                        """);
                int education_choice = scanner.nextInt();
                scanner.nextLine();
                String programm;
                switch (education_choice) {
                    case 1:
                        education = "No formal education";
                        break;
                    case 2:
                        education = "Primary school";
                        break;
                    case 3:
                        education = "Middel school";
                        break;
                    case 4:
                        education = "High School Graduate";
                        break;
                    case 5:
                        education = "Associate's degree";
                        break;
                    case 6:
                        System.out.println("Specify Programm : ");
                        programm = scanner.nextLine();
                        education = "Bachelor's degree in " + programm;
                        break;
                    case 7:
                        System.out.println("Specify Programm : ");
                        programm = scanner.nextLine();
                        education = "Master's degree in " + programm;
                        break;
                    case 8:
                        System.out.println("Specify Programm : ");
                        programm = scanner.nextLine();
                        education = "Doctoral degree in " + programm;
                        break;
                    default:
                        System.out.println("Invalid Input...");
                        System.out.println(education_choice);
                        b1 = true;
                        break;
                }
            } while (b1);

        } catch (Exception e) {
            System.out.println(e);
            b1 = true;
        }
        System.out.println("Education : " + education);
        scanner.nextLine();
        boolean b4 = true;
        String contactNo = "";
        while (b4) {
            try {
                System.out.print("Enter contact number: ");
                contactNo = scanner.nextLine();
                if (!isNumberValid(contactNo)) {
                    System.out.println("Enter Valid Contact number....");
                    b4 = true;
                } else {
                    b4 = false;
                }
            } catch (Exception e) {
                System.out.println("Enter In Valid Format");
                b4 = true;
            }
        }
        String experience = "";
        while (true) {
            System.out.print("Do you have any priore working experience ? (yes/no) : ");
            String experience_choice = scanner.nextLine();
            if (experience_choice.equalsIgnoreCase("Yes")) {
                System.out.println("Enter Experience (in Years) : ");
                int experience_year = scanner.nextInt();
                if (experience_year == 1) {
                    experience = "fresher";
                } else {
                    experience = experience_year + " years";
                }
                break;
            } else if (experience_choice.equalsIgnoreCase("no")) {
                experience = "No Experience";
                break;
            } else {
                System.out.println("Invalid Input");
            }
        }
        boolean b2 = false;
        String preferences = "";
        try {
            do {
                System.out.println("Enter Your Preferences for working : ");
                System.out.println("""
                        1:FREE LANCING
                        2:REMOTE WORK
                        3:OFFICE WORK
                        4:FLEXIBLE
                        """);
                int preference_choice = scanner.nextInt();
                switch (preference_choice) {
                    case 1:
                        preferences = "free lancing";
                        break;
                    case 2:
                        preferences = "remote work";
                        break;
                    case 3:
                        preferences = "office work";
                        break;
                    case 4:
                        preferences = "flexible";
                        break;
                    default:
                        System.out.println("Invalid Input...");
                        b2 = true;
                        break;
                }
            } while (b2);

        } catch (Exception e) {
            System.out.println(e);
            b2 = true;
        }

        System.out.println("your id : " + id);
        System.out.print("Set password  : ");
        scanner.nextLine();
        String password = scanner.nextLine();
        User user = null;
        user = new User(id, name, email, education, preferences, experience, contactNo,
                password, getPoints(experience, education, preferences));
        users.insertInOrder(user);
        if (user_data.addUser(user)) {
            System.out.println("User registered successfully Into Database!");
        }

    }

    boolean isNumberValid(String num) {
        boolean f = true;
        if (num.length() != 10) {
            f = false;
        } else {
            for (int i = 0; i < num.length(); i++) {
                char c = num.charAt(i);
                if (Character.isLetter(c)) {
                    f = false;
                }
            }
        }
        return f;
    }

    void chooseJob2(int user_id) throws Exception {
        Statement st = DB_Connection.conn.createStatement();
        ResultSet temp_rs = st.executeQuery("select * from jobs where status='available'");
        int count = 0;
        while (temp_rs.next()) {
            count++;
        }

        int[] job_ids = new int[count];
        count = 0;
        ResultSet rs = st.executeQuery("select id,compony_name,role from jobs where status='available'");
        System.out.println("Availabe Jobs");
        while (rs.next()) {
            job_ids[count++] = rs.getInt(1);
            System.out.print(
                    "ID : " + rs.getInt(1) + " | Company name : " + rs.getString(2) + " | Role : " + rs.getString(3));
            System.out.println();
        }
        PreparedStatement pst = DB_Connection.conn.prepareStatement("insert into job_request values(?,?,'Waiting')");
        int choice = 0;
        try {
            do {
                System.out.println("1:Choose job");
                System.out.println("2:exit");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Enter Job_id : ");
                        int j_id = scanner.nextInt();
                        st.execute(
                                "delete from job_request where job_id=" + j_id + " and requester_id=" + user_id + ";");
                        if (isJobValid(j_id, job_ids)) {
                            pst.setInt(1, user_id);
                            pst.setInt(2, j_id);
                            System.out.println((pst.executeUpdate() > 0 ? "Job " + j_id + " selected successfully"
                                    : "Probelm While Applying for job " + j_id));
                        } else {
                            System.out.println("Invalid Job Id");
                        }

                        break;
                    case 2:
                        choice = -1;
                        break;

                    default:
                        System.out.println("Invalid Choice");
                        break;
                }
            } while (choice != -1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    boolean isJobValid(int job_id, int[] jobs) {
        boolean f = false;
        for (int i = 0; i < jobs.length; i++) {
            if (job_id == jobs[i]) {
                f = true;
            }
        }
        return f;
    }

    public void login() throws Exception {
        boolean b = true;

        try {
            System.out.print("Enter ID: ");
            String id1 = scanner.nextLine();
            int id = Integer.valueOf(id1);
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            if (users.loginIsValid(id, password)) {
                System.out.println("Login successful!");
                userInterface2(id, password);
                b = false;

            } else {
                System.out.println("Invalid ID or password");
                b = true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void userInterface2(int id, String password) throws Exception {
        boolean b = true;
        while (b) {
            System.out.println("1. Find Job");
            System.out.println("2. View profile");
            System.out.println("3. Update Profile");
            System.out.println("4. Delete profile ");
            System.out.println("5. Exit");
            System.out.print("Choose an option : ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    chooseJob2(id);
                    break;
                case "2":
                    users.displayUser(id);
                    break;
                case "3":
                    user_data.updateUser(users.updateUser(id));
                    break;
                case "4":
                    users.delete(id, password);
                    user_data.deleteUser(id);
                    break;
                case "5":
                    b = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }

    void userInterface() throws Exception {
        boolean b = true;
        while (b) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option : ");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    register();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    b = false;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }

    }
}
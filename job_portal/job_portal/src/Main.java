import java.util.Scanner;

public class Main {
    static User_Manager U_Manager;
    static Job_Manager J_Manager;
    static Scanner sc = new Scanner(System.in);
    static {

        try {
            new DB_Connection();
            U_Manager = new User_Manager();
            J_Manager = new Job_Manager();
            U_Manager.copyData();
            J_Manager.copyData();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("||-----------> Welcome to  job portal <-----------||");

        while (true) {
            System.out.println("""
                    1:find job
                    2:Hire Empoyee
                    3:display user and jobs
                    4:exit
                    """);
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    U_Manager.userInterface();
                    break;
                case "2":
                    J_Manager.JobInterface();
                    break;

                case "3":
                    System.out.println("========================Users Data========================");
                    U_Manager.users.displayUsers();
                    System.out.println("=======================jobs data=========================");
                    J_Manager.jobs.displayJobs();
                    break;
                case "4":
                    System.out.println("||-----------> Thank you for Using Our System <-----------||");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter Valid Input");
                    break;
            }
        }
    }
}
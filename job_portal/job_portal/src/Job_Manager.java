import java.sql.*;
import java.util.*;

public class Job_Manager {
    static Scanner sc = new Scanner(System.in);
    static Job_DLL jobs;
    static Job_DAO jobs_data;
    static int id = 0;
    static Candidate_DLL temp;

    public Job_Manager() throws SQLException {
        this.jobs = new Job_DLL();
        temp = new Candidate_DLL();
        this.jobs_data = new Job_DAO(DB_Connection.conn);
    }

    void copyData() throws Exception {
        // boolean f = false;
        try {
            String q = "select * from jobs;";
            ResultSet rs = Job_DAO.st.executeQuery(q);
            while (rs.next()) {
                id = rs.getInt(1);
                Job temp = new Job(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8),
                        getPoints(rs.getString(4)));
                jobs.insertInOrder(temp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void postJob() {
        id++;
        sc.nextLine();
        System.out.println("your id : " + id);
        System.out.println("Set password  : ");
        String password = sc.nextLine();
        System.out.println("enter company name : ");
        String cname = sc.nextLine();
        System.out.println("enter company role : ");
        String crole = sc.nextLine();
        String salary = "";
        boolean b = true;
        do {
            try {
                long temp_salary;
                System.out.println("How Would You Like to enter Salary Detailes : (Anual Income/Monthly Income)");
                String salary_type = sc.nextLine();
                if (salary_type.equalsIgnoreCase("Anual")) {
                    System.out.println("Enter Anual Salary(in LPA) : ");
                    temp_salary = sc.nextLong();
                    salary = temp_salary + " LPA";
                } else if (salary_type.equalsIgnoreCase("Monthly")) {
                    System.out.println("Enter Monthly Salary : ");
                    temp_salary = sc.nextLong();
                    temp_salary = (temp_salary * 12) / 100000;
                    salary = temp_salary + " LPA";
                }
                b = false;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Enter Valid Input in Digits");
            }
        } while (b);
        sc.nextLine();
        String status = "Available";
        System.out.println("enter company address : ");
        String caddress = sc.nextLine();
        int vacancy = Integer.MIN_VALUE;
        do {
            try {
                System.out.println("Enter number of Available Posts : ");
                vacancy = sc.nextInt();
                if (vacancy <= 0) {
                    System.out.println("Invalid input (Need to be atleast 1 or more)");
                }
            } catch (Exception e) {
                System.out.println(e);
                vacancy = Integer.MIN_VALUE;
            }
        } while (vacancy <= 0);
        System.out.println("Job Posting successfully!");
        Job temp = new Job(id, cname, crole, salary, caddress, vacancy, status, password, getPoints(salary));
        jobs_data.addJobs(temp);
        jobs.insertInOrder(temp);

    }

    public static int getPoints(String salary) {
        int point = 0;
        String temp_salary[] = salary.split(" ");
        point = Integer.valueOf(temp_salary[0]);
        return point;
    }

    static void view_candidates2(int job_id) throws Exception {
        temp.displayUsers();
        Statement st = DB_Connection.conn.createStatement();
        ResultSet rs1 = st
                .executeQuery(
                        "select * from user where id in(select requester_id from job_request where job_id=" + job_id
                                + " and status='waiting');");
        while (rs1.next()) {
            User t = new User(rs1.getInt(1), rs1.getString(2), rs1.getString(3),
                    rs1.getString(4), rs1.getString(5), rs1.getString(6), rs1.getString(7),
                    rs1.getString(8), User_Manager.getPoints(rs1.getString(6), rs1.getString(4), rs1.getString(5)));
            temp.insertInOrder(t);
        }
        temp.displayUsers();
        PreparedStatement pst = DB_Connection.conn
                .prepareStatement(
                        "update job_request set Status='Selected' where requester_id=? and job_id=" + job_id);

        boolean b = true;
        do {
            System.out.println("""
                    1:Choose candidate
                    2:exit
                    """);
            String choice = sc.nextLine();
            if (choice.equals("1")) {
                System.out.println("Enter candidate ID : ");
                try {
                    int candidate_id = sc.nextInt();
                    sc.nextLine();
                    pst.setInt(1, candidate_id);
                    if (pst.executeUpdate() > 0) {
                        System.out.println("candidate : " + candidate_id + " selected succesfully ");
                        st.execute("update jobs set vacancy=vacancy-1 where id=" + job_id + ";");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else if (choice.equals("2")) {
                b = false;
            } else {
                System.out.println("Enter valid Input");
            }

        } while (b);
    }

    public void login() throws Exception {
        // sc.nextLine();
        boolean b = true;
        do {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            if (jobs.isLoginValid(id, password)) {
                System.out.println("Login successful!");
                JobInterface2(id, password);
                b = false;
            } else {
                System.out.println("Invalid email or password");
            }

        } while (b);
    }

    void JobInterface2(int id, String password) throws Exception {
        boolean b = true;
        do {
            System.out.println("""
                    1:Delete Post
                    2:Update post
                    3:View Candidates
                    4:exit
                    """);
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    jobs.deleteJob(id, password);
                    jobs_data.deleteJob(id);
                    b = false;
                    break;
                case "2":
                    if (jobs_data.updateJob(jobs.updateJob(id))) {
                        System.out.println("Job Updated succesfully");
                    } else {
                        System.out.println("Job not updated");
                    }
                    break;
                case "3":
                    view_candidates2(id);
                    break;
                case "4":
                    b = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (b);
    }

    void JobInterface() throws Exception {
        String choice = "";
        do {
            System.out.println("""
                    1:Post Job
                    2:Login
                    3:Exit
                    """);
            choice = sc.nextLine();
            switch (choice) {
                case "1":
                    postJob();
                    break;
                case "2":
                    login();
                    break;
                case "3":
                    // exit();
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (choice.equalsIgnoreCase("3"));

    }
}

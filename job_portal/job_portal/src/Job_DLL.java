import java.util.Scanner;

public class Job_DLL {
    // static Job_DAO job_data;
    static Scanner sc = new Scanner(System.in);

    public class Node {
        Job job;
        Node next;
        Node prev;

        Node(Job d) {
            job = d;
            next = null;
            prev = null;
            // System.out.println("");
        }
    }

    static Node Head = null;

    public static int[] giveIDs() {
        Node temp = Head;
        int i = 0;
        while (temp != null) {
            i++;
            temp = temp.next;
        }
        temp = Head;
        int[] IDs = new int[i];
        i = 0;
        while (temp != null) {
            IDs[i++] = temp.job.getId();
            temp = temp.next;
        }
        return IDs;

    }

    public static int getVacancy(int id) {
        Node temp = Head;
        int vacancy = 0;
        while (temp != null) {
            if (temp.job.getId() == id) {
                vacancy = temp.job.getVacancy();
                break;
            }
            temp = temp.next;
        }
        return vacancy;
    }

    void insertInOrder(Job job) {
        Node n = new Node(job);
        if (Head == null) {
            Head = n;
        } else if (Head.job.getPoints() <= n.job.getPoints()) {
            n.next = Head;
            Head.prev = n;
            Head = n;
        } else {
            Node temp = Head;
            while (temp.next != null && temp.job.getPoints() > n.job.getPoints()) {
                temp = temp.next;
            }
            n.next = temp.next;
            n.prev = temp;
            if (temp.next != null) {
                temp.next.prev = n;
            }
            temp.next = n;
        }
    }

    boolean isLoginValid(int id, String password) {
        Node temp = Head;
        while (temp != null) {
            if (temp.job.getId() == id && temp.job.getPassword().equals(password)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    static boolean isJobExist(int id) {
        int flag = 0;
        Node temp = Head;
        while (temp != null) {
            if (temp.job.getId() == id) {
                flag = 1;
            }
            temp = temp.next;
        }
        if (flag != 0) {
            return true;
        } else {
            return false;
        }
    }

    void displayJobs() {
        Node temp = Head;
        while (temp != null) {
            Job t = temp.job;
            if (t.getStatus().equalsIgnoreCase("Available")) {
                System.out.println("Id : " + t.getId());
                System.out.println("company name : " + t.getcompany_name());
                System.out.println("Location : " + t.getLocation());
                System.out.println("Salary range : " + t.getSalary());
                System.out.println("Role : " + t.getRole());
                System.out.println("Number of posts available : " + t.getVacancy());
                System.out.println("Points : " + t.getPoints());
                System.out.println("---------------------------------");
            }
            temp = temp.next;
        }
    }

    void displayJob(int id) {
        Node temp = Head;
        int flag = 0;
        while (temp != null) {
            if ((temp.job.getId() == id)) {
                flag++;
                break;
            }
            temp = temp.next;
        }
        if (flag == 1) {
            Job t = temp.job;
            System.out.println("=====================================");
            System.out.println("Id : " + t.getId());
            System.out.println("company name : " + t.getcompany_name());
            System.out.println("Location : " + t.getLocation());
            System.out.println("Salary range : " + t.getSalary());
            System.out.println("Role : " + t.getRole());
            System.out.println("Number of posts available : " + t.getVacancy());
        } else {
            System.out.println("Job not found (from display job)");
        }
    }

    public Job updateJob(int id) {
        Node temp = Head;
        while (temp != null) {
            if (temp.job.getId() == id) {
                break;
            }
            temp = temp.next;
        }
        System.out.println("Detailes Of Your Job posting");
        displayJob(id);
        int choice = 0;
        do {
            System.out.println("""
                    1:change compony_name
                    2:change Role
                    3:change Salary_range
                    4:change Status
                    5:change Location
                    6:change Password
                    7:change Number Of Vacancy Available
                    7:Exit
                    """);
            choice = sc.nextInt();
            sc.nextLine();
            String updated_value;
            switch (choice) {
                case 1:
                    System.out.print("Enter New Compony Name : ");
                    updated_value = sc.nextLine();
                    temp.job.setcompany_name(updated_value);

                    break;
                case 2:
                    System.out.println("Enter new role : ");
                    updated_value = sc.nextLine();
                    temp.job.setRole(updated_value);
                    break;
                case 3:
                    System.out.print("Enter Starting salary : ");
                    String S_Salary = sc.nextLine();
                    System.out.print("Enter Ending salary : ");
                    String E_Salary = sc.nextLine();
                    temp.job.setSalary(S_Salary + " To " + E_Salary);
                    break;

                case 4:
                    System.out.println("Change Your Post Status to Unavailable?(yes/no)");
                    String dummy = sc.nextLine();
                    if (dummy.equalsIgnoreCase("yes")) {
                        temp.job.setStatus("Unavailable");
                        System.out.println("Post Status Changed to Unavailable");
                    } else {
                        System.out.println("Post Status Remains to Available");
                    }
                    break;
                case 5:
                    System.out.println("Enter New Address : ");
                    updated_value = sc.nextLine();
                    temp.job.setLocation(updated_value);
                    break;
                case 6:
                    System.out.println("Enter New Password : ");
                    updated_value = sc.nextLine();
                    temp.job.setPassword(updated_value);
                    break;
                case 7:
                    System.out.println("Enter New Number Of Posts Available : ");
                    int New_vacancy = sc.nextInt();
                    temp.job.setVacancy(New_vacancy);
                    break;
                default:
                    System.out.println("Enter valid input");
                    break;
            }

        } while (choice != 8);
        return temp.job;
    }

    void deleteJob(int id, String password) {
        Node temp = Head;
        int flag = 0;
        while (temp != null) {
            if ((temp.job.getId() == id) && (temp.job.getPassword().equals(password))) {
                flag++;
                break;
            }
            temp = temp.next;
        }
        if (flag == 1) {
            if (temp.prev != null) {
                temp.prev.next = temp.next;
            }
            if (temp.next != null) {
                temp.next.prev = temp.prev;
            }
            temp.prev = null;
            temp.next = null;
            System.out.println("Job Deleted Succesfully");
        } else {
            System.out.println("Job not found");
        }
    }

}
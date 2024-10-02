import java.util.Scanner;

class User_DLL {
    static Scanner sc = new Scanner(System.in);

    class Node {
        User user;
        Node next;
        Node prev;

        Node(User d) {
            user = d;
            next = null;
            prev = null;
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
            IDs[i++] = temp.user.getId();
            temp = temp.next;
        }
        return IDs;
    }

    void insertInOrder(User user) {
        Node n = new Node(user);
        if (Head == null) {
            Head = n;
        } else if (Head.user.getPoints() <= n.user.getPoints()) {
            n.next = Head;
            Head.prev = n;
            Head = n;
        } else {
            Node temp = Head;
            while (temp.next != null && temp.user.getPoints() > n.user.getPoints()) {
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

    void displayUsers() {
        Node temp = Head;
        while (temp != null) {
            User t = temp.user;
            System.out.println("ID : " + t.getId());
            System.out.println("Name : " + t.getName());
            System.out.println("E-Mail : " + t.getEmail());
            System.out.println("Education : " + t.getEducation());
            System.out.println("Preferences : " + t.getPreferences());
            System.out.println("Experience : " + t.getExperience());
            System.out.println("Password : " + t.getPassword());
            System.out.println("Points : " + t.getPoints());
            System.out.println("------------------------------------");
            temp = temp.next;
        }
    }

    void displayUser(int id) throws Exception {
        Node temp = Head;
        int flag = 0;
        while (temp != null) {
            if ((temp.user.getId() == id)) {
                flag++;
                break;
            }
            temp = temp.next;
        }
        if (flag == 0) {
            System.out.println("User not availabe");
        } else {
            User t = temp.user;
            System.out.println("ID : " + t.getId());
            System.out.println("Name : " + t.getName());
            System.out.println("E-Mail : " + t.getEmail());
            System.out.println("Education : " + t.getEducation());
            System.out.println("Preferences : " + t.getPreferences());
            System.out.println("Experience : " + t.getExperience());
            System.out.println("Password : " + t.getPassword());
            System.out.println("Points : " + t.getPoints());
        }
    }

    boolean loginIsValid(int id, String password) {
        Node temp = Head;
        while (temp != null) {
            if (temp.user.getId() == id && temp.user.getPassword().equals(password)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public User updateUser(int id) {
        Node temp = Head;
        while (temp != null) {
            if (temp.user.getId() == id) {
                break;
            }
            temp = temp.next;
        }
        int choice = 0;
        do {
            System.out.println("""
                    1:change Name
                    2:change Email
                    3:change contact number
                    4:change Education
                    5:change Preferences
                    6:change Experience
                    7:change Password
                    8:exit
                    """);
            choice = sc.nextInt();
            sc.nextLine();
            String updated_value = "";
            switch (choice) {
                case 1:
                    System.out.println("Enter new Name : ");
                    updated_value = sc.nextLine();
                    temp.user.setName(updated_value);
                    break;
                case 2:
                    System.out.println("Enter new Email : ");
                    updated_value = sc.nextLine();
                    temp.user.setEmail(updated_value);
                    break;
                case 3:
                    System.out.println("Enter new contact number : ");
                    updated_value = sc.nextLine();
                    temp.user.setPreferences(updated_value);
                    break;
                case 4:
                    boolean b1 = false;
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
                        int education_choice = sc.nextInt();
                        sc.nextLine();
                        String programm;
                        switch (education_choice) {
                            case 1:
                                updated_value = "No formal education";
                                break;
                            case 2:
                                updated_value = "Primary school";
                                break;
                            case 3:
                                updated_value = "Middel school";
                                break;
                            case 4:
                                updated_value = "High School Graduate";
                                break;
                            case 5:
                                updated_value = "Associate's degree";
                                break;
                            case 6:
                                System.out.println("Specify Programm : ");
                                programm = sc.nextLine();
                                updated_value = "Bachelor's degree in " + programm;
                                break;
                            case 7:
                                System.out.println("Specify Programm : ");
                                programm = sc.nextLine();
                                updated_value = "Master's degree in " + programm;
                                break;
                            case 8:
                                System.out.println("Specify Programm : ");
                                programm = sc.nextLine();
                                updated_value = "Doctoral degree in " + programm;

                            default:
                                System.out.println("Invalid Input...");
                                b1 = true;
                                break;
                        }
                    } while (b1);
                    temp.user.setEducation(updated_value);
                    break;
                case 5:
                    boolean b2 = false;
                    do {
                        System.out.println("Enter Your Preferences for working : ");
                        System.out.println("""
                                1:FREE LANCING
                                2:REMOTE WORK
                                3:OFFICE WORK
                                4:FLEXIBLE
                                """);
                        int preference_choice = sc.nextInt();
                        switch (preference_choice) {
                            case 1:
                                updated_value = "free lancing";
                                break;
                            case 2:
                                updated_value = "remote work";
                                break;
                            case 3:
                                updated_value = "office work";
                                break;
                            case 4:
                                updated_value = "flexible";
                                break;
                            default:
                                System.out.println("Invalid Input...");
                                b2 = true;
                                break;
                        }
                    } while (b2);
                    temp.user.setPreferences(updated_value);
                    break;
                case 6:
                    while (true) {
                        System.out.println("Do you have any priore working experience ? (yes/no)");
                        String experience_choice = sc.nextLine();
                        if (experience_choice.equalsIgnoreCase("Yes")) {
                            System.out.println("Enter Experience (in Years) : ");
                            int experience_year = sc.nextInt();
                            updated_value = experience_year + " years";
                            break;
                        } else if (experience_choice.equalsIgnoreCase("no")) {
                            updated_value = "No Experience";
                            break;
                        } else {
                            System.out.println("Invalid Input");
                        }
                    }
                    break;
                case 7:
                    System.out.println("Enter new Password : ");
                    updated_value = sc.nextLine();
                    temp.user.setPassword(updated_value);
                    break;

                default:
                    if (choice != 8) {
                        System.out.println("Enter valid input : ");
                    }
                    break;
            }

        } while (choice != 8);
        return temp.user;
    }

    void delete(int id, String password) {
        Node temp = Head;
        int flag = 0;
        while (temp != null) {
            if ((temp.user.getId() == id) && (temp.user.getPassword().equals(password))) {
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
            System.out.println("User Deleted Succesfully");
        } else {
            System.out.println("User not found");
        }
    }

    public static User getUser(int id) {
        Node temp = Head;
        int flag = 0;
        while (temp != null) {
            if (temp.user.getId() == id) {
                flag = 1;
                break;
            }
            temp = temp.next;
        }
        if (flag == 1) {
            return temp.user;
        } else {
            return null;
        }
    }

}
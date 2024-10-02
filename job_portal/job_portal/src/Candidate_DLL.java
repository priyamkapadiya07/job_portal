import java.util.Scanner;

class Candidate_DLL {
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
            System.out.println("User is not availabe");
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
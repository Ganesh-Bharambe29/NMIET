import java.util.Scanner;

class Node {
    int marks;
    String Name;
    int RollNo;
    Node next;

    Node(int marks, String Name, int RollNo) {
        this.marks = marks;
        this.Name = Name;
        this.RollNo = RollNo;
        this.next = null;
    }
}

class LinkedList {
    int size = 0;
    Node head;

    public void add(int marks, String Name, int RollNo) {
        Node n1 = new Node(marks, Name, RollNo);
        size++;
        if (head == null) {
            head = n1;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = n1;
    }

    public void show() {
        if (head == null) {
            System.out.println("The Linked List is Empty.");
            return;
        }
        Node temp = head;
        System.out.print("LinkedList is: ");
        while (temp != null) {
            System.out.print("marks: " + temp.marks + " Name: " + temp.Name + " RollNo: " + temp.RollNo + " -> ");
            temp = temp.next;
        }
        System.out.println("Null");
    }

    public void delete(int RollNo) {
        if (head == null) {
            System.out.println("LinkedList is Empty");
            return;
        }
        if (head.RollNo == RollNo) {
            System.out.println("Deleted Student Name: " + head.Name + " Roll No. " + head.RollNo);
            head = head.next;
            size--;
            return;
        }
        Node prev = head;
        Node curr = head.next;

        while (curr != null) {
            if (curr.RollNo == RollNo) {
                System.out.println("Deleted Student: " + curr.Name + " Roll No. " + curr.RollNo);
                prev.next = curr.next;
                size--;
                return;
            }
            prev = curr;
            curr = curr.next;
        }
        System.out.println("Student with RollNo " + RollNo + " Not Found");
    }

    public int search(int RollNo) {
        Node temp = head;
        int i = 0;
        while (temp != null) {
            if (temp.RollNo == RollNo) {
                System.out.println("Student Found: " + temp.Name + " Roll No: " + temp.RollNo + " Marks: " + temp.marks);
                return i;
            }
            temp = temp.next;
            i++;
        }
        System.out.println("Student with RollNo: " + RollNo + " Not Found.");
        return -1;
    }

    public void update(int RollNo, String newName, int newMarks, int newRollNo) {
        if (head == null) {
            System.out.println("LinkedList is Empty.");
            return;
        }

        Node temp = head;
        while (temp != null) {
            if (temp.RollNo == RollNo) {
                System.out.println("Updating Student with RollNo: " + RollNo);
                temp.Name = newName;
                temp.marks = newMarks;
                temp.RollNo = newRollNo;
                System.out.println("Student Updated Successfully!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with RollNo: " + RollNo + " Not Found.");
    }

    public void sort() {
        if (head == null || head.next == null) {
            return;
        }
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            while (current.next != null) {
                if (current.marks > current.next.marks) {
                    int tempMarks = current.marks;
                    String tempName = current.Name;
                    int tempRoll = current.RollNo;

                    current.marks = current.next.marks;
                    current.Name = current.next.Name;
                    current.RollNo = current.next.RollNo;

                    current.next.marks = tempMarks;
                    current.next.Name = tempName;
                    current.next.RollNo = tempRoll;

                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
        System.out.println("Students sorted by marks successfully.");
    }
}

public class Student {
    public static void main(String args[]) {
        LinkedList l1 = new LinkedList();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n====== Student Linked List Menu ======");
            System.out.println("1. Add Student");
            System.out.println("2. Show Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Update Student");
            System.out.println("6. Sort Students by Marks");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();
                    System.out.print("Enter Roll No: ");
                    int rollNo = sc.nextInt();
                    l1.add(marks, name, rollNo);
                    break;

                case 2:
                    l1.show();
                    break;

                case 3:
                    System.out.print("Enter Roll No to delete: ");
                    int delRoll = sc.nextInt();
                    l1.delete(delRoll);
                    break;

                case 4:
                    System.out.print("Enter Roll No to search: ");
                    int searchRoll = sc.nextInt();
                    l1.search(searchRoll);
                    break;

                case 5:
                    System.out.print("Enter Roll No to update: ");
                    int updateRoll = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter new Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter new Marks: ");
                    int newMarks = sc.nextInt();
                    System.out.print("Enter new Roll No: ");
                    int newRoll = sc.nextInt();
                    l1.update(updateRoll, newName, newMarks, newRoll);
                    break;

                case 6:
                    l1.sort();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}

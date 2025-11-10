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
            System.out.print("The Linked List is Empty.\n");
            return;
        }
        Node temp = head;
        System.out.print("LinkedList is: ");
        while (temp != null) {
            System.out.print("marks: " + temp.marks);
            System.out.print(" Name: " + temp.Name);
            System.out.print(" RollNo: " + temp.RollNo);
            System.out.print(" -> ");
            temp = temp.next;
        }
        System.out.print("Null");
        System.out.println();
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

    // ✅ NEW UPDATE METHOD
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
        System.out.println("The Sorted Student LinkedList by Marks:");
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
    }
}

public class Student {
    public static void main(String args[]) {
        LinkedList l1 = new LinkedList();
        l1.show();
        l1.add(100, "Ramesh", 1);
        l1.add(99, "Mukesh", 2);
        l1.add(90, "Sunil", 3);
        l1.add(95, "Anil", 4);
        l1.add(93, "Rohan", 5);
        l1.show();

        l1.sort();
        l1.show();

        l1.delete(2);
        l1.show();

        l1.search(3);

        l1.update(3, "Sunil Kumar", 92, 33);
        l1.show();

        l1.delete(4);
        l1.show();
        l1.search(4);
    }
}


import java.util.Scanner;

class Call {
    int maxSize;
    int CustomerID[];
    float CallRecord[];
    int front;
    int rear;
    int count;

    Call(int size) {
        maxSize = size;
        CustomerID = new int[maxSize];
        CallRecord = new float[maxSize];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void addCall(int customerID, float callRecord) {
        if (count == maxSize) { // Fixed logic: use count instead of rear==maxSize-1
            System.out.println("No Call Can be added! Queue is full.");
            return;
        } else {
            rear = (rear + 1) % maxSize;
            CallRecord[rear] = callRecord;
            CustomerID[rear] = customerID;
            count++;
        }
    }

    public void answerCall() {
        if (isQueueEmpty()) {
            System.out.println("No call can be answered!");
            return;
        } else {
            System.out.println("[CustomerID: " + CustomerID[front] + " | Call Time: " + CallRecord[front] + "] is answered.");
            front = (front + 1) % maxSize;
            count--;
        }
    }

    public void viewCall() {
        if (isQueueEmpty()) {
            System.out.println("No call in Call Record.");
            return;
        }

        System.out.println("The call records are:");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % maxSize;
            System.out.print("[CustomerID: " + CustomerID[index] + "  Call Time: " + CallRecord[index] + "] ");
        }
        System.out.println();
    }

    public boolean isQueueEmpty() {
        return count == 0;
    }
}

public class CallRecord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Call c = new Call(5);
        int choice;

        do {
            System.out.println("\n--- Call Record Menu ---");
            System.out.println("1. Add Call");
            System.out.println("2. Answer Call");
            System.out.println("3. View All Calls");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Call Time (in minutes): ");
                    float time = sc.nextFloat();
                    c.addCall(id, time);
                    break;

                case 2:
                    c.answerCall();
                    break;

                case 3:
                    c.viewCall();
                    break;

                case 4:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice! Please enter again.");
            }
        } while (choice != 4);

        sc.close();
    }
}

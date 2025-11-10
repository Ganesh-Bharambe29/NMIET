import java.util.Scanner;

class Event {
    int maxSize;
    String EventArray[];
    int front;
    int rear;
    int count;

    Event(int size) {
        maxSize = size;
        EventArray = new String[maxSize];
        front = 0;
        rear = -1;
        count = 0;
    }

    public void addEvent(String event) {
        if (count == maxSize) {
            System.out.println("No Event can be added. Queue is full.");
        } else {
            rear = (rear + 1) % maxSize;
            EventArray[rear] = event;
            count++;
            System.out.println("Event added: " + event);
        }
    }

    public void completedEvent() {
        if (count == 0) {
            System.out.println("No Event to complete.");
        } else {
            System.out.println("The Event \"" + EventArray[front] + "\" is Completed.");
            front = (front + 1) % maxSize;
            count--;
        }
    }

    public void pendingEvent() {
        if (count == 0) {
            System.out.println("No pending events.");
        } else {
            System.out.println("Pending Events:");
            for (int i = 0; i < count; i++) {
                int idx = (front + i) % maxSize;
                System.out.print(EventArray[idx] + "  ");
            }
            System.out.println();
        }
    }

    public void cancelEvent(String eventName) {
        if (count == 0) {
            System.out.println("No events to cancel.");
            return;
        }

        boolean found = false;
        int indexToRemove = -1;

        for (int i = 0; i < count; i++) {
            int index = (front + i) % maxSize;
            if (EventArray[index].equalsIgnoreCase(eventName)) {
                found = true;
                indexToRemove = index;
                break;
            }
        }

        if (!found) {
            System.out.println("Event \"" + eventName + "\" not found or already processed.");
            return;
        }

        int i = indexToRemove;
        while (i != rear) {
            int nextIndex = (i + 1) % maxSize;
            EventArray[i] = EventArray[nextIndex];
            i = nextIndex;
        }

        EventArray[rear] = null;
        rear = (rear - 1 + maxSize) % maxSize;
        count--;

        System.out.println("Event canceled: " + eventName);
    }
}

public class EventQueue {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter maximum number of events: ");
        int size = sc.nextInt();
        sc.nextLine(); // consume newline

        Event e = new Event(size);
        int choice;

        do {
            System.out.println("\n===== Event Management Menu =====");
            System.out.println("1. Add Event");
            System.out.println("2. Show Pending Events");
            System.out.println("3. Complete Event");
            System.out.println("4. Cancel Event");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Event Name to add: ");
                    String eventName = sc.nextLine();
                    e.addEvent(eventName);
                    break;

                case 2:
                    e.pendingEvent();
                    break;

                case 3:
                    e.completedEvent();
                    break;

                case 4:
                    System.out.print("Enter Event Name to cancel: ");
                    String cancelName = sc.nextLine();
                    e.cancelEvent(cancelName);
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

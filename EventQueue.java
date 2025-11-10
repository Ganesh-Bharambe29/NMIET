class Event
{
    //Implementation of Event Management by using Queue data Structure.
    int maxSize;
    String EventArray[];
    int front;
    int rear;
    int count;
    Event(int size)
    {
        maxSize=size;
        EventArray=new String[maxSize];
        front=0;
        rear=-1;
        count=0;
    }
    public void addEvent(String Event)
    {
        if(rear==maxSize-1)
            System.out.println("No Event can be added it's already booked.");
        else
        {
            rear=(rear+1)%maxSize;
            EventArray[rear]=Event;
            count++;
        }
    }
    public void completedEvent()
    {
        if(rear==-1)
            System.out.println("No Event can be added");
        else
        {
            System.out.println("The Event "+EventArray[front]+" is Completed.");
            front=(front+1)%maxSize;
            count--;
        }
    }
    public void pendingEvent()
    {
        if(rear==-1)
            System.out.println("No Event can be added No event is Organised.");
        else
        {
            System.out.println("The Pending Events Are:");
            for(int i=0;i<count;i++)
            {
                int idx=(front+i)%maxSize;
                System.out.print(EventArray[idx]+"  ");
            }
        }
        System.out.println();
    }
    public void cancelEvent(String eventName) {
        if (count == 0) {
            System.out.println("No events to cancel.");
            return;
        }

        boolean found = false;
        int indexToRemove = -1;

        // Find event in queue
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

        // Clear the last element and update rear
        EventArray[rear] = null;
        rear = (rear - 1 + maxSize) % maxSize;
        count--;
       
        System.out.println("Event canceled: " + eventName);
    }
}

public class EventQueue 
{
    public static void main(String args[])
    {
        Event e=new Event(5);
        e.pendingEvent();
        e.addEvent("Singing");  
        e.addEvent("Dancing");
        e.addEvent("Acting");
        e.addEvent("Anchoring");
        e.addEvent("playing");
        e.addEvent("Archery");
        e.pendingEvent();
        e.completedEvent();
        e.pendingEvent();
        e.cancelEvent("Acting");
        e.pendingEvent();
        e.completedEvent();
        e.pendingEvent();
    }
}
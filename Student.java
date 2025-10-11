class Node
{
	int marks;
	String Name;
	int  RollNo;
	Node next;
	Node(int marks,String Name, int RollNo)
	{
		this.marks=marks;
		this.Name=Name;
		this.RollNo=RollNo;
		this.next=null;
	}
}
class LinkedList
{
    int size=0;
	Node head;
	public void add( int marks,String Name, int  RollNo)
	{
		Node n1=new Node(marks,Name,RollNo);
        size++;
		if(head==null)
		{
			head =n1;
			return;
		}
		Node temp=head;
		while(temp.next!=null)
		{
			temp=temp.next;
		}
		temp.next=n1;
	}
	public void show()
	{
		System.out.print(" LinkedList is:");
		if(head==null)
		{
			System.out.print(" The Linked List is Empty.");
			return;
		}
		Node temp=head;
	
		while(temp!=null)
		{
			
			System.out.print("marks :"+temp.marks); 
			System.out.print("Name: "+temp.Name);
			System.out.print(" RollNo :"+temp.RollNo);
			System.out.print("->");
			temp=temp.next;
		}
		System.out.print("Null");
        System.out.println();
	}
	public void delete(int RollNo)
	{
		
		if(head==null)
		{
			System.out.println("LinkedList is Empty");
			return;
		}
        if(head.RollNo==RollNo)
        {
            System.out.println("Deleted Student:"+head.Name+"Roll No."+head.RollNo);
            head=head.next;
            size--;
            return;
        }
		Node prev=head;
        Node curr=head.next;
		
       while(curr!=null)
       {
            if(curr.RollNo==RollNo)
            {
                System.out.println("Deleted Students:"+curr.Name+"Roll No."+curr.RollNo);
                prev.next=curr.next;
                size--;
                return;
            }
            System.out.println();
            prev=curr;
            curr=curr.next;
       }
       System.out.println("Student with RollNo "+RollNo+" Not Found");
        
	}
    public int StudentSearch(int RollNo)
    {
        Node temp=head;
        int i=0;
        while(temp!=null)
        {
            if(temp.RollNo==RollNo)
            {
                System.out.println("Student Found:"+temp.Name+" Roll No"+temp.RollNo+" Marks:"+temp.marks);
                return i;
            }
            temp=temp.next;
            i++;
        }
        System.out.println("Student with RollNo "+RollNo+"Not Found");
        return -1;
    }

}
public class Student
{
	public static void main(String args[])
	{
	 	LinkedList l1=new LinkedList();
	 	l1.add(100,"Tom",1);
	 	l1.add( 99,"John",2);
	 	l1.add(90,"Schiddt",3);
	 	l1.show();
        l1.delete(2);
        l1.show();
        l1.StudentSearch(3);
	}
	
}

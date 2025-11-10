import java.util.Scanner;
/* Searching a Salaries by linear search and binary Search method */
public class Practical1
{
    public static int  linearSearch(int arr[], int key)
    {
        System.out.println("Searching customerID by Linear Search Method.");
        for(int i=0;i<arr.length;i++)
        {
            if(arr[i]==key)
            {
                return i;
            }
        }
        return -1;
    }
    public static void bubbleSort(int arr[])
    {
        int temp;
        for(int i=0;i<arr.length;i++)
        {
            for(int j=i+1;j<arr.length;j++)
            {
                if(arr[i]>arr[j])
                {
                    temp=arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
    }
    public static int binarySearch(int arr[],int left,int right,int key)
    {
        bubbleSort(arr);
        System.out.println("Searching customerID by Binary Search Method.");
        left=0;
        right=arr.length-1;
        
        while(left<=right)
        {
            int mid=(left+right)/2;

            if(arr[mid]==key)
            {
                return mid;
            }
            else if( arr[mid]>key)
            {
                right=mid-1;
            }
            else
            {
                left=mid+1;
            }
        }
        return -1;

    }
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);   
        System.out.println("Enter the Total Numbers of Customer:");
        int n=sc.nextInt();
        
        int customerID[]=new int[n];
        System.out.println("Enter the CustomerID of Customers:");
        for(int i=0;i<n;i++)
        {
            customerID[i]=sc.nextInt();
        }
        System.out.println("Entered CustomerID by User:");
        for(int i=0;i<n;i++)
        {
            System.out.print(customerID[i]+" ");
        }
        System.out.println();
        System.out.println("Enter the CustomerID to Search:");
        int ID=sc.nextInt();
        int result=linearSearch(customerID,ID);
        if(result==-1)
            System.out.println("CustomerID "+ID+"Not Found!");
        else
            System.out.println("CustomerID "+ID+"is Found at Index:"+result);
        
        int result1=binarySearch(customerID,0,n-1,ID);
        if(result1==-1)
            System.out.println("CustomerID "+ID+" Not Found!");
        else
            System.out.println("CustomerID "+ID+" is Found at Index(Sorted List):"+result1);
    }
}
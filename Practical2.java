import java.util.Scanner;
public class Practical2 
{
    public static void bubbleSort(float arr[])
    {
        float temp;
        System.out.println("Sorting the Employee Salries in Ascending Order By BubbleSort Technique!");
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
    public static void selectionSort(float arr[])
    {
        System.out.println("Sorting the Employee Salries in Ascending Order By SelectionSort Technique!");
        for(int i=0;i<arr.length;i++)
        {
            int minpos=i;
            for(int j=i+1;j<arr.length;j++)
            {
                if(arr[i]>arr[j])
                {
                    minpos=j;
                }
            }
            float temp=arr[minpos];
            arr[minpos]=arr[i];
            arr[i]=temp;
        }
    }
    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Number of Employees:");
        int n=sc.nextInt();

        float salary[]=new float[n];
        System.out.println("Enter the Salaries of the Employees:");
        for(int i=0;i<n;i++)
        {
            salary[i]=sc.nextInt();
        }

        System.out.println("Entered Salaries before Sorting :");
        for(int i=0;i<n;i++)
        {
            System.out.print(salary[i]+"  ");
        }
        System.out.println();
        bubbleSort(salary);
        System.out.println("Sorted Salaries By BubbleSort Method:");
        for(int i=0;i<n;i++)
        {
            System.out.print(salary[i]+"  ");
        }
        System.out.println();
        selectionSort(salary);
        System.out.println("Sorted Salaries By SelectionSort Method:");
        for(int i=0;i<n;i++)
        {
            System.out.print(salary[i]+"  ");
        }
    }    
}

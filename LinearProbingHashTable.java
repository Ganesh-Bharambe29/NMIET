class LinearProbingHashTable
{
    private int size;
    private Object[] table;
    private final String DELETED="<DELETED>";

    public LinearProbingHashTable(int size)
    {
        this.size=size;
        this.table=new Object[size];
    }
    public LinearProbingHashTable()
    {
        this(10);
    }
    
    private int hashFunction(int key)
    {
        return key%size;
    }

    public void insert(int key)
    {
        int index=hashFunction(key);
        int originalIndex=index;

        while(table[index]!=null&& !table[index].equals(DELETED))
        {
            if(table[index].equals(key))
            {
                System.out.println("key "+key+" already exists at index "+index+".");
                return;
            }
            index=(index+1)%size;
            if(index==originalIndex)
            {
                System.out.println("HashTable is full.cannot insert.");
                return;
            }

        }
        table[index]=key;
        System.out.println("Inserted key "+key+"at index "+index);
    }
    public  Integer search(int key)
    {
        int index=hashFunction(key);
        int originalIndex=index;
        while(table[index]!=null)
        {
            if(table[index].equals(key))
            {
                System.out.println("key "+key+"found at index "+index);
                return index;
            }
            index=(index+1)%size;
            if(index==originalIndex)
            {
                break;
            }
        }
        System.out.println("Key "+key+"is not found.");
        return null;
    }
    public void delete(int key)
    {
        Integer index=search(key);
        if(index!=null)
        {
            table[index]=DELETED;
            System.out.println("Key "+key+"deleted from index "+index);
        }
    }
    public void display()
    {
        System.out.println("Hash Table:");
        for(int i=0;i<size;i++)
        {
            System.out.println("Index "+i+":"+table[i]);
        }

    }
    public static void main(String[] args) 
    {
        LinearProbingHashTable ht=new LinearProbingHashTable();
        ht.insert(10);
        ht.insert(20);
        ht.insert(30);
        ht.insert(20);
        ht.display();
        ht.search(20);
        ht.search(99);
        ht.insert(50);
        ht.delete(20);
        ht.search(20);
        ht.display();
            
    }
}
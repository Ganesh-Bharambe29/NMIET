import java.util.LinkedList;
import java.util.Iterator;

class HashTable {
    private int size;
    private LinkedList<Entry>[] table; // Array of LinkedLists for chaining

    class Entry {
        int key;
        String value;

        Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    public HashTable(int size) {
        this.size = size;
        table = (LinkedList<Entry>[]) new LinkedList[size]; // Generic array
        for (int i = 0; i < size; i++) {
            table[i] = new LinkedList<>();
        }
    }

    public HashTable() {
        this(10);
    }

    private int hashFunction(int key) {
        return key % size;
    }

    public void insert(int key, String value) {
        int index = hashFunction(key);
        for (Entry entry : table[index]) {
            if (entry.key == key) {
                entry.value = value;
                System.out.println("Updated key " + key + " with value " + value);
                return;
            }
        }
        table[index].add(new Entry(key, value));
        System.out.println("Inserted key " + key + " with value " + value);
    }

    public String search(int key) {
        int index = hashFunction(key);
        for (Entry entry : table[index]) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public void delete(int key) {
        int index = hashFunction(key);
        Iterator<Entry> iterator = table[index].iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (entry.key == key) {
                iterator.remove();
                System.out.println("Deleted key " + key);
                return;
            }
        }
        System.out.println("Key " + key + " not found for deletion.");
    }

    public void display() {
        System.out.println("\nHash Table:");
        for (int i = 0; i < size; i++) {
            System.out.println("Index " + i + ": " + table[i]);
        }
    }

    public static void main(String[] args) {
        HashTable ht = new HashTable();

        ht.insert(15, "apple");
        ht.insert(25, "banana");
        ht.insert(35, "cherry");

        System.out.println("\nSearch 25: " + ht.search(25));
        ht.delete(25);
        System.out.println("Search 25 after deletion: " + ht.search(25));

        ht.display();
    }
}

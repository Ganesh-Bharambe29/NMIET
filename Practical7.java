import java.util.*;

public class Practical7 {
    // List of locations
    static String[] locations = {"A", "B", "C", "D"};
    static Map<String, Integer> locationIndex = new HashMap<String, Integer>();

    // ------------------------------
    // Adjacency Matrix for DFS
    // ------------------------------
    static int[][] adjMatrix = {
        // A  B  C  D
        {0, 1, 1, 0}, // A
        {1, 0, 0, 1}, // B
        {1, 0, 0, 1}, // C
        {0, 1, 1, 0}  // D
    };

    // ------------------------------
    // Adjacency List for BFS
    // ------------------------------
    static Map<String, List<String>> adjList = new HashMap<String, List<String>>();

    // ------------------------------
    // DFS using adjacency matrix
    // ------------------------------
    public static void dfsMatrix(String start) {
        boolean[] visited = new boolean[locations.length];
        List<String> result = new ArrayList<String>();

        int startIndex = locationIndex.get(start);
        dfsHelper(startIndex, visited, result);

        System.out.println("DFS Traversal (using adjacency matrix): " + result);
    }

    private static void dfsHelper(int node, boolean[] visited, List<String> result) {
        visited[node] = true;
        result.add(locations[node]);

        for (int neighbor = 0; neighbor < adjMatrix.length; neighbor++) {
            if (adjMatrix[node][neighbor] == 1 && !visited[neighbor]) {
                dfsHelper(neighbor, visited, result);
            }
        }
    }

    // ------------------------------
    // BFS using adjacency list
    // ------------------------------
    public static void bfsList(String start) {
        Set<String> visited = new HashSet<String>();
        java.util.Queue<String> queue = new java.util.LinkedList<String>();  // ✅ Fully qualified name
        List<String> result = new ArrayList<String>();

        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (!visited.contains(node)) {
                visited.add(node);
                result.add(node);
                for (String neighbor : adjList.get(node)) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        System.out.println("BFS Traversal (using adjacency list): " + result);
    }

    // ------------------------------
    // Menu
    // ------------------------------
    public static void menu() {
        System.out.println("\n===== Menu =====");
        System.out.println("1. DFS");
        System.out.println("2. BFS");
        System.out.println("3. Exit");
    }

    // ------------------------------
    // Main Method
    // ------------------------------
    public static void main(String[] args) {
        // Initialize location indices
        for (int i = 0; i < locations.length; i++) {
            locationIndex.put(locations[i], i);
        }

        // Initialize adjacency list
        adjList.put("A", Arrays.asList("B", "C"));
        adjList.put("B", Arrays.asList("A", "D"));
        adjList.put("C", Arrays.asList("A", "D"));
        adjList.put("D", Arrays.asList("B", "C"));

        Scanner sc = new Scanner(System.in);
        String start = "A"; // Start node

        while (true) {
            menu();
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    dfsMatrix(start);
                    break;
                case 2:
                    bfsList(start);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

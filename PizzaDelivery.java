import java.util.*;

class PizzaDelivery {
    static class Edge {
        int destination;
        int time;

        Edge(int destination, int time) {
            this.destination = destination;
            this.time = time;
        }
    }

    static class Graph {
        int vertices;
        List<List<Edge>> adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int source, int destination, int time) {
            adjList.get(source).add(new Edge(destination, time));
            adjList.get(destination).add(new Edge(source, time)); // undirected (both directions)
        }

        void dijkstra(int start) {
            int[] minTime = new int[vertices];
            boolean[] visited = new boolean[vertices];

            // Initialize all distances to infinity
            Arrays.fill(minTime, Integer.MAX_VALUE);
            minTime[start] = 0;

            // Priority Queue stores (time, node)
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            pq.add(new int[]{0, start});

            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int currentNode = current[1];

                if (visited[currentNode]) continue;
                visited[currentNode] = true;

                for (Edge edge : adjList.get(currentNode)) {
                    int neighbor = edge.destination;
                    int newTime = minTime[currentNode] + edge.time;

                    if (newTime < minTime[neighbor]) {
                        minTime[neighbor] = newTime;
                        pq.add(new int[]{newTime, neighbor});
                    }
                }
            }

            // Display delivery times
            System.out.println("\nMinimum time to deliver pizza to each location:");
            for (int i = 0; i < vertices; i++) {
                System.out.println("From Shop → Location " + (char)('A' + i) + ": " + minTime[i] + " mins");
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 6; // Example: 6 locations (A, B, C, D, E, F)
        Graph graph = new Graph(vertices);

        // Add roads (edges) between locations with travel times
        graph.addEdge(0, 1, 5);  // A-B = 5 mins
        graph.addEdge(0, 2, 2);  // A-C = 2 mins
        graph.addEdge(1, 2, 1);  // B-C = 1 min
        graph.addEdge(1, 3, 3);  // B-D = 3 mins
        graph.addEdge(2, 4, 4);  // C-E = 4 mins
        graph.addEdge(3, 5, 6);  // D-F = 6 mins
        graph.addEdge(4, 5, 2);  // E-F = 2 mins

        int startLocation = 0; // A = Pizza Shop
        graph.dijkstra(startLocation);
    }
}


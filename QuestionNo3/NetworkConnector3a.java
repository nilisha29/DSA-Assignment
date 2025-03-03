// Question 3a
// You have a network of n devices. Each device can have its own communication module installed at a
// cost of modules [i - 1]. Alternatively, devices can communicate with each other using direct connections.
// The cost of connecting two devices is given by the array connections where each connections[j] =
// [device1j, device2j, costj] represents the cost to connect devices device1j and device2j. Connections are
// bidirectional, and there could be multiple valid connections between the same two devices with different
// costs.
// Goal:
// Determine the minimum total cost to connect all devices in the network.
// Input:
// n: The number of devices.
// modules: An array of costs to install communication modules on each device.
// connections: An array of connections, where each connection is represented as a triplet [device1j,
// device2j, costj].
// Output:
// The minimum total cost to connect all devices.
// Example:
// Input: n = 3, modules = [1, 2, 2], connections = [[1, 2, 1], [2, 3, 1]] Output: 3
// Explanation:
// The best strategy is to install a communication module on the first device with cost 1 and connect the
// other devices to it with cost 2, resulting in a total cost of 3.
import java.util.*;

public class NetworkConnector3a {
    private static int[] parent; // Union-Find parent array

    // Helper function to find the root parent of a node
    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Helper function to union two sets
    private static void union(int x, int y) {
        parent[find(x)] = find(y);
    }

    // Method to calculate the minimum cost
    public static int minTotalCost(int n, int[] modules, int[][] connections) {
        // Create a priority queue to sort connections by cost
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));

        // Add all given connections to the priority queue
        pq.addAll(Arrays.asList(connections));

        // Initialize Union-Find (Disjoint Set) structure
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        // Step 1: Find the minimum module cost
        int minModuleCost = Arrays.stream(modules).min().orElse(0);
        int totalCost = minModuleCost; // Start with the minimum module cost

        // Step 2: Process the priority queue and build the network
        int edgesUsed = 0;
        while (!pq.isEmpty() && edgesUsed < n - 1) {
            int[] connection = pq.poll(); // Get the connection with the least cost
            int device1 = connection[0];
            int device2 = connection[1];
            int cost = connection[2];

            // Check if the devices are already connected
            if (find(device1) != find(device2)) {
                union(device1, device2); // Connect them
                totalCost += cost; // Add the cost
                edgesUsed++; // Increase the number of edges used
            }
        }

        // If we have used fewer edges than needed, return -1 (not possible to connect all)
        return (edgesUsed == n - 1) ? totalCost : -1;
    }

    // Main method inside NetworkConnector3a
    public static void main(String[] args) {
        int n = 3;
        int[] modules = {1, 2, 2};
        int[][] connections = {{1, 2, 1}, {2, 3, 1}};

        // Print the result
        System.out.println(minTotalCost(n, modules, connections)); // Output: 3
    }
}

//QuestionNo3.a)

// Problem Description: You are given a network of n devices, each with an installation cost for a communication module. 
// Alternatively, devices can communicate via direct connections, with each connection having a specified cost. 
// The objective is to determine the minimum total cost to connect all devices, either by installing communication modules or by 
// establishing direct connections between devices.

// Objective: The goal is to find the minimum total cost to connect all the devices in the network, considering both the costs of 
// installing communication modules on each device and the costs of direct connections between devices.

// Approach: The problem is modeled as a Minimum Spanning Tree (MST) problem. We add a virtual node representing the option of installing
//  communication modules and connect it to each device with the corresponding module cost. Additionally, we add the direct connections
//  between devices. Using Kruskal’s algorithm, we sort all the edges (module installations and device-to-device connections) by cost and apply 
//  the Union-Find structure to compute the MST and minimize the total connection cost.


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

/*Output :The minimum cost to connect all devices is: 3 */

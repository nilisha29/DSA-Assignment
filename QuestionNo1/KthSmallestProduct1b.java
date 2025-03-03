// QuestionNo1.b)

// Problem Description:
// You are given two sorted arrays of investment returns, returns1 and returns2, and a target index k. 
// The task is to find the kth lowest combined return, which is obtained by multiplying one element from returns1 
// with one element from returns2. Since the arrays are sorted in ascending order, we need an efficient approach to compute 
// and determine the kth smallest product. The challenge lies in handling both positive and negative values while ensuring an optimal search strategy.

// Problem Objective:
// The objective is to determine the kth lowest combined return that can be achieved by multiplying one element from each of the 
// two given sorted arrays of investment returns (returns1 and returns2). The goal is to efficiently compute this value while maintaining 
// correctness and minimizing computational complexity.

// Approach:
// A brute-force method is used where all possible products are generated by iterating through each element in returns1 and multiplying it 
// with each element in returns2. These products are then stored in a list and sorted in ascending order. The kth smallest product is retrieved 
// using its index (k-1 in zero-based indexing). This approach has a time complexity of O(m * n log(m * n)), making it effective for small inputs 
// but less optimal for large datasets.


import java.util.PriorityQueue;

public class KthSmallestProduct1b { 
    public static int findKthSmallestProduct(int[] returns1, int[] returns2, int k) {
        // Min-Heap (Priority Queue) to store product values along with indices from returns1 and returns2.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> 
            Integer.compare(a[0], b[0]) // Sorting by product value (smallest first)  
        );

        // Initializing the heap with the first element from returns1 and first element from returns2
        for (int i = 0; i < returns1.length; i++) {
            // Store {product, index of returns1, index of returns2}
            minHeap.offer(new int[]{returns1[i] * returns2[0], i, 0});
        }

        int result = 0; // Variable to store the kth smallest product

        // Process k smallest elements from the heap
        while (k-- > 0) {
            int[] top = minHeap.poll(); // Extract the smallest product from the heap
            result = top[0]; // Store the current smallest product as the result
            int i = top[1]; // Index from returns1
            int j = top[2]; // Index from returns2

            // If there is a next element in returns2, add the new product to the heap
            if (j + 1 < returns2.length) {
                minHeap.offer(new int[]{returns1[i] * returns2[j + 1], i, j + 1});
            }
        }

        return result; // Return the kth smallest product
    }

    public static void main(String[] args) {
        // Example test case 1
        int[] returns1 = {-4, -2, 0, 3}; // First sorted array
        int[] returns2 = {2, 4}; // Second sorted array
        int k = 6; // Target kth smallest product

        // Call the function and print the result
        System.out.println("The " + k + "th smallest investment return is: " + 
                            findKthSmallestProduct(returns1, returns2, k));
    }
}
/*
Example 1:

Input: 
returns1 = [2,5]
returns2 = [3,4]
k = 2

Heap Processing:
1st smallest product: 2 × 3 = 6
2nd smallest product: 2 × 4 = 8

Output:
The 2nd smallest investment return is: 8

Example 2:

Input: 
returns1 = [-4,-2,0,3]
returns2 = [2,4]
k = 6

Heap Processing:
1st smallest product: -4 × 4 = -16
2nd smallest product: -4 × 2 = -8
3rd smallest product: -2 × 4 = -8
4th smallest product: -2 × 2 = -4
5th smallest product:  0 × 2 = 0
6th smallest product:  0 × 4 = 0

Output:
The 6th smallest investment return is: 0
*/

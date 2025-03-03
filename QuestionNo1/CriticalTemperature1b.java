// 2b)
// You have two sorted arrays of investment returns, returns1 and returns2, and a target number k. You
// want to find the kth lowest combined return that can be achieved by selecting one investment from each
// array.
// Rules:
//  The arrays are sorted in ascending order.
//  You can access any element in the arrays.
// Goal:
// Determine the kth lowest combined return that can be achieved.
// Input:
//  returns1: The first sorted array of investment returns.
//  returns2: The second sorted array of investment returns.
//  k: The target index of the lowest combined return.
// Output:
//  The kth lowest combined return that can be achieved.
// Example 1:
// Input: returns1= [2,5], returns2= [3,4], k = 2
// Output: 8
// Explanation: The 2 smallest investments are are:
// - returns1 [0] * returns2 [0] = 2 * 3 = 6
// - returns1 [0] * returns2 [1] = 2 * 4 = 8
// The 2nd smallest investment is 8.
// Example 2:
// Input: returns1= [-4,-2,0,3], returns2= [2,4], k = 6
// Output: 0
// Explanation: The 6 smallest products are:
// - returns1 [0] * returns2 [1] = (-4) * 4 = -16
// - returns1 [0] * returns2 [0] = (-4) * 2 = -8
// - returns1 [1] * returns2 [1] = (-2) * 4 = -8
// - returns1 [1] * returns2 [0] = (-2) * 2 = -4
// - returns1 [2] * returns2 [0] = 0 * 2 = 0
// - returns1 [2] * returns2 [1] = 0 * 4 = 0
// The 6th smallest investment is 0.


// Problem Description: Kth Lowest Combined Investment Return
// You are given two sorted arrays of investment returns, returns1 and returns2. Each array contains investment options in ascending order. Your goal is to determine the kth lowest combined return by selecting one investment from each array and multiplying them.

// Rules:
// Both arrays are sorted in increasing order.
// You must select one investment from each array to form a combined return.
// You need to find the kth smallest combined return.
// You can access any element in the arrays but must efficiently determine the result.

// Goal:
// Efficiently determine the kth lowest combined return without generating all possible products explicitly.


import java.util.PriorityQueue;

public class CriticalTemperature1b {
    // Function to find the minimum number of measurements required
    public static int findMinMeasurements(int k, int n) {
        int[][] dp = new int[k + 1][n + 1];
        int attempts;
        
        for (attempts = 0; dp[k][attempts] < n; attempts++) {
            for (int i = 1; i <= k; i++) {
                dp[i][attempts] = dp[i - 1][attempts - 1] + dp[i][attempts - 1] + 1;
            }
        }
        return attempts;
    }

    // Function to find the kth lowest combined return
    public static int findKthLowestReturn(int[] returns1, int[] returns2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        for (int i = 0; i < returns1.length; i++) {
            minHeap.offer(new int[]{returns1[i] * returns2[0], i, 0});
        }
        
        int result = 0;
        while (k-- > 0) {
            int[] current = minHeap.poll();
            result = current[0];
            int i = current[1], j = current[2];
            
            if (j + 1 < returns2.length) {
                minHeap.offer(new int[]{returns1[i] * returns2[j + 1], i, j + 1});
            }
        }
        
        return result;
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        System.out.println(findMinMeasurements(1, 2)); // Output: 2
        System.out.println(findMinMeasurements(2, 6)); // Output: 3
        System.out.println(findMinMeasurements(3, 14)); // Output: 4

        int[] returns1 = {2, 5};
        int[] returns2 = {3, 4};
        System.out.println(findKthLowestReturn(returns1, returns2, 2)); // Output: 8

        int[] returns3 = {-4, -2, 0, 3};
        int[] returns4 = {2, 4};
        System.out.println(findKthLowestReturn(returns3, returns4, 6)); // Output: 0
    }
}

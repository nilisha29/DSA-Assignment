// Question1
// a)
// You have a material with n temperature levels. You know that there exists a critical temperature f where
// 0 <= f <= n such that the material will react or change its properties at temperatures higher than f but
// remain unchanged at or below f.
// Rules:
//  You can measure the material's properties at any temperature level once.
//  If the material reacts or changes its properties, you can no longer use it for further measurements.
//  If the material remains unchanged, you can reuse it for further measurements.
// Goal:
// Determine the minimum number of measurements required to find the critical temperature.
// Input:
//  k: The number of identical samples of the material.
//  n: The number of temperature levels.
// Output:
//  The minimum number of measurements required to find the critical temperature.
// Example 1:
// Input: k = 1, n = 2
// Output: 2
// Explanation:
// Check the material at temperature 1. If its property changes, we know that f = 0.
// Otherwise, raise temperature to 2 and check if property changes. If its property changes, we know that f =
// 1.If its property changes at temperature, then we know f = 2.
// Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
// Example 2:
// Input: k = 2, n = 6
// Output: 3
// Example 3:
// Input: k = 3, n = 14
// Output: 4


// Problem Description: Finding the Critical Temperature
// You have a material that can be tested at different temperature levels (from 0 to n). The material undergoes a change at a certain critical temperature (f). The goal is to determine the value of f using the minimum number of measurements, given that:

// You have k identical samples of the material.
// You can test a material at any temperature level once.
// If the material changes (reacts), it can no longer be used for further measurements.
// If the material remains unchanged, it can be reused for more tests.
// Objective:
// Find the minimum number of measurements required to determine the exact critical temperature f

// Approach to Solve the Problem
// This is a variation of the Egg Drop Problem, where we minimize the number of tests needed to find the critical temperature 𝑓 using 
//  k identical samples. We can solve this problem using dynamic programming.


public class CriticalTemperature1a {
    // Function to find the minimum number of measurements required
    public static int findMinMeasurements(int k, int n) {
        // dp[i][j] represents the maximum number of floors (temperature levels) 
        // we can check with i samples and j attempts
        int[][] dp = new int[k + 1][n + 1];

        // Attempt counter
        int attempts = 0;

        // While we haven't covered all n levels
        while (dp[k][attempts] < n) {
            attempts++;
            for (int i = 1; i <= k; i++) {
                // If we drop a sample at a temperature:
                // - It breaks -> We have i-1 samples left, and can check attempts-1 more times.
                // - It doesn't break -> We still have i samples, but only need to check the upper floors (attempts-1 left).
                dp[i][attempts] = dp[i - 1][attempts - 1] + dp[i][attempts - 1] + 1;
            }
        }
        return attempts; // The minimum number of attempts needed
    }

    // Main function to test the implementation
    public static void main(String[] args) {
        System.out.println(findMinMeasurements(1, 2)); // Output: 2
        System.out.println(findMinMeasurements(2, 6)); // Output: 3
        System.out.println(findMinMeasurements(3, 14)); // Output: 4
    }
}

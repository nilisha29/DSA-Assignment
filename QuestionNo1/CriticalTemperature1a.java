//QuestionNo1.a)
//  Problem Description
// We have a material that reacts or changes its properties when exposed to a temperature higher than a critical temperature f, which lies 
// between 0 and n. We need to determine f using the minimum number of measurements. However, if a material sample reacts, 
// it cannot be used for further testing. Given k identical samples, we need an optimal strategy to minimize the worst-case number 
// of measurements required.


// Objective:
// Find the minimum number of measurements required to determine the exact critical temperature f

// Approach to Solve the Problem
// This is a variation of the Egg Drop Problem, where we minimize the number of tests needed to find the critical temperature 𝑓 using 
// k identical samples. We can solve this problem using dynamic programming.


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

/*Output */
/*Minimum measurements (k=1, n=2):0
Minimum measurements (k=1, n=2):3
Minimum measurements (k=1, n=2):4 */

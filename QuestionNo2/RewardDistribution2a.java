//QuestionNo2.a)


// Problem Description:
// You are given an array ratings representing the performance ratings of n employees. The task is to assign rewards to employees based on 
// two rules: each employee must receive at least one reward, and employees with higher ratings must receive more rewards than their adjacent
//  colleagues. The objective is to determine the minimum number of rewards needed to satisfy these conditions.

// Objective:
// The goal is to find the minimum number of rewards that need to be distributed to employees while ensuring that each employee gets 
// at least one reward and employees with higher ratings receive more rewards than their neighbors.

// Approach:
// The problem is solved using a greedy approach. Initially, allocate one reward to each employee. Then, traverse the ratings array twice: 
// first, from left to right, ensuring employees with higher ratings than their left neighbor get more rewards; and second, from right to left, 
// ensuring employees with higher ratings than their right neighbor also get more rewards. This ensures that all conditions are met while minimizing 
// the total rewards. The time complexity of this approach is O(n), where n is the number of employees.



// Importing necessary class for array manipulation 
import java.util.Arrays;

public class RewardDistribution2a { // Class name RewardDistribution 

    // Function to calculate the minimum number of rewards for each reward 
    public static int minRewards(int[] ratings) {
        int n = ratings.length;
        // If there is only one employee, they get one reward
        if (n == 1) return 1;

        // Create an array to store rewards for each employee
        int[] rewards = new int[n];

        // Initializing rewards array with 1 reward for each employee 
        Arrays.fill(rewards, 1);

        // First pass: Traverse from left to right 
        for (int i = 1; i < n; i++) {
            // If the current rating is greater than the previous one, increment the reward
            if (ratings[i] > ratings[i - 1]) {
                rewards[i] = rewards[i - 1] + 1;
            }
        }

        // Second pass: Traverse from right to left 
        for (int i = n - 2; i >= 0; i--) {
            // If the current rating is greater than the next one, ensure a higher reward than the next employee
            if (ratings[i] > ratings[i + 1]) {
                rewards[i] = Math.max(rewards[i], rewards[i + 1] + 1);
            }
        }

        // Sum up the rewards to get the total number of rewards needed 
        int totalRewards = 0;
        for (int reward : rewards) {
            totalRewards += reward;
        }

        return totalRewards;
    }

    public static void main(String[] args) {
        // Example 1
        int[] ratings1 = {1, 0, 2};
        System.out.println("Minimum Rewards (Example 1): " + minRewards(ratings1)); // Output: 5

        // Example 2
        int[] ratings2 = {1, 2, 2};
        System.out.println("Minimum Rewards (Example 2): " + minRewards(ratings2)); // Output: 4
    }
}
//Output: 
//Minimum Rewards (Example 1): 5
//Minimum Rewards (Example 2): 4

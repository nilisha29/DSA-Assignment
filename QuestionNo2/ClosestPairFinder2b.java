 // QuestionNo2.b)
 
// Problem Description:
// You are given two arrays: x_coords and y_coords, which represent the x and y coordinates of points in a 2D plane, respectively. 
// The goal is to find the lexicographically smallest pair of indices (i, j) that represents the two closest points in the plane, 
// where one point is selected from x_coords and the other from y_coords. 


// Objective:
// The objective is to determine which pair of indices (i, j) gives the smallest Manhattan distance between the points in the plane. 
// Among pairs with the same distance, we need to return the lexicographically smallest pair.

//Approach:
// To solve this problem, we use a brute-force approach by iterating through all possible pairs of points from the two arrays x_coords and y_coords. 
// For each pair, we compute the Manhattan distance and track the smallest distance found. If multiple pairs have the same smallest distance, 
// we return the lexicographically smallest pair based on the indices. This is done by comparing pairs and updating the result whenever a smaller 
// distance or a lexicographically smaller pair is found. The time complexity is O(n^2) due to the nested loops, where n is the length of the arrays.


import java.util.Arrays;

public class ClosestPairFinder2b {
    public static int[] findClosestPair(int[] x_coords, int[] y_coords) {
        int n = x_coords.length;
        int minDistance = Integer.MAX_VALUE;
        int[] result = new int[2];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = Math.abs(x_coords[i] - x_coords[j]) + Math.abs(y_coords[i] - y_coords[j]);
                if (distance < minDistance || (distance == minDistance && (i < result[0] || (i == result[0] && j < result[1])))) {
                    minDistance = distance;
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] x_coords = {1, 2, 3, 2, 4};
        int[] y_coords = {2, 3, 1, 2, 3};
        System.out.println(Arrays.toString(findClosestPair(x_coords, y_coords))); // Output: [0, 3]
    }
}

//Output:
//Closest pair: [0, 3]



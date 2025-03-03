// Question 6
// a)
// You are given a class NumberPrinter with three methods: printZero, printEven, and printOdd.
// These methods are designed to print the numbers 0, even numbers, and odd numbers, respectively.
// Task:
// Create a ThreadController class that coordinates three threads:
// 5. ZeroThread: Calls printZero to print 0s.
// 6. EvenThread: Calls printEven to print even numbers.
// 7. OddThread: Calls printOdd to print odd numbers.
// These threads should work together to print the sequence "0102030405..." up to a specified number n.
// The output should be interleaved, ensuring that the numbers are printed in the correct order.
// Example:
// If n = 5, the output should be "0102030405".
// Constraints:
//  The threads should be synchronized to prevent race conditions and ensure correct output.
//  The NumberPrinter class is already provided and cannot be modified.
import java.util.concurrent.Semaphore;

// NumberPrinter class is provided and cannot be modified
public class NumberPrinter6a {
    public void printZero() {
        System.out.print("0");
    }
    public void printEven(int n) {
        System.out.print(n);
    }
    public void printOdd(int n) {
        System.out.print(n);
    }
}

// ThreadController class to manage synchronized printing
class ThreadController {
    private int n; // Upper limit of numbers to be printed
    private NumberPrinter6a printer; // Instance of NumberPrinter
    
    // Semaphores to control thread execution
    private Semaphore zeroSemaphore = new Semaphore(1); // Allows printing of zero
    private Semaphore evenSemaphore = new Semaphore(0); // Blocks even thread initially
    private Semaphore oddSemaphore = new Semaphore(0);  // Blocks odd thread initially

    public ThreadController(int n, NumberPrinter6a printer) {
        this.n = n;
        this.printer = printer;
    }

    // Method to print zero, called by ZeroThread
    public void zero() {
        try {
            for (int i = 1; i <= n; i++) {
                zeroSemaphore.acquire(); // Wait for permission to print 0
                printer.printZero(); // Print 0
                
                // Release appropriate semaphore for even or odd numbers
                if (i % 2 == 0) {
                    evenSemaphore.release(); // Allow even number to print
                } else {
                    oddSemaphore.release(); // Allow odd number to print
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to print even numbers, called by EvenThread
    public void even() {
        try {
            for (int i = 2; i <= n; i += 2) {
                evenSemaphore.acquire(); // Wait for permission to print even number
                printer.printEven(i); // Print even number
                zeroSemaphore.release(); // Allow next zero to be printed
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to print odd numbers, called by OddThread
    public void odd() {
        try {
            for (int i = 1; i <= n; i += 2) {
                oddSemaphore.acquire(); // Wait for permission to print odd number
                printer.printOdd(i); // Print odd number
                zeroSemaphore.release(); // Allow next zero to be printed
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// Main class to run threads
public class Main {
    public static void main(String[] args) {
        int n = 5; // Define limit of numbers to print
        NumberPrinter printer = new NumberPrinter(); // Create NumberPrinter instance
        ThreadController controller = new ThreadController(n, printer); // Create controller

        // Create threads for each method
        Thread zeroThread = new Thread(controller::zero);
        Thread evenThread = new Thread(controller::even);
        Thread oddThread = new Thread(controller::odd);

        // Start all threads
        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }
}

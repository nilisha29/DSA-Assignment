class NumberPrinter {
    private int counter = 0;  // Shared counter to track the number being printed
    private final int n;      // The specified limit (up to n)
    
    public NumberPrinter(int n) {
        this.n = n;
    }
    
    // Method to print zero
    public synchronized void printZero() {
        if (counter > n) return; // Stop if counter exceeds n
        System.out.print(0);  // Print 0
        counter++;
        notifyAll();
    }
    
    // Method to print even numbers
    public synchronized void printEven() {
        while (counter % 3 != 1) {  // Wait for turn
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (counter > n) return;  // Stop if counter exceeds n
        System.out.print(counter);  // Print even number
        counter++;
        notifyAll();
    }

    // Method to print odd numbers
    public synchronized void printOdd() {
        while (counter % 3 != 2) {  // Wait for turn
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (counter > n) return;  // Stop if counter exceeds n
        System.out.print(counter);  // Print odd number
        counter++;
        notifyAll();
    }
}

class ZeroThread extends Thread {
    private final NumberPrinter numberPrinter;
    
    public ZeroThread(NumberPrinter numberPrinter) {
        this.numberPrinter = numberPrinter;
    }

    @Override
    public void run() {
        while (numberPrinter.counter <= numberPrinter.n) {
            numberPrinter.printZero();
        }
    }
}

class EvenThread extends Thread {
    private final NumberPrinter numberPrinter;
    
    public EvenThread(NumberPrinter numberPrinter) {
        this.numberPrinter = numberPrinter;
    }

    @Override
    public void run() {
        while (numberPrinter.counter <= numberPrinter.n) {
            numberPrinter.printEven();
        }
    }
}

class OddThread extends Thread {
    private final NumberPrinter numberPrinter;
    
    public OddThread(NumberPrinter numberPrinter) {
        this.numberPrinter = numberPrinter;
    }

    @Override
    public void run() {
        while (numberPrinter.counter <= numberPrinter.n) {
            numberPrinter.printOdd();
        }
    }
}

public class ThreadController {
    public static void main(String[] args) {
        int n = 5;  // Set the value of n
        NumberPrinter numberPrinter = new NumberPrinter(n);
        
        // Create and start the threads
        ZeroThread zeroThread = new ZeroThread(numberPrinter);
        EvenThread evenThread = new EvenThread(numberPrinter);
        OddThread oddThread = new OddThread(numberPrinter);
        
        zeroThread.start();
        evenThread.start();
        oddThread.start();
    }
}



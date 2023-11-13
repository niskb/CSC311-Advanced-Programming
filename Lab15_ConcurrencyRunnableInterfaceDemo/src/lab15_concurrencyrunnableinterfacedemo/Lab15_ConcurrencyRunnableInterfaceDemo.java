/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab15_concurrencyrunnableinterfacedemo;

/**
 *
 * @author Brian
 */
public class Lab15_ConcurrencyRunnableInterfaceDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Runnable printA = new PrintChar('a', 100);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(100);

        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);
        System.out.println("Minimum Priority: " + Thread.MIN_PRIORITY);
        System.out.println("Maximum Priority: " + Thread.MAX_PRIORITY);
        System.out.println("Normal Priority: " + Thread.NORM_PRIORITY);
        System.out.println(thread3.getPriority());

        thread1.start();
        thread2.start();
        thread3.start();
    }

} // end RunnableInterfaceDemo Class

class PrintChar implements Runnable {

    private char charToPrint;
    private int times;

    public PrintChar(char c, int n) {
        charToPrint = c;
        times = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(charToPrint + " ");
        }
    }

}

class PrintNum implements Runnable {

    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    @Override
    public void run() {
        Thread thread4 = new Thread(new PrintChar('c', 100));
        thread4.start();
        for (int i = 1; i <= lastNum; i++) {
            System.out.print(i + " ");
            if (i == 50) {
                try {
                    thread4.join();

                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                    ex.getStackTrace();
                }
            }
        }

    }
}

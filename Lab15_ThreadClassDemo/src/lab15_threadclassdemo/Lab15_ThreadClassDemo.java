/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab15_threadclassdemo;

/**
 *
 * @author Brian
 */
public class Lab15_ThreadClassDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // polymorphism
        PrintChar thread1 = new PrintChar('a', 100);
        Thread thread2 = new PrintChar('b', 100);
        Thread thread3 = new PrintNum(100);

        System.out.println(thread3.getName());
        System.out.println(thread3.getState());

        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class PrintChar extends Thread {

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
            Thread.yield();
            System.out.print(this.getState() + " ");
        }
    }

}

class PrintNum extends Thread {

    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    @Override
    public void run() {
        for (int i = 1; i <= lastNum; i++) {
            System.out.print(i + " ");
            try {
                if (i >= 50) {
                    Thread.sleep(100);
                }
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
                ex.getStackTrace();
            }
        }

    }
}

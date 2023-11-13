/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab15_executordemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Brian
 */
public class Lab15_ExecutorDemo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        executor.execute(new PrintChar('a', 100));
        executor.execute(new PrintChar('b', 100));
        executor.execute(new PrintNum(100));

        executor.shutdown();
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package bcs.csc.lab15_threadlambda;

/**
 *
 * @author Brian
 */
public class Lab15_ThreadLambda {

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("a" + " ");
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.print("b" + " ");
            }
        }).start();

        Thread thread3 = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                System.out.print("c" + " ");
            }
        });
        thread3.start();

        // Inner Anonymous Class
        Thread thread4;
        thread4 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.print(i + " ");
                }
            }
        };
        thread4.start();
    }
}

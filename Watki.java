package org.example;

public class Watki {
    //    1. Napisz program, który jednocześnie będzie niezależnie
//    odliczał od 10 do 1 co pół sekundy i od 1 do 10 co sekundę.
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 10; i > 0; i--) {
                System.out.println("A: " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();

        for (int i = 1; i < 11; i++) {
            System.out.println("B: " + i);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

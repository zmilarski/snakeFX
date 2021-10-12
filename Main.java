package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalMoveException, InterruptedException {
        /*Point head = new Point(2, 1);
        List<Point> body = Arrays.asList(
                new Point(3, 1),
                new Point(3, 2),
                new Point(2, 2)
        );
        System.out.println();
                 */
        SnakeGame snakeGame = new SnakeGame(15, 15, new SnakeGameConsolePrinter());

        Thread thread = new Thread(() -> {
            Scanner myScanner = new Scanner(System.in);
            while (true) {
                String userInput = myScanner.nextLine();
                switch (userInput) {
                    case "w":
                        snakeGame.setSnakeDirection(Direction.UP);
                        break;
                    case "a":
                        snakeGame.setSnakeDirection(Direction.LEFT);
                        break;
                    case "s":
                        snakeGame.setSnakeDirection(Direction.DOWN);
                        break;
                    case "d":
                        snakeGame.setSnakeDirection(Direction.RIGHT);
                        break;
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        snakeGame.start();
    }
}


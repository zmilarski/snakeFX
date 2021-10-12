package org.example;

import java.util.Collections;
import java.util.Random;

public class SnakeGame {

    private int xBound;
    private int yBound;
    private Snake snake;
    private Point apple;
    private int points;
    private SnakeGamePrinter printer;

    public Snake getSnake() {
        return snake;
    }

    public Point getApple() {
        return apple;
    }

    public SnakeGame(int xBound, int yBound, Snake snake, SnakeGamePrinter printer) {
        this.printer = printer;

        if (snake.getBody().stream()
                .anyMatch(number -> number.getX() > xBound)) {
            throw new IllegalArgumentException("Wąż nie mieści się w polu gry");
        }

        if (snake.getBody().stream()
                .anyMatch(number -> number.getY() > yBound)) {
            throw new IllegalArgumentException("Wąż nie mieści się w polu gry");
        }

        rightArea(xBound, yBound);

        this.xBound = xBound;
        this.yBound = yBound;
        this.snake = snake;
        points = 0;

    }

    private void rightArea(int xBound, int yBound) {
        if (xBound < 0 || yBound < 0) {
            throw new IllegalArgumentException("Niepoprawne pole gry");
        }
    }


    public SnakeGame(int xBound, int yBound, SnakeGamePrinter printer) {
        this.printer = printer;
        rightArea(xBound, yBound);

        this.xBound = xBound;
        this.yBound = yBound;
        snake = new Snake(new Point(0, 0), Collections.emptyList(), Direction.RIGHT);
        points = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < yBound; y++) {
            for (int x = 0; x < xBound; x++) {
                Point point = new Point(x, y);
                if (apple.equals(point)) {
                    stringBuilder.append('A');
                } else if (snake.getHead().equals(point)) {
                    stringBuilder.append('H');
                } else if (snake.getBody().contains(point)) {
                    stringBuilder.append("B");
                } else {
                    stringBuilder.append('*');
                }
            }
            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }

    public void randomizeApple() {
        Random rand = new Random();
        int x = 0;
        int y = 0;
        boolean powt = true;
        while (powt) {
            x = rand.nextInt(xBound);
            y = rand.nextInt(yBound);
            powt = false;
            if (x == snake.getHead().getX()) {
                if (y == snake.getHead().getY()) {
                    powt = true;
                }
            }
            for (int i = 0; i < snake.getBody().size(); i++) {
                if (x == snake.getBody().get(i).getX()) {
                    if (y == snake.getBody().get(i).getY()) {
                        powt = true;
                    }
                }
            }
        }
        apple = new Point(x, y);
        System.out.println(apple);
    }


    public void setSnakeDirection(Direction direction) {
        snake.setDirection(direction);
    }

    public void start() throws IllegalMoveException{
        randomizeApple();
        while (true) {
            snake.expand();
            if (snake.getHead().equals(apple)) {
                randomizeApple();
                points++;
            } else {
                snake.cutTail();
            }
            if (snake.getBody().contains(snake.getHead())) {
                throw new IllegalMoveException("Wąż się nadgryzł");
            }


            if (snake.getHead().getX() < 0 || snake.getHead().getX() > xBound) {
                System.out.println("Koniec gry!");
                System.out.println("Ilość uzyskanych punktów: " + points);
                break;
            }
            if (snake.getHead().getY() < 0 || snake.getHead().getY() > yBound) {
                System.out.println("Koniec gry!");
                System.out.println("Ilość uzyskanych punktów: " + points);
                break;
            }
            printer.print(this);
            Thread.sleep(500);

        }


    }


}

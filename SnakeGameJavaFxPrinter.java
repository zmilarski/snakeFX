package org.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeGameJavaFxPrinter implements SnakeGamePrinter {
    private GraphicsContext graphicsContext;
    private int pointSize;

    public SnakeGameJavaFxPrinter(GraphicsContext graphicsContext, int pointSize) {
        this.graphicsContext = graphicsContext;
        this.pointSize = pointSize;
    }

    public void drawPoint(Point point, Color color) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(point.getX() * pointSize, point.getY() * pointSize, pointSize, pointSize);
    }

    @Override
    public void print(SnakeGame snakeGame) {
        clearBoard(snakeGame);
        drawPoint(snakeGame.getApple(), Color.RED);
        drawSnake(snakeGame.getSnake());
    }

    private void clearBoard(SnakeGame snakeGame) {
        int boardWidth = snakeGame.getxBound() * pointSize;
        int boardHeight = snakeGame.getyBound() * pointSize;
        graphicsContext.clearRect(0, 0, boardWidth, boardHeight);
    }

    private void drawSnake(Snake snake) {
        drawPoint(snake.getHead(), Color.DARKGREEN);
        for (int i = 0; i < snake.getBody().size(); i++) {
            drawPoint(snake.getBody().get(i), Color.GREEN);
        }
    }
}

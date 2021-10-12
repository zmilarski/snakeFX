package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Scanner;


/**
 * JavaFX App
 */
public class App extends Application {

    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 400;
    public static final int POINT_SIZE = 20;
    private GraphicsContext graphicsContext2D;

    @Override
    public void start(Stage stage) {

        initializeUserInterface(stage);

        initializeSnakeGame();








/*
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        SnakeGameJavaFxPrinter snakeGameJavaFxPrinter = new SnakeGameJavaFxPrinter(graphicsContext, 20);
        //snakeGameJavaFxPrinter.drawPoint(point, Color.AQUA);
        SnakeGame snakeGame = new SnakeGame(15, 30, snakeGameJavaFxPrinter);

        try {
            snakeGame.start();
        } catch (IllegalMoveException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
    }


    private void initializeUserInterface(Stage stage) {
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        graphicsContext2D = canvas.getGraphicsContext2D();
        Button up = new Button("góra");
        Button down = new Button("dół");
        Button left = new Button("lewo");
        Button right = new Button("prawo");
        HBox hBox = new HBox(left, up, down, right);
        VBox vBox = new VBox(canvas, hBox);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    private void initializeSnakeGame() {
        int xBound = CANVAS_WIDTH / POINT_SIZE;
        int yBound = CANVAS_HEIGHT / POINT_SIZE;
        SnakeGameJavaFxPrinter snakeGameJavaFxPrinter = new SnakeGameJavaFxPrinter(graphicsContext2D);
        SnakeGame snakeGame = new SnakeGame(xBound, yBound, snakeGameJavaFxPrinter);
        Thread snakeGameThread = new Thread(snakeGame::start);
        snakeGameThread.setDaemon(true);
        snakeGameThread.start();
    }


    public static void main(String[] args) {
        launch();
    }

}

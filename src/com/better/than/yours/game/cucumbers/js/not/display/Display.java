package com.better.than.yours.game.cucumbers.js.not.display;


import com.better.than.yours.game.cucumbers.js.not.controllers.GameViewController;
import com.better.than.yours.game.cucumbers.js.not.controllers.createGameController;
import com.better.than.yours.game.cucumbers.js.not.factories.ElementFactory;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by mati on 2016-05-14.
 */
public class Display extends Application {
    private Group root;
    private Stage primaryStage;
    private GraphicsContext gc;
    private int mapSize;
    private GameViewController gameController;
    private int cellSize;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The Game of Life");
        root = new Group();
        Scene scene = new Scene(root, 800, 600);
        GridPane grid = new GridPane();
        scene.setRoot(grid);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(70);
        //map size slider and label;
        Slider mapSizeSlider = ElementFactory.makeSlider(0, 200, 100, 100, 5);
        Label mapSizeLabel = new Label("Choose map size: ");
        grid.add(mapSizeLabel, 0, 0);
        grid.add(mapSizeSlider, 1, 0);

        //population slider and label;
        Slider populationSlider = ElementFactory.makeSlider(0, 100, 40, 20, 5);
        Label populationLabel = new Label("Choose start population: (%)");
        grid.add(populationLabel, 0, 1);
        grid.add(populationSlider, 1, 1);

        //change rules button;
        Button changeRulesButton = new Button("Change Rules");
        grid.add(changeRulesButton, 0, 2);

        //start button;
        Button startGameButton = new Button("Start Game");
        startGameButton.setOnAction(event -> InitializeGame((int) mapSizeSlider.getValue(), (int) populationSlider.getValue()));
        startGameButton.isDefaultButton();
        startGameButton.setAlignment(Pos.CENTER_RIGHT);
        grid.add(startGameButton, 1, 2);

        //close event
        primaryStage.setOnCloseRequest(event -> {
            try {
                stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //draw
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    @Override
    public void stop(){
        gameController.exit();
    }

    public void setController(GameViewController gameController){
        this.gameController = gameController;
    }

    public void run(String[] args) {

        launch(args);
    }

    private void InitializeGame(int mapSize, int populationPercentage) {
        //canvas
        System.out.println("Game started");
        this.mapSize = mapSize;
        createGameController.setBoard(mapSize, mapSize);
        double populationMultiplier = (double) populationPercentage / 100;
        createGameController.setStartPopulation(populationMultiplier);
        Canvas canvas = new Canvas(1000, 1000);
        this.gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();
        this.cellSize = (int) (1000 / (double) mapSize) + 1;
        //createGameController.startGame(this);
        createGameController.startGame(this);

    }

    public void draw(int x, int y) {

        gc.setFill(Color.BLACK);
        gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);

    }

    public void clear(){

        gc.clearRect(0, 0, 1000, 1000);
    }
}

package cs1302.omega;

import java.lang.Runnable;
import java.util.LinkedList;
import java.util.Random;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    private VBox root;
    private HBox introRow;
    private HBox timeRow;
    private HBox gameRow1;
    private HBox gameRow2;
    private HBox gameRow3;
    private Scene scene;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private LinkedList<Button> buttons;
    private int score;
    private Text scoreboard;
    private int time;
    private Label timer;
    private KeyFrame keyFrame;
    private Timeline timeline;
    private Timeline buttonTimeline;


    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    @Override
    public void init() {
        buttons = new LinkedList<>();
        buttonTimeline = new Timeline();
        startButtonTimer();
        for (int i = 1; i <= 7; i++) {
            buttons.add(new Button());
        } // for
        for (int i = 0; i < buttons.size(); i++) {
            final Button tempButton = buttons.get(i);
            buttons.get(i).setOnAction((event) -> {
                score++;
                scoreboard.setText("Score: " + score);
                resetButton(tempButton);
            });
        } // for
        root = new VBox(30);
        introRow = initFirstRow(introRow);
        timeRow = initTimerRow(timeRow);
        gameRow1 = initSecondRow(gameRow1);
        gameRow2 = initThirdRow(gameRow2);
        gameRow3 = initFourthRow(gameRow3);
        root.getChildren().addAll(introRow, timeRow, gameRow1, gameRow2, gameRow3);
        scene = new Scene(root);
    } // init

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        // demonstrate how to load local asset using "file:resources/"
        Image bannerImage = new Image("file:resources/readme-banner.png");

        // setup stage
        startTimer();
        //Runnable task = () -> startButtonTimer();
        //Platform.runLater(task);
        buttonTimeline.play();
        stage.setTitle("Whac-A-Mole");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();
    } // start

    /**
     * Initializing the first row of the game.
     * @param hbox the hbox of the first row
     */
    private HBox initFirstRow(HBox hbox) {
        hbox = new HBox();
        Text title = new Text("Whac-A-Mole");
        title.setFont(Font.font("Verdana", 40));
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(title);
        return hbox;
    } // initFirstRow

    /**
     * Initializing the row for the timer.
     * @param hbox the hbox for the timer
     */
    private HBox initTimerRow(HBox hbox) {
        hbox = new HBox(300);
        score = 0;
        scoreboard = new Text("Score: " + score);
        scoreboard.setFont(Font.font("Verdana", 30));
        time = 60;
        timer = new Label("Time: " + time);
        timer.setFont(Font.font("Verdana", 30));
        hbox.getChildren().addAll(scoreboard, timer);
        return hbox;
    } // initTimerRow

    /**
     * Initializing the second hbox, which is the game's first row.
     * @param hbox the hbox of the second row
     */
    private HBox initSecondRow(HBox hbox) {
        hbox = new HBox(100);
        buttons.get(0).setPrefHeight(100);
        buttons.get(0).setPrefWidth(100);
        buttons.get(1).setPrefHeight(100);
        buttons.get(1).setPrefWidth(100);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buttons.get(0), buttons.get(1));
        return hbox;
    } // initSecondRow

    /**
     * Initializing the third hbox, which is the game's second row.
     * @param hbox the hbox of the third row
     */
    private HBox initThirdRow(HBox hbox) {
        hbox = new HBox(60);
        buttons.get(2).setPrefHeight(100);
        buttons.get(2).setPrefWidth(100);
        buttons.get(3).setPrefHeight(100);
        buttons.get(3).setPrefWidth(100);
        buttons.get(4).setPrefHeight(100);
        buttons.get(4).setPrefWidth(100);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buttons.get(2), buttons.get(3), buttons.get(4));
        return hbox;
    } // initSecondRow

    /**
     * Initializing the fourth hbox, which is the game's third row.
     * @param hbox the hbox of the fourth row
     */
    private HBox initFourthRow(HBox hbox) {
        hbox = new HBox(100);
        buttons.get(5).setPrefHeight(100);
        buttons.get(5).setPrefWidth(100);
        buttons.get(6).setPrefHeight(100);
        buttons.get(6).setPrefWidth(100);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buttons.get(5), buttons.get(6));
        return hbox;
    } // initSecondRow

    /**
     * Start the timer of the game, which is 60 seconds.
     */
    private void startTimer() {
        EventHandler<ActionEvent> handler = (event) -> {
            time--;
            timer.setText("Time: " + time);
        };
        keyFrame = new KeyFrame(Duration.seconds(1), handler);
        timeline = new Timeline();
        timeline.setCycleCount(60);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    } // void

    private void startButtonTimer() {
        Random rand = new Random();
        EventHandler<ActionEvent> handle = (buttonEvent) -> {
            int buttonNum = rand.nextInt(7);
            Image image = new Image("file:resources/mole.png");
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            buttons.get(buttonNum).setGraphic(imageView);
        };
        KeyFrame buttonFrame = new KeyFrame(Duration.seconds(1), handle);
        buttonTimeline.setCycleCount(Timeline.INDEFINITE);
        buttonTimeline.getKeyFrames().add(buttonFrame);
    } // startButtonTimer

    private void resetButton(final Button button) {
        button.setStyle("-fx-background-color: #0090B0");
    } // resetButton
} // OmegaApp

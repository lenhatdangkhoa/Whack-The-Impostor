package cs1302.omega;

import java.lang.Runnable;
import java.util.ArrayList;
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
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
    private Button[] buttons;
    private boolean[] buttonBool;
    private int score;
    private Text scoreboard;
    private int time;
    private Label timer;
    private KeyFrame keyFrame;
    private Timeline timeline;
    private Timeline buttonTimeline;
    private Stage stages;
    private Scene endScene;
    private Scene startScene;

    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    @Override
    public void init() {
        buttons = new Button[7];
        buttonBool = new boolean[7];
        buttonTimeline = new Timeline();
        startButtonTimer();
        for (int i = 0; i < 7; i++) {
            Image img = new Image("file:resources/hole.png");
            ImageView defaultImages = new ImageView(img);
            defaultImages.setFitHeight(70);
            defaultImages.setFitWidth(70);
            Button button = new Button();
            button.setPrefWidth(100);
            button.setPrefHeight(100);
            button.setGraphic(defaultImages);
            buttons[i] = button;
            buttons[i].setGraphic(defaultImages);
            buttons[i].setStyle("-fx-background-color: #5928AB;");
            buttonBool[i] = false;
        } // for
    } // init

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {
        Image tempImg = new Image("file:resources/mole.png");
        ImageView tempImage = new ImageView(tempImg);
        for (int i = 0; i < buttons.length; i++) {
            final Button tempButton = buttons[i];
            final int tempInt = i;
            buttons[i].setOnAction((event) -> {
                ImageView tempp = (ImageView) tempButton.getGraphic();
                if (tempButton == event.getSource() && buttonBool[tempInt] == true) {
                    score++;
                    buttonBool[tempInt] = false;
                } // if
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
        BackgroundSize size = new BackgroundSize(
            1500, 5000, true, true, true, true);
        BackgroundImage background = new BackgroundImage(
            new Image("file:resources/space.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            size);
        root.setBackground(new Background(background));

        scene = new Scene(root, 500, 500);
        startTimer();
        buttonTimeline.play();
        Image cursor = new Image("file:resources/knife.png", 250, 250, false, false);
        scene.setCursor(new ImageCursor(cursor));
        stage.setTitle("Whack The Impostor");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        this.stages = stage;
        stage.show();
    } // start

    /**
     * Initializing the first row of the game.
     * @param hbox the hbox of the first row
     * @return hbox the hbox of the first row
     */
    private HBox initFirstRow(HBox hbox) {
        hbox = new HBox();
        Text title = new Text("Whack The Impostor");
        title.setFont(Font.font("Verdana", 40));
        title.setFill(Color.RED);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(title);
        return hbox;
    } // initFirstRow

    /**
     * Initializing the row for the timer.
     * @param hbox the hbox for the timer
     * @return hbox the hbox for the timer
     */
    private HBox initTimerRow(HBox hbox) {
        hbox = new HBox(300);
        score = 0;
        scoreboard = new Text("Score: " + score);
        scoreboard.setFont(Font.font("Verdana", 20));
        scoreboard.setFill(Color.WHITE);
        time = 60;
        timer = new Label("Time: " + time);
        timer.setFont(Font.font("Verdana", 20));
        timer.setTextFill(Color.WHITE);
        hbox.getChildren().addAll(scoreboard, timer);
        hbox.setAlignment(Pos.CENTER);
        return hbox;
    } // initTimerRow

    /**
     * Initializing the second hbox, which is the game's first row.
     * @param hbox the hbox of the second row
     * @return hbox the hbox of the second row
     */
    private HBox initSecondRow(HBox hbox) {
        hbox = new HBox(100);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buttons[0], buttons[1]);
        return hbox;
    } // initSecondRow

    /**
     * Initializing the third hbox, which is the game's second row.
     * @param hbox the hbox of the third row
     * @return hbox the hbox of the third row
     */
    private HBox initThirdRow(HBox hbox) {
        hbox = new HBox(60);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buttons[2], buttons[3], buttons[4]);
        return hbox;
    } // initSecondRow

    /**
     * Initializing the fourth hbox, which is the game's third row.
     * @param hbox the hbox of the fourth row
     * @return hbox the hbox of the fourth row
     */
    private HBox initFourthRow(HBox hbox) {
        hbox = new HBox(100);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(buttons[5], buttons[6]);
        return hbox;
    } // initSecondRow

    /**
     * Start the timer of the game, which is 60 seconds.
     */
    private void startTimer() {
        EventHandler<ActionEvent> handler = (event) -> {
            time--;
            timer.setText("Time: " + time);
            if (time == 0) {
                VBox endVBox = new VBox(50);
                Text tempText = new Text("Game Over");
                tempText.setFont(Font.font("Verdana", 50));
                tempText.setFill(Color.RED);
                String scoreForEndScene = "Your score is " + this.score;
                Text tempScore = new Text(scoreForEndScene);
                tempScore.setFont(Font.font("Verdana", 40));
                tempScore.setFill(Color.YELLOW);
                Button tempButton = new Button("Play Again");
                tempButton.setPrefHeight(100);
                tempButton.setPrefWidth(300);
                tempButton.setOnAction((event1) -> start(stages));
                HBox tempBox = new HBox(tempText);
                tempBox.setAlignment(Pos.CENTER);
                HBox tempBox2 = new HBox(tempScore);
                tempBox2.setAlignment(Pos.CENTER);
                HBox tempBox3 = new HBox(tempButton);
                tempBox3.setAlignment(Pos.CENTER);
                endVBox.getChildren().addAll(tempBox, tempBox2, tempBox3);
                BackgroundSize size = new BackgroundSize(
                    1500, 1500, true, true, true, false);
                BackgroundImage background = new BackgroundImage(
                    new Image("file:resources/grass.png"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    size);
                endVBox.setBackground(new Background(background));
                endScene = new Scene(endVBox, 750 , 545);
                Image cursor = new Image("file:resources/knife.png", 100, 100, false, false);
                endScene.setCursor(new ImageCursor(cursor));
                stages.setScene(endScene);
                stages.show();
            } // if
        };
        keyFrame = new KeyFrame(Duration.seconds(1), handler);
        timeline = new Timeline();
        timeline.setCycleCount(60);
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    } // void

    /**
     * Start the random images on each button.
     */
    private void startButtonTimer() {
        Random rand = new Random();
        Image image = new Image("file:resources/mole.png");
        EventHandler<ActionEvent> handle = (buttonEvent) -> {
            int buttonNum = rand.nextInt(7);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            buttons[buttonNum].setGraphic(imageView);
            buttonBool[buttonNum] = true;
        };
        KeyFrame buttonFrame = new KeyFrame(Duration.millis(1100), handle);
        buttonTimeline.setCycleCount(Timeline.INDEFINITE);
        buttonTimeline.getKeyFrames().add(buttonFrame);
    } // startButtonTimer

    /**
     * Reset the button to the original image after clicked.
     * @param button the button to be resetted
     */
    private void resetButton(Button button) {
        Image img = new Image("file:resources/hole.png");
        ImageView defaultImages = new ImageView(img);
        defaultImages.setFitHeight(70);
        defaultImages.setFitWidth(70);
        button.setGraphic(defaultImages);
    } // resetButton
} // OmegaApp

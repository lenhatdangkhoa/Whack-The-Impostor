package cs1302.omega;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class OmegaApp extends Application {

    private HBox introRow;
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


    /**
     * Constructs an {@code OmegaApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public OmegaApp() {}

    @Override
    public void init() {
        VBox root = new VBox(30);
        introRow = initFirstRow(introRow);
        gameRow1 = initSecondRow(gameRow1);
        gameRow2 = initThirdRow(gameRow2);
        gameRow3 = initFourthRow(gameRow3);
        root.getChildren().addAll(introRow, gameRow1, gameRow2, gameRow3);
        scene = new Scene(root);
    } // init

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        // demonstrate how to load local asset using "file:resources/"
        Image bannerImage = new Image("file:resources/readme-banner.png");

        // setup stage
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
     * Initializing the second hbox, which is the game's first row.
     * @param hbox the hbox of the second row
     */
    private HBox initSecondRow(HBox hbox) {
        hbox = new HBox(100);
        button1 = new Button("1");
        button1.setPrefHeight(100);
        button1.setPrefWidth(100);
        button2 = new Button("2");
        button2.setPrefHeight(100);
        button2.setPrefWidth(100);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(button1, button2);
        return hbox;
    } // initSecondRow

    /**
     * Initializing the third hbox, which is the game's second row.
     * @param hbox the hbox of the third row
     */
    private HBox initThirdRow(HBox hbox) {
        hbox = new HBox(60);
        button3 = new Button("3");
        button3.setPrefHeight(100);
        button3.setPrefWidth(100);
        button4 = new Button("4");
        button4.setPrefHeight(100);
        button4.setPrefWidth(100);
        button5 = new Button("5");
        button5.setPrefHeight(100);
        button5.setPrefWidth(100);
        hbox.getChildren().addAll(button3, button4, button5);
        return hbox;
    } // initSecondRow

    /**
     * Initializing the fourth hbox, which is the game's third row.
     * @param hbox the hbox of the fourth row
     */
    private HBox initFourthRow(HBox hbox) {
        hbox = new HBox(100);
        button6 = new Button("6");
        button6.setPrefHeight(100);
        button6.setPrefWidth(100);
        button7 = new Button("7");
        button7.setPrefHeight(100);
        button7.setPrefWidth(100);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(button6, button7);
        return hbox;
    } // initSecondRow

} // OmegaApp

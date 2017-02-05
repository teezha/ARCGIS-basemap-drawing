package sample;

/** ==============================================================================
 * File         : some_file.java
 *
 * Current Author: Robert Hewlett
 *
 * Previous Author: None
 *
 * Contact Info: somebody@somewhere.com
 *
 * Purpose :
 *
 * Dependencies: None
 *
 * Modification Log :
 *    --> Created MMM-DD-YYYY (fl)
 *    --> Updated MMM-DD-YYYY (fl)
 *
 * =============================================================================
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application implements EventHandler<WindowEvent>{

    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(this);
    }


    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */


    public static void main(String[] args) {
        launch(args);
    }

    /** ===========================================================
     * The following block does ....
     * ===========================================================
     */


    @Override
    public void handle(WindowEvent event) {
        System.exit(0);
    }
}

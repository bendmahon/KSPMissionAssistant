package Assistant;

import Assistant.scenes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Assistant extends Application {
    public static Scene deltaVCalculatorScene;
    public static Scene kerbolMapScene;
    public void start(Stage primaryStage) throws Exception{
        loadScenes(primaryStage);
        primaryStage.setTitle("Assistant");
        primaryStage.setScene(deltaVCalculatorScene);
        primaryStage.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }

    private void loadScenes(Stage stage){
        deltaVCalculatorScene = DeltaVCalculatorScene.create(stage);
        kerbolMapScene = KerbolMapScene.create(stage);
    }
}

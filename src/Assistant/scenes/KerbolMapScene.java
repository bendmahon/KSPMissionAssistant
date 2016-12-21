package Assistant.scenes;


import Assistant.Assistant;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class KerbolMapScene {
    public static Scene create(Stage stage){
        Scene scene = new Scene(new Group(), 800, 600);
        Button changeSceneButton = new Button("Go to dV Calculator");
        changeSceneButton.setOnAction(e ->{
            stage.setScene(Assistant.deltaVCalculatorScene);
        });
        GridPane grid = new GridPane();
        grid.add(changeSceneButton,1,2,1,1);
        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);
        return scene;
    }
}

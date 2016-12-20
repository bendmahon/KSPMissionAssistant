package Assistant;

import MathAssistant.DeltaVCalculator;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Assistant extends Application {
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(new Group(), 400, 200);
        scene.getStylesheets().add("Assistant/css/style.css");

        Label ispLabel = new Label("Specific Impulse: ");
        ispLabel.getStyleClass().add("myLabel");
        TextField ispInput = new TextField();

        Label initialMassLabel = new Label("Initial Mass (tons): ");
        initialMassLabel.getStyleClass().add("myLabel");
        TextField initialMassInput = new TextField();

        Label availableFuelLabel = new Label("Available Fuel: ");
        initialMassLabel.getStyleClass().add("myLabel");
        TextField availableFuelInput = new TextField();

        Label resultLabel = new Label("dv = ");
        Button calculateButton = new Button("Calculate dV");
        calculateButton.setOnAction(e ->{
//            System.out.println(Double.parseDouble(ispInput.getText()));
//            System.out.println(Double.parseDouble(availableFuelInput.getText()));
//            System.out.println(Double.parseDouble(initialMassInput.getText()));
            resultLabel.setText(String.format("dv = %s m/s",
                    DeltaVCalculator.getDeltaV(Double.parseDouble(ispInput.getText()),
                                               Double.parseDouble(availableFuelInput.getText()),
                                               Double.parseDouble(initialMassInput.getText()))));
        });

        GridPane grid = new GridPane();
        grid.add(ispLabel,0,0);
        grid.add(ispInput,1,0);
        grid.add(initialMassLabel,0,1);
        grid.add(initialMassInput,1,1);
        grid.add(availableFuelLabel,0,2);
        grid.add(availableFuelInput,1,2);
        grid.add(calculateButton,0,3);
        grid.add(resultLabel,0,4);


        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);

        primaryStage.setTitle("Assistant");
        primaryStage.setScene(scene);
        primaryStage.requestFocus();
        primaryStage.show();
    }

    public static void main(String[] args) {
//        System.out.println(DeltaVCalculator.getDeltaV(isp, 3000, 84));
        Application.launch();
    }
}

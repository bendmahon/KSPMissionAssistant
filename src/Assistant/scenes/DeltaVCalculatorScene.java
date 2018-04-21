package Assistant.scenes;

import Assistant.Assistant;
import MathAssistant.DeltaVCalculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DeltaVCalculatorScene {
    public static String originBody = null;
    public static String destinationBody = null;

    public static void printTargets(){
        System.out.println("Origin: " + originBody + "\nDestination: " + destinationBody);
    }

    public static Scene create(Stage stage){
        Scene scene = new Scene(new Group(), 400, 200);
        scene.getStylesheets().add("Assistant/css/style.css");

        //Input Labels with default values
        //Falcon 9 FT
        //ISP of Merlin 1D @ Sea Level = 282s
        //Initial Mass = 549054kg
        //Dry Mass after S1 = 395700kg
        Label ispLabel = new Label("Specific Impulse: ");
        ispLabel.getStyleClass().add("textLabel");
        TextField ispInput = new TextField("282");
        ispInput.getStyleClass().add("inputLabel");

        Label initialMassLabel = new Label("Initial Mass (kg): ");
        initialMassLabel.getStyleClass().add("textLabel");
        TextField initialMassInput = new TextField("549054");
        initialMassInput.getStyleClass().add("inputLabel");

        Label availableFuelLabel = new Label("Available Fuel (kg): ");
        initialMassLabel.getStyleClass().add("textLabel");
        TextField availableFuelInput = new TextField("395700");
        availableFuelInput.getStyleClass().add("inputLabel");

        Label resultLabel = new Label("");
        Button calculateButton = new Button("Calculate dV");
        calculateButton.setOnAction(e ->{
            try {
                resultLabel.setText(String.format("dv = %s m/s",
                        MathAssistant.DeltaVCalculator.getDeltaV(Double.parseDouble(ispInput.getText()),
                                Double.parseDouble(availableFuelInput.getText()),
                                Double.parseDouble(initialMassInput.getText()))
                        )
                );
            }catch (NumberFormatException nfe){
                resultLabel.setText("dV = ERROR");
            }
        });

        Label originLabel = new Label("Origin Body");
        ObservableList<String> planets = FXCollections.observableArrayList("Moho", "Eve", "Kerbin", "Duna");
        //TODO Populate suborigin list based on origin selected
        ObservableList<String> originMoons = FXCollections.observableArrayList();
        ObservableList<String> destinationMoons = FXCollections.observableArrayList("Gilly", "Mun", "Minmus", "Ike");

        Label destinationLabel = new Label("Destination Body");

        final ComboBox originBodies = new ComboBox(planets);
        final ComboBox destinationBodies = new ComboBox(planets);
        final ComboBox subOriginBodies = new ComboBox(originMoons);
        final ComboBox subDestinationBodies = new ComboBox(destinationMoons);
        GridPane grid = new GridPane();

        Label enoughLabel = new Label("");
        Button isItEnough = new Button("Is It Enough?");
//        isItEnough.setOnAction(e -> {
//            enoughLabel.setText(DeltaVCalculator.isItEnough(Double.Double(DeltaVCalculator.getDeltaV()))
//        });

        //ACTION HANDLERS
        originBodies.setOnAction(e -> {
            String originPlanet = originBodies.getSelectionModel().getSelectedItem().toString();
            originBody = originPlanet;
            boolean subOrigin = false;
            originMoons.clear();
            if (originPlanet.equals("Kerbin")){
                originMoons.clear();
                originMoons.add("Mun");
                originMoons.add("Minmus");
                subOrigin = true;
            }
            else if (originPlanet.equals("Eve")){
                originMoons.add("Gilly");
                subOrigin = true;
            }
            if(subOrigin) grid.add(subOriginBodies, 2, 5);
            else{
                grid.getChildren().remove(subOriginBodies);
            }
            printTargets();
        });

        subOriginBodies.setOnAction(e -> {
            originBody = subOriginBodies.getSelectionModel().getSelectedItem().toString();
            printTargets();
        });

        destinationBodies.setOnAction(e -> {
            destinationBody = destinationBodies.getSelectionModel().getSelectedItem().toString();
            boolean subDestination = false;
            destinationMoons.clear();
            if (destinationBody.equals("Kerbin")){
                destinationMoons.add("Kerbin");
                destinationMoons.add("Mun");
                destinationMoons.add("Minmus");
                subDestination = true;
            }
            else if(destinationBody.equals("Eve")){
                destinationMoons.add("Gilly");
                subDestination = true;
            }
            if(subDestination) grid.add(subDestinationBodies, 2, 6);
            else{
                grid.getChildren().remove(subDestinationBodies);
            }
            printTargets();
        });

        subDestinationBodies.setOnAction(e -> {
            destinationBody = subDestinationBodies.getSelectionModel().getSelectedItem().toString();
            printTargets();
        });

//        Button sceneChangeButton = new Button("Change Scene");
//        sceneChangeButton.setOnAction(e -> {
//            stage.setScene(Assistant.kerbolMapScene);
//        });

//        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid");
        grid.add(ispLabel,0,0);
        grid.add(ispInput,1,0);
        grid.add(initialMassLabel,0,1);
        grid.add(initialMassInput,1,1);
        grid.add(availableFuelLabel,0,2);
        grid.add(availableFuelInput,1,2);
        grid.add(calculateButton,1,3);
        grid.add(resultLabel,1,4);
        grid.add(originLabel, 0, 5);
        grid.add(originBodies, 1, 5);
        grid.add(destinationLabel, 0, 6);
        grid.add(destinationBodies, 1,6);
        grid.add(isItEnough, 1, 7);
//        grid.add(sceneChangeButton,1,7);


        Group root = (Group) scene.getRoot();
        root.getChildren().add(grid);
        return scene;
    }
}

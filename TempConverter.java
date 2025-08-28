import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TempConverter extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Temperature Converter");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setVgap(10);
        grid.setHgap(10);

        Label labelValue = new Label("Enter Temperature:");
        TextField fieldValue = new TextField();

        Label labelUnit = new Label("Select Unit:");
        ComboBox<String> unitBox = new ComboBox<>();
        unitBox.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        unitBox.getSelectionModel().selectFirst();

        Button convertBtn = new Button("Convert");
        TextArea outputArea = new TextArea();
        outputArea.setEditable(false);

        grid.add(labelValue, 0, 0);
        grid.add(fieldValue, 1, 0);
        grid.add(labelUnit, 0, 1);
        grid.add(unitBox, 1, 1);
        grid.add(convertBtn, 0, 2, 2, 1);
        grid.add(outputArea, 0, 3, 2, 1);

        convertBtn.setOnAction(e -> {
            try {
                double temp = Double.parseDouble(fieldValue.getText());
                String unit = unitBox.getValue();
                String result = convertTemperature(temp, unit);
                outputArea.setText(result);
            } catch (NumberFormatException ex) {
                outputArea.setText("Please enter a valid number.");
            }
        });

        Scene scene = new Scene(grid, 400, 250);
        stage.setScene(scene);
        stage.show();
    }

    private String convertTemperature(double temp, String unit) {
        double celsius, fahrenheit, kelvin;

        switch (unit) {
            case "Celsius":
                celsius = temp;
                fahrenheit = (temp * 9 / 5) + 32;
                kelvin = temp + 273.15;
                break;
            case "Fahrenheit":
                celsius = (temp - 32) * 5 / 9;
                fahrenheit = temp;
                kelvin = celsius + 273.15;
                break;
            case "Kelvin":
                celsius = temp - 273.15;
                fahrenheit = (celsius * 9 / 5) + 32;
                kelvin = temp;
                break;
            default:
                return "Invalid unit";
        }

        return String.format(
    "Celsius: %.2f \u00B0C\nFahrenheit: %.2f \u00B0F\nKelvin: %.2f K",
    celsius, fahrenheit, kelvin
);
    }
}

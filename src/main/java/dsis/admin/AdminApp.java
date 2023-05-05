package dsis.admin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;


public class AdminApp extends Application {

    private static final String SERVER_URL = "https://dsisapp.cyclic.app";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label responseLabel = new Label();

        ChoiceBox<String> functionChoiceBox = new ChoiceBox<>();
        functionChoiceBox.getItems().addAll("signup", "add", "edit");
        functionChoiceBox.setValue("signup");

        TextField idField = new TextField();
        idField.setPromptText("Enter ID");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");

        TextField numberField = new TextField();
        numberField.setPromptText("Enter Number");

        TextField schoolIdField = new TextField();
        schoolIdField.setPromptText("Enter Student No");

        TextField creditField = new TextField();
        creditField.setPromptText("Enter Credit");

        TextField hoursField = new TextField();
        hoursField.setPromptText("Enter Hours");

        TextField codeField = new TextField();
        codeField.setPromptText("Enter Code");

        TextField evalCountField = new TextField();
        evalCountField.setPromptText("Enter Eval Count");

        TextField weightsField = new TextField();
        weightsField.setPromptText("Enter Weights");

        TextField namesField = new TextField();
        namesField.setPromptText("Enter Names");

        functionChoiceBox.setOnAction(event -> {
            String functionName = functionChoiceBox.getValue();
            if (functionName.equals("signup")) {
                nameField.setVisible(true);
                numberField.setVisible(true);
                idField.setVisible(false);
                schoolIdField.setVisible(false);
                creditField.setVisible(false);
                hoursField.setVisible(false);
                codeField.setVisible(false);
                evalCountField.setVisible(false);
                weightsField.setVisible(false);
                namesField.setVisible(false);
            } else if (functionName.equals("add")) {
                nameField.setVisible(true);
                numberField.setVisible(true);
                idField.setVisible(true);
                schoolIdField.setVisible(true);
                creditField.setVisible(true);
                hoursField.setVisible(true);
                codeField.setVisible(true);
                evalCountField.setVisible(true);
                weightsField.setVisible(true);
                namesField.setVisible(true);
            } else if (functionName.equals("edit")) {
                nameField.setVisible(false);
                numberField.setVisible(false);
                idField.setVisible(false);
                schoolIdField.setVisible(true);
                creditField.setVisible(false);
                hoursField.setVisible(false);
                codeField.setVisible(false);
                evalCountField.setVisible(false);
                weightsField.setVisible(false);
                namesField.setVisible(false);
            }
        });

        Button callButton = new Button("Call function");
        callButton.setOnAction(event -> {
            String functionName = functionChoiceBox.getValue();
            Map<String, String> params = new HashMap<>();
            if (functionName.equals("signup")) {
                String name = nameField.getText();
                String number = numberField.getText();
                params.put("name", name);
                params.put("schoolId", number);
            } else if (functionName.equals("add")) {
                String schoolId = schoolIdField.getText();
                String number = numberField.getText();
                String name = nameField.getText();
                String credit = creditField.getText();
                String hours = hoursField.getText();
                String code = codeField.getText();
                String id = idField.getText();
                String evalCount = evalCountField.getText();
                String weights = weightsField.getText();
                String names = namesField.getText();
                params.put("schoolId", schoolId);
                params.put("number", number);
                params.put("name", name);
                params.put("credit", credit);
                params.put("hours", hours);
                params.put("code", code);
                params.put("id", id);
                params.put("evalCount", evalCount);
                params.put("weights", weights);
                params.put("names", names);
            }
             else if (functionName.equals("edit")) {
                String schoolId = schoolIdField.getText();
                params.put("schoolId", schoolId);
            }
            try {
                String response = callFunction(functionName, params);
                responseLabel.setText(response);
            } catch (Exception e) {
                responseLabel.setText("Error: " + e.getMessage());
            }
        });

        VBox root = new VBox(10, functionChoiceBox, nameField, numberField, schoolIdField, creditField, hoursField, codeField, idField, evalCountField, weightsField, namesField, callButton, responseLabel);
        root.setPrefSize(400, 500);

        functionChoiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "signup":
                    schoolIdField.setVisible(false);
                    creditField.setVisible(false);
                    hoursField.setVisible(false);
                    codeField.setVisible(false);
                    idField.setVisible(false);
                    evalCountField.setVisible(false);
                    weightsField.setVisible(false);
                    namesField.setVisible(false);
                    nameField.setVisible(true);
                    numberField.setVisible(true);
                    break;
                case "add":
                    schoolIdField.setVisible(true);
                    creditField.setVisible(true);
                    hoursField.setVisible(true);
                    codeField.setVisible(true);
                    idField.setVisible(true);
                    evalCountField.setVisible(true);
                    weightsField.setVisible(true);
                    namesField.setVisible(true);
                    nameField.setVisible(true);
                    numberField.setVisible(true);
                    break;
                case "edit":
                    schoolIdField.setVisible(true);
                    creditField.setVisible(false);
                    hoursField.setVisible(false);
                    codeField.setVisible(false);
                    idField.setVisible(false);
                    evalCountField.setVisible(false);
                    weightsField.setVisible(false);
                    namesField.setVisible(false);
                    nameField.setVisible(false);
                    numberField.setVisible(false);
                    break;
                default:
                    break;
            }
        });

        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String callFunction(String functionName, Map<String, String> params)  throws Exception {
        String url = "https://dsisapp.cyclic.app/" + functionName;

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Set the request body with parameters
            String requestBody = getParamsString(params);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
            outputStream.close();

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                throw new Exception("HTTP error: " + responseCode);
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private String getParamsString(Map<String, String> params) throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first){
                first = false;
            } else{
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
        }

        return result.toString();
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
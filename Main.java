package com.zlatamigas.colorconverter;

public class Main extends Application {

    private static String APP_NAME = "Color converter";
    private static String APP_PAGE = "/view/app.fxml";
    private static String ICON = "/static/icon.png";

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(Main.class.getResource(APP_PAGE));
        Parent root = (Parent) loader.load();

        Scene scene = new Scene(root);

        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream(ICON)));
        primaryStage.setTitle(APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
import Discord.Bot;
import FileWork.FilesWorker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("GUI/GUI.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setFullScreen(false);
        primaryStage.setMaximized(false);
        primaryStage.setIconified(false);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();

    }

    public static void main(String[] args) throws InterruptedException {
        FilesWorker fw = new FilesWorker("GamePresets");

        Thread t = new Thread(new Bot(fw.readConfigs().getDiscordToken()));
        t.run();
        Thread.sleep(5000);
        launch(args);

    }
}

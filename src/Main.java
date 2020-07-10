import Core.GameCore;
import Discord.Bot;
import Discord.MessageListener;
import FileWork.FilesWorker;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.ArrayList;
import java.util.Scanner;


public class Main extends Application {
    private GameCore gameCore = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
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
        if (args[0] != "--no-gui") {
            new Main();

        } else {
            launch(args);
        }


    }

    private Main() {
        Scanner scanner = new Scanner(System.in);
        boolean isGameStarted = false;
        String card = "";
        while (true) {
            if (scanner.nextLine().equals("start")) {
                isGameStarted = true;
                gameCore = new GameCore("GamePresets");
                new Thread(gameCore).start();
                for (int i = 0; i < gameCore.getGameFiles().get("card").getGameParametr().size(); i++) {
                    System.out.println(i + "/" + gameCore.getGameFiles().get("card").getGameParametr().get(i));
                }
            }
            if (isGameStarted) {
                card = gameCore.getGameFiles().get("card").getGameParametr().get(scanner.nextInt());
                if(card.equals("Изменить профессию игрока  -- карта"))
                    changeCharacteristic("prof",false);
                if(card.equals("Изменить здоровье игрока  -- карта"))
                    changeCharacteristic("health",false);
                if(card.equals("Изменить свое хобби  -- карта"))
                    changeCharacteristic("hobby",false);
                if(card.equals("Изменить фобию игрока  -- карта"))
                    changeCharacteristic("fobia",false);
                if(card.equals("Изменить телосложение игрока  -- карта"))
                    changeCharacteristic("telo",false);
                if(card.equals("Изменить катастрофу  -- карта"))
                    changeCharacteristic("katastrof",false);
                if(card.equals("Изменить бункер  -- карта"))
                    changeCharacteristic("bunker",false);
                if(card.equals("Изменить фобию всех  -- карта"))
                    changeCharacteristic("fobia",true);
                if(card.equals("Изменить здоровье всех  -- карта"))
                    changeCharacteristic("health",true);
                if(card.equals("Изменить профессию всех  -- карта"))
                    changeCharacteristic("prof",true);
                if(card.equals("Изменить телосложение всех  -- карта"))
                    changeCharacteristic("telo",true);
            }
        }
    }

    private void changeCharacteristic(String characteristic, boolean isAll) {
        if (!isAll) {

            ArrayList<String> temp = gameCore.getGameFiles().get(characteristic).getGameParametr();
            String ans = temp.get((int) (Math.random() * temp.size()));
            if (characteristic.equals("katastrof") | characteristic.equals("bunker")) {
                for (MessageChannel ms : MessageListener.getMessageChannels()) {
                    ms.sendMessage(ans).queue();
                }
                return;
            }
            System.out.print("Введите ник: ");
            String nick = new Scanner(System.in).nextLine();
            for (MessageChannel ms : MessageListener.getMessageChannels()) {
                if (ms.getName().equals(nick)) {
                    ms.sendMessage(ans).queue();
                }
            }
        } else {
            ArrayList<String> temp;
            String ans;
            for (MessageChannel ms : MessageListener.getMessageChannels()) {
                temp = gameCore.getGameFiles().get(characteristic).getGameParametr();
                ans = temp.get((int) (Math.random() * temp.size()));
                ms.sendMessage(ans).queue();
            }
        }
    }
}

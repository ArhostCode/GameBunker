package GUI;

import Core.GameCore;
import Discord.MessageListener;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.ArrayList;


public class GUIController {

    private GameCore gameCore;

    @FXML
    public ListView playerslist;

    @FXML
    public ChoiceBox cardChoiser;

    @FXML
    public TextField nickField;

    @FXML
    public void startGame(){
       Thread thread =  new Thread(gameCore);
       thread.start();
    }

    @FXML
    public void useCard(){
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить профессию игрока  -- карта"))
            changeCharacteristic("prof",false, nickField.getText());
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить здоровье игрока  -- карта"))
            changeCharacteristic("health",false, nickField.getText());
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить свое хобби  -- карта"))
            changeCharacteristic("hobby",false, nickField.getText());
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить фобию игрока  -- карта"))
            changeCharacteristic("fobia",false, nickField.getText());
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить телосложение игрока  -- карта"))
            changeCharacteristic("telo",false, nickField.getText());
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить катастрофу  -- карта"))
            changeCharacteristic("katastrof",false, nickField.getText());
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить бункер  -- карта"))
            changeCharacteristic("bunker",false, "");
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить фобию всех  -- карта"))
            changeCharacteristic("fobia",true, "");
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить здоровье всех  -- карта"))
            changeCharacteristic("health",true, "");
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить профессию всех  -- карта"))
            changeCharacteristic("prof",true, "");
        if(cardChoiser.getSelectionModel().getSelectedItem().equals("Изменить телосложение всех  -- карта"))
            changeCharacteristic("telo",true, "");



    }
    @FXML
    public void initialize(){
        gameCore = new GameCore("GamePresets");
        ObservableList<String> players = FXCollections.observableArrayList();
        ObservableList<String> cards = FXCollections.observableArrayList();
        for (String item:gameCore.getGameFiles().get("card").getGameParametr()) {
            cards.add(item);
        }
        cardChoiser.setItems(cards);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                players.clear();
                for (MessageChannel ms: MessageListener.getMessageChannels()) {
                    players.add(ms.getName());
                }
                playerslist.setItems(players);
            }
        };
        animationTimer.start();
    }

    private void changeCharacteristic(String characteristic, boolean isAll, String nick) {
        if (!isAll) {
            ArrayList<String> temp = gameCore.getGameFiles().get(characteristic).getGameParametr();
            String ans = temp.get((int) (Math.random() * temp.size()));
            if(characteristic.equals("katastrof")|characteristic.equals("bunker")){
                for (MessageChannel ms : MessageListener.getMessageChannels()) {
                    ms.sendMessage(ans).queue();
                }
                return;
            }

            for (MessageChannel ms : MessageListener.getMessageChannels()) {
                if (ms.getName().equals(nick)) {
                    ms.sendMessage(ans).queue();
                }
            }
        }else{
            ArrayList<String> temp;
            String ans;
            for (MessageChannel ms: MessageListener.getMessageChannels()) {
                temp = gameCore.getGameFiles().get(characteristic).getGameParametr();
                ans = temp.get((int) (Math.random() * temp.size()));
                ms.sendMessage(ans).queue();
            }
        }
    }

}

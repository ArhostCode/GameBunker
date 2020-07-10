package Core;

import Discord.MessageListener;
import FileWork.FilesWorker;
import FileWork.GameFile;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameCore extends Thread {
    private Map<String, GameFile> gameFiles;
    private ArrayList<Player> players;
    private FilesWorker filesWorker;
    private static int minAge = 18;
    private static int maxAge = 70;

    public GameCore(String path) {
        filesWorker = new FilesWorker(path);
        try {
            gameFiles = filesWorker.readGameFiles();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ArrayList<MessageChannel> messageChannels = MessageListener.getMessageChannels();
        String katastrof = gameFiles.get("katastrof").getGameParametr().get((int) (Math.random() * gameFiles.get("katastrof").getGameParametr().size()));
        String bunker = "Бункер на " + (int) (1 + Math.random() * 30) + " месяц(ев)." + (int) (10 + Math.random() * 250) + " кв.м. " + gameFiles.get("bunker").getGameParametr().get((int) (Math.random() * gameFiles.get("bunker").getGameParametr().size()));
        System.out.println(katastrof + " " + bunker);
        players = new ArrayList<>();
        Map<String, String> player;
        ArrayList<String> temp;
        String stage = "";
        String answer = "";
        for (int i = 0; i < messageChannels.size(); i++) {
            player = new HashMap<>();
            answer = "";
            String[] gender = new String[]{"М", "Ж"};
            String[] child = new String[]{"Ч", "", "", ""};
            int age = (int) (minAge + Math.random() * (maxAge - minAge));
            String ageAndGender = age + " " + gender[(int) (Math.random() * 2)] + " " + child[(int) (Math.random() * 4)];

            for (String gameField : filesWorker.getGameFields()) {
                stage = "";
                temp = gameFiles.get(gameField).getGameParametr();
                if (gameField.equals("prof") | gameField.equals("hobby"))
                    stage = "  Стаж " + (int) (Math.random() * (age - 18)) + " лет";
                player.put(gameField, temp.get((int) (Math.random() * temp.size())) + stage);
            }
            players.add(new Player(player, messageChannels.get(i).getName()));
            answer += katastrof + " " + bunker + "\n\n\n";
            for (Map.Entry item : player.entrySet()) {
                answer += item.getValue() + "\n";
            }
            messageChannels.get(i).sendMessage(ageAndGender + "\n" + answer).queue();
        }
    }

    public Map<String, GameFile> getGameFiles() {
        return gameFiles;
    }



}

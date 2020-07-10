package FileWork;

import java.util.ArrayList;

public class GameFile {

    private ArrayList<String> gameParametrs;

    public GameFile(ArrayList<String> gameParametr){
        this.gameParametrs = gameParametr;
    }

    public ArrayList<String> getGameParametr() {
        return gameParametrs;
    }
}

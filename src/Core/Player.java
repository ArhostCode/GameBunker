package Core;

import java.util.Map;

public class Player {

    private String name;
    private Map<String,String> characteristics;

    public Player(Map<String,String> characteristics,String name){
        this.characteristics = characteristics;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
    }

    public Map<String, String> getCharacteristics() {
        return characteristics;
    }
}

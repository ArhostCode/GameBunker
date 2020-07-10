package FileWork;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class FilesWorker {
    private String path;
    private File dir;
    private ArrayList<String> gameFields;

    public FilesWorker(String path) {
        this.path = path;
        dir = new File(path);
        gameFields = new ArrayList<>();
    }

    public ArrayList<String> getGameFields() {
        System.out.println(gameFields);
        return gameFields;
    }

    public ConfigFile readConfigs()  {

        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path+"/Configs")), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(fileReader);
        String token = scanner.nextLine();
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ConfigFile(token);
    }

    public Map<String, GameFile> readGameFiles() throws FileNotFoundException {
        Map<String, GameFile> gameFiles = new HashMap<>();
        BufferedReader fr = null;
        Scanner scanner;
        ArrayList<String> gameParametrs;
        for (File f:dir.listFiles()) {
            if(f.getName().equals("Configs"))
                continue;

            gameParametrs=new ArrayList<>();
            fr = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path+"/"+f.getName())),StandardCharsets.UTF_8));
            scanner = new Scanner(fr);
            while (scanner.hasNextLine())
                gameParametrs.add(scanner.nextLine());
            gameFiles.put(f.getName(),new GameFile(gameParametrs));
            if(f.getName().equals("bunker") | f.getName().equals("katastrof"))
                continue;
            gameFields.add(f.getName());
        }
        try {
            assert fr != null;
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gameFiles;
    }

}

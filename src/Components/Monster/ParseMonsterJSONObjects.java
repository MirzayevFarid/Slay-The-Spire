package Components.Monster;

import Components.Potion.Potion;
import Components.Potion.Potions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ParseMonsterJSONObjects {
    private Gson gson;//Google gson library for json-pojo object mapping!
    private String directory;
    private String path;// File path that includes json libraries
    private Monsters monsters;

    ParseMonsterJSONObjects() throws Exception {
        gson = new GsonBuilder().create();                     //creating gson
        directory = System.getProperty("user.dir");
        path = directory + "\\src\\Components\\Monster\\MonsterJSON";              //path for json files
        monsters = new Monsters(parseJSONFiles());
    }
    public Monsters parseJSONFiles() throws Exception{

        File folder = new File(path);
        File[] files = folder.listFiles();
        ArrayList<Monster> monsters = new ArrayList<>();

        for (File file: files) {
            try {
                Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));
                //Override predicate

                Object object = gson.fromJson(new FileReader(file.getAbsolutePath()), Monster.class);

                if(object instanceof Monster) {
                    ((Monster)(object)).initialMonster();
                    monsters.add((Monster) object);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Monsters(monsters);
    }
    @Override
    public String toString() {
        return "ParseMonsterJSONObjects{" +
                "monsters=" + monsters.toString() +
                '}';
    }
}

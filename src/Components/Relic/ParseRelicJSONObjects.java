package Components.Relic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ParseRelicJSONObjects {
    private Gson gson;//Google gson library for json-pojo object mapping!
    private String directory;
    private String path;// File path that includes json libraries
    private Relics relics;

    public ParseRelicJSONObjects() throws Exception {
        gson = new GsonBuilder().create();                     //creating gson
        directory = System.getProperty("user.dir");
        path = directory + "/src/Components/Relic/RelicJSON";              //path for json files
        relics = new Relics(parseRelicJSONFiles());
    }

    public Relics parseRelicJSONFiles() throws Exception{

        File folder = new File(path);
        File[] files = folder.listFiles();
        ArrayList<Relic> relicPool = new ArrayList<>();

        for (File file: files) {
            try {
                Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));
                //Override predicate

                Object object = gson.fromJson(new FileReader(file.getAbsolutePath()), Relic.class);

                if(object instanceof Relic) {
                    relicPool.add((Relic) object);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Relics(relicPool);
    }

    public Relics getRelics() {
        return relics;
    }

    @Override
    public String toString() {
        return "ParseCardJSONObjects{" +
                "cardDeck=" + relics.toString() +
                '}';
    }
}

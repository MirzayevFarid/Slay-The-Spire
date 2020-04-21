package Components.Potion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ParsePotionJSONObjects {
    private Gson gson;//Google gson library for json-pojo object mapping!
    private String directory;
    private String path;// File path that includes json libraries
    private Potions potions;

    ParsePotionJSONObjects() throws Exception {
        gson = new GsonBuilder().create();                     //creating gson
        directory = System.getProperty("user.dir");
        path = directory + "\\src\\Components\\Potion\\PotionJSON";              //path for json files
        potions = new Potions(parseJSONFiles());
    }
    public Potions parseJSONFiles() throws Exception{

        File folder = new File(path);
        File[] files = folder.listFiles();
        ArrayList<Potion> potions = new ArrayList<>();

        for (File file: files) {
            try {
                Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));
                //Override predicate

                Object object = gson.fromJson(new FileReader(file.getAbsolutePath()), Potion.class);

                if(object instanceof Potion) {
                    potions.add((Potion) object);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Potions(potions);
    }
    public Class<?> typeToClass(String className){

        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        }
        catch (ClassNotFoundException e){
        }
        return cls;
    }

    @Override
    public String toString() {
        return "ParsePotionJSONObjects{" +
                "potions=" + potions.toString() +
                '}';
    }

}

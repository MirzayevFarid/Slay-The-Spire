package Components.Card;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ParseCardJSONObjects {
    private Gson gson;//Google gson library for json-pojo object mapping!
    private String directory;
    private String path;// File path that includes json libraries
    private Cards cardDeck;

    public ParseCardJSONObjects() throws Exception {
        gson = new GsonBuilder().create();                     //creating gson
        directory = System.getProperty("user.dir");
        path = directory + "/src/Components/Card/CardJSON";              //path for json files
        cardDeck = new Cards(parseJSONFiles());
    }

    public Cards parseJSONFiles() throws Exception{

        File folder = new File(path);
        File[] files = folder.listFiles();
        ArrayList<Card> cardList = new ArrayList<>();

        for (File file: files) {
            try {
                Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()));
                   //Override predicate

                Object object = gson.fromJson(new FileReader(file.getAbsolutePath()), Card.class);

                if(object instanceof Card) {
                    cardList.add((Card) object);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new Cards(cardList);
    }

    public Class<?> typeToClass(String className){

        Class<?> cls = null;
        try {
            cls = Class.forName(className);
        }
        catch (ClassNotFoundException ignored){
        }
        return cls;
    }

    public Cards getCardDeck() {
        return cardDeck;
    }

    @Override
    public String toString() {
        return "ParseCardJSONObjects{" +
                "cardDeck=" + cardDeck.toString() +
                '}';
    }
}

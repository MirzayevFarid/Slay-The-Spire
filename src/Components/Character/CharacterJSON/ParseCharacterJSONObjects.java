package Components.Character.CharacterJSON;

import Components.Card.ParseCardJSONObjects;
import Components.Character.Character;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.Constants;

import java.io.File;
import java.io.FileReader;

public class ParseCharacterJSONObjects {
    private String characterType;
    private Gson gson;//Google gson library for json-pojo object mapping!
    private String directory;
    private String path;// File path that includes json libraries
    private Character character;

    public ParseCharacterJSONObjects(String characterType) throws Exception {
        this.characterType = characterType;
        gson = new GsonBuilder().create();                     //creating gson
        directory = System.getProperty("user.dir");
        path = directory + "/src/Components/Character/CharacterJSON/" + characterType + ".json";              //path for json files
        parseCharacterJSONFiles();
    }

    public void parseCharacterJSONFiles() throws Exception{
        File file = new File(path);
        Object object = gson.fromJson(new FileReader(file.getAbsolutePath()), Character.class);
        if(object instanceof Character) {
            character = new Character((Character) object);
        }
    }


    public Character getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "ParseCardJSONObjects{" + "\n" +
                "cardDeck=" + character.toString() +
                '}';
    }
}

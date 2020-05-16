package Components.Character.CharacterJSON;

import Components.Card.Card;
import Components.Card.Cards;
import Components.Card.ParseCardJSONObjects;
import Components.Character.Character;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

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

        addCards();

    }

    private void addCards() throws Exception {
        ParseCardJSONObjects cards = new ParseCardJSONObjects();
        // TODO: Change Default Card Count
        for(int i = 0; i <= 9; i++){
            this.character.getCardsOfPlayer().getCardList().add(cards.getCardDeck().getCardList().get(i));
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

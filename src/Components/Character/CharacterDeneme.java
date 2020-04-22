package Components.Character;

import Components.Character.CharacterJSON.ParseCharacterJSONObjects;

public class CharacterDeneme {
    public static void main(String[] args) throws Exception {
        ParseCharacterJSONObjects character = new ParseCharacterJSONObjects("Ironclad");
        System.out.println(character.toString());
    }
}

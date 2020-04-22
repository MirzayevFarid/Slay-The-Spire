package Components.Monster;

import Components.Potion.ParsePotionJSONObjects;

public class MonsterDeneme {
    public static void main(String[] args) throws Exception {
        ParseMonsterJSONObjects monster = new ParseMonsterJSONObjects();
        System.out.println(monster.toString());
    }
}

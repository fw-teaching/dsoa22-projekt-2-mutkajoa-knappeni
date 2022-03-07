import java.security.Key;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    
    static HashMap<Character, Integer> getFrequency(String input){
        input = cleanString(input);
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (Character character : input.toCharArray()) {
            Integer val = map.get(character);
            if(val != null){
                map.put(character, val+1);
            }else{
                map.put(character, 1);
            }
        }
        return map;
    }

    static String cleanString(String input){
        input = input.toLowerCase();
        input = input.replaceAll("[^a-zåäöøæéèóòôáàçâêîôûàìíùëïüñß]", "");
        return input;  
    }

    static HashMap<Character, Float> getPrecentage(String input){
        Integer stringSize = cleanString(input).length();
        HashMap<Character, Integer> frequencyHashMap = getFrequency(input);
        HashMap<Character, Float> map = new HashMap<Character, Float>();
        frequencyHashMap.forEach((Character chara, Integer intg) ->{
            map.put(chara, ((float)intg/(float)stringSize)*100);
        });
        return map;
    }


}

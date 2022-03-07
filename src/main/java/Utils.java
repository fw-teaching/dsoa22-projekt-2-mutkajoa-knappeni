import java.util.HashMap;

public class Utils {
    
    public HashMap<Character, Integer> getFrequency(String input){
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

    public String cleanString(String input){
        input = input.toLowerCase();
        input = input.replaceAll("[^a-zåäöøæéèóòôáàçâêîôûàìíùëïüñß]", "");
        return input;  
    }

    


}

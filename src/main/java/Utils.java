import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.InputMap;

public class Utils {

    static String cleanString(String input){
        input = input.toLowerCase();
        input = input.replaceAll("[^a-zåäöøæéèóòôáàçâêîôûàìíùëïüñß]", "");
        return input;  
    }
    
    static HashMap<String, Integer> getCharFrequency(String input){
        input = cleanString(input);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Character character : input.toCharArray()) {
            Integer val = map.get(character.toString());
            if(val != null){
                map.put(character.toString(), val+1);
            }else{
                map.put(character.toString(), 1);
            }
        }
        return map;
    }

    static HashMap<String, Integer> getThreeCharFrequency(String input){
        input = cleanString(input);
        char[] inputarray = input.toCharArray();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i <= inputarray.length-3; i++) {
                Character char0 = inputarray[i];
                Character char1 = inputarray[i+1];
                Character char2 = inputarray[i+2];
                String chars = char0.toString() + char1.toString() + char2.toString();
                Integer val = map.get(chars);
                if(val != null){
                    map.put(chars, val+1);
                }else{
                    map.put(chars, 1);
                }
               
             
         }
         return map;

    }

    static HashMap<String,Integer> getFirstCharFrequency(String input){
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        ArrayList<String> wordsList = new ArrayList<String>();
        String[] wordsArr = input.split(" ");

        for (String word : wordsArr) {
            String cleanword = cleanString(word);
            if(!cleanword.isEmpty()){
                wordsList.add(cleanword);
            }
        }

        for (String word : wordsList) {
            Character wordchar = word.charAt(0);
            Integer val = map.get(wordchar.toString());
            if(val != null){
                map.put(wordchar.toString(), val+1);
            }else{
                map.put(wordchar.toString(), 1);
            }
        }

        return map;
    }    

    static HashMap<String, Float> getPrecentage(HashMap<String, Integer> input){
        Integer[]  amount = {0};
        for (Integer i : input.values()) {
           amount[0] += i;
        }
        HashMap<String, Float> map = new HashMap<String, Float>();
        input.forEach((String chara, Integer intg) ->{
            map.put(chara, ((float)intg/(float)amount[0]));
        });
        return map;
    }

    static ArrayList<HashMap<String, HashMap<String,Float>>> getTrainData(){
       ArrayList<HashMap<String, HashMap<String,Float>>>map = new ArrayList<HashMap<String, HashMap<String,Float>>>();
       HashMap<String, HashMap<String,Float>>oneCharMap = new HashMap<String, HashMap<String,Float>>();
       HashMap<String, HashMap<String,Float>>threeCharMap = new HashMap<String, HashMap<String,Float>>();
       HashMap<String, HashMap<String,Float>>firstCharMap = new HashMap<String, HashMap<String,Float>>();
       for (LangLabel label : LangLabel.values()){
           oneCharMap.put(label.getName(), getPrecentage(getCharFrequency(FileHandler.readTextFile("assets/lang-samples/"+label+".txt"))));
           threeCharMap.put(label.getName(), getPrecentage(getThreeCharFrequency(FileHandler.readTextFile("assets/lang-samples/"+label+".txt"))));
           firstCharMap.put(label.getName(), getPrecentage(getFirstCharFrequency(FileHandler.readTextFile("assets/lang-samples/"+label+".txt"))));
       }
       map.add(oneCharMap);
       map.add(threeCharMap);
       map.add(firstCharMap);
        return map;
    }
    static HashMap<String,HashMap<String,Float>> getInputData(String input){
        HashMap<String,HashMap<String,Float>>map = new HashMap<String,HashMap<String,Float>>();
            
       
       map.put("onechar", getPrecentage(getCharFrequency(input)));
       map.put("threechar", getPrecentage(getThreeCharFrequency(input)));
       map.put("firstchar", getPrecentage(getFirstCharFrequency(input)));
       return map;
    }
   
    static HashMap<String,HashMap<String, HashMap<String,Float>>> getScore(ArrayList<HashMap<String, HashMap<String,Float>>> trainedData, HashMap<String, HashMap<String,Float>> inputtedData){
        HashMap<String,HashMap<String, HashMap<String,Float>>> list = new HashMap<String,HashMap<String, HashMap<String,Float>>>();
        String[] labelStrings = {"onechar","threechar","firstchar"};
            
            for (var firstNestedHashMap : trainedData) {
                    for (var secondNestedHashMap : firstNestedHashMap.entrySet()) {
                        for (var charValue : secondNestedHashMap.getValue().entrySet()) {
                          var murr =  inputtedData.get(labelStrings[trainedData.indexOf(firstNestedHashMap)]);
                          var surr = murr.get(charValue.getKey());
                            if(surr!=null){
                                //bygga upp resultatet "list"
                               if(list.containsKey(secondNestedHashMap.getKey())){
                                   var kurr = list.get(secondNestedHashMap.getKey());
                                   var hurr = kurr.get(labelStrings[trainedData.indexOf(firstNestedHashMap)]);
                                   if(hurr != null){
                                        hurr.put(charValue.getKey(), surr+charValue.getValue());
                                   }else{
                                       var turr = new HashMap<String,Float>();
                                       turr.put(charValue.getKey(), surr+charValue.getValue());
                                       kurr.put(labelStrings[trainedData.indexOf(firstNestedHashMap)], turr);
                                   }
                               }else{
                                    HashMap<String,HashMap<String,Float>> lurr = new HashMap<String,HashMap<String,Float>>();
                                    HashMap<String,Float> jurr = new HashMap<String,Float>();
                                    jurr.put(charValue.getKey(), surr+charValue.getValue());
                                    lurr.put(labelStrings[trainedData.indexOf(firstNestedHashMap)], jurr);
                                    list.put(secondNestedHashMap.getKey(), lurr);
                               }
                                
                              
                            }
                        }
                    }
            }
        


        return list;
    }
    

    

}

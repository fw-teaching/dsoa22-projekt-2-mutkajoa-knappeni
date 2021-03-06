import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Utils {

    static String cleanString(String input) {
        input = input.toLowerCase();
        input = input.replaceAll("[^a-zåäöøæéèóòôáàçâêîôûàìíùëïüñß]", "");
        return input;
    }

    static HashMap<String, Integer> getCharFrequency(String input) {
        input = cleanString(input);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (Character character : input.toCharArray()) {
            Integer val = map.get(character.toString());
            if (val != null) {
                map.put(character.toString(), val + 1);
            } else {
                map.put(character.toString(), 1);
            }
        }
        return map;
    }

    static HashMap<String, Integer> getThreeCharFrequency(String input) {
        input = cleanString(input);
        char[] inputarray = input.toCharArray();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i <= inputarray.length - 3; i++) {
            Character char0 = inputarray[i];
            Character char1 = inputarray[i + 1];
            Character char2 = inputarray[i + 2];
            String chars = char0.toString() + char1.toString() + char2.toString();
            Integer val = map.get(chars);
            if (val != null) {
                map.put(chars, val + 1);
            } else {
                map.put(chars, 1);
            }

        }
        return map;

    }

    static HashMap<String, Integer> getFirstCharFrequency(String input) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<String> wordsList = new ArrayList<String>();
        String[] wordsArr = input.split(" ");

        for (String word : wordsArr) {
            String cleanword = cleanString(word);
            if (!cleanword.isEmpty()) {
                wordsList.add(cleanword);
            }
        }

        for (String word : wordsList) {
            Character wordchar = word.charAt(0);
            Integer val = map.get(wordchar.toString());
            if (val != null) {
                map.put(wordchar.toString(), val + 1);
            } else {
                map.put(wordchar.toString(), 1);
            }
        }

        return map;
    }

    static HashMap<String, Float> getPrecentage(HashMap<String, Integer> input) {
        Integer[] amount = { 0 };
        for (Integer i : input.values()) {
            amount[0] += i;
        }
        HashMap<String, Float> map = new HashMap<String, Float>();
        input.forEach((String chara, Integer intg) -> {
            map.put(chara, ((float) intg / (float) amount[0]));
        });
        return map;
    }

    static ArrayList<HashMap<String, HashMap<String, Float>>> getTrainData() {
        ArrayList<HashMap<String, HashMap<String, Float>>> map = new ArrayList<HashMap<String, HashMap<String, Float>>>();
        HashMap<String, HashMap<String, Float>> oneCharMap = new HashMap<String, HashMap<String, Float>>();
        HashMap<String, HashMap<String, Float>> threeCharMap = new HashMap<String, HashMap<String, Float>>();
        HashMap<String, HashMap<String, Float>> firstCharMap = new HashMap<String, HashMap<String, Float>>();
        for (LangLabel label : LangLabel.values()) {
            String fileString = FileHandler.readTextFile("assets/lang-samples/" + label + ".txt");
            oneCharMap.put(label.getName(),
                    getPrecentage(getCharFrequency(fileString)));
            threeCharMap.put(label.getName(), getPrecentage(
                    getThreeCharFrequency(fileString)));
            firstCharMap.put(label.getName(), getPrecentage(
                    getFirstCharFrequency(fileString)));
        }
        map.add(oneCharMap);
        map.add(threeCharMap);
        map.add(firstCharMap);
        return map;
    }

    static HashMap<String, HashMap<String, Float>> getInputData(String input) {
        HashMap<String, HashMap<String, Float>> map = new HashMap<String, HashMap<String, Float>>();
        map.put("onechar", getPrecentage(getCharFrequency(input)));
        map.put("threechar", getPrecentage(getThreeCharFrequency(input)));
        map.put("firstchar", getPrecentage(getFirstCharFrequency(input)));
        return map;
    }

    /*
     * vår getScore() funktion hittar de bokstäver eller kombination av boksäver som
     * finns i båda datasetten
     * destu flera bokstäver som finns i båda, och med större frekvens, destu högre
     * "score" får språket
     * när vi plussar ihop frekvenserna
     */
    static HashMap<String, HashMap<String, Float>> getScore(
            ArrayList<HashMap<String, HashMap<String, Float>>> trainedData,
            HashMap<String, HashMap<String, Float>> inputtedData) {
        HashMap<String, HashMap<String, LinkedHashMap<String, Float>>> list = new HashMap<String, HashMap<String, LinkedHashMap<String, Float>>>();
        HashMap<String, HashMap<String, Float>> result = new HashMap<String, HashMap<String, Float>>();
        String[] labelStrings = { "onechar", "threechar", "firstchar" };
        // här itererar vi över både trainedData och inputtedData samtidigt
        for (HashMap<String, HashMap<String, Float>> firstNestedHashMap : trainedData) {
            for (Entry<String, HashMap<String, Float>> secondNestedHashMap : firstNestedHashMap.entrySet()) {
                for (Entry<String, Float> charValue : secondNestedHashMap.getValue().entrySet()) {
                    HashMap<String, Float> murr = inputtedData
                            .get(labelStrings[trainedData.indexOf(firstNestedHashMap)]);
                    //// ta data från jämför bokstäverna, eller sekvenser av tre bokstäver, som
                    //// finns i båda datasetten
                    Float surr = murr.get(charValue.getKey());
                    if (surr != null) {
                        // bygga upp resultatet "list"
                        if (list.containsKey(secondNestedHashMap.getKey())) {
                            HashMap<String, LinkedHashMap<String, Float>> kurr = list.get(secondNestedHashMap.getKey());
                            HashMap<String, Float> hurr = kurr
                                    .get(labelStrings[trainedData.indexOf(firstNestedHashMap)]);
                            if (hurr != null) {
                                hurr.put(charValue.getKey(), surr + charValue.getValue());
                            } else {
                                LinkedHashMap<String, Float> turr = new LinkedHashMap<String, Float>();
                                turr.put(charValue.getKey(), surr + charValue.getValue());
                                kurr.put(labelStrings[trainedData.indexOf(firstNestedHashMap)], turr);
                            }
                        } else {
                            HashMap<String, LinkedHashMap<String, Float>> lurr = new HashMap<String, LinkedHashMap<String, Float>>();
                            LinkedHashMap<String, Float> jurr = new LinkedHashMap<String, Float>();
                            jurr.put(charValue.getKey(), surr + charValue.getValue());
                            lurr.put(labelStrings[trainedData.indexOf(firstNestedHashMap)], jurr);
                            list.put(secondNestedHashMap.getKey(), lurr);
                        }
                    }
                }
            }
        }
        // Räkna ut resultatet för varje språk
        for (Entry<String, HashMap<String, LinkedHashMap<String, Float>>> langElement : list.entrySet()) {
            for (Entry<String, LinkedHashMap<String, Float>> charSequenceElement : langElement.getValue().entrySet()) {
                for (Entry<String, Float> valueElements : charSequenceElement.getValue().entrySet()) {
                    if (result.containsKey(langElement.getKey())) {
                        HashMap<String, Float> resultCharSeqMap = result.get(langElement.getKey());
                        Float resultFloat = valueElements.getValue();
                        if (resultCharSeqMap.containsKey(charSequenceElement.getKey())) {
                            Float temp = 0.0f;
                            Float f = resultCharSeqMap.get(charSequenceElement.getKey());
                            temp += f;
                            resultCharSeqMap.put(charSequenceElement.getKey(), temp + resultFloat);
                        } else {
                            Float temp = 0.0f;
                            resultCharSeqMap.put(charSequenceElement.getKey(), temp);
                        }
                    } else {
                        LinkedHashMap<String, Float> resultcharSequenceMap = new LinkedHashMap<>();
                        Float temp = 0.0f;
                        resultcharSequenceMap.put(charSequenceElement.getKey(), temp);
                        result.put(langElement.getKey(), resultcharSequenceMap);
                    }
                }
            }
        }
        // fixa en total
        for (Entry<String, HashMap<String, Float>> langEntry : result.entrySet()) {
            Float vFloat = 0.0f;
            for (Entry<String, Float> value : langEntry.getValue().entrySet()) {
                vFloat += value.getValue();
            }
            HashMap<String, Float> values = result.get(langEntry.getKey());
            values.put("total", vFloat);
        }
        totalScore(result);

        return result;
    }

    // Rankar allt och lägger i string
    // Kanske lite onödiga TreeMaps men de funkar
    static String[] totalScore(HashMap<String, HashMap<String, Float>> result) {
        String[] returnedStrings = new String[2];
        String tableString = "Språk  Analys 1  Analys 2  Analys 3  Kombinerat  Rangordning\n";
        TreeMap<String, Float> oneCharAnalysis = new TreeMap<>();
        TreeMap<String, Float> threeCharAnalysis = new TreeMap<>();
        TreeMap<String, Float> firstCharAnalysis = new TreeMap<>();
        TreeMap<String, Float> totalAnalysis = new TreeMap<>();
        TreeMap<Float, String> tempTotalAnalysis = new TreeMap<>();
        TreeMap<Integer, String> rankMap = new TreeMap<>();
        TreeMap<String, String> languageMap = new TreeMap<>();

        for (LangLabel label : LangLabel.values()) {
            oneCharAnalysis.put(label.getName(), result.get(label.getName()).get("onechar"));
            threeCharAnalysis.put(label.getName(), result.get(label.getName()).get("threechar"));
            firstCharAnalysis.put(label.getName(), result.get(label.getName()).get("firstchar"));
            totalAnalysis.put(label.getName(), result.get(label.getName()).get("total"));
            tempTotalAnalysis.put(result.get(label.getName()).get("total"), label.getName());
            languageMap.put(label.getName(), label.toString());
        }
        // Här lägger vi allt in i en stor string som vi printtar som fin table
        for (int i = 1; i < totalAnalysis.size() + 1; i++) {
            String lastEntry = tempTotalAnalysis.pollLastEntry().getValue();
            rankMap.put(i, lastEntry);
            tableString = tableString
                    .concat(languageMap.get(lastEntry) + "     "
                            + String.format("%.2f", oneCharAnalysis.get(lastEntry)) + "      "
                            + String.format("%.2f", threeCharAnalysis.get(lastEntry)) +
                            "      "
                            + String.format("%.2f", firstCharAnalysis.get(lastEntry)) +
                            "      "
                            + String.format("%.2f", totalAnalysis.get(lastEntry))
                            + "        " + i + "\n");
        }
        returnedStrings[0] = tableString;
        returnedStrings[1] = rankMap.get(1);
        return returnedStrings;
    }

}

public class UI {

    public static void printTable(String input) {
        String returnedString[] = Utils.totalScore(Utils.getScore(Utils.getTrainData(), Utils.getInputData(
                input)));
        System.out.println("Jag gissar att du skriver på " + returnedString[1] + "!");
        System.out.println("Nedanför är en tabell på analyserna gjorda på texten och rangordingen.");
        System.out.println(returnedString[0]);
    }
}

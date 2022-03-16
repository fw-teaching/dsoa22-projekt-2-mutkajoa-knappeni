public class UI {

    public static void printTable(String input) {
        var surr = Utils.totalScore(Utils.getScore(Utils.getTrainData(), Utils.getInputData(
                input)));
        System.out.println(surr);
    }
}

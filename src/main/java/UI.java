public class UI {

    public static void printTable(String input) {
        var table = Utils.getScore(Utils.getTrainData(), Utils.getInputData(
                "staf Emil Mannerheim, maréchal de Finlande."))
                .get("svenska").get("total");
        System.out.println(table);
    }
}

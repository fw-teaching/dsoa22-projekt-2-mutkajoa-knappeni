import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(Utils.getScore(Utils.getTrainData(), Utils.getInputData("Während die finnischen Eisenerzlagerstätten fast erschöpft sind, finden sich noch bedeutende Vorkommen an Kupfer, Nickel, Zink und Chrom. In den 1860er Jahren folgte auf den Fund von Gold im Flusssand des Kemijoki ein regelrechter Goldrausch in Lappland. Bis heute wird an den Flüssen Lapplands teils durch Handwäsche, teils industriell Gold gewaschen, eine große Untertagemine befindet sich in Pahtavaara bei Sodankylä. Weitere, größtenteils noch unerschlossene Goldvorkommen sind über das gesamte Land verteilt, zuletzt wurde 1996 bei Kittilä eine auf Vorräte von 50 Tonnen Gold geschätzte Lagerstätte entdeckt. Außerdem ist Finnland der größte europäische Exporteur von Talk.[7] Dieses vor allem in der Papierindustrie benötigte Mineral wird derzeit in Sotkamo und Polvijärvi in großem Umfang abgebaut. Weitere in Finnland gewonnene Industrieminerale sind Wollastonit, Dolomit, Apatit, Quarz und Feldspat.")));
		/*
		Scanner scanner = new Scanner(System.in);

		String deString = FileHandler.readTextFile("assets/lang-samples/de.txt");
		String enString = FileHandler.readTextFile("assets/lang-samples/en.txt");
		String esString = FileHandler.readTextFile("assets/lang-samples/es.txt");
		String fiString = FileHandler.readTextFile("assets/lang-samples/fi.txt");
		String frString = FileHandler.readTextFile("assets/lang-samples/fr.txt");
		String itString = FileHandler.readTextFile("assets/lang-samples/it.txt");
		String noString = FileHandler.readTextFile("assets/lang-samples/no.txt");
		String svString = FileHandler.readTextFile("assets/lang-samples/sv.txt");

		System.out
				.println(Utils.getPrecentage(getCharFrequency(FileHandler.readTextFile("assets/lang-samples/fi.txt"))));
		System.out.println(Utils.getPrecentage(enString));
		System.out.println("Feed me!");
		String fedString = scanner.nextLine();
		System.out.println(Utils.getPrecentage(fedString));
		Float fiScore = 0.0f;
		Float enScore = 0.0f;
		for (int i = 0; i <= fedString.length() - 1; i++) {
			fiScore += Utils.getPrecentage(fiString).get(fedString.charAt(i));
			enScore += Utils.getPrecentage(enString).get(fedString.charAt(i));

		}
		System.out.println("fi " + fiScore);
		System.out.println("en " + enScore);

		// final File folder = new File("assets/lang-samples/");
		// ArrayList<String> surr = FileHandler.listFilesInFolder(folder);

		/*
		 * for (int i = 0; i <= surr.size() - 1; i++) {
		 * System.out.println(surr.get(i));
		 * }
		 */
		// System.out.println(FileHandler.listFilesInFolder(folder));
		// System.out.println(Utils.getPrecentage(test));

	}
}

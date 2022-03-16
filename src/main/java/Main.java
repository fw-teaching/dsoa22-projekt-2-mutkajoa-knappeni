import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println(Utils.getScore(Utils.getTrainData(), Utils.getInputData("Longtemps sous domination du voisin suédois, sous le règne duquel fut fondée la première capitale, Turku, la Finlande demeure marquée par les nombreux conflits qui l'ont opposée durant toute son histoire récente à la Russie, puissance impérialiste dont elle est devenue un grand-duché autonome en 1809, et dont elle n'a obtenu son indépendance qu'en 1917. L'année suivante, une guerre civile ensanglante le pays, suivie de deux guerres contre l'URSS pendant la Seconde Guerre mondiale, et enfin, d'une longue tutelle soviétique indirecte durant la guerre froide, connue sous le nom de « finlandisation ». Dans chaque guerre, l'armée de la République de Finlande était dirigée par Carl Gustaf Emil Mannerheim, maréchal de Finlande.")));
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

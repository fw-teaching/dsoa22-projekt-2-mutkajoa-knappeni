
public class Main {

	public static void main(String[] args) {
		String test = FileHandler.readTextFile("assets/lang-samples/SV.txt");
		System.out.println(Utils.getPrecentage(test));
	}
}


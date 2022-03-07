import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    static Object loadObject(String fileName) {
        Object returnedObject = null;

        try {
            ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(fileName));
            returnedObject = objectIn.readObject();
            objectIn.close();
        } catch (FileNotFoundException e) {
            System.out.println("No save was found.");
        } catch (InvalidClassException e) {
            System.out.println("Something wrong with the save file(probably too old).");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
        return returnedObject;
    }

    static void saveObject(String fileName, Object objectToSave) {
        System.out.println(String.format("Saving object to file: %s", fileName));
        try {
            FileOutputStream outStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            objectOutStream.writeObject(objectToSave);
            objectOutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String readTextFile(String fileName) {
        System.out.println(String.format("Reading file: %s", fileName));
        StringBuilder returnedString = new StringBuilder();

        try {
            FileInputStream inStream = new FileInputStream(fileName);
            InputStreamReader reader = new InputStreamReader(inStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                returnedString.append(line);
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnedString.toString();
    }

    static void writeTextFile(String fileName, String fileContents) {
        System.out.println("Writing file " + fileName);

        try {
            FileOutputStream outStream = new FileOutputStream(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(outStream, StandardCharsets.UTF_8);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(fileContents);
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
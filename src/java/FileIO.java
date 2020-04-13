import com.opencsv.CSVReader;

import java.io.*;

public class FileIO {
    static private Converter converter;

    private static void readFromFile() throws IOException {
        Reader reader = new FileReader("input.csv");
        CSVReader csvReader = new CSVReader(reader, ';');

        int countCharsOfAlphabet = Integer.parseInt(csvReader.readNext()[0]);
        char[] alphabet = new char[countCharsOfAlphabet];

        String[] next = csvReader.readNext();
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = next[i].charAt(0);
        }

        int countOfStates = Integer.parseInt(csvReader.readNext()[0]);
        converter = new Converter(alphabet, countOfStates);

        int countOfTransitions = Integer.parseInt(csvReader.readNext()[0]);

        for (int i = 0; i < countOfTransitions; i++) {
             next = csvReader.readNext();
             converter.insertIntoTransitionTable(Integer.parseInt(next[0]), next[1].charAt(0), Integer.parseInt(next[2]));
        }

        csvReader.close();
        reader.close();

    }

    private static void writeToFile() throws IOException {
        OutputStream os = new FileOutputStream("output.txt");
        converter.printEquivalentNFA(new PrintStream(os));
    }

    public static void main(String[] args) {
        try {
            readFromFile();
            System.out.println("Чтение файла...");
        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл");
        }

        try {
            writeToFile();
            System.out.println("Запись файла...");
            System.out.println("Успешно!");
        } catch (IOException e) {
            System.err.println("Не удалось записать данные в файл");
        }


    }
}

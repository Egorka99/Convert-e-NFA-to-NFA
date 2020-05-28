package com.program.nfa;

import com.opencsv.CSVReader;

import java.io.*;

public class FileIO {
    static private Converter converter;

    private static final String INPUT_FILE_PATH = "input.csv";
    private static final String OUTPUT_FILE_PATH = "output.txt";

    private static PrintStream printStream;

    static {
        try {
            printStream = new PrintStream(new FileOutputStream(OUTPUT_FILE_PATH));
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка записи файла");
        }
    }

    private static void readFromFile() throws IOException {
        Reader reader = new FileReader(INPUT_FILE_PATH);
        CSVReader csvReader = new CSVReader(reader, ';');

        int countCharsOfAlphabet = Integer.parseInt(csvReader.readNext()[0]);
        char[] alphabet = new char[countCharsOfAlphabet];

        String[] next = csvReader.readNext();
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = next[i].charAt(0);
        }

        int countOfStates = Integer.parseInt(csvReader.readNext()[0]);

        int numberOfStartState = Integer.parseInt(csvReader.readNext()[0]);

        int countOfFinalStates = Integer.parseInt(csvReader.readNext()[0]);

        int[] numbersOfFinalStates = new int[countOfFinalStates];

        for (int i = 0; i < countOfFinalStates; i++) {
            next = csvReader.readNext();
            numbersOfFinalStates[i] = Integer.parseInt(next[i]);
        }

        converter = new Converter(alphabet, countOfStates, printStream, numberOfStartState, countOfFinalStates, numbersOfFinalStates);

        int countOfTransitions = Integer.parseInt(csvReader.readNext()[0]);

        for (int i = 0; i < countOfTransitions; i++) {
            next = csvReader.readNext();
            converter.insertIntoTransitionTable(Integer.parseInt(next[0]), next[1].charAt(0), Integer.parseInt(next[2]));
        }

        csvReader.close();
        reader.close();

    }

    private static void writeToFile() {
        converter.printEquivalentNFA();
    }

    public static void main(String[] args) {
        try {
            readFromFile();
            System.out.println("Чтение файла " + INPUT_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Не удалось прочитать файл");
        }

        writeToFile();
        System.out.println("Запись файла " + OUTPUT_FILE_PATH);
        System.out.println("Успешно!");


    }
}

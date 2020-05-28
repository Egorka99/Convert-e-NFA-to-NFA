package com.program.nfa;

import java.util.Scanner;

public class ConsoleIO {

    public static void main(String[] args) {

        Converter converter;

        Scanner scanner = new Scanner(System.in);
        int countOfStates, countOfTransitions, numberOfStartState, countOfFinalStates;

        System.out.println("Входной НКА с эпсилон-командами");
        System.out.println("-----------------------------------");
        System.out.println("Количество элементов в алфавите: ");
        int noalpha = scanner.nextInt();

        char[] alphabet = new char[noalpha];

        System.out.println("Заметка:- [ Алфавит (вершины графа) - латинские буквы]");
        System.out.println("Заметка:- [ Используйте латинскую букву 'e' в качестве эпсилона]");
        System.out.println("Заметка:- ['e' должна быть последним символом во введенном алфавите]");
        scanner.nextLine();
        System.out.println("Алфавит: ");
        for (int i = 0; i < noalpha; i++) {
            String input = scanner.nextLine();
            alphabet[i] = input.charAt(0);
        }
        System.out.println("Количество состояний: ");
        countOfStates = scanner.nextInt();
        System.out.println("Начальное состояние: ");
        numberOfStartState = scanner.nextInt();
        System.out.println("Число конечных состояний");
        countOfFinalStates = scanner.nextInt();

        int[] numbersOfFinalStates = new int[countOfFinalStates];

        System.out.println("Введите конечные состояния");
        for (int i = 0; i < countOfFinalStates; i++) {
            numbersOfFinalStates[i] = scanner.nextInt();
        }

        System.out.println("Количество переходов: ");
        countOfTransitions = scanner.nextInt();
        scanner.nextLine();

        converter = new Converter(alphabet, countOfStates, System.out, numberOfStartState, countOfFinalStates, numbersOfFinalStates);

        System.out.println("Заметка:- [Переходы должны быть в такой форме--> номер состояния   символ алфавита   номер состояния]");
        System.out.println("Заметка:- [Пример: 1 b 2]");
        System.out.println("Заметка:- [Номер состояния - больше 0]");
        System.out.println("Переходы: ");
        for (int i = 0; i < countOfTransitions; i++) {
            String input = scanner.nextLine();
            String[] transition = input.split(" ");
            int r = Integer.parseInt(transition[0]);
            char c = transition[1].charAt(0);
            int s = Integer.parseInt(transition[2]);
            converter.insertIntoTransitionTable(r, c, s);
        }
        System.out.println();

        converter.printEquivalentNFA();

    }
}

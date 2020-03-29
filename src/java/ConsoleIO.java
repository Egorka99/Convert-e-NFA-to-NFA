import java.util.Scanner;

public class ConsoleIO {

    public static void main(String[] args) {

        Converter converter;

        Scanner scanner = new Scanner(System.in);
        int countOfStates, countOfTransitions, countOfFinalStates, numberOfStartState;


        System.out.println("enter the number of alphabets?");
        int noalpha = scanner.nextInt();

        char[] alphabet = new char[noalpha];

        System.out.println("NOTE:- [ use letter e as epsilon]");
        System.out.println("NOTE:- [e must be last character ,if it is present]");
        scanner.nextLine();
        System.out.println("\nEnter alphabets?");
        for (int i = 0; i < noalpha; i++) {
            String input = scanner.nextLine();
            alphabet[i] = input.charAt(0);
        }
        System.out.println("Enter the number of states?");
        countOfStates = scanner.nextInt();
        System.out.println("Enter the start state?");
        numberOfStartState = scanner.nextInt();
        System.out.println("Enter the number of final states?");
        countOfFinalStates = scanner.nextInt();

        int[] numbersOfFinalStates = new int[countOfFinalStates];

        System.out.println("Enter the final states?");
        for (int i = 0; i < countOfFinalStates; i++) {
            numbersOfFinalStates[i] = scanner.nextInt();
        }

        System.out.println("Enter no of transition?");
        countOfTransitions = scanner.nextInt();
        scanner.nextLine();

        converter = new Converter(alphabet,countOfStates,numberOfStartState,countOfFinalStates,numbersOfFinalStates);

        System.out.print("NOTE:- [Transition is in the form--> qno   alphabet   qno]" + '\n' + countOfTransitions);
        System.out.println("NOTE:- [States number must be greater than zero]");
        System.out.println("\nEnter transition?");
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

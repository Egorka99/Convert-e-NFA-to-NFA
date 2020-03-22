import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node {
    int st;
    Node link;
}

public class Converter {
    private static int[] set = new int[20], finalstate = new int[20], buffer = new int[20];
    private static int nostate, noalpha, s, notransition, nofinal, start, c, r;
    public static char[] alphabet = new char[20];
    private static int[][] eClosure = new int[20][20];
    public static Node[][] transitionTable = new Node[20][20];

    public static void tableInit() {
        for (int i = 0; i < notransition; i++) {
            for (int j = 0; j < notransition; j++) {
                transitionTable[i][j] = null;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node temp;
        System.out.println("enter the number of alphabets?");
        noalpha = scanner.nextInt();

        System.out.println("NOTE:- [ use letter e as epsilon]");
        System.out.println("NOTE:- [e must be last character ,if it is present]");
        scanner.nextLine();
        System.out.println("\nEnter alphabets?");
        for (int i = 0; i < noalpha; i++) {
           String input = scanner.nextLine();
            alphabet[i] = input.charAt(0);
        }
        System.out.println("Enter the number of states?");
        nostate = scanner.nextInt();
        System.out.println("Enter the start state?");
        start = scanner.nextInt();
        System.out.println("Enter the number of final states?");
        nofinal = scanner.nextInt();

        System.out.println("Enter the final states?");
        for (int i = 0; i < nofinal; i++) {
            finalstate[i] = scanner.nextInt();
        }
        System.out.println("Enter no of transition?");
        notransition = scanner.nextInt();
        scanner.nextLine();
        tableInit();
        System.out.print("NOTE:- [Transition is in the form--> qno   alphabet   qno]" + '\n' + notransition);
        System.out.println("NOTE:- [States number must be greater than zero]");
        System.out.println("\nEnter transition?");
        for (int i = 0; i < notransition; i++) {
            String input = scanner.nextLine();
            String[] transition = input.split(" ");
            int r = Integer.parseInt(transition[0]);
            Character c = transition[1].charAt(0);
            int s = Integer.parseInt(transition[2]);
            insertTrantbl(r,c,s);
        }
        System.out.println();

        for (int i = 1; i <= nostate; i++) {
            c = 0;
            for (int j = 0; j < 20; j++) {
                buffer[j] = 0;
                eClosure[i][j] = 0;
            }
            findclosure(i, i);
        }

        System.out.println("Equivalent NFA without epsilon");
        System.out.println("-----------------------------------");
        System.out.print("start state:");
        printEClosure(start);
        System.out.print("\nAlphabets:");
        for (int i = 0; i < noalpha; i++) {
            System.out.print(alphabet[i] + " ");
        }
        System.out.print("\n States :");
        for (int i = 1; i <= nostate; i++) {
            printEClosure(i);
        }
        System.out.println("\nTransitions are...:");
        int t;
        for (int i = 1; i <= nostate; i++) {
            for (int j = 0; j < noalpha - 1; j++) {
                for (int m = 1; m <= nostate; m++) {
                    set[m] = 0;
                }
                for (int k = 0; eClosure[i][k] != 0; k++) {
                    t = eClosure[i][k];
                    temp = transitionTable[t][j];
                    while (temp != null) {
                        unionclosure(temp.st);
                        temp = temp.link;
                    }
                }
                System.out.println();
                printEClosure(i);
                System.out.print(alphabet[j] + "\t");
                System.out.print("{");
                for (int n = 1; n <= nostate; n++) {
                    if (set[n] != 0) {
                        System.out.print("q" + n + ",");
                    }
                }
                System.out.print("}");
            }
        }
        System.out.print("\n Final states:");
        findfinalstate();
    }

    public static void findclosure(int x, int sta) {
        Node temp;
        if(buffer[x] != 0) {
            return;
        }
        eClosure[sta][c++] = x;
        buffer[x] = 1;
        if(alphabet[noalpha - 1] == 'e' && transitionTable[x][noalpha - 1] != null) {
            temp = transitionTable[x][noalpha - 1];
            while(temp != null) {
                findclosure(temp.st, sta);
                temp = temp.link;
            }
        }
    }
    public static void insertTrantbl(int r, char c, int s) {
        int j;
        Node temp;
        j = findalpha(c);
        if(j == 999) {
            System.out.println("error");
            System.exit(0);
        }
        temp = new Node();
        temp.st = s;
        temp.link = transitionTable[r][j];
        transitionTable[r][j] = temp;
    }

    public static int findalpha(char c) {
        for(int i = 0; i < noalpha; i++) {
            if(alphabet[i] == c) {
                return i;
            }
        }
        return 999;
    }

    public static void unionclosure(int i) {
        int j = 0, k;
        while(eClosure[i][j] != 0) {
            k = eClosure[i][j];
            set[k] = 1;
            j++;
        }
    }

    public static void findfinalstate() {
        int t;
        for(int i = 0; i < nofinal; i++) {
            for(int j = 1; j <= nostate; j++) {
                for(int k = 0; eClosure[j][k] != 0; k++) {
                    if(eClosure[j][k] == finalstate[i]) {
                        printEClosure(j);
                    }
                }
            }
        }
    }

    public static void printEClosure(int i) {
        System.out.print("{");
        for(int j = 0; eClosure[i][j] != 0; j++) {
            System.out.print("q" + eClosure[i][j] + ",");
        }
        System.out.print("}\t");
    }
}


public class Converter {

    private int[] set = new int[20], numbersOfFinalStates, buffer = new int[20];
    private int countOfStates, countOfFinalStates, numberOfStartState, c;
    private char[] alphabet;
    private int[][] eClosure = new int[20][20];
    private Node[][] transitionTable = new Node[20][20];


    public Converter(char[] alphabet, int countOfStates, int numberOfStartState, int countOfFinalStates, int[] numbersOfFinalStates) {
        this.alphabet = alphabet;
        this.countOfStates = countOfStates;
        this.numberOfStartState = numberOfStartState;
        this.countOfFinalStates = countOfFinalStates;
        this.numbersOfFinalStates = numbersOfFinalStates;

        tableInit();
    }

    private void tableInit() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                transitionTable[i][j] = null;
            }
        }
    }

    public void printEquivalentNFA() {
        Node temp;
        for (int i = 1; i <= countOfStates; i++) {
            c = 0;
            for (int j = 0; j < 20; j++) {
                buffer[j] = 0;
                eClosure[i][j] = 0;
            }
            findClosure(i, i);
        }

        System.out.println("Эквивалентный НКА без ε-команд");
        System.out.println("-----------------------------------");
        System.out.print("Начальное состояние: ");
        System.out.print("q" + numberOfStartState);
        System.out.print("\nАлфавит: ");
        for (int i = 0; i < alphabet.length; i++) {
            if (i == alphabet.length - 1) {
                System.out.print(alphabet[i]);
            }
            else {
                System.out.print(alphabet[i] + ",");
            }
        }
        System.out.print("\nСостояния: ");
        for (int i = 1; i <= countOfStates; i++) {
            if (i == countOfStates) {
                System.out.print("q" + i);
            }
            else {
                System.out.print("q" + i + ",");
            }
        }
        System.out.println("\nПереходы:");
        int t;
        for (int i = 1; i <= countOfStates; i++) {
            for (int j = 0; j < alphabet.length - 1; j++) {
                for (int m = 1; m <= countOfStates; m++) {
                    set[m] = 0;
                }
                for (int k = 0; eClosure[i][k] != 0; k++) {
                    t = eClosure[i][k];
                    temp = transitionTable[t][j];
                    while (temp != null) {
                        unionClosure(temp.st);
                        temp = temp.link;
                    }
                }
                System.out.println();
                System.out.print("q" + i + "  ");
                System.out.print(alphabet[j] + "  ");
                System.out.print("{");
                for (int n = 1; n <= countOfStates; n++) {
                    if (set[n] != 0) {
                        if ((n == countOfStates)) {
                            System.out.print("q" + n);
                        } else {
                            System.out.print("q" + n + ",");
                        }
                    }
                }
                System.out.print("}");
            }
        }
        System.out.println();
        System.out.print("\nКонечные состояния: ");
        findFinalState();
    }

    private void findClosure(int x, int sta) {
        Node temp;
        if (buffer[x] != 0) {
            return;
        }
        eClosure[sta][c++] = x;
        buffer[x] = 1;
        if (alphabet[alphabet.length - 1] == 'e' && transitionTable[x][alphabet.length - 1] != null) {
            temp = transitionTable[x][alphabet.length - 1];
            while (temp != null) {
                findClosure(temp.st, sta);
                temp = temp.link;
            }
        }
    }

    public void insertIntoTransitionTable(int r, char c, int s) {
        int j;
        Node temp;
        j = findCharacterInAlphabet(c);
        if (j == -1) {
            System.out.println("error");
            System.exit(0);
        }
        temp = new Node();
        temp.st = s;
        temp.link = transitionTable[r][j];
        transitionTable[r][j] = temp;
    }

    private int findCharacterInAlphabet(char c) {
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private void unionClosure(int i) {
        int j = 0, k;
        while (eClosure[i][j] != 0) {
            k = eClosure[i][j];
            set[k] = 1;
            j++;
        }
    }

    private void findFinalState() {
        for (int i = 0; i < countOfFinalStates; i++) {
            for (int j = 1; j <= countOfStates; j++) {
                for (int k = 0; eClosure[j][k] != 0; k++) {
                    if (eClosure[j][k] == numbersOfFinalStates[i]) {
                            System.out.print(j + " ");
                    }
                }
            }
        }
    }

    private void printEClosure(int i) {
        System.out.print("{");
        for (int j = 0; eClosure[i][j] != 0; j++) {
            System.out.print("q" + eClosure[i][j] + ",");
        }
        System.out.print("}\t");
    }
}


public class Converter {

    private int countOfStates;
    private int c;
    private int[] set;
    private int[] buffer;
    private char[] alphabet;
    private int[][] eClosure;
    private Node[][] transitionTable;


    public Converter(char[] alphabet, int countOfStates) {
        this.alphabet = alphabet;
        this.countOfStates = countOfStates;

        set = new int[countOfStates+1];
        buffer = new int[countOfStates+1];
        eClosure = new int[countOfStates+1][countOfStates+1];
        transitionTable = new Node[countOfStates+1][countOfStates+1];

        tableInit();
    }

    private void tableInit() {
        for (int i = 0; i < countOfStates+1; i++) {
            for (int j = 0; j < countOfStates+1; j++) {
                transitionTable[i][j] = null;
            }
        }
    }

    public void printEquivalentNFA() {
        fillEClosure();

        Node temp;
        System.out.println("Эквивалентный НКА без ε-команд");
        System.out.println("-----------------------------------");
        System.out.print("Алфавит: ");
        for (int i = 0; i < alphabet.length; i++) {
            if (i == alphabet.length - 1) {
                System.out.print(alphabet[i]);
            } else {
                System.out.print(alphabet[i] + ",");
            }
        }
        System.out.print("\nСостояния: ");
        for (int i = 1; i <= countOfStates; i++) {
            if (i == countOfStates) {
                System.out.print("q" + i);
            } else {
                System.out.print("q" + i + ",");
            }
        }
        System.out.println("\nТаблица:");
        int t;

        //заголовок таблицы
        for (int j = 0; j < alphabet.length - 1; j++) {
            System.out.printf("%10s", alphabet[j]);
        }
        System.out.println();

        //левая колонка + тело
        for (int i = 1; i <= countOfStates; i++) {
            System.out.print("q" + i + "  ");
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

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("{");
                for (int n = 1; n <= countOfStates; n++) {
                    if (set[n] != 0) {
                        if (isEndIndex(set,n)) {
                            stringBuilder.append("q").append(n);
                        } else {
                            stringBuilder.append("q").append(n).append(",");
                        }
                    }
                }
                stringBuilder.append("}");
                System.out.printf("%10s", stringBuilder.toString());
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean isEndIndex(int[] array, int index) {
        if (index == array.length - 1) {
            return true;
        }
        if (array[index + 1] == 1) {
            return false;
        }
        else {
            return isEndIndex(set,index + 1);
        }
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

    private void fillEClosure() {
        for (int i = 1; i <= countOfStates; i++) {
            c = 0;
            for (int j = 0; j < countOfStates+1; j++) {
                buffer[j] = 0;
                eClosure[i][j] = 0;
            }
            findClosure(i, i); 
        }
    }

    private void unionClosure(int i) {
        int j = 0, k;
        while (eClosure[i][j] != 0) {
            k = eClosure[i][j];
            set[k] = 1;
            j++;
        }
    }

}


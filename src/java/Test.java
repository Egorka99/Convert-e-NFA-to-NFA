public class Test {
    public static void main(String[] args) {
        Converter converter = new Converter(new char[]{'a','b','c','e'},3,1,1,new int[]{3});
        converter.insertIntoTransitionTable(1,'a',1);
        converter.insertIntoTransitionTable(1,'e',2);
        converter.insertIntoTransitionTable(2,'b',2);
        converter.insertIntoTransitionTable(2,'e',3);
        converter.insertIntoTransitionTable(3,'c',3);

        converter.printEquivalentNFA();
    }
}
 
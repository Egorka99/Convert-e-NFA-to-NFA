public class Test {
//    public static void main(String[] args) {
//        Converter converter = new Converter(new char[]{'a','b','c','e'},3);
//        converter.insertIntoTransitionTable(1,'a',1);
//        converter.insertIntoTransitionTable(1,'e',2);
//        converter.insertIntoTransitionTable(2,'b',2);
//        converter.insertIntoTransitionTable(2,'e',3);
//        converter.insertIntoTransitionTable(3,'c',3);
//
//        converter.printEquivalentNFA();
//    }

//    public static void main(String[] args) {
//        Converter converter = new Converter(new char[]{'a','b','e'},3);
//        converter.insertIntoTransitionTable(1,'a',1);
//        converter.insertIntoTransitionTable(1,'e',2);
//        converter.insertIntoTransitionTable(2,'b',2);
//        converter.insertIntoTransitionTable(2,'e',3);
//        converter.insertIntoTransitionTable(3,'a',3);
//        converter.insertIntoTransitionTable(3,'b',3);
//
//        converter.printEquivalentNFA();
//    }

    public static void main(String[] args) {
        Converter converter = new Converter(new char[]{'a','b','e'},5);
        converter.insertIntoTransitionTable(1,'b',2);
        converter.insertIntoTransitionTable(2,'b',1);
        converter.insertIntoTransitionTable(1,'e',3);
        converter.insertIntoTransitionTable(3,'a',4);
        converter.insertIntoTransitionTable(4,'a',3);
        converter.insertIntoTransitionTable(3,'b',5);
        converter.insertIntoTransitionTable(5,'a',3);

        converter.printEquivalentNFA();
    }

}

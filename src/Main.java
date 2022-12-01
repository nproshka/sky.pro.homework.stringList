import StringList.StringList;
import StringList.StringListImpl;

public class Main {
    public static void main(String[] args) {
        StringList test = new StringListImpl(10);
        test.add("тест1");
        test.add("тест2");
        test.add(1, "test3");
        test.get(2);
        test.isEmpty();
        test.remove(0);
        test.size();
        test.set(1, "test4");
        test.contains("test4");
        test.clear();

//        Только я не понял как мне сделать тесты если у меня обычный проект и нет файла pom.xml
    }
}
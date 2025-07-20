import java.util.*;
import java.util.stream.*;

@FunctionalInterface
interface Operate{
    int operations(int a, int b);

}

@FunctionalInterface
interface string{
    String string_operate(String str);
}


class InterfaceTest{
    string str = s -> s.toLowerCase();

    public InterfaceTest(String s){
        System.out.println(str.string_operate(s));
    }

    public InterfaceTest(int p, int q) throws ArithmeticException{
        Operate divide = (a,b) -> a/b;
        System.out.println(divide.operations(p,q));
    }
}

public class Sandbox {
    public static void main(String args[]){
        System.out.println("***********  Stream and map ************");

        List<String> str = new ArrayList<String>();
        str.add("1");
        str.add("2");
        str.add("3");
        str.add("4");
        List<Integer> i = new ArrayList<Integer>();
        i = str.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        //i.stream().map(s -> s*10).forEach(System.out::println);
        //Can also be written as:
        i.stream().map(s -> s*10).forEach(s -> System.out.println(s+1));

        HashMap<Integer,String> hmap = new HashMap<>();
        hmap.put(1, "ONE");
        hmap.put(2, "TWO");
        hmap.put(3, "THREE");

        hmap.values().stream().map(s -> s.toLowerCase()).forEach(System.out::println);
        System.out.println("keys with values multiplied my value length");

        hmap.keySet().stream().map(k -> k*(hmap.get(k)).length()).forEach(System.out::println);

        System.out.println("***********  Lambda functions ************");

        Operate multiply = (a,b) -> a*b;
        System.out.println(multiply.operations(5,2));

        string upper = s -> s.toUpperCase();
        System.out.println(upper.string_operate("THIS IS A LOWER CASE SENTENCE"));

        InterfaceTest obj = new InterfaceTest("LIVERPOOL");
        obj = new InterfaceTest(10,0);
        

    }
}

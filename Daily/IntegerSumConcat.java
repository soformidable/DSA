public class IntegerSumConcat{
    public static long sumAndMultiply(int n) {

        String str = String.valueOf(n);
        StringBuilder new_str = new StringBuilder();
        long sum = 0L;
        for(char x : str.toCharArray()){
            if(x != '0')
                new_str.append(x);
            sum += Character.getNumericValue(x);
        }
        if(new_str.length() == 0)
            return 0;

        return (Long.parseLong(new_str.toString()) * sum);
    }

    public static void main(String args[]){
        System.out.println(sumAndMultiply(000));
    }
}
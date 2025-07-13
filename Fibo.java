public class Fibo {
    public static void fib(int n){
        int cur = 0, p1 = 1, p2 = 0;
        if(n>=2)
            System.out.print(p2 + " " +  p1 + " ");
        if(n<=1)
            System.out.print(p2 + " ");
        n=n-2;
        while(n>0){
            cur = p1 + p2;
            p2 = p1 ;
            p1 = cur;
            System.out.print(cur + " ");
            n--;
        }
    }

    public static void main(String args[]){

        int n=9;

        fib(n);
//        int p1 = 1, p2 =0, i = 0, cur=0;
//
//        if(n>=2)
//        System.out.print(p2 + " " + p1 + " ");
//        if(n<=1)
//            System.out.print(p2 + " ");
//
//
//        for(i=2;i<n;i++){
//            cur = p1 + p2;
//            p2 = p1;
//            p1 = cur;
//            System.out.print(cur + " ");
//        }

    }
}

// 0 1 1 2 3 5
// 1 2 3 4 5 6  n = 6

public class Array {
    public static void main(String args[]){
        int matrix[][] = new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}};       
        System.out.println(matrix.length+" "+matrix[0].length);

        int row = matrix.length;
        int column = matrix[0].length;

        for(int i=0;i<row;i++){
            System.out.println("");
            for(int j=0;j<column;j++){
                System.out.print(matrix[i][j]+"\t");
            }
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if (matrix[i][j]==0){
                    int p=0,q=0;
                    while(p<column){
                        matrix[i][p]=-1;
                        p++;
                    }
                    while(q<row){
                        matrix[q][j]=-1;
                        q++;
                    }
                }
            }
            
        }
                
        System.out.println("\nAfter");
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                if (matrix[i][j] == -1)
                    matrix[i][j] = 0;
            }
        }

        System.out.println("\nAfter");
        for(int i=0;i<row;i++){
            System.out.println("");
            for(int j=0;j<column;j++){
                System.out.print(matrix[i][j]+"\t");
            }
        
        
    }
}
}

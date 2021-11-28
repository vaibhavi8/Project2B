import java.util.*;
//import java.lang.*;
public class Project2{
    public static void main(String[]args){
        int[][] graph = { {0,7,12,100,100,100}, {100,0,2,9,100,100}, {100,100,0,100,10,100}, {100,100,100,0,100,1}, {100,100,100,4,0,5}, {100,100,100,100,100,0} };
        int [][] graphB = 
        {{0,3,100,7},{8,0,2,100},{5, 100, 0, 1},{2, 100, 100, 0}};
        for(int i = 0; i < 6; i++){
            System.out.println(Arrays.toString(graph[i]));
        }
        for(int i = 0; i < 4; i++){
            System.out.println(Arrays.toString(graphB[i]));
        }
        System.out.println();
        FloydWarshall(graph);


        

    }
    public static int[][] createGraphSizeN(int n){
        Random r = new Random();
        int[][] matrix = new int[n][n];
        for(int i = 0; i<n; i++){
          for(int j = 0; j<n; j++){
            if(i==j){
                  matrix[i][j] = 0;
            }
            else{
                int randomValue = -100 + (100 -(-100)) * r.nextInt();
                matrix[i][j] = randomValue;
            } 
          }
        }
        return matrix;
      }
    public static int[][] FloydWarshall(int a[][]){
        int n = a.length;
        int[][] prev = new int[n][n];
        int [][] curr = new int[n][n];
        prev = a;
        for(int i = 0; i < prev.length; i++){
            for(int j =0; j<prev[0].length;j++){
                System.out.print(prev[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for(int k = 0; k < n; k++){
            for(int i = 0; i < n ; i++){
                for(int j=0; j < n; j++){
                    curr[i][j] = Math.min(prev[i][j], prev[i][k]+prev[k][j]);
                }
            }
            prev = curr;
        }
        for(int i = 0; i < curr.length; i++){
            for(int j = 0; j<curr[0].length;j++){
                System.out.print(curr[i][j] + " ");
            }
            System.out.println();
        }
        return curr; 
    }
}
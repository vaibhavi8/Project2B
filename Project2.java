import java.util.*;
//import java.lang.*;
public class Project2{
    public static void main(String[]args){
        //the verticies that have no paths to other vertices are considered to be 1000 (a positive big number representing infinite) instead of 0
        int inf = 10000000;
        int[][] graph = { {0,7,12,inf,inf,inf}, {inf,0,2,9,inf,inf}, {inf,inf,0,inf,10,inf}, {inf,inf,inf,0,inf,1}, {inf,inf,inf,4,0,5}, {inf,inf,inf,inf,inf,0} };
        int [][] graphB = 
        {{0,3,inf,7},{8,0,2,inf},{5, inf, 0, 1},{2, inf, inf, 0}};
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
        //prints out the graph, and ensures that graph prev replicates a 
        for(int i = 0; i < prev.length; i++){  
            for(int j =0; j<prev[0].length;j++){
                if(prev[i][j]>1000){
                    System.out.print("inf ");
                }
                else{
                    System.out.print(prev[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();

        //creates k iterations of the graph, each iteration keeps the previous graph's row/column k
        //number of iterations is the number of verticies
        for(int k = 0; k < n; k++){
            for(int i = 0; i < n ; i++){
                for(int j=0; j < n; j++){
                    //goes through the graph, the current graph selects the shortest path by comparing the current path to the path that has to go through vertice k
                    //the new shortest path is set as current, and the algorithm continues to check paths through all the verticies to get the overall shortest path (by iterating through k)
                    curr[i][j] = Math.min(prev[i][j], prev[i][k]+prev[k][j]);
                }
            }
            //the previous graph is set to the current graph to continue comparisons and find the shortest path
            prev = curr;
        }
        //prints out the shortest paths for the directed weighted graphs
        for(int i = 0; i < curr.length; i++){
            for(int j = 0; j<curr[0].length;j++){
                if(curr[i][j]>1000){
                    System.out.print("inf ");
                }
                else{
                    System.out.print(curr[i][j] + " ");
                }
            }
            System.out.println();
        }
        return curr; 
    }
}
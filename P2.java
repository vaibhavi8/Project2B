import java.util.*;


public class P2 {
  public static int[][] dijkstra(int a[][]){
    int nodeCount = a[0].length;
    // System.out.println("length is " + nodeCount);
    int[][] dist = new int[nodeCount-1][nodeCount];   //
    int[][] prev = new int[nodeCount-1][nodeCount];   //
    for(int i = 0 ; i < a[0].length;i++){
      dist[0][i] = a[0][i];   //first progression chart
      prev[0][i] = 0;
    }
    int[] nodes = new int[nodeCount-1];
    int node = 0;   //dominant node/node we choose-
    int distance = node;
    nodes[0] = node;
    int tempNode = 0;
    
    int count = 1;
    while (count < nodeCount -1){     //going from 1 < 5 so from 1 to 4
      int min = 0;
      for(int i = 0; i < nodeCount; i++){
        if(dist[count-1][i] != 0){
          if(min == 0 && i != node && dist[count-1][i] > distance){       //note choosing an already traversed node
            min = dist[count-1][i];     //finding smallest dist in chart that is not 0
            tempNode = i;
            // System.out.println("node i am choosing is " + node);
          }
          else if(min != 0 && i != node && dist[count-1][i] > distance){
            if(dist[count-1][i] < min){
              min = dist[count-1][i];
              tempNode = i;
              // System.out.println("node i am choosing is " + node);
            }
          }
        }
      }
      distance = min;
      node = tempNode;
      // System.out.println("node is "+node);
      nodes[count-1] = node;
      // node = temp
      

      //put values of previous chart into current chart
      for(int i = 0; i < nodeCount; i++){
        prev[count][i] = prev[count-1][i];
        dist[count][i] = dist[count-1][i];
      }

      //adjusting current chart values
      for(int i = 0; i < nodeCount; i++){
        if(a[node][i] != 0){    //looking at numerical values in graph
          int added = a[node][i] + min;
          if(added < dist[count-1][i]){       //smallest distance for a specific node is either already in chart or i will update
            dist[count][i] = added;
            prev[count][i] = node;
          }
          if(dist[count-1][i] == 0){      //when the val is 0(infinity)
            dist[count][i] = added;
            prev[count][i] = node;
          }
        }  
      }
      count++;
    
    }
    //last value in progression chart also needs to be identified
    int min = 0;
    for(int i = 0; i < nodeCount; i++){
      if(dist[count-1][i] != 0){
        if(min == 0 && i != node && dist[count-1][i] >= distance){       //note choosing an already traversed node
          min = dist[count-1][i];     //finding smallest dist in chart that is not 0
          tempNode = i;
          // System.out.println("node i am choosing is " + dist[count-1][i]);
        }
        else if(min != 0 && i != node && dist[count-1][i] >= distance){
          if(dist[count-1][i] < min){
            min = dist[count-1][i];
            tempNode = i;
            // System.out.println("node i am choosing is " + dist[count-1][i]);
          }
        }
      }
    }
    distance = min;
    node = tempNode;
    // System.out.println("node is "+node);
    // nodes[count-1] = dist[count-1][node];
    nodes[nodeCount-2] = node;
    // System.out.println("nodes");
    // System.out.println(Arrays.toString(nodes));
    System.out.println();
    //printing out the distance and prev progression charts

    // for(int i = 0; i < nodeCount-1; i++){
    //     System.out.println(Arrays.toString(dist[i]));
    //     System.out.println(Arrays.toString(prev[i]));
    //     System.out.println();
    // }
    int count2 = 0;
    while( count2 < nodeCount-1){
      // System.out.println("here");
      int[] backtrack = new int[nodeCount-1];
      int looking = nodes[count2];
      backtrack[0]=looking;
      int backNode = prev[nodeCount-2][looking];    //getting the prev node in the last progression chart
      int addTrack = 1;
      backtrack[addTrack] = backNode;
      looking = backNode;
      while(backNode != 0){
        addTrack++;
        // System.out.println("add is "+ addTrack);
        looking = backNode;
        backNode = prev[nodeCount-2][looking];
        backtrack[addTrack] = backNode;

      }

      
      for(int i = addTrack; i >= 1;i--){
        System.out.print( backtrack[i] + " -> " );
      }
      System.out.print(backtrack[0] + "  \t  " +dist[nodeCount-2][backtrack[0]]);
      System.out.println();

      count2++;
    }


    return a;
  }

  public static int[][] sparseCreate(int num){
    int[][] ranGraph = new int[num][num];
    int min = 1;
		int max = 33;

		Random random = new Random();

    for(int i = 0; i < num; i++){
      for(int j = 0; j < num; j++){
        if(i == j){
          ranGraph[i][j]=0;
        }
        else{
          ranGraph[i][j] = random.nextInt(max + min) + min;
        }
      }
    }
    int halfNum = num/2;
    int quart = halfNum/2;
    int infCount = (halfNum + quart) * num;    //number of infinities to get (75 percent ish)
    int min2 = 0;
		int max2 = num-1;     //geting random ints from 0 to 1-length
    int count = 0;
    while(count <= infCount){
      Random r = new Random();
      int i = r.nextInt(max2 + min2) + min2;
      int j = r.nextInt(max2 + min2) + min2;
      if(i == j){
      }
      else if(i != j){
        ranGraph[i][j] = 0;
        count++;
      }

    }

    return ranGraph;
  }
  public static int[][] denseCreate(int num){
    int[][] ranGraph = new int[num][num];
    int min = 1;
		int max = 33;

		Random random = new Random();

    for(int i = 0; i < num; i++){
      for(int j = 0; j < num; j++){
        if(i == j){
          ranGraph[i][j]=0;
        }
        else{
          ranGraph[i][j] = random.nextInt(max + min) + min;
        }
      }
    }
    int halfNum = num/2;
    int quart = halfNum/2;
    int infCount = (quart) * num;    //number of infinities to get (25 percent ish)
    int min2 = 0;
		int max2 = num-1;     //geting random ints from 0 to 1-length
    int count = 0;
    while(count <= infCount){
      Random r = new Random();
      int i = r.nextInt(max2 + min2) + min2;
      int j = r.nextInt(max2 + min2) + min2;
      if(i == j){
      }
      else if(i != j){
        ranGraph[i][j] = 0;
        count++;
      }

    }

    return ranGraph;
  }

  public static int[][] createGraphSizeN(int n){
    int min = 1;
    int max = 200;
    int[][] matrix = new int[n][n];
    for(int i = 0; i<n; i++){
      for(int j = 0; j<n; j++){
        if(i==j){
              matrix[i][j] = 0;
        }
        else{
            int randomValue = (int) Math.floor(Math.random()*(max-min+1)+min);
            matrix[i][j] = randomValue;
        } 
      }
    }
    return matrix;
}
public static int[][] createSparseGraphSizeN(int n){
    int min = 70;
    int max = 200;
    int[][] matrix = new int[n][n];
    for(int i = 0; i<n; i++){
      for(int j = 0; j<n; j++){
        if(i==j){
              matrix[i][j] = 0;
        }
        else{
            int randomValue = (int) Math.floor(Math.random()*(max-min+1)+min);
            matrix[i][j] = randomValue;
        } 
      }
    }
    return matrix;
}
public static int[][] createDenseGraphSizeN(int n){
    int min = 1;
    int max = 129;
    int[][] matrix = new int[n][n];
    for(int i = 0; i<n; i++){
      for(int j = 0; j<n; j++){
        if(i==j){
              matrix[i][j] = 0;
        }
        else{
            int randomValue = (int) Math.floor(Math.random()*(max-min+1)+min);
            matrix[i][j] = randomValue;
        } 
      }
    }
    return matrix;
}
public static int[][] floydWarshall(int a[][]){
    int n = a.length;
    int[][] prev = new int[n][n];
    int [][] curr = new int[n][n];
    prev = a;
    //prints out the graph, and ensures that graph prev replicates a 
    System.out.println("Directed Weighted Graph: ");
    for(int i = 0; i < prev.length; i++){  
        for(int j =0; j<prev[0].length;j++){
            if(prev[i][j]>100){
                System.out.print("inf\t");
            }
            else{
                System.out.print(prev[i][j] + "\t");
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
    System.out.println("Shortest Path for the Directed Weighted Graph: ");
    for(int i = 0; i < curr.length; i++){
        for(int j = 0; j<curr[0].length;j++){
            if(curr[i][j]>100){
                System.out.print("inf\t");
            }
            else{
                System.out.print(curr[i][j] + "\t");
            }
        }
        System.out.println();
    }
    return curr; 
}
  public static void main(String[] args) {
    double startTime;
    double endTime;
    double timeTook;
    //graph in here
    int inf = 10000000;
    // int[][] graph = { {0,7,12,0,0,0}, {0,0,2,9,0,0}, {0,0,0,0,10,0}, {0,0,0,0,0,1}, {0,0,0,4,0,5}, {0,0,0,0,0,0} };
		// System.out.println("weighted graph: ");
    // for(int i = 0; i < 6; i++){
    //     System.out.println(Arrays.toString(graph[i]));
    // }

    // dijkstra
    int size = 10;
    int[][] sparse = sparseCreate(size);
    int[][] dense = denseCreate(size);
    //sparse
    startTime = System.nanoTime();
    for(int i = 0; i < 10; i++){
      dijkstra(sparse);
    }
    endTime = System.nanoTime();
    timeTook = (endTime-startTime)/1000000000;
    System.out.println("dijkstra took " + timeTook/10 + " seconds for 10 interations of size " + size + " for sparse ");
    //dense
    startTime = System.nanoTime();
    for(int i = 0; i < 10; i++){
      dijkstra(dense);
    }
    endTime = System.nanoTime();
    timeTook = (endTime-startTime)/1000000000;
    System.out.println("dijkstra took " + timeTook/10 + " seconds for 10 interations of size " + size + " for dense ");

    //FloydWarshall
    int[][] sparseGraph = createSparseGraphSizeN(size);
    int[][] denseGraph = createDenseGraphSizeN(size);
    //sparse
    startTime = System.nanoTime();
    floydWarshall(sparseGraph);
    endTime = System.nanoTime();
    timeTook = (endTime-startTime)/1000000000;
    System.out.println("Floyd-Warshall took " + timeTook/10 + " seconds for 10 interations of size " + size + " for sparse ");
    
    //dense
    startTime = System.nanoTime();
    floydWarshall(denseGraph);
    endTime = System.nanoTime();
    timeTook = (endTime-startTime)/1000000000;
    System.out.println("Floyd-Warshall took " + timeTook/10 + " seconds for 10 interations of size " + size + " for dense");
  }
}

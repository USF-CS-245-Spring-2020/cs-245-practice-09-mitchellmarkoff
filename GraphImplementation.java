import java.util.*;

public class GraphImplementation implements Graph {

    //Declare adjacency matrix
    private final int[][] adjacencyMatrix;

    public GraphImplementation(int vertices) {
        //Matrix is initialized to represent size of graph
        adjacencyMatrix = new int [vertices][vertices]; 
    }

    public void addEdge(int source, int target) throws Exception {
        if (((source < 0) || (source > adjacencyMatrix.length)) || ((target < 0) || (target > adjacencyMatrix.length))){
            //through exception if the source and target are less than 0
            throw new Exception("Out of bounds"); 
        }
        if (source != target) {
        //An edge is added if the source and target are different values
            adjacencyMatrix[source][target] = 1; 
        }
    }

    public List<Integer> neighbors(int vertex) throws Exception{
        if (vertex < 0 || vertex > adjacencyMatrix.length) {
            throw new Exception("Out of bounds"); 
        }

        List<Integer> adjacent = new ArrayList<>(); 

        for(int i = 0; i < adjacencyMatrix.length; i++) {
            if(adjacencyMatrix[vertex][i] == 1) {
            //Add to adjacency martix for evry edge
                adjacent.add(i); 
            }
        }
        return adjacent;
    }

    private int[] incident(){
        //Initialize incidents with the length of the adjacent matrix 
        int[] incidents = new int[adjacencyMatrix.length]; 
        for (int v = 0; v < adjacencyMatrix.length; v++) {
            for (int w = 0; w < adjacencyMatrix.length; w++) {
                //add incidents[v] to adjacency matrix
                incidents[v] += adjacencyMatrix[w][v]; 
            }
        }

        return incidents; 
    } 

    public List<Integer> topologicalSort(){
        List<Integer> arr = new ArrayList<>();

        int[] incidents = incident(); 

        for (int i = 0; i < incidents.length; i++) { 
            int v = noIncidents(incidents);
            //v equals i no insident occurs
            //i otherwise -1
            if (v != -1) { 
                //add v to arr
                arr.add(v); 
                incidents[v] = -1;
                for(int j = 0; j < incidents.length; j++) {
                    if (adjacencyMatrix[v][j] == 1) {
                        incidents[j] -= 1;
                    }
                }
            } 
        }
        System.out.println(arr); //Print arr and return it
        return arr;
    }
    
    private int noIncidents (int [] incidents) {
        for (int i = 0; i < incidents.length; i++) {
            //If there is no incident
            if (incidents[i] == 0) {
                return i; 
            }
        }
        return -1;
    }
}
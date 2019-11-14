import java.util.*;

public class GraphImplementation implements Graph {
    int vertices;
    int[][] graph;

    public GraphImplementation(int vertices) {
        this.vertices = vertices; 
        this.graph = new int[vertices][vertices];
    }


    public void addEdge(int v1, int v2) throws Exception {
        if ((v1 >= vertices || v1 < 0) || (v2 >= vertices || v2 < 0)) {
            throw new Exception();
        }
        graph[v1][v2] = 1;
    }

    private int findZero(int[] sums) {
        for (int i = 0; i < sums.length; i++) {
            if (sums[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints (to console) one ordering of vertices such that each directed edge
     * (v1, v2) from vertex v1 to vertex v2, v1 comes before v2 in the ordering. If
     * such an ordering is not possible (due to cycles, for example), this function
     * must indicate so, though it may print a partial solution in so doing.
     */
    public List<Integer> topologicalSort() {
        List<Integer> sorted = new LinkedList<Integer>();
        int[] sums = new int[vertices];
        for(int i=0; i<vertices; i++){
            for(int j=0; j<vertices; j++){
                sums[i] += graph[j][i];
            }
        }
        for(int i=0; i<vertices; i++){
            int next = findZero(sums);
            if(next == -1){
                return sorted;
            }
            sorted.add(next);
            sums[next] = -1;
            for(int j=0; j<vertices; j++){
                sums[j] -= graph[next][j];
            }
        }
        return sorted;
    }

    

    public List<Integer> neighbors(int vertex) throws Exception {
        if (vertex > vertices || vertex < 0) {
            throw new Exception();
        }
        List<Integer> neighbor = new LinkedList<Integer>();
        for(int i=0; i<vertices; i++){
            if(graph[vertex][i] == 1){
                neighbor.add(i);
            }
        }
        return neighbor;
    }

}
package project;

/**
 * Created by Rachel on 4/1/2016.
 */
public class Tester {
    public static void main(String[] args){
        String fileName ="C:\\Users\\Rachel\\IdeaProjects\\EADS\\data\\network\\edge_idx_list.csv";
        String adjFileName ="C:\\Users\\Rachel\\IdeaProjects\\EADS\\data\\network\\edge_idx_matrix.csv";
        Dijkstra dijk = new Dijkstra(fileName,adjFileName);
        dijk.printContents();
        dijk.computeAllShortestPaths();
    }
}

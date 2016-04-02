package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Rachel on 4/1/2016.
 */
public class Dijkstra {

    private String roadFileName;
    private String adjFileName;
    private HashMap<String,HashMap<String,String>> neighbDistMap;
    private HashMap<String,ArrayList<String>> adjNeighbMap;

    public Dijkstra(String roadFileName, String adjacencyFileName){
        this.roadFileName = roadFileName;
        this.adjFileName = adjacencyFileName;
        loadRoads();
        loadAdjacency();
    }

    private void loadRoads(){
        BufferedReader reader = null;
        neighbDistMap = new HashMap<String,HashMap<String,String>>();
        HashMap<String,String> destDist = null;
        try{
            //Instantiate and prepare the reader
            reader = new BufferedReader(new FileReader(roadFileName));
            String str;
            try{
                while((str = reader.readLine()) != null){
                    String[] lineParts = str.split(",");

                    //We check if a particular node is already a key in the Neighbour-Distance map
                    if(!neighbDistMap.containsKey(lineParts[1])){
                        //If not, we add the neighbour to the outer Map
                        destDist = new HashMap<String,String>();
                    } else{
                        //If the node exists, then we append to the existing HashMap
                        destDist = neighbDistMap.get(lineParts[0]);
                    }
                    destDist.put(lineParts[1],lineParts[2]);
                    neighbDistMap.put(lineParts[0],destDist);
                }
            } finally{
                reader.close();
            }

        } catch(FileNotFoundException e){
            System.out.println("The file is not found!");
        } catch (IOException e){
            System.out.println("The bufferedReader met with a problem; Road Nodes");
        }

        System.out.println("Node-edgeweight load complete.");
    }

    private void loadAdjacency(){
        BufferedReader read = null;
        adjNeighbMap = new HashMap<String,ArrayList<String>>();
        Integer counter = 0;
        try{
            read = new BufferedReader(new FileReader(adjFileName));
            String str;
            try{
                while ((str = read.readLine())!=null){
                    String[] rowOfAdjacency = str.split(",");
                    ArrayList<String> adjacentNeighbours = new ArrayList<String>();
                    for(String nextNode : rowOfAdjacency){
                        if(!nextNode.equals("-1")){
                            adjacentNeighbours.add(nextNode);
                        }
                    }
                    String x = counter.toString();
                    adjNeighbMap.put(x,adjacentNeighbours);
                    counter++;
                }
            } finally {
                read.close();
            }
        } catch(FileNotFoundException e){
            System.out.println("The adjacency matrix file could not be found.");
        } catch(IOException e){
            System.out.println("Reader met with IO exception; Adjacency Matrix");
        }
    }

    public int[][] computeAllShortestPaths(){
        //String originNode = null;
        //String destNode = null;
        HashMap<String,ArrayList<String>> allPaths = new HashMap<String,ArrayList<String>>();
        HashMap<String,HashMap<String,String>> allODDistances = new HashMap<String,HashMap<String,String>>();
        Set<String> allOriginNodes = adjNeighbMap.keySet();


        allOriginNodes.parallelStream()
                .forEach(
                    originNode -> {
                        //ArrayList<String> allNeighbours = adjNeighbMap.get(originNode);
                        ArrayList<String> path = new ArrayList<String>();
                        //int nearestDist = 0;
                });
        return null;
    }

    //For testing
    public void printContents(){

        //Test Road Loads
        /*
        Set<String> x = neighbDistMap.keySet();
        for (String a : x){
            String z = (String) a;
            HashMap<String,String> testerHash = neighbDistMap.get(z);
            Set<String> b = testerHash.keySet();
            for(String c : b){
                String y = (String) c;
                System.out.println(z+","+c+","+testerHash.get(y));
            }
        }
        */

        //Test Adjacency Loads
        /*
        Set<String> x = adjNeighbMap.keySet();
        for(String key : x){
            System.out.print("Current key "+key+":");
            ArrayList<String> allMyNeighbours = adjNeighbMap.get(key);
            allMyNeighbours.parallelStream().forEach(item -> System.out.print(item+","));
            System.out.println();
        }
        */
    }
}

package ca.ubc.ece.cpen221.mp2.boggle;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.graph.AdjacencyMatrixGraph;
import ca.ubc.ece.cpen221.mp2.graph.Permeate;

import java.util.*;

public class BogglePlayer {

    public String[] dictionary;
    // Some empty methods provided to ensure that the
    // build succeeds. You should implement these methods
    // and the others that are required.

    public BogglePlayer(String[] dictionary) {
        this.dictionary = dictionary;
    }

    public Set<String> getAllValidWords(BoggleBoard board) {
        Set<String> validWords = new TreeSet<>();
        Graph bMatrix = new AdjacencyMatrixGraph();
        Map<String, Vertex> letters = new HashMap<>();

        Permeate.boggleGraph(board, bMatrix);

        for(Vertex v : bMatrix.getVertices()){
            letters.put(v.getLabel(), v);
        }

        for(String word : dictionary){
            //Check if all letters in dictionary exist in boggle board.
            if(containsAllLetters(letters, word)){

            }
        }

        return validWords;
    }

    public int getMaximumScore(BoggleBoard board){

        return 0;
    }
    public int scoreOf(String word){

        return 0;
    }

    private boolean containsAllLetters(Map<String, Vertex> letterList, String word){
        for(int index = 0; index < word.length(); index++){
            if(!letterList.keySet().contains(word.substring(index, index))){
                return false;
            }
        }
        return true;
    }
}


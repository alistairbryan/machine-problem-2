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
        Permeate.boggleGraph(board, bMatrix);
        List<Vertex> vertices = bMatrix.getVertices();
        List<String> letters = new ArrayList<>();
        boolean wordFound;

        for(Vertex v : vertices){
            letters.add(v.getLabel());
        }

        for(String word : dictionary){
            wordFound = true;
            //Check if all letters in dictionary exist in boggle board.
            if(containsAllLetters(letters, word)){
                //See if the 'letterNum'th letter has the 'letterNum + 1'th letter adjacent to it.
                for(int letterNum = 0; letterNum < word.length(); letterNum++){
                    //checks if bMatrix has the matrix adjacent to it
                    if(!bMatrix.getNeighbors(vertices.get(letters.indexOf(word.substring(letterNum,letterNum)))).contains(vertices.get(letters.indexOf(word.substring(letterNum + 1,letterNum + 1))))){
                        wordFound = false;
                        break;
                    }
                }
                if(wordFound){
                    validWords.add(word);
                }
            }
        }

        return validWords;
    }

    public int getMaximumScore(BoggleBoard board){
        Set<String> words = getAllValidWords(board);
        int maxLength = 0;
        String bestWord = "";

        for(String word : words){
            if(word.length() > maxLength){
                maxLength = word.length();
                bestWord = word;
            }
            if(maxLength > 7){
                return 11;
            }
        }
        return scoreOf(bestWord);
    }
    public int scoreOf(String word){
        int length = word.length();
        if(length < 3){
            return 0;
        }
        if (length > 2 && length < 5){
            return 1;
        }
        if(length == 5){
            return 2;
        }
        if(length == 6){
            return 3;
        }
        if(length == 7){
            return 5;
        }
        else{
            return 11;
        }
    }

    private boolean containsAllLetters(List<String> letters, String word){
        for(int index = 0; index < word.length(); index++){
            if(!letters.contains(word.substring(index, index))){
                return false;
            }
        }
        return true;
    }
}


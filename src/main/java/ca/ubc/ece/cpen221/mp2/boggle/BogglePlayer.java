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
        Graph bGraph = new AdjacencyMatrixGraph();
        Permeate.boggleGraph(board, bGraph);
        List<Vertex> vertices = bGraph.getVertices();
        List<String> letters = new ArrayList<>();
        boolean wordFound;

        for(Vertex v : vertices){
            letters.add(v.getLabel());
        }

        for(String word : dictionary){
            wordFound = true;

            if(containsAllLetters(letters, word)){ //Check if all letters in dictionary exist in boggle board.

                //See if the 'letterNum'th letter has the 'letterNum + 1'th letter adjacent to it.
                for(int letterNum = 0; letterNum < word.length()-2; letterNum++){
                    //checks if bGraph has the matrix adjacent to it
                    if(!bGraph.getNeighbors(vertices.get(letters.indexOf(word.substring(letterNum,letterNum+1)))).contains(vertices.get(letters.indexOf(word.substring(letterNum + 1,letterNum + 2))))){
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
        int maxScore = 0;
        for (String word : words) {
            maxScore += scoreOf(word);
        }
        return maxScore;
    }

    //Looks good
    public int scoreOf(String word){
        int length = word.length();
        if(length < 3){
            return 0;
        } else if (length < 5){
            return 1;
        } else if (length == 5){
            return 2;
        } else if (length == 6){
            return 3;
        } else if (length == 7){
            return 5;
        } else {
            return 11;
        }
    }

    private boolean containsAllLetters(List<String> letters, String word){
        for(int index = 0; index < word.length()-1; index++){
            System.out.println("Letter in word: " + word.substring(index,index+1));
            if(!letters.contains(word.substring(index, index+1))){
                return false;
            }
        }
        return true;
    }

}


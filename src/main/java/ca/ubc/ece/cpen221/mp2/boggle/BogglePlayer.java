package ca.ubc.ece.cpen221.mp2.boggle;

import ca.ubc.ece.cpen221.mp2.core.Graph;
import ca.ubc.ece.cpen221.mp2.core.Vertex;
import ca.ubc.ece.cpen221.mp2.graph.AdjacencyListGraph;
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
        Graph bGraph = new AdjacencyListGraph();
        Permeate.boggleGraph(board, bGraph);
        List<Vertex> vertices = bGraph.getVertices();
        List<String> letters = new ArrayList<>();
        boolean wordFound;

        for (Vertex v : vertices){
            letters.add(v.getLabel());
        }

        for (String word : dictionary){
            wordFound = true;
            if (containsAllLetters(letters, word)){ //Check if all letters in dictionary exist in boggle board.

                //See if the 'letterNum'th letter has the 'letterNum + 1'th letter adjacent to it.
                for (int letterNum = 0; letterNum < word.length()-1; letterNum++){
                    //checks if bGraph has the matrix adjacent to it
                    String thisLetter;
                    String nextLetter;

                    if (letterNum != word.length() - 2) {
                        thisLetter = word.substring(letterNum, letterNum + 1);
                    }else{
                        thisLetter = word.substring(letterNum);
                    }


                    if (!thisLetter.equalsIgnoreCase("Q")) {
                        nextLetter = word.substring(letterNum + 1, letterNum + 2);
                    }else {
                        nextLetter = word.substring(letterNum + 2, letterNum + 3);
                    }

                    if(thisLetter.equalsIgnoreCase("E")) {
                        System.out.println("thisLetter: " + thisLetter);
                        System.out.println("nextLetter: " + nextLetter);
                    }

                    int indexThisLetter = word.indexOf(thisLetter);
                    int indexNextLetter = word.indexOf(nextLetter);

                    while (indexThisLetter >= 0){
                        wordFound = true;

                        if (!bGraph.getNeighbors(vertices.get(indexThisLetter)).contains(vertices.get(indexNextLetter))) {
                            wordFound = false;
                        }

                        indexThisLetter = word.indexOf(thisLetter, indexThisLetter + 1);
                        if(thisLetter.equalsIgnoreCase("E")) {
                            System.out.println("indexThisLetter: " + indexThisLetter);
                        }
                    }
                    System.out.println("------");
                    if(!wordFound){
                        break;
                    }
                }
                if(wordFound) {
                    validWords.add(word);
                }
            }
        }

        return validWords;
    }


    /**
     * @param board, a BoggleBoard.
     *               Requires that the board is not null.
     * @return the highest score one can obtain from finding dictionary words on the given BoggleBoard.
     */
    public int getMaximumScore(BoggleBoard board){
        Set<String> words = getAllValidWords(board);
        int maxScore = 0;
        for (String word : words) {
            maxScore += scoreOf(word);
        }
        return maxScore;
    }

    /**
     * @param word, a word.
     *               Requires that the word is not null.
     * @return the score for which that word is eligible.
     */
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

    /**
     * @param letters, a list of letters.
     *               Requires that the list contains single letters or Qu. No entries may be null.
     * @param word, a word.
     *              Requires that the word contains no spaces or punctuation. Just uppercase letters from the English alphabet.
     * @return whether the word can be written using characters (with duplicates) from the letters array.
     */
    private boolean containsAllLetters(List<String> letters, String word){
        for(int index = 0; index < word.length()-1; index++){
            if(!letters.contains(word.substring(index, index+1))){
                return false;
            }
        }
        return true;
    }
}


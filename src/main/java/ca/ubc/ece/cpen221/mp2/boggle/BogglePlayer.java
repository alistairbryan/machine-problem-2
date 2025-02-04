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

    /**
     * @param board, a BoggleBoard
     *              Requires board is not null.
     * @return a Set containing all words in dictionary that can be created from board's vertices and by following the rules of Boggle.
     */
    public Set<String> getAllValidWords(BoggleBoard board) {
        Set<String> validWords = new TreeSet<>();
        Graph bGraph = new AdjacencyMatrixGraph();
        Permeate.boggleGraph(board, bGraph);

        List<String> letters = new ArrayList<>();

        for(Vertex v : bGraph.getVertices()){
            letters.add(v.getLabel());
        }
        if (letters.contains("Q")) {
            letters.add("U");
        }

        for(String word : dictionary){
            if(word.length() > 2 && containsAllLettersNotQI(letters, word)){ //Check if all letters in dictionary exist in boggle board.
                for(Vertex startV : bGraph.getVertices()) {
                    if (wordInBoard(word, bGraph, startV, new HashSet<Vertex>())) {
                        validWords.add(word);
                    }
                }
            }
        }
        return validWords;
    }


    /**
     * @param word, word that you are trying to find on board.
     *              Requires that word is not null.
     * @param bGraph, graph that represents the boggle board.
     *                Require that bGraph is not null.
     * @param currentV, current Vertex from which you are adjacent Vertices
     * @param traversed, Set of Vertices you have already been to.
     * @return the highest score one can obtain from finding dictionary words on the given BoggleBoard.
     */
    private boolean wordInBoard(String word, Graph bGraph, Vertex currentV, Set<Vertex> traversed) {
        if (word.length() == 1 || word.equals("QU")) { //end of line
            if (word.equals(currentV.getLabel())) {
                return true;
            }
            if (word.equals("QU") && currentV.getLabel().equals("Q")) {
                return true;
            }

        } else { // recursive call

            Set<Vertex> beenTo = new HashSet<Vertex>();
            beenTo.add(currentV);
            for (Vertex v : traversed) {
                beenTo.add(v);
            }

            if (word.substring(0,1).equals(currentV.getLabel())) {
                int cutLength = word.charAt(0) == 'Q' && word.length() > 2 ? 2 : 1;
                for (Vertex neighbor : bGraph.getNeighbors(currentV)) {
                    if (!beenTo.contains(neighbor) && wordInBoard(word.substring(cutLength), bGraph, neighbor, beenTo)) {
                        return true;
                    }
                }
            }
        }

        return false;
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
     *              Requires that the word is not null.
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
    private boolean containsAllLettersNotQI(List<String> letters, String word){
        for(int index = 0; index < word.length()-1; index++){
            if(!letters.contains(word.substring(index, index+1))){
                return false;
            }
        }

        for(int i = 0; i < word.length()-1; i++) {
            if (word.charAt(i) == 'Q') {
                if (word.charAt(i+1) != 'U') {
                    return false;
                }
            }
        }

        return true;
    }
}


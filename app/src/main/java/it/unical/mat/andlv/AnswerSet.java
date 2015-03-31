package it.unical.mat.andlv;

import java.util.HashMap;

/**
 * Created by Dario Campisano on 28/03/2015.
 * <p>AnswerSet class rapresents an Answer Set and contains get and set methods to handle it.</p>
 * @see java.util.HashMap
 */
public class AnswerSet {
    private String answerSet;//String representing the Answer Set
    private HashMap<Integer,Integer> weightMap;//Answer sets weights

    /**
     * Constructor intialize an AnswerSet object with a String containing the Answer Set
     * and an {@link java.util.HashMap} containing Answer Set weight
     * @param outputString String representing an Answer Set
     */
    public AnswerSet(String outputString){
        this.answerSet = outputString;
        this.weightMap = new HashMap<Integer, Integer>();
    }

    /**
     * Get function for the Answer Set in String format
     * @return answerSet Answer Set in String format
     */
    public String getAnswerSet(){
        return this.answerSet;
    }

    /*public HashMap<Integer, Integer> getWeightMap() {
        return this.weightMap;
    }*/


    /*public boolean isWeightMapEmpty(){
        return this.isMapEmpty;
    }*/
}

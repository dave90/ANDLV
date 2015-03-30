package it.unical.mat.andlv;

import java.util.HashMap;

/**
 * Created by Dario Campisano on 28/03/2015.
 * <p>AnswerSet class rapresents an ASP Answer Set and contains utility functions to handle it.</p>
 */
public class AnswerSet {
    private String answerSet;//String representing the AnswerSet
    private HashMap<Integer,Integer> weightMap;//Weights of the Answer Set

    /**
     * Constructor intialize an AnswerSet object with a String containing the Answer Set
     * @param outputString a String representing an Answer Set
     */
    public AnswerSet(String outputString){
        this.answerSet = outputString;
        this.weightMap = new HashMap<Integer, Integer>();
    }

    /**
     * Simple get function for the AnswerSet in String format
     * @return answerSet
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

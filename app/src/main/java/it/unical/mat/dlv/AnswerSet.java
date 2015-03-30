package it.unical.mat.dlv;

import java.util.HashMap;

/**
 * Created by Dario Campisano on 28/03/2015.
 */
public class AnswerSet {
    private String answerSet;
    private boolean isMapEmpty;
    private HashMap<Integer,Integer> weightMap;

    public AnswerSet(String outputString, HashMap<Integer, Integer> weightMap){
        this.answerSet = outputString;
        this.weightMap = weightMap;
        this.isMapEmpty = weightMap.isEmpty();
    }
    public AnswerSet(String outputString){
        this.answerSet = outputString;
        this.weightMap = new HashMap<Integer, Integer>();
        this.isMapEmpty = weightMap.isEmpty();
    }

    public String getAnswerSet(){
        return this.answerSet;
    }

    public HashMap<Integer, Integer> getWeightMap() {
        return this.weightMap;
    }

    //Utility Func.
    public boolean isWeightMapEmpty(){
        return this.isMapEmpty;
    }
}

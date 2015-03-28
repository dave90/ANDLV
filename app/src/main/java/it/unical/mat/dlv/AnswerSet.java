package it.unical.mat.dlv;

import java.util.HashMap;

/**
 * Created by Dario Campisano on 28/03/2015.
 */
public class AnswerSet {
    private String answerSet;
    private HashMap<Integer,Integer> weightMap;

    public AnswerSet(){

    }

    public AnswerSet(String outputString){
        this.answerSet = outputString;
    }

    public String getAnswerSet(){
        return this.answerSet;
    }

    public void setAnswerSet(String outputString){
        this.answerSet = outputString;
    }

}

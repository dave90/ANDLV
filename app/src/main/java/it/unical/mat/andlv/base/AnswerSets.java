package it.unical.mat.andlv.base;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Dario Campisano on 01/04/2015.
 * <p>AnswerSets class store and parse Answer Sets</p>
 */
public abstract class AnswerSets {
    private String answerSets; //contains String Answer Sets

    private ArrayList<AnswerSet> answerSetsList; //ArrayList of Answer Sets

    /**
     * Constructor initialize AnswerSets object with a String tha contains ALL Answer Set generated.
     * @param answerSets
     */
    public AnswerSets(String answerSets){
        this.answerSets = answerSets;
    }

    /**
     * Parse outputToParse string and generate AnswerSet objects
     * @param outputToParse
     */
    abstract protected void parse(String outputToParse);//Create AnswerSet objects from outputToParse String. Then add objects to answerSetList

    public ArrayList<AnswerSet> getAnswerSetsList() {

        return answerSetsList;
    }
}

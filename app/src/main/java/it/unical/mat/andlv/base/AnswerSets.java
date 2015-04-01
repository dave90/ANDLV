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
     * Default constructor
     */
    public AnswerSets(){
        this.answerSetsList = new ArrayList<AnswerSet>();
    }

    /**
     * Constructor initialize AnswerSets object with a String that contains ALL Answer Set generated.
     * @param answerSets
     */
    public AnswerSets(String answerSets){
        this.answerSetsList = new ArrayList<AnswerSet>();
        this.answerSets = answerSets;
    }

    /**
     * Parse outputToParse String and generate AnswerSet objects
     * @param outputToParse
     */
    abstract protected void parse(String outputToParse);//Create AnswerSet objects from outputToParse String. Then add objects to answerSetList

    /**
     * Get method for {@link java.util.ArrayList} of generated {@link it.unical.mat.andlv.base.AnswerSet}
     * @return answerSetList
     */
    public ArrayList<AnswerSet> getAnswerSetsList() {
        return answerSetsList;
    }

    /**
     * Get method for Answer Sets String to parse
     * @return answerSets
     */
    public String getAnswerSets() {
        return answerSets;
    }

    /**
     * Set method for Answer Sets String to parse
     * @param answerSets
     */
    public void setAnswerSets(String answerSets) {
        this.answerSets = answerSets;
    }
}

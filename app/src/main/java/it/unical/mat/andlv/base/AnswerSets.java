package it.unical.mat.andlv.base;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Dario Campisano on 01/04/2015.
 */
public abstract class AnswerSets {
    private ArrayList<AnswerSet> answerSetsList; //ArrayList of Answer Sets

    abstract protected void parse(String outputToParse);//Create AnswerSet objects from outputToParse String. Then add objects to answerSetList
}

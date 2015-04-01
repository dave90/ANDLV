package it.unical.mat.andlv.dlv;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unical.mat.andlv.base.AnswerSet;
import it.unical.mat.andlv.base.AnswerSets;

/**
 *
 */
public class DLVAnswerSets extends AnswerSets{

    /**
     * Constructor initialize AnswerSets object with a String that contains ALL Answer Set generated from ASPService.
     *
     * @param answerSets
     */
    public DLVAnswerSets(String answerSets) {
        super(answerSets);
    }

    @Override
    protected void parse() {
        Pattern pattern = Pattern.compile("[{](.*)[}]");
        Matcher matcher = pattern.matcher(answerSetsString);
        while (matcher.find())
            answerSetsList.add(new AnswerSet(matcher.group()));

    }
}

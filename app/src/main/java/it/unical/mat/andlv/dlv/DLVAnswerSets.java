package it.unical.mat.andlv.dlv;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.unical.mat.andlv.base.AnswerSet;
import it.unical.mat.andlv.base.AnswerSets;
import it.unical.mat.andlv.utils.TimeTracker;

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
        TimeTracker.logTime("Beginning of DLVAnswerSets::parse function");
        Pattern pattern = Pattern.compile("\\{(.*)\\}");
        Matcher matcher = pattern.matcher(answerSetsString);
        while (matcher.find()) {
            String answerSet=matcher.group();
            Pattern patternAnswerSet = Pattern.compile("(-?[a-z][A-Za-z0-9_]*(\\(.*?\\))?)(, |\\})");
            Matcher matcherAnswerSet = patternAnswerSet.matcher(answerSet);
            List<String> answerSetList=new ArrayList<>();
            while (matcherAnswerSet.find()) {
                answerSetList.add(matcherAnswerSet.group(1));
            }
            answerSetsList.add(new AnswerSet(answerSetList));
        }
        TimeTracker.logTime("End of DLVAnswerSets::parse function");
    }
}

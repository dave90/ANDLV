package it.unical.mat.dlv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DlvAnswerSetMatcher extends Thread{
	private ParseResult dlvparser;//TODELETE
	private CyclicBarrier cyclicBarrier;
	private ArrayList<String> answerSets;
	private String outputToParse;

	//TODELETE
	public DlvAnswerSetMatcher(ParseResult dlvparser,
			CyclicBarrier cyclicBarrier) {
		this.dlvparser = dlvparser;
		this.cyclicBarrier = cyclicBarrier;
	}

	public DlvAnswerSetMatcher(String outPutToParse,
			CyclicBarrier cyclicBarrier) {
		this.outputToParse = outPutToParse;
		this.cyclicBarrier = cyclicBarrier;
		this.answerSets = new ArrayList<String>();
	}
	
	public void run() {
			Pattern pattern = Pattern.compile("[{](.)*[}]");
			Matcher matcher = pattern.matcher(outputToParse);
			
			while (matcher.find()) {
				answerSets.add(matcher.group());
			}

		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getAnswerSets(){
		return answerSets;
	}
	
	public String fileToString(File file) {
		StringBuilder text = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {

				text.append(line + "\n");
			}
			br.close();
		} catch (IOException e) {

		}
		return text.toString();
	}
}

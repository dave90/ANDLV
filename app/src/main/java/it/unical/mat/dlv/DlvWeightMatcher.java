package it.unical.mat.dlv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DlvWeightMatcher extends Thread{
	private CyclicBarrier cyclicBarrier;
	private String outputToParse;
	private ArrayList<HashMap<Integer,Integer>> weightMaps;

	public DlvWeightMatcher(String outputToParse,
			CyclicBarrier cyclicBarrier) {
		this.outputToParse = outputToParse;
		this.cyclicBarrier = cyclicBarrier;
		this.weightMaps = new ArrayList<HashMap<Integer,Integer>>();
	}
	
	public void run() {
		
			Pattern pattern = Pattern.compile("[<][\\[](\\d).(\\d)[\\]][>]");
			Matcher matcher = pattern.matcher(outputToParse);
			
			while (matcher.find()) {	
				//dlvparser.addWeight(matcher.group());
				int key = Integer.valueOf(matcher.group(1));
				int value = Integer.valueOf(matcher.group(2));
				
				HashMap<Integer, Integer> weightMap = new HashMap<Integer, Integer>();
				weightMap.put(key, value);
				
				weightMaps.add(weightMap);
			}

		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<HashMap<Integer, Integer>> getWeightMaps(){
		return weightMaps; 
	}
}

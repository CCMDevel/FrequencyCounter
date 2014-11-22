package com.plasticwrapps.myo;

import java.util.LinkedList;

public class FrequencyCounter {
	private long t0;
    private LinkedList<Double> rollingQueue;
    private double rollingTotal;
    
    // Instantiate the FrequencyCounter to use it.
    public FrequencyCounter(){
    	this.t0 = System.nanoTime();
    	this.rollingQueue = new LinkedList<Double>();
    	this.rollingTotal = 0;
    }
    
    // Self explanatory (Hz)
    public double getFrequency(){
    	if (rollingQueue.size() == 0){
    		return 0;
    	} 
    	
    	return 1 / (rollingTotal / rollingQueue.size());
    }
    
    // This method gets called on your FrequencyCounter object when an occurance
    // of the event being counted has been called. 
    public void logOccurance(){
    	if (rollingTotal < 1.0) {
    		long t1 = System.nanoTime();
    		double dMillis =  ((t1 - t0) / 1000000000.0);
    		t0 = t1;
    		
    		rollingTotal += dMillis;
    		rollingQueue.add(dMillis);
    	} else {
    		long t1 = System.nanoTime();
    		double dMillis =  ((t1 - t0) / 1000000000.0);
    		t0 = t1;
    		
    		double lastDMillis = rollingQueue.pop();
    		rollingTotal -= lastDMillis;
    		rollingTotal += dMillis;
    		rollingQueue.add(dMillis);
    	}
    }
}

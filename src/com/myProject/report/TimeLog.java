package com.myProject.report;

/**
 * @author pnlmm075
 *
 */
public class TimeLog {
	private long startTime;
	
	public TimeLog(){
		startTime = System.currentTimeMillis();
	}
	
	public double getElapsedTime(){
		long endTime = System.currentTimeMillis();
		return(double)(endTime - startTime) / (1000);
	}
}

package com.talent.base.task;

import org.quartz.JobExecutionContext;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class BatchJobTask extends QuartzJobBean{
	
	private static JobLauncher jobLauncher = null;
	
	private static Job onStartJob = null;

	private static Job oneMinuteJob = null;
	
	public static void setJobLauncher(JobLauncher jobLauncher) {
		BatchJobTask.jobLauncher = jobLauncher;
	}

	public static void setOnStartJob(Job onStartJob) {
		BatchJobTask.onStartJob = onStartJob;
	}

	public static void setOneMinuteJob(Job oneMinuteJob) {
		BatchJobTask.oneMinuteJob = oneMinuteJob;
	}

	public void onStart(){
		try {
			jobLauncher.run(onStartJob,new JobParameters());
		}catch(Exception e) {
			
		}
	}
	
	public void everyMinute(){
		try {
			jobLauncher.run(oneMinuteJob,new JobParameters());
		}catch(Exception e) {
			
		}
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws org.quartz.JobExecutionException {
		
	}
}

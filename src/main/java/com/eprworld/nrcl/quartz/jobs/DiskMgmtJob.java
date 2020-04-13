package com.eprworld.nrcl.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;


public class DiskMgmtJob extends QuartzJobBean {
    
	Logger Log = LoggerFactory.getLogger(getClass());

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		try {
			Log.info("Starting DiskMgmt Job, trigger: {}", context.getTrigger().getDescription());
			Thread.sleep(2000);
			Log.info("Finished DiskMgmt Job");
		} catch (InterruptedException e) {
			Log.error("Disk Mgmt job interrupted", e);
		}
	}

}

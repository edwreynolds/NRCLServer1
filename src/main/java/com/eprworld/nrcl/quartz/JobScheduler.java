package com.eprworld.nrcl.quartz;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.eprworld.nrcl.quartz.jobs.DiskMgmtJob;

@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
public class JobScheduler {
	Logger Log = LoggerFactory.getLogger(getClass());

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void init() {
		Log.info("Job Scheduler constructed...");
	}

	@Bean
	public SpringBeanJobFactory springBeanJobFactory() {
		SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
		Log.info("Configuring job factory");

		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean scheduler(org.quartz.Trigger[] triggers, JobDetail job) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setConfigLocation(new ClassPathResource("quartz.properties"));

		Log.info("Setting the scheduler up");
		schedulerFactory.setJobFactory(springBeanJobFactory());
		schedulerFactory.setJobDetails(job);
		schedulerFactory.setTriggers(triggers);

		// Comment the following line to use the default Quartz job store.
		// DataSource quartzDataSource
		// schedulerFactory.setDataSource(quartzDataSource);

		return schedulerFactory;
	}

	@Bean
	public SimpleTriggerFactoryBean trigger(JobDetail job) {
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(job);

		int frequencyInSec = 10;
		Log.info("Configuring trigger to fire every {} seconds", frequencyInSec);

		trigger.setRepeatInterval(frequencyInSec * 1000);
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		trigger.setName("Disk Mgmt Trigger");
		return trigger;
	}

	@Bean
	public JobDetailFactoryBean jobDetail() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(DiskMgmtJob.class);
		jobDetailFactory.setName("Disk Mgmt_Job_Detail");
		jobDetailFactory.setDescription("Invoke Disk Mgmt job service...");
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}

}

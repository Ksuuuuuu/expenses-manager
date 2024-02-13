//package app.configs.sheduler;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.quartz.CronTrigger;
//import org.quartz.Job;
//import org.quartz.JobDetail;
//import org.quartz.SimpleTrigger;
//import org.quartz.Trigger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
//import org.springframework.scheduling.quartz.JobDetailFactoryBean;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
//import org.springframework.scheduling.quartz.SpringBeanJobFactory;
//
//import javax.sql.DataSource;
//import java.util.Calendar;
//import java.util.Properties;
//
//@Configuration
//public class QuartzConfig {
//
//    private final ApplicationContext applicationContext;
//    private final DataSource dataSource;
//    public QuartzConfig(ApplicationContext applicationContext, DataSource dataSource) {
//        this.applicationContext = applicationContext;
//        this.dataSource = dataSource;
//    }
//
//    @Bean
//    public SpringBeanJobFactory springBeanJobFactory() {
//        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
//        jobFactory.setApplicationContext(applicationContext);
//        return jobFactory;
//    }
//
//
//    public SchedulerFactoryBean scheduler(Trigger trigger) {
//        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
//        Properties properties = new Properties();
//        properties.setProperty("org.quartz.scheduler.instanceName", "MyInstanceName");
//        properties.setProperty("org.quartz.scheduler.instanceId", "Instance1");
//        schedulerFactory.setOverwriteExistingJobs(true);
//        schedulerFactory.setAutoStartup(true);
//        schedulerFactory.setQuartzProperties(properties);
//        schedulerFactory.setDataSource(dataSource);
//        schedulerFactory.setJobFactory(springBeanJobFactory());
//        schedulerFactory.setWaitForJobsToCompleteOnShutdown(true);
//        schedulerFactory.setTriggers(trigger);
//        return schedulerFactory;
//    }
//
//    static CronTriggerFactoryBean createCronTrigger(JobDetail jobDetail, String cronExpression, String triggerName) {
//        //log.debug("createCronTrigger(jobDetail={}, cronExpression={}, triggerName={})", jobDetail.toString(), cronExpression, triggerName);
//        // To fix an issue with time-based cron jobs
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
//        factoryBean.setJobDetail(jobDetail);
//        factoryBean.setCronExpression(cronExpression);
//        factoryBean.setStartTime(calendar.getTime());
//        factoryBean.setStartDelay(0L);
//        factoryBean.setName(triggerName);
//        factoryBean.setMisfireInstruction(CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING);
//        return factoryBean;
//    }
//
//    static JobDetailFactoryBean createJobDetail(Class<? extends Job> jobClass, String jobName) {
//       // log.debug("createJobDetail(jobClass={}, jobName={})", jobClass.getName(), jobName);
//        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
//        factoryBean.setName(jobName);
//        factoryBean.setJobClass(jobClass);
//        factoryBean.setDurability(true);
//        return factoryBean;
//    }
//}

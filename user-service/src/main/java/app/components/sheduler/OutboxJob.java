//package app.components.sheduler;
//
//import org.quartz.DisallowConcurrentExecution;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.springframework.stereotype.Component;
//
//@Component
//@DisallowConcurrentExecution
//public class OutboxJob implements Job {
//
//    @Override
//    public void execute(JobExecutionContext context) {
//        System.out.println("Hello");
//       // log.info("Job ** {} ** starting @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
//       // memberService.memberStats();
//        //log.info("Job ** {} ** completed.  Next job scheduled @ {}", context.getJobDetail().getKey().getName(), context.getNextFireTime());
//    }
//}

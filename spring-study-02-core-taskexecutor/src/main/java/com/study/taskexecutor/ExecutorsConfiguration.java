package com.study.taskexecutor;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@ComponentScan
public class ExecutorsConfiguration {
    
    /**
     * TaskExecutorAdapter는 ExecutorSevice(java.util.concurrence.Executors)의 레퍼 역할
     */
    @Bean
    public TaskExecutorAdapter taskExecutorAdapter() {
        return new TaskExecutorAdapter(Executors.newCachedThreadPool());
    }
    
    /**
     * submit한 잡마다 새로운 쓰레드를 만들어 제공(풀링X)
     */
    @Bean
    public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
    
    /**
     *동기적으로 쓰레드를 띄워 잡을 실행 후 join()메서드에 바로 연결 사실상 스레딩은 완전히 건너뛰고
     *호출 스레드에서 run()메서드를 수동 실행한 것과 같음.
     */
    @Bean
    public SyncTaskExecutor syncTaskExecutor() {
        return new SyncTaskExecutor();
    }
    
    @Bean
    public ScheduledExecutorTask scheduledExecutorTask(Runnable runnable) {
        ScheduledExecutorTask scheduledExecutorTask = new ScheduledExecutorTask();
        scheduledExecutorTask.setPeriod(1000);
        scheduledExecutorTask.setRunnable(runnable);
        return scheduledExecutorTask;
    }
    
    
    /**
     * scheduledExecutorTask빈으로 정의된 잡을 트리거 한다.
     * scheduledExecutorTask인스턴스 목록을 지정해서 여러 잡을 동시 실행할 수도 있다.
     * scheduledExecutorTask인스턴스에는 작업 실행 간 공백시간을 인수로 넣을 수도 있다.
     */
    @Bean
    public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean(
            ScheduledExecutorTask scheduledExecutorTask) {
        ScheduledExecutorFactoryBean scheduledExecutorFactoryBean 
            = new ScheduledExecutorFactoryBean();
        scheduledExecutorFactoryBean.setScheduledExecutorTasks(scheduledExecutorTask);
        return scheduledExecutorFactoryBean;
    }
    
    /**
     * java.uitl.concurrent.ThreadPoolExecutor를 깁나으로 모든 기능이 완비된 쓰레드 풀이다.
     */
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(50);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setAllowCoreThreadTimeOut(true);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }
}

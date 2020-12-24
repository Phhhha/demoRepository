package com.csair.demo.job;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
@PropertySource("classpath:job.properties")
public class MyJobDemo {
    @Autowired
    private MyJob myJob;
    @Value("${job.namespace}")
    private String namespace;
    @Value("${job.corn}")
    private String corn;
    @PostConstruct
    public void init(){
        new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration()).schedule();
        System.out.println(myJob.getNum());
        System.out.println(myJob.getStr());
    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "my-job"));
        regCenter.init();
        return regCenter;
    }

    private  JobConfiguration createJobConfiguration() {
        // 创建作业配置
        JobConfiguration jobConfig = JobConfiguration.newBuilder(namespace, 3).cron(corn).build();
        return jobConfig;
    }
}


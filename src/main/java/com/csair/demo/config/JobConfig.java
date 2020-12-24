package com.csair.demo.config;

import com.csair.demo.job.MyJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class JobConfig {
    @Bean
    public MyJob getJob(){
        MyJob myJob = new MyJob();
        myJob.setNum(2);
        myJob.setStr("job字符串... ");
        return myJob;
    }




}

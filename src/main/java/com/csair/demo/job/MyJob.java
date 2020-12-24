package com.csair.demo.job;

import lombok.Data;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
@Data
public class MyJob implements SimpleJob {
    private String str;
    private int num;



    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                System.out.println("任务0执行...");
                break;
            case 1:
                // do something by sharding item 1
                System.out.println("任务1执行...");
                break;
            case 2:
                // do something by sharding item 2
                System.out.println("任务2执行...");
                break;
            // case n: ...
        }
    }
}

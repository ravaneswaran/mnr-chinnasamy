package com.mnrc.forex.commodity.cronjob;

import com.mnrc.core.cronjob.MNRCCronJob;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableAsync
public class MNRCGoldPriceCronJob implements MNRCCronJob {

    @Async
    @Scheduled(cron = "1 * * * * ?")
    public void job() {
        System.out.println("Hello World");
    }
}

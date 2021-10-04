package com.mnrc.forex.commodity.cronjob;

import com.google.gson.Gson;
import com.mnrc.core.cronjob.MNRCCronJob;
import com.mnrc.forex.commodity.entities.GoldPrice;
import com.mnrc.forex.commodity.services.GoldPriceService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Configuration
@EnableScheduling
@EnableAsync
public class MNRCGoldPriceCronJob implements MNRCCronJob {

    Logger logger = LoggerFactory.getLogger(MNRCGoldPriceCronJob.class);

    @Autowired
    private GoldPriceService goldPriceService;

    @Value("${goldpricez.inr.measure.all}")
    private String goldPriceInrMeasureAllApiUrl;

    @Value("${goldpricez.api.key}")
    private String apikey;

    @Async
    @Scheduled(cron = "1 * * * * ?")
    public void job() {

        this.logger.info(this.getDecoratedTimeStamp());

        Request request = new Request.Builder()
                .url(this.goldPriceInrMeasureAllApiUrl)
                .addHeader("X-API-KEY", this.apikey)
                .get()
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = null;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
           this.logger.error(e.getMessage(), e);
        }

        if(null != response){
            String jsonString = null;
            try {
                jsonString = response.body().string();
            } catch (IOException e) {
                this.logger.error(e.getMessage(), e);
            }

            if(null != jsonString){
                jsonString = jsonString.substring(1, jsonString.length() - 1);
                jsonString = jsonString.replaceAll("\\\\\"", "'");

                Gson gson = new Gson();
                GoldPrice goldPrice = gson.fromJson(jsonString, GoldPrice.class);

                Date now = new Date();
                goldPrice.setUUID(UUID.randomUUID().toString());
                goldPrice.setCreatedBy("MNRCGoldPriceCronJob");
                goldPrice.setModifiedBy("-");
                goldPrice.setCreatedDate(now);
                goldPrice.setModifiedDate(now);

                this.goldPriceService.save(goldPrice);
            } else {
                this.logger.error("unable to unmarshall the json string to a object....");
            }
        } else {
            this.logger.error("the response from gold price is found to be null...");
        }
    }

    private String getDecoratedTimeStamp(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String timeStamp = simpleDateFormat.format(new Date());
        return String.format("<------------------ FETCHING THE GOLD PRICE AT %s ------------------>", timeStamp);
    }
}

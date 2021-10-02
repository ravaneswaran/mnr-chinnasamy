package com.mnrc.forex.commodity;

import com.google.gson.Gson;
import com.mnrc.forex.commodity.entities.GoldPrice;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootConfiguration
public class GoldPriceTest {

    @Test
    public void testGoldPriceRequest() throws IOException {
        Request request = new Request.Builder()
                .url("http://goldpricez.com/api/rates/currency/inr/measure/all")
                .addHeader("X-API-KEY", "4f8b6dee787a3163b4402d9a277bc5924f8b6dee")
                .get()
                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();

        Assert.assertNotNull(response);
        String jsonString = response.body().string();
        jsonString = jsonString.substring(1, jsonString.length() - 1);
        jsonString = jsonString.replaceAll("\\\\\"", "'");
        System.out.println(jsonString);

        Gson gson = new Gson();
        GoldPrice goldPrice = gson.fromJson(jsonString, GoldPrice.class);
        Assert.assertNotNull(goldPrice);
        System.out.println("1 Gram of Gold in INR : = " + goldPrice.getGramInINR());
        System.out.println("1 Milligram of Gold in INR : = " + Float.valueOf(goldPrice.getGramInINR()).floatValue() / 1000);
    }
}

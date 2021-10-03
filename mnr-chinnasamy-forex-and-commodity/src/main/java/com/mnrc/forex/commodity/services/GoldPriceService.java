package com.mnrc.forex.commodity.services;

import com.mnrc.forex.commodity.entities.GoldPrice;

public interface GoldPriceService {

    public GoldPrice save(GoldPrice goldPrice);

    public GoldPrice getGoldPrice(String uuid);
}

package com.mnrc.forex.commodity.services.impl;

import com.mnrc.forex.commodity.entities.GoldPrice;
import com.mnrc.forex.commodity.repositories.GoldPriceRepository;
import com.mnrc.forex.commodity.services.GoldPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoldPriceServiceImpl implements GoldPriceService {

    @Autowired
    private GoldPriceRepository goldPriceRepository;

    @Override
    public GoldPrice save(GoldPrice goldPrice) {
        return this.goldPriceRepository.save(goldPrice);
    }

    @Override
    public GoldPrice getGoldPrice(String uuid) {
        return this.goldPriceRepository.findById(uuid).get();
    }
}

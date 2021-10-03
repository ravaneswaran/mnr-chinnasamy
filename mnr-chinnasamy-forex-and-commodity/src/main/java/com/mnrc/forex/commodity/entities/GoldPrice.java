package com.mnrc.forex.commodity.entities;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "gold_price")
public class GoldPrice {

    @Id
    @Column(name = "uuid")
    private String UUID;

    @SerializedName(value = "ounce_price_usd")
    @Column(name = "ounce_price_usd")
    private String ouncePriceUSD;

    @SerializedName(value = "gmt_ounce_price_usd_updated")
    @Column(name = "gmt_ounce_price_usd_updated")
    private String gmtOuncePriceUSDUpdated;

    @SerializedName(value = "ounce_price_ask")
    @Column(name = "ounce_price_ask")
    private String ouncePriceAsk;

    @SerializedName(value = "ounce_price_bid")
    @Column(name = "ounce_price_bid")
    private String ouncePriceBid;

    @SerializedName(value = "ounce_price_usd_today_low")
    @Column(name = "ounce_price_usd_today_low")
    private String ouncePriceUSDTodayLow;

    @SerializedName(value = "ounce_price_usd_today_high")
    @Column(name = "ounce_price_usd_today_high")
    private String ouncePriceUSDTodayHigh;

    @SerializedName(value = "usd_to_inr")
    @Column(name = "usd_to_inr")
    private String usdToInr;

    @SerializedName(value = "gmt_inr_updated")
    @Column(name = "gmt_inr_updated")
    private String gmtInrUpdated;

    @SerializedName(value = "ounce_in_inr")
    @Column(name = "ounce_in_inr")
    private String ounceInINR;

    @SerializedName(value = "gram_to_ounce_formula")
    @Column(name = "gram_to_ounce_formula")
    private String gramToOunceFormula;

    @SerializedName(value = "gram_in_usd")
    @Column(name = "gram_in_usd")
    private String gramInUSD;

    @SerializedName(value = "gram_in_inr")
    @Column(name = "gram_in_inr")
    private String gramInINR;

    @SerializedName(value = "kg_to_ounce_formula")
    @Column(name = "kg_to_ounce_formula")
    private String  kgToOunceFormula;

    @SerializedName(value = "kg_in_usd")
    @Column(name = "kg_in_usd")
    private String kgInUSD;

    @SerializedName(value = "kg_in_inr")
    @Column(name = "kg_in_inr")
    private String kgInINR;

    @SerializedName(value = "grain_to_ounce_formula")
    @Column(name = "grain_to_ounce_formula")
    private String grainToOunceFormula;

    @SerializedName(value = "grain_in_usd")
    @Column(name = "grain_in_usd")
    private String grainInUSD;

    @SerializedName(value = "grain_in_inr")
    @Column(name = "grain_in_inr")
    private String grainInINR;

    @SerializedName(value = "tael-hongkong_to_ounce_formula")
    @Column(name = "tael_hongkong_to_ounce_formula")
    private String taelHongKongToOunceFormula;

    @SerializedName(value = "tael-hongkong_in_usd")
    @Column(name = "tael_hongkong_in_usd")
    private String taelHongKongInUSD;

    @SerializedName(value = "tael-hongkong_in_inr")
    @Column(name = "tael_hongkong_in_inr")
    private String taelHongKongInINR;

    @SerializedName(value = "tael-japan_to_ounce_formula")
    @Column(name = "tael_japan_to_ounce_formula")
    private String taelJapanToOunceFormula;

    @SerializedName(value = "tael-japan_in_usd")
    @Column(name = "tael_japan_in_usd")
    private String taelJapanInUSD;

    @SerializedName(value = "tael-japan_in_inr")
    @Column(name = "tael_japan_in_inr")
    private String taelJapanInINR;

    @SerializedName(value = "tola-india_to_ounce_formula")
    @Column(name = "tola_india_to_ounce_formula")
    private String tolaIndiaToOunceFormula;

    @SerializedName(value = "tola-india_in_usd")
    @Column(name = "tola_india_in_usd")
    private String tolaIndiaInUSD;

    @SerializedName(value = "tola-india_in_inr")
    @Column(name = "tola_india_in_inr")
    private String tolaIndiaInINR;

    @SerializedName(value = "tola-pakistan_to_ounce_formula")
    @Column(name = "tola_pakistan_to_ounce_formula")
    private String tolaPakistanToOunceFormula;

    @SerializedName(value = "tola-pakistan_in_usd")
    @Column(name = "tola_pakistan_in_usd")
    private String tolaPakistanInUSD;

    @SerializedName(value = "tola-pakistan_in_inr")
    @Column(name = "tola_pakistan_in_inr")
    private String tolaPakistanInINR;

    @SerializedName(value = "masha_to_ounce_formula")
    @Column(name = "masha_to_ounce_formula")
    private String mashaToOunceFormula;

    @SerializedName(value = "masha_in_usd")
    @Column(name = "masha_in_usd")
    private String mashaInUSD;

    @SerializedName(value = "masha_in_inr")
    @Column(name = "masha_in_inr")
    private String mashaInINR;

    @SerializedName(value = "ratti_to_ounce_formula")
    @Column(name = "ratti_to_ounce_formula")
    private String rattiToOunceFormula;

    @SerializedName(value = "ratti_in_usd")
    @Column(name = "ratti_in_usd")
    private String rattiInUSD;

    @SerializedName(value = "ratti_in_inr")
    @Column(name = "ratti_in_inr")
    private String rattiInINR;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getOuncePriceUSD() {
        return ouncePriceUSD;
    }

    public void setOuncePriceUSD(String ouncePriceUSD) {
        this.ouncePriceUSD = ouncePriceUSD;
    }

    public String getGmtOuncePriceUSDUpdated() {
        return gmtOuncePriceUSDUpdated;
    }

    public void setGmtOuncePriceUSDUpdated(String gmtOuncePriceUSDUpdated) {
        this.gmtOuncePriceUSDUpdated = gmtOuncePriceUSDUpdated;
    }

    public String getOuncePriceAsk() {
        return ouncePriceAsk;
    }

    public void setOuncePriceAsk(String ouncePriceAsk) {
        this.ouncePriceAsk = ouncePriceAsk;
    }

    public String getOuncePriceBid() {
        return ouncePriceBid;
    }

    public void setOuncePriceBid(String ouncePriceBid) {
        this.ouncePriceBid = ouncePriceBid;
    }

    public String getOuncePriceUSDTodayLow() {
        return ouncePriceUSDTodayLow;
    }

    public void setOuncePriceUSDTodayLow(String ouncePriceUSDTodayLow) {
        this.ouncePriceUSDTodayLow = ouncePriceUSDTodayLow;
    }

    public String getOuncePriceUSDTodayHigh() {
        return ouncePriceUSDTodayHigh;
    }

    public void setOuncePriceUSDTodayHigh(String ouncePriceUSDTodayHigh) {
        this.ouncePriceUSDTodayHigh = ouncePriceUSDTodayHigh;
    }

    public String getUsdToInr() {
        return usdToInr;
    }

    public void setUsdToInr(String usdToInr) {
        this.usdToInr = usdToInr;
    }

    public String getGmtInrUpdated() {
        return gmtInrUpdated;
    }

    public void setGmtInrUpdated(String gmtInrUpdated) {
        this.gmtInrUpdated = gmtInrUpdated;
    }

    public String getOunceInINR() {
        return ounceInINR;
    }

    public void setOunceInINR(String ounceInINR) {
        this.ounceInINR = ounceInINR;
    }

    public String getGramToOunceFormula() {
        return gramToOunceFormula;
    }

    public void setGramToOunceFormula(String gramToOunceFormula) {
        this.gramToOunceFormula = gramToOunceFormula;
    }

    public String getGramInUSD() {
        return gramInUSD;
    }

    public void setGramInUSD(String gramInUSD) {
        this.gramInUSD = gramInUSD;
    }

    public String getGramInINR() {
        return gramInINR;
    }

    public void setGramInINR(String gramInINR) {
        this.gramInINR = gramInINR;
    }

    public String getKgToOunceFormula() {
        return kgToOunceFormula;
    }

    public void setKgToOunceFormula(String kgToOunceFormula) {
        this.kgToOunceFormula = kgToOunceFormula;
    }

    public String getKgInUSD() {
        return kgInUSD;
    }

    public void setKgInUSD(String kgInUSD) {
        this.kgInUSD = kgInUSD;
    }

    public String getKgInINR() {
        return kgInINR;
    }

    public void setKgInINR(String kgInINR) {
        this.kgInINR = kgInINR;
    }

    public String getGrainToOunceFormula() {
        return grainToOunceFormula;
    }

    public void setGrainToOunceFormula(String grainToOunceFormula) {
        this.grainToOunceFormula = grainToOunceFormula;
    }

    public String getGrainInUSD() {
        return grainInUSD;
    }

    public void setGrainInUSD(String grainInUSD) {
        this.grainInUSD = grainInUSD;
    }

    public String getGrainInINR() {
        return grainInINR;
    }

    public void setGrainInINR(String grainInINR) {
        this.grainInINR = grainInINR;
    }

    public String getTaelHongKongToOunceFormula() {
        return taelHongKongToOunceFormula;
    }

    public void setTaelHongKongToOunceFormula(String taelHongKongToOunceFormula) {
        this.taelHongKongToOunceFormula = taelHongKongToOunceFormula;
    }

    public String getTaelHongKongInUSD() {
        return taelHongKongInUSD;
    }

    public void setTaelHongKongInUSD(String taelHongKongInUSD) {
        this.taelHongKongInUSD = taelHongKongInUSD;
    }

    public String getTaelHongKongInINR() {
        return taelHongKongInINR;
    }

    public void setTaelHongKongInINR(String taelHongKongInINR) {
        this.taelHongKongInINR = taelHongKongInINR;
    }

    public String getTaelJapanToOunceFormula() {
        return taelJapanToOunceFormula;
    }

    public void setTaelJapanToOunceFormula(String taelJapanToOunceFormula) {
        this.taelJapanToOunceFormula = taelJapanToOunceFormula;
    }

    public String getTaelJapanInUSD() {
        return taelJapanInUSD;
    }

    public void setTaelJapanInUSD(String taelJapanInUSD) {
        this.taelJapanInUSD = taelJapanInUSD;
    }

    public String getTaelJapanInINR() {
        return taelJapanInINR;
    }

    public void setTaelJapanInINR(String taelJapanInINR) {
        this.taelJapanInINR = taelJapanInINR;
    }

    public String getTolaIndiaToOunceFormula() {
        return tolaIndiaToOunceFormula;
    }

    public void setTolaIndiaToOunceFormula(String tolaIndiaToOunceFormula) {
        this.tolaIndiaToOunceFormula = tolaIndiaToOunceFormula;
    }

    public String getTolaIndiaInUSD() {
        return tolaIndiaInUSD;
    }

    public void setTolaIndiaInUSD(String tolaIndiaInUSD) {
        this.tolaIndiaInUSD = tolaIndiaInUSD;
    }

    public String getTolaIndiaInINR() {
        return tolaIndiaInINR;
    }

    public void setTolaIndiaInINR(String tolaIndiaInINR) {
        this.tolaIndiaInINR = tolaIndiaInINR;
    }

    public String getTolaPakistanToOunceFormula() {
        return tolaPakistanToOunceFormula;
    }

    public void setTolaPakistanToOunceFormula(String tolaPakistanToOunceFormula) {
        this.tolaPakistanToOunceFormula = tolaPakistanToOunceFormula;
    }

    public String getTolaPakistanInUSD() {
        return tolaPakistanInUSD;
    }

    public void setTolaPakistanInUSD(String tolaPakistanInUSD) {
        this.tolaPakistanInUSD = tolaPakistanInUSD;
    }

    public String getTolaPakistanInINR() {
        return tolaPakistanInINR;
    }

    public void setTolaPakistanInINR(String tolaPakistanInINR) {
        this.tolaPakistanInINR = tolaPakistanInINR;
    }

    public String getMashaToOunceFormula() {
        return mashaToOunceFormula;
    }

    public void setMashaToOunceFormula(String mashaToOunceFormula) {
        this.mashaToOunceFormula = mashaToOunceFormula;
    }

    public String getMashaInUSD() {
        return mashaInUSD;
    }

    public void setMashaInUSD(String mashaInUSD) {
        this.mashaInUSD = mashaInUSD;
    }

    public String getMashaInINR() {
        return mashaInINR;
    }

    public void setMashaInINR(String mashaInINR) {
        this.mashaInINR = mashaInINR;
    }

    public String getRattiToOunceFormula() {
        return rattiToOunceFormula;
    }

    public void setRattiToOunceFormula(String rattiToOunceFormula) {
        this.rattiToOunceFormula = rattiToOunceFormula;
    }

    public String getRattiInUSD() {
        return rattiInUSD;
    }

    public void setRattiInUSD(String rattiInUSD) {
        this.rattiInUSD = rattiInUSD;
    }

    public String getRattiInINR() {
        return rattiInINR;
    }

    public void setRattiInINR(String rattiInINR) {
        this.rattiInINR = rattiInINR;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}

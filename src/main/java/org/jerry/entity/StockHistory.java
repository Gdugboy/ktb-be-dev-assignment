package org.jerry.entity;


import jakarta.persistence.*;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "stocks_history")
@IdClass(StockHistoryId.class) // 🔹 복합 키 사용
public class StockHistory {

    @Id
    @Column(name = "company_code", nullable = false)
    private String companyCode;

    @Id
    @Column(name = "trade_date", nullable = false)
    private LocalDate tradeDate;

    @Column(name = "open_price", nullable = false)
    private Double openPrice;

    @Column(name = "high_price", nullable = false)
    private Double highPrice;

    @Column(name = "low_price", nullable = false)
    private Double lowPrice;

    @Column(name = "close_price", nullable = false)
    private Double closePrice;

    @Column(name = "volume", nullable = false)
    private Long volume;

    public StockHistory() {}

    public StockHistory(String companyCode, LocalDate tradeDate, Double openPrice, Double highPrice, Double lowPrice, Double closePrice, Long volume) {
        this.companyCode = companyCode;
        this.tradeDate = tradeDate;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public Long getVolume() {
        return volume;
    }
}
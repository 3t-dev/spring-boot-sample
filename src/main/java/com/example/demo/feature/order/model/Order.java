package com.example.demo.feature.order.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Order {
    private Long id;
    private String code;
    private String product;
    private Double volume;
    private Action action;
    private Date createdDate;
    private Date updatedDate;

    public enum Action {
        UNKNOWN(0),
        BUY(1),
        SELL(2);

        private int intValue;

        Action(int intValue) {
            this.intValue = intValue;
        }

        public int intValue() {return this.intValue;}

        public static Action fromIntValue(int value) {
            switch (value) {
                case 1: return BUY;
                case 2: return SELL;
                default: return UNKNOWN;
            }
        }
    }
}

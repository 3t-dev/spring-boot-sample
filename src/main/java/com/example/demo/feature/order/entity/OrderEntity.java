package com.example.demo.feature.order.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TBL_ORDER")
@Data
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "PRODUCT")
    private String product;

    @Column(name = "VOLUME")
    private Double volume;

    @Column(name = "ACTION")
    private int action;

    @Column(name = "CREATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "UPDATED_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
}

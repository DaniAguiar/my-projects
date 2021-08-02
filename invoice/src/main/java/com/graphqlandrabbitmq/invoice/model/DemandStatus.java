package com.graphqlandrabbitmq.invoice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DemandStatus {

    private Demand demand;
    private String status;
    private  String message;
}

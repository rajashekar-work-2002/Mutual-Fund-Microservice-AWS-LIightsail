package com.firstLayer.firstLayer.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ReturnsCalculate {
    String futureValue;
    String increasedAmount;
    List<MFEntity> list;
}

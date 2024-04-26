package com.DbLayer.DbLayer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedVales {
    List<Integer> keys;
    List<Integer> values;
    List<Integer> year;
    List<Double> percentage;
}

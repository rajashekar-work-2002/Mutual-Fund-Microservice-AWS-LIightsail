package com.firstLayer.firstLayer.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlotEntity {


    private Long id;
    private String yearNav;
    private String nav;
    private String yearCount;
    private String retRate;
}

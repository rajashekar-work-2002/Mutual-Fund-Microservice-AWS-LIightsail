package com.firstLayer.firstLayer.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MFEntity {

    private Long id;
    private String name;
    private String path;
    private String shortName;
    private String code;
    private Double ret1;
    private Double ret3;
    private Double nav;

}

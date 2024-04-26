package com.mutualFund.mf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input {
    private String nav;
    private String code;
    private Double per1;
    private Double per3;
}

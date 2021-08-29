package com.dongbao.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StaleDataedResult implements Serializable {


    private List<Problem> testStaleDatedList;


    private List<Problem> productStaleDatedList;


    private List<Problem> developStaleDatedList;

}

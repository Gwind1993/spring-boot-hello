package com.spring.boot.test;

public enum CountryEnum {

    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"韩"),
    FIVE(5,"赵"),
    SIX(6,"魏");

    private Integer retCode;

    private String retName;

    CountryEnum(Integer retCode, String retName) {
        this.retCode = retCode;
        this.retName = retName;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetName() {
        return retName;
    }

    public static CountryEnum getCountryByIndex(Integer retCode){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum element: countryEnums) {
            if(element.getRetCode() == retCode){
                return element;
            }
        }
        return null;
    }
}

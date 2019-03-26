package com.example.c7_ong.comphouse;

import java.util.ArrayList;
import java.util.List;

//Getter and setter class for the company information
public class Company {
    public String companyTitle;
    public String companyNumber;
    public Company()
    {

    }
    public String getCompanyTitle()
    {
        return companyTitle;
    }

    public void setCompanyTitle(String company)
    {
        this.companyTitle = company;
    }

    public String getCompanyNumber()
    {
        return companyNumber;
    }

    public void setCompanyNumber(String companyNumber)
    {
        this.companyNumber = companyNumber;
    }

}

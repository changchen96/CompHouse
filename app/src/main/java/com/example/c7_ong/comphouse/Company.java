package com.example.c7_ong.comphouse;

import java.util.List;

public class Company {
    public String companyTitle;
    private List<Company> companyList;
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

    public void setCompanyList(List<Company> compNames)
    {
        this.companyList = compNames;
    }

    public List getCompanyList()
    {
        return companyList;
    }
}

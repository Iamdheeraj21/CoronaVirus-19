package com.example.coronavirus_19;

public class CountryModel
{
    private String flag,country,cases,todayCases,todayDeaths,deaths,active,critical,recovered;

    public CountryModel(String flag, String country,String cases,String todayCases,String deaths,String todayDeaths,String active,String critical,String recovered) {
        this.flag = flag;
        this.country = country;
        this.cases=cases;
        this.todayCases=todayCases;
        this.deaths=deaths;
        this.todayDeaths=todayDeaths;
        this.active=active;
        this.critical=critical;
        this.recovered=recovered;
    }
    public CountryModel() {
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}

package Business;

public class CovidRecord {
    //fields
    private String date;
    private String city;
    private int cases;
    private int deaths;
    private int recovered;

    //constructor
    public CovidRecord(String date, String city, int cases, int deaths, int recovered) {
        this.date = date;
        this.city = city;
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    //get and set methods
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    //To string method that prints the object
    @Override
    public String toString() {
        return this.getDate() + "," + this.getCity() + "," + this.getCases() + "," + this.getDeaths() + "," + this.getRecovered();
    }

}

package tasks;

public class DataFile {
    private int jobId;
    private long clickId;
    private String country;
    private String date;
    private double money;

    public DataFile() {
    }

    public DataFile(int jobId, long clickId, String country, String date, double money) {
        this.jobId = jobId;
        this.clickId = clickId;
        this.country = country;
        this.date = date;
        this.money = money;
    }

    // Геттеры и сеттеры
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public long getClickId() {
        return clickId;
    }

    public void setClickId(long clickId) {
        this.clickId = clickId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DataFile{" +
                "jobId=" + jobId +
                ", clickId=" + clickId +
                ", country='" + country + '\'' +
                ", date='" + date + '\'' +
                ", money=" + money +
                '}';
    }
}

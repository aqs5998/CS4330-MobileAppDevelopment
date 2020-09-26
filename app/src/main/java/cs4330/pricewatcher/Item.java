package cs4330.pricewatcher;

public class Item {
    private String url;
    private String name;
    private double currentPrice;
    private double initialPrice;

    public Item(){

    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setInitialPrice(double initialPrice) {
        this.initialPrice = initialPrice;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getInitialPrice() {
        return initialPrice;
    }
}

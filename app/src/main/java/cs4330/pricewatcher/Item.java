package cs4330.pricewatcher;

public class Item {
    private String url;
    private String name;
    private double currentPrice;
    private double initialPrice;

    public Item(){
        this.url = "NoUrl";
        this.name = "NoName";
        this.currentPrice = -1.0;
        this.initialPrice = -1.0;
    }

    /**
     *
     * @return Returns the currentPrice over the initial price, restricted up to 2 decimals
     */
    public double getPercentChange(){ return Math.round((this.currentPrice/this.initialPrice)-1)*100; }

    public void setUrl(String url) { this.url = url; }

    public void setName(String name) { this.name = name; }

    public void setCurrentPrice(double currentPrice) { this.currentPrice = currentPrice; }

    public void setInitialPrice(double initialPrice) { this.initialPrice = initialPrice; }

    public String getUrl() { return url; }

    public String getName() { return name; }

    public double getCurrentPrice() { return currentPrice; }

    public double getInitialPrice() { return initialPrice; }

}

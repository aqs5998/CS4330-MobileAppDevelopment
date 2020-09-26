package cs4330.pricewatcher;

public class PriceWatcher {

    public PriceWatcher(){

    }

    /**
     * Look up the item using the provided url, then return the price of it. If price not found, -1 is returned.
     * @param url The url for an item somewhere in the internet
     * @return The price of the given item, currently math.random()*10 is used which returns a random double from 0 to 10.
     */
    public double findPrice(String url){
        return Math.random()*10;
    }
}

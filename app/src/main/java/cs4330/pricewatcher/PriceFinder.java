package cs4330.pricewatcher;

import android.util.Log;

public class PriceFinder {

    public PriceFinder(){

    }

    /**
     * Look up the item using the provided url, then return the price of it. If price not found, -1 is returned.
     * @param url The url for an item somewhere in the internet
     * @return The price of the given item, currently math.random()*10 is used which returns a random double from 0 to 10.
     */
    public double findPrice(String url){
        Log.d("PriceFinder", "findPrice("+url+")-------------- ");
        return Math.random()*10;
    }
}

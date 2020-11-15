package cs4330.pricewatcher;

import android.util.Log;

public class PriceFinder {

    public PriceFinder(){

    }

    /**
     * Look up the item using the provided url, then return the price of it. If price not found, -1 is returned.
     * @param url The url for an item somewhere in the internet
     * @return The price of the given item, returns -1.0 if it cannot be found
     */
    public double findPrice(String url){
        Log.d("PriceFinder", "findPrice("+url+")-------------- ");
        /**
         * HW3.R1)
         * TODO
         * Find the price, return -1 if it cannot be found R
         * Must be functional for homedepot.com and lowes.com
         */

        return (Math.round(Math.random()*1000))/10;
    }
}

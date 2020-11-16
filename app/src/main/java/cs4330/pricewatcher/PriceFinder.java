package cs4330.pricewatcher;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PriceFinder {


    public double amountToReturn;

    public PriceFinder(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    /**
     * Look up the item using the provided url, then return the price of it. If price not found, -1 is returned.
     * @param url The url for an item somewhere in the internet
     * @return The price of the given item, returns -1.0 if it cannot be found
     */
    public double findPrice(String url) {
        Log.d("PriceFinder", "findPrice("+url+")-------------- ");
        /**
         * HW3.R1)
         * TODO
         * Find the price, return -1 if it cannot be found R
         * Must be functional for homedepot.com and lowes.com
         */
        if(url.contains("homedepot.com")){
            Log.d("HOMEDEPOT URL", "findPrice("+url+")-------------- ");
            return findHomeDepotPrice(url);
        } else if (url.contains("walmart.com")) {
            Log.d("walmart URL", "findPrice("+url+")-------------- ");
            return findWalmartPrice(url);
        }
        Log.d("NO URL", "findPrice("+url+")-------------- ");
        return -1.0;
    }

    public double findHomeDepotPrice(String url){
        try{
            URL urlVar = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlVar.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null){
                // For home depot this is what they use
                if(currentLine.contains("price-format__large-symbols")){
                    int index = currentLine.indexOf("price-format__large-symbols");

                    // we know that the price is somewhere in here
                    String substring = currentLine.substring(index, index+100);
                    int firstSpanIndex = substring.indexOf("<span>");
                    int firstDigitIndex = firstSpanIndex+6;

                    String dollarsAsString = "";
                    String centsAsString = "";
                    while(Character.isDigit(substring.charAt(firstDigitIndex))){
                        dollarsAsString = dollarsAsString + substring.charAt(firstDigitIndex++);
                    }
                    // now we look for the cents, the cents are 49 characters after the dollars amount ends
                    firstDigitIndex += 49;
                    while(Character.isDigit(substring.charAt(firstDigitIndex))){
                        centsAsString = centsAsString + substring.charAt(firstDigitIndex++);
                    }
                    Log.d("DOLLARS IS ", dollarsAsString);
                    Log.d("CENTS IS ", centsAsString);
                    String price = dollarsAsString+"."+centsAsString;
                    Log.d("FINAL PRICE IS ", price);
                    // dollarsAsString = dollarsAsString.replaceAll(", $","");

                    double dollars = Double.parseDouble(dollarsAsString);
                    double cents = Double.parseDouble(centsAsString);


                    return dollars + (cents*.01);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return -1.0;
        }
        return -2.0;
    }

    public double findWalmartPrice(String url){
        try{
            URL urlVar = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlVar.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String currentLine;
            while((currentLine = bufferedReader.readLine()) != null){

                // For lowes this is what they use
                String stringToFind = "price-characteristic";


                if(currentLine.contains(stringToFind)){
                    //Log.d("IF STATEMENT", currentLine);

                    int index = currentLine.indexOf(stringToFind);
                    index = stringToFind.length() + index;

                    // we know that the price is somewhere in here
                    String substring = currentLine.substring(index, index+100);
                    //Log.d("IF STATEMENT", substring);

                    int firstDigitIndex = 28;


                    String dollarsAsString = "";
                    String centsAsString = "";
                    while(Character.isDigit(substring.charAt(firstDigitIndex))){
                        dollarsAsString = dollarsAsString + substring.charAt(firstDigitIndex++);
                    }

                    // now we look for the cents, the cents is 1 character after the dollars amount ends
                    firstDigitIndex += 1;
                    while(Character.isDigit(substring.charAt(firstDigitIndex))){
                        centsAsString = centsAsString + substring.charAt(firstDigitIndex++);
                    }
                    Log.d("DOLLARS IS ", dollarsAsString);
                    Log.d("CENTS IS ", centsAsString);
                    String price = dollarsAsString+"."+centsAsString;
                    Log.d("FINAL PRICE IS ", price);
                    // dollarsAsString = dollarsAsString.replaceAll(", $","");

                    double dollars = Double.parseDouble(dollarsAsString);
                    double cents = Double.parseDouble(centsAsString);


                    return dollars + (cents*.01);

                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
            return -1.0;
        }
        return -2.0;
    }

    public double getAmountToReturn(){
        return this.amountToReturn;
    }

}

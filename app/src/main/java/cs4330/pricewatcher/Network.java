package cs4330.pricewatcher;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

class Network {
    public static String getConnectivityStatusString(Context context) {
        String status = null;

        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //Android library to check for active network
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                status = "Wifi On";
                return status;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                status = "Mobile data On";
                return status;
            }
        } else {
            //No Internet will direct to wifi settings page no matter what
            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            status = "NO INTERNET DETECTED. DIRECTING TO WIFI SETTINGS PAGE";
            return status;
        }
        return status;
    }
}

package cs4330.pricewatcher;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;
public class BdReceiver extends android.content.BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = Network.getConnectivityStatusString(context);
        if(status.isEmpty()) {
            context.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
            status = "NO INTERNET DETECTED. DIRECTING TO WIFI SETTINGS PAGE";
        }
//        for (int i=0; i < 2; i++)
//        {
            // Displays network status
            Toast.makeText(context, status, Toast.LENGTH_LONG).show();
//        }
    }
}
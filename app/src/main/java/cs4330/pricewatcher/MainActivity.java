package cs4330.pricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    PriceWatcher pricewatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pricewatcher = new PriceWatcher();

        Button updatePriceButton = getCon
    }
}
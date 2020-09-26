package cs4330.pricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private PriceFinder priceFinder;
    private Item item;
    private TextView itemNameTextView;
    private TextView itemInitialPriceTextView;
    private TextView itemCurrentPriceTextView;
    private TextView itemPercentChangeTextView;
    private Button itemUpdatePriceButton;
    private Button visitItemOnlineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This current PriceFinder instance only has one method and is for placeholder purposes
        priceFinder = new PriceFinder();
        // Initialize an item instance
        item = new Item();
        item.setName("Test Item");
        item.setCurrentPrice(0.0);
        item.setInitialPrice(0.0);
        item.setUrl("https://testurl.com/testItem");

        // Initialize all TextViews and Buttons
        itemNameTextView = findViewById(R.id.ItemNameTextView);
        itemInitialPriceTextView = findViewById(R.id.ItemInitialPriceTextView);
        itemCurrentPriceTextView = findViewById(R.id.ItemCurrentPriceTextView);
        itemPercentChangeTextView = findViewById(R.id.ItemPercentChangeTextView);
        itemUpdatePriceButton = findViewById(R.id.ItemUpdatePriceButton);
        visitItemOnlineButton = findViewById(R.id.VisitItemOnlineButton);


        // Add on click listeners
        itemUpdatePriceButton.setOnClickListener(this::itemUpdatePriceButtonClicked);
        visitItemOnlineButton.setOnClickListener(this::visitItemOnlineButtonClicked);

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Main", "onStart() -------------- ");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("Main", "OnResume()-------------- ");
    }

    public void itemUpdatePriceButtonClicked(View view){
        Log.d("Main", "itemUpdatePriceButtonClicked()-------------- ");

        // call the PriceFinder with the item url, then update the item price.
        item.setCurrentPrice(priceFinder.findPrice(item.getUrl()));

    }

    public void visitItemOnlineButtonClicked(View view){
        Log.d("Main", "visitItemOnlineButtonClicked()-------------- ");
    }


}
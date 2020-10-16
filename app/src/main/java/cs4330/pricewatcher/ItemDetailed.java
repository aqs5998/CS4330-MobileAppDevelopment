package cs4330.pricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetailed extends AppCompatActivity {

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
        setContentView(R.layout.item_detailed);
        Log.d("ItemDetailed", "onCreate()-------------------");
        // This current PriceFinder instance only has one method and is for placeholder purposes
        priceFinder = new PriceFinder();
        // Initialize an item instance
        item = new Item();
        item.setName("Test Item");
        item.setInitialPrice(5.0);
        item.setCurrentPrice(priceFinder.findPrice(item.getUrl()));
        item.setUrl("https://www.walmart.com/ip/Asus-VivoBook-14-FHD-Thin-and-Light-Laptop-AMD-Ryzen-5-3500U-Processor-8GB-RAM-256GB-SSD-Storage-Windows-10-Google-Classroom-Compatible/223381882");

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

        // initialize UI, but check if there is a saved instanced state first
        updateUI();


    }

    /**
     * This method updated the UI with the new item values
     */
    public void updateUI(){
        itemNameTextView.setText("Item Name:" + item.getName());
        itemInitialPriceTextView.setText("Item Initial Price:" + item.getInitialPrice());
        itemCurrentPriceTextView.setText("Item Current Price:" + item.getCurrentPrice());
        itemPercentChangeTextView.setText("Item Percent Change:" + item.getPercentChange() + "%");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ItemDetailed", "onStart() -------------- ");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("ItemDetailed", "OnResume()-------------- ");
    }

    public void itemUpdatePriceButtonClicked(View view){
        Log.d("ItemDetailed", "itemUpdatePriceButtonClicked()-------------- ");

        // call the PriceFinder with the item url, then update the item price.
        item.setCurrentPrice(priceFinder.findPrice(item.getUrl()));
        updateUI();

    }

    public void visitItemOnlineButtonClicked(View view){
        Log.d("ItemDetailed", "visitItemOnlineButtonClicked()-------------- ");

        Uri uri = Uri.parse(item.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}
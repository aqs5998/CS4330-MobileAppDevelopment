package cs4330.pricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetailed extends AppCompatActivity {

    private PriceFinder priceFinder;
    private Item item;
    private Intent intent;
    private TextView itemNameTextView;
    private TextView itemInitialPriceTextView;
    private TextView itemCurrentPriceTextView;
    private TextView itemPercentChangeTextView;
    private TextView itemUrlTextView;
    private Button itemUpdatePriceButton;
    private Button visitItemOnlineButton;
    private WebView webView;
    private ItemDatabaseHelper dbTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detailed);
        Log.d("ItemDetailed", "onCreate()-------------------");
        // This current PriceFinder instance only has one method and is for placeholder purposes
        priceFinder = new PriceFinder();
        // Initialize an item instance

        dbTool = new ItemDatabaseHelper(getApplicationContext());

        intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        String itemUrl = intent.getStringExtra("itemUrl");
        double itemInitialPrice = intent.getDoubleExtra("itemInitial", 1.0);
        double itemCurrentPrice = intent.getDoubleExtra("itemCurrent", 1.0);

        item = new Item(itemName, itemUrl, itemInitialPrice, itemCurrentPrice);

        // Initialize all TextViews and Buttons
        itemNameTextView = findViewById(R.id.ItemNameTextView);
        itemUrlTextView = findViewById(R.id.ItemUrl);
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

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new MyBrowser());
        webView.loadUrl(item.getUrl());

        webView.setWebViewClient(new MyBrowser() {

            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                //findViewById(R.id.imageLoading1).setVisibility(View.GONE);
                //show webview
                findViewById(R.id.webview).setVisibility(View.VISIBLE);
            }


        });

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("tel:") || url.startsWith("sms:") || url.startsWith("smsto:") || url.startsWith("mailto:") || url.startsWith("mms:") || url.startsWith("mmsto:") || url.startsWith("market:") || url.equals("http://wingcrony.com/?actie=donate")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }
            else {
                view.loadUrl(url);
                return true;
            }
        }
    }


    /**
     * This method updated the UI with the new item values
     */
    public void updateUI(){
        itemNameTextView.setText("Item Name: " + item.getName());
        itemUrlTextView.setText("Item URL: " + item.getUrl());
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
        dbTool.update(item);

        updateUI();

    }

    public void visitItemOnlineButtonClicked(View view){
        Log.d("ItemDetailed", "visitItemOnlineButtonClicked()-------------- ");

        /**
         * FIXME
         * This crashes too >:p
         */
        Uri uri = Uri.parse(item.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


}
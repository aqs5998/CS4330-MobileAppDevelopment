package cs4330.pricewatcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PriceFinder priceFinder;
    private EditText itemName;
    private EditText itemUrl;
    private Button addButton;
    private Button removeAllButton;
    private Button removeItemButton;
    private ListView listView;
    private ItemListedAdapter itemListedAdapter;
    private ItemDatabaseHelper dbTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActiviy", "onCreate()-------------------");

        // This current PriceFinder instance only has one method and is for placeholder purposes
        priceFinder = new PriceFinder();

        itemName = findViewById(R.id.itemName);
        itemUrl =  findViewById(R.id.itemUrl);
        addButton = findViewById(R.id.addButton);
        removeAllButton = findViewById(R.id.removeButton);
        removeItemButton = findViewById(R.id.removeItemButton);
        listView = findViewById(R.id.listView);
        dbTool = new ItemDatabaseHelper(getApplicationContext());

        itemListedAdapter = new ItemListedAdapter(getApplicationContext(), R.layout.item_detailed, dbTool.allItems(), this);
        listView.setAdapter(itemListedAdapter);

        addButton.setOnClickListener(this::addButtonClicked);
        removeAllButton.setOnClickListener(this::removeAllClicked);

        //System.out.println(removeItemButton + " REMOVE ITEM BUTTON");
        if (removeItemButton != null) { removeItemButton.setOnClickListener(this::removeClicked); }


        // initialize UI, but check if there is a saved instanced state first
        updateUI();

    }

    /**
     * This method updated the UI with the new item values
     */
    public void updateUI(){
        itemListedAdapter = new ItemListedAdapter(getApplicationContext(), R.layout.item_detailed, dbTool.allItems(), this);
        listView.setAdapter(itemListedAdapter);
    }

    public void addButtonClicked(View view){
        Log.d("Main", "addButtonClicked()........");

        // add item, then clear textedit
        String name = itemName.getText().toString();
        String url = itemUrl.getText().toString();
        // Do nothing if either are blank
        if(name.equals("") || url.equals("")){ return; }
        itemName.setText("");
        itemUrl.setText("");

        //Used to check to see if URL is valid
        Uri uri = Uri.parse(url);

        //Check to see if URL is valid
        if (URLUtil.isValidUrl(String.valueOf(uri)) == false) {
            System.out.println("THIS IS NOT VALID URL");
            Toast toast = Toast.makeText(getApplicationContext(), "invalid URL!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        Item todoItem = new Item(name, url);

        Log.d("addButtonClicked()", "Adding new item " + todoItem.getName());
        dbTool.addItem(todoItem);
        Log.d("addButtonClicked()", "Printing all items");
        List<Item> list = dbTool.allItems();
        for(int i=0; i<list.size(); i++){
            Log.d("for loop +++++++++++", list.get(i).getName());
        }
        updateUI();
    }

    public void removeAllClicked(View view){
        Log.d("Main", "removeAllClicked()........");

        dbTool.deleteAll();

        updateUI();
    }

    public void removeClicked(View view){
        Log.d("Main", "removeClicked()........");

        updateUI();
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


}
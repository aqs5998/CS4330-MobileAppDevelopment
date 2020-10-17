package cs4330.pricewatcher;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs4330.pricewatcher.Item;
import cs4330.pricewatcher.R;

/** Provide views for an AdapterView by return ing a view
 * for each ToDoItem contained in a list. */
public class ItemListedAdapter extends ArrayAdapter<Item> {
    private Context context;

    public ItemListedAdapter(Context context, int resourceId, List<Item> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    public interface ItemClickListener {
        void itemClicked(Item item);
    }

    private ItemClickListener listener;

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_listed, parent, false);
//            Might add checkboxes to list in the future
//            CheckBox checkBox = convertView.findViewById(R.id.checkBox);
//            checkBox.setOnClickListener(view -> {
//                CheckBox cb = (CheckBox) view;
//                Item item = (Item) cb.getTag();
//                item.setDone(cb.isChecked());
//                if (listener != null) {
//                    listener.itemClicked(item);
//                }
//            });

            Button viewItemButton = convertView.findViewById(R.id.viewItemButton);
            View finalConvertView = convertView;
            View finalConvertView1 = convertView;
            viewItemButton.setOnClickListener(view -> {
                /**
                 * Create a new intent and change the current view to item detailed
                 */
                Log.d("getView()","event listener for iteem");

                Intent intent = new Intent(this.getContext(), ItemDetailed.class);
                Log.d("getView()","setting item name ");
                EditText itemName = (EditText) finalConvertView1.findViewById(R.id.itemName);
                Log.d("getView()","setting item price");
                EditText itemPrice = (EditText) finalConvertView1.findViewById(R.id.itemUrl);

                Log.d("getView()", "putting extras");
                /**
                 * FIXME
                 * For some reason itemName and itemPrice are being set to null :(
                 * Probably has something to do with convert view >:o
                 */
                if(itemName == null || itemPrice == null){
                    intent.putExtra("itemName", "test");
                    intent.putExtra("itemUrl", "url goes here");
                } else {
                    intent.putExtra("itemName", itemName.getText());
                    intent.putExtra("itemUrl", itemPrice.getText());
                }

                context.startActivity(intent);
            });
        }

        Item current = getItem(position);
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(String.valueOf(current.getName()));
//        Checkbox functionality
//        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
//        checkBox.setChecked(current.isDone());
//        checkBox.setTag(current);
        return convertView;
    }

}
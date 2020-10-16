package cs4330.pricewatcher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ItemDatabaseHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "todoDB";
    private static final String ITEM_TABLE = "items";

    private static final String KEY_ID = "_id";
    private static final String KEY_INITIAL_PRICE = "initial_price";
    private static final String KEY_CURRENT_PRICE = "current_price";
    private static final String KEY_URL = "url";
    private static final String KEY_NAME = "name";

    public ItemDatabaseHelper(Context context){
        super (context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE " + ITEM_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_INITIAL_PRICE + " TEXT, "
                + KEY_CURRENT_PRICE + " TEXT, "
                + KEY_URL + " TEXT, "
                + KEY_NAME + " TEXT " + ")";
        db.execSQL(table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        onCreate(database);
    }

    public void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CURRENT_PRICE, String.valueOf(item.getCurrentPrice())); // currentPrice
        values.put(KEY_INITIAL_PRICE, String.valueOf(item.getInitialPrice()));
        values.put(KEY_NAME, item.getName());
        values.put(KEY_URL, item.getUrl());

        long id = db.insert(ITEM_TABLE, null, values);
        item.setId((int) id);
        db.close();
    }

    public List<Item> allItems() {
        List<Item> itemList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ITEM_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String initialPriceAsString = cursor.getString(1);
                String currentPriceAsString = cursor.getString(2);
                String url = cursor.getString(3);
                String name = cursor.getString(4);

                Item curr = new Item(id, Double.parseDouble(initialPriceAsString),
                        Double.parseDouble(currentPriceAsString),
                        name, url);
                itemList.add(curr);
            } while (cursor.moveToNext());
        }
        return itemList;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_TABLE, null, new String[]{});
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEM_TABLE, KEY_ID + " = ?", new String[] { Integer.toString(id) } );
        db.close();
    }

    public void update(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_CURRENT_PRICE, String.valueOf(item.getCurrentPrice()));
        values.put(KEY_INITIAL_PRICE, String.valueOf(item.getInitialPrice()));
        values.put(KEY_NAME, item.getName());
        values.put(KEY_URL, item.getUrl());
        db.update(ITEM_TABLE, values, KEY_ID + " = ?", new String[]{String.valueOf(item.id())});
        db.close();
    }


}

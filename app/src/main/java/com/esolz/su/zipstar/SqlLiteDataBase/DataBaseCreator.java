package com.esolz.su.zipstar.SqlLiteDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Image;
import android.util.Log;
import android.widget.Toast;

import com.esolz.su.zipstar.DataType.APP_DATA;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by su on 22/9/15.
 */
public class DataBaseCreator extends SQLiteOpenHelper {

    private static final String TABLE_FAVOURITE = "favouritetable";

    private static final String TIME_TABLE = "timetable";

    private static final String D_ID = "table_id";
    private static final String D_DATE_TIME = "Time";



    private static final String NAME="name";
    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    private static final String TABLE_LIST = "listtable";
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Table Names
    private static final String TABLE_TODO = "todos";
    private static final String TABLE_TAG = "tags";
    private static final String TABLE_TODO_TAG = "todo_tags";

    // Common column names

    private static final String KEY_CREATED_AT = "created_at";

    // NOTES Table - column nmaes
    private static final String KEY_TODO = "todo";
    private static final String KEY_STATUS = "status";

    // TAGS Table - column names
    private static final String KEY_TAG_NAME = "tag_name";

    // NOTE_TAGS Table - column names
    private static final String KEY_TODO_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    // List Table Columns names

    private static final String BRAND_NAME="brand_name";
    private static final String UPC="upc14";

    private static final String IMG="img";
    private static final String QUANTITY="quantity";
    private static final String Product_Type="producttype";
    private static final String ManuallyAdded="manuallyadded";
    private static final String ID = "id";
    // Favorite Table Columns names
    private static final String F_BRAND_NAME="brand_name";
    private static final String F_UPC="upc14";
    private static final String F_NAME="name";
    private static final String F_IMG="img";
    private static final String F_ID = "id";
    private static final String F_QUANTITY="quantity";
    private static final String F_Product_Type="producttype";
    private static final String F_ManuallyAdded="manuallyadded";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_TODO = "CREATE TABLE "
            + TABLE_TODO + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TODO
            + " TEXT," + KEY_STATUS + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_TODO_TAG = "CREATE TABLE "
            + TABLE_TODO_TAG + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_TODO_ID + " INTEGER," + KEY_TAG_ID + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";
    private static final   String CREATE_LIST_TABLE = "create table if not exists listtable (id INTEGER,brand_name TEXT,upc14 TEXT,name TEXT,img TEXT,quantity INTEGER,producttype TEXT,manuallyadded INTEGER,PRIMARY KEY (brand_name,name,upc14))";
    private static final   String CREATE_FAVOURITE_TABLE =  "create table if not exists favouritetable (id INTEGER,brand_name TEXT,upc14 TEXT,name TEXT,img TEXT,quantity INTEGER,producttype TEXT,manuallyadded INTEGER,PRIMARY KEY(brand_name,name,upc14))";
    private static final   String CREATE_TIMETABLE =  "create table if not exists timetable (Time text,table_id INTEGER primary key)";


    public DataBaseCreator(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public LinkedList<ListDataType> getAllList() {
        LinkedList<ListDataType> List = new  LinkedList<ListDataType>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_LIST;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ListDataType listDataType = new ListDataType();
//                listDataType.setID(Integer.parseInt(cursor.getString(0)));
//                listDataType.setID(Integer.parseInt(cursor.getString(0)));
                listDataType.setPhoneNumber(cursor.getString(1));
                listDataType.setName(cursor.getString(3));
                listDataType.setQnt(cursor.getString(5));
                listDataType.setUpcid(cursor.getString(2));
                listDataType.setImage(cursor.getString(4));
                listDataType.setImage(cursor.getString(6));
                // Adding list data to list
                List.add(listDataType);
            } while (cursor.moveToNext());
        }

        // return  list
        return List;
    }












    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_TODO);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_TODO_TAG);
        db.execSQL(CREATE_LIST_TABLE);
        db.execSQL(CREATE_FAVOURITE_TABLE);
        db.execSQL(CREATE_TIMETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO_TAG);

        // create new tables
        onCreate(db);
    }

    // ------------------------ "todos" table methods ----------------//
    public void addFav(FavouriteDataType favouriteDataType) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(F_NAME, favouriteDataType.abc.getName()); // Contact Name
        values.put(F_BRAND_NAME, favouriteDataType.abc.getBrand_name()); // Contact Phone
        values.put(F_QUANTITY, favouriteDataType.abc.getQnt());
        values.put(F_IMG, favouriteDataType.abc.getImage());
        values.put(F_UPC, favouriteDataType.abc.getUpc14());
        values.put(F_Product_Type, favouriteDataType.abc.getProduct_name());
        db.insert(TABLE_FAVOURITE, null, values);
        db.close(); // Closing database connection

//        values.put(ManuallyAdded, favouriteDataType.ge());
        // Inserting Row



    }

    public String gettime() {

        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT Time FROM timetable";
        Cursor cursor = database.rawQuery(selectQuery, null);
        if(cursor!=null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do {
                APP_DATA.prefered_time = cursor.getString(0);
            } while (cursor.moveToNext());
        }

    return APP_DATA.prefered_time;
}





    // ------------------------ "todos" table methods ----------------//
    public LinkedList<DateTimeDataType> addtimetable(String dateTimeDataType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(D_DATE_TIME, APP_DATA.prefered_time); // Contact Phone
        db.insert(TIME_TABLE, null, values);
        db.close(); // Closing database connection


        return null;
    }



    // ------------------------ "todos" table methods ----------------//
    public void addManualfav(FavouriteDataType favouriteDataType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(F_NAME, favouriteDataType.get_name()); // Contact Name
        values.put(F_BRAND_NAME, favouriteDataType.get_phone_number()); // Contact Phone
        values.put(F_QUANTITY, favouriteDataType.getQnt());
        // Inserting Row
        db.insert(TABLE_FAVOURITE, null, values);
        db.close(); // Closing database connection
    }
    // ------------------------ "todos" table methods ----------------//
    public void addmanuallytolist(ListDataType listDataType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, listDataType.getName()); // Contact Name
        values.put(BRAND_NAME, listDataType.getPhoneNumber()); // Contact Phone
        values.put(ID, listDataType.getID());
        // Inserting Row
        db.insert(TABLE_LIST, null, values);
        db.close(); // Closing database connection
    }


    public LinkedList<FavouriteDataType> getAllFavList() {
        LinkedList<FavouriteDataType> favList = new  LinkedList<FavouriteDataType>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_FAVOURITE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                FavouriteDataType favouriteDataType = new FavouriteDataType();
                favouriteDataType.set_phone_number(cursor.getString(1));
                favouriteDataType.set_name(cursor.getString(3));
                favouriteDataType.setQnt(cursor.getString(5));
                favouriteDataType.setUpcid(cursor.getString(2));
                favouriteDataType.setImage(cursor.getString(4));
                favouriteDataType.setProduct_type(cursor.getString(6));
                favList.add(favouriteDataType);
            } while (cursor.moveToNext());
        }

        // return fav list
        return favList;
    }




    public void addList(ListDataType listDataType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME,listDataType.abc.getName()); // Contact Name
        values.put(BRAND_NAME,listDataType.abc.getBrand_name()); // Contact Phone
        values.put(QUANTITY,listDataType.abc.getQnt());
        values.put(IMG,listDataType.abc.getImage());
        values.put(UPC,listDataType.abc.getUpc14());
        values.put(Product_Type,listDataType.abc.getProduct_name());

        // Inserting Row
        db.insert(TABLE_LIST, null, values);
        db.close(); // Closing database connection
    }


    public void addfromfavList(ListDataType listDataType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME,listDataType.favouriteDataType.get_name()); // Contact Name
        values.put(BRAND_NAME,listDataType.favouriteDataType.get_phone_number()); // Contact Phone
        values.put(QUANTITY,listDataType.favouriteDataType.getQnt());
        values.put(UPC,listDataType.favouriteDataType.getUpcid());
        values.put(IMG,listDataType.favouriteDataType.getImage());
        values.put(Product_Type,listDataType.favouriteDataType.getProduct_type());

        // Inserting Row
        db.insert(TABLE_LIST, null, values);
        db.close(); // Closing database connection
    }



    //for quantity part

//    public void addquantity(ListDataType listDataType) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(QUANTITY,listDataType.getQnt()); // Contact Name
//
//        // Inserting Row
//        db.insert(TABLE_LIST, null, values);
//        db.close(); // Closing database connection
//    }





//    /*
//     * get single todo
//     */
//    public Todo getTodo(long todo_id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " WHERE "
//                + KEY_ID + " = " + todo_id;
//
//        Log.e(LOG, selectQuery);
//
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        if (c != null)
//            c.moveToFirst();
//
//        Todo td = new Todo();
//        td.setId(c.getInt(c.getColumnIndex(KEY_ID)));
//        td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
//        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
//
//        return td;
//    }

//    /**
//     * getting all todos
//     * */
//    public List<Todo> getAllToDos() {
//        List<Todo> todos = new ArrayList<Todo>();
//        String selectQuery = "SELECT  * FROM " + TABLE_TODO;
//
//        Log.e(LOG, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                Todo td = new Todo();
//                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
//                td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
//                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
//
//                // adding to todo list
//                todos.add(td);
//            } while (c.moveToNext());
//        }
//
//        return todos;
//    }

//    /**
//     * getting all todos under single tag
//     * */
//    public List<Todo> getAllToDosByTag(String tag_name) {
//        List<Todo> todos = new ArrayList<Todo>();
//
//        String selectQuery = "SELECT  * FROM " + TABLE_TODO + " td, "
//                + TABLE_TAG + " tg, " + TABLE_TODO_TAG + " tt WHERE tg."
//                + KEY_TAG_NAME + " = '" + tag_name + "'" + " AND tg." + KEY_ID
//                + " = " + "tt." + KEY_TAG_ID + " AND td." + KEY_ID + " = "
//                + "tt." + KEY_TODO_ID;
//
//        Log.e(LOG, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                Todo td = new Todo();
//                td.setId(c.getInt((c.getColumnIndex(KEY_ID))));
//                td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
//                td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));
//
//                // adding to todo list
//                todos.add(td);
//            } while (c.moveToNext());
//        }
//
//        return todos;
//    }

//    /*
//     * getting todo count
//     */
//    public int getToDoCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_TODO;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//
//        int count = cursor.getCount();
//        cursor.close();
//
//        // return count
//        return count;
//    }

    /*
     * Updating a todo
     */
    public int updateToDo(ListDataType listDataType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(NAME,listDataType.getName()); // Contact Name
//        values.put(BRAND_NAME,listDataType.getPhoneNumber()); // Contact Phone
//        values.put(QUANTITY,listDataType.getQnt());
        values.put(QUANTITY, APP_DATA.upcid);
        values.put(ID, listDataType.getID());

        // updating row
        return db.update(TABLE_LIST, values, QUANTITY + " = ?",
                new String[]{String.valueOf(listDataType.getID())});
    }

    public int Update_Contact(ListDataType listDataType) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUANTITY,listDataType.getQnt());
        values.put(NAME, listDataType.getName());
        values.put(BRAND_NAME, listDataType.getPhoneNumber());


        // updating row
        return db.update(TABLE_LIST, values, BRAND_NAME + " = ?",
                new String[] { (listDataType.getPhoneNumber()) });
    }


    public void deleteFavlistiten(FavouriteDataType favouriteDataType) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.delete(TABLE_FAVOURITE, "brand_name=? AND name=? AND upc14=?",
                new String[] { favouriteDataType.get_phone_number(),favouriteDataType.get_name(),favouriteDataType.getUpcid() });


        db.close();
    }


//    insertSQL = [NSString stringWithFormat:@"delete from listtable where brand_name='%@' and name='%@' and producttype='%@'",brandname,name,producttype];
//
//    NSLog(@"insertSQL %@",insertSQL);

    public void deletelistiten(ListDataType listDataType) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LIST, "brand_name=? AND name=? AND upc14=?",
                new String[] { listDataType.getPhoneNumber(),listDataType.getName(),listDataType.getUpcid() });
        db.close();
    }



    /**
     * get datetime
     * */



//    // ------------------------ "tags" table methods ----------------//
//
////    /*
////     * Creating tag
////     */
//    public long createTag(Tag tag) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_TAG_NAME, tag.getTagName());
//        values.put(KEY_CREATED_AT, getDateTime());
//
//        // insert row
//        long tag_id = db.insert(TABLE_TAG, null, values);
//
//        return tag_id;
//    }

//    /**
//     * getting all tags
//     * */
//    public List<Tag> getAllTags() {
//        List<Tag> tags = new ArrayList<Tag>();
//        String selectQuery = "SELECT  * FROM " + TABLE_TAG;
//
//        Log.e(LOG, selectQuery);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (c.moveToFirst()) {
//            do {
//                Tag t = new Tag();
//                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
//                t.setTagName(c.getString(c.getColumnIndex(KEY_TAG_NAME)));
//
//                // adding to tags list
//                tags.add(t);
//            } while (c.moveToNext());
//        }
//        return tags;
//    }

//    /*
//     * Updating a tag
//     */
//    public int updateTag(Tag tag) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_TAG_NAME, tag.getTagName());
//
//        // updating row
//        return db.update(TABLE_TAG, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(tag.getId()) });
//    }

//    /*
//     * Deleting a tag
//     */
//    public void deleteTag(Tag tag, boolean should_delete_all_tag_todos) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        // before deleting tag
//        // check if todos under this tag should also be deleted
//        if (should_delete_all_tag_todos) {
//            // get all todos under this tag
//            List<Todo> allTagToDos = getAllToDosByTag(tag.getTagName());
//
//            // delete all todos
//            for (Todo todo : allTagToDos) {
//                // delete todo
//                deleteToDo(todo.getId());
//            }
//        }
//
//        // now delete the tag
//        db.delete(TABLE_TAG, KEY_ID + " = ?",
//                new String[] { String.valueOf(tag.getId()) });
//    }

    // ------------------------ "todo_tags" table methods ----------------//

//    /*
//     * Creating todo_tag
//     */
//    public long createTodoTag(long todo_id, long tag_id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_TODO_ID, todo_id);
//        values.put(KEY_TAG_ID, tag_id);
//        values.put(KEY_CREATED_AT, getDateTime());
//
//        long id = db.insert(TABLE_TODO_TAG, null, values);
//
//        return id;
//    }

//    /*
//     * Updating a todo tag
//     */
//    public int updateNoteTag(long id, long tag_id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_TAG_ID, tag_id);
//
//        // updating row
//        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
//                new String[] { String.valueOf(id) });
//    }

//    /*
//     * Deleting a todo tag
//     */
//    public void deleteToDoTag(long id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_TODO, KEY_ID + " = ?",
//                new String[] { String.valueOf(id) });
//    }

//    // closing database
//    public void closeDB() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        if (db != null && db.isOpen())
//            db.close();
//    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
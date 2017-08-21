package sql;

/**
 * Created by PC on 7/16/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import model.Products;
import model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_PRODUCTS = "products";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    private static final String COLUMN_PRODUCTS_ID = "products_id";
    private static final String COLUMN_PRODUCTS_CATEGORY = "products_category";
    private static final String COLUMN_PRODUCTS_NAME = "products_name";
    private static final String COLUMN_PRODUCTS_DESCRIPTION = "products_description";
    private static final String COLUMN_PRODUCTS_PRICE = "products_price";
    private static final String COLUMN_PRODUCTS_USED = "products_used";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

    private String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "("
            + COLUMN_PRODUCTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PRODUCTS_CATEGORY + " TEXT,"
            + COLUMN_PRODUCTS_NAME + " TEXT," + COLUMN_PRODUCTS_DESCRIPTION + " TEXT" + COLUMN_PRODUCTS_PRICE + "INTEGER" + COLUMN_PRODUCTS_USED + "TEXT" + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    private String DROP_PRODUCTS_TABLE = "DROP TABLE IF EXISTS " + TABLE_PRODUCTS;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PRODUCTS_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addProduct(Products products) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTS_CATEGORY, products.getCategory());
        values.put(COLUMN_PRODUCTS_NAME, products.getName());
        values.put(COLUMN_PRODUCTS_DESCRIPTION, products.getDescription());
        values.put(COLUMN_PRODUCTS_PRICE, products.getPrice());
        values.put(COLUMN_PRODUCTS_USED, products.getUsed());

        // Inserting Row
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_NAME + " ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }

    public List<Products> getAllProducts() {
        // array of columns to fetch
        String[] columnsProducts = {
                COLUMN_PRODUCTS_ID,
                COLUMN_PRODUCTS_CATEGORY,
                COLUMN_PRODUCTS_NAME,
                COLUMN_PRODUCTS_DESCRIPTION,
                COLUMN_PRODUCTS_PRICE,
                COLUMN_PRODUCTS_USED
        };
        // sorting orders
        String sortProducts =
                COLUMN_PRODUCTS_NAME + " ASC";
        List<Products> listProducts = new ArrayList<Products>();

        SQLiteDatabase db1 = this.getReadableDatabase();

        Cursor cursor = db1.query(TABLE_PRODUCTS, //Table to query
                columnsProducts,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortProducts); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Products products = new Products();
                products.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTS_ID))));
                products.setCategory(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTS_CATEGORY)));
                products.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTS_NAME)));
                products.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTS_DESCRIPTION)));
                products.setPrice(cursor.getColumnIndex(COLUMN_PRODUCTS_PRICE));
                products.setUsed(cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTS_USED)));
                // Adding user record to list
                listProducts.add(products);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db1.close();

        // return user list
        return listProducts;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void updateProduct(Products products) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTS_CATEGORY, products.getCategory());
        values.put(COLUMN_PRODUCTS_NAME, products.getName());
        values.put(COLUMN_PRODUCTS_DESCRIPTION, products.getDescription());
        values.put(COLUMN_PRODUCTS_PRICE, products.getPrice());
        values.put(COLUMN_PRODUCTS_USED, products.getUsed());

        // updating row
        db.update(TABLE_PRODUCTS, values, COLUMN_PRODUCTS_ID + " = ?",
                new String[]{String.valueOf(products.getId())});
        db.close();
    }

    /**
     * This method is to delete user record
     *
     * @param user
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public void deleteProducts(Products products) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_PRODUCTS, COLUMN_PRODUCTS_ID + " = ?",
                new String[]{String.valueOf(products.getId())});
        db.close();
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @return true/false
     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public String getUserName(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_NAME
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

        String name = null;
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return name;

    }

}

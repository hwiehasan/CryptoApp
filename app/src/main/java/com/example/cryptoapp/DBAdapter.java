package com.example.cryptoapp;

/*
پیاده سازی دیتابیس با استفاده از sqlite برای اینکه وقتی کاربر یک بار لاگین شد
و کالاها لود شدن در جایی از حافظه ذخیره شه تا بتونه سری بعد بصورت افلاین هم استفاده کنه
*/
//این اداپتر رو ساختم تا پایگاه داده رو برام بسازه
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {

    //ستون های جدول
    static final String KEY_KALA_ID = "id";
    static final String KEY_KALA_NAME = "name";
    static final String KEY_KALA_DATE = "date";
    static final String KEY_KALA_CATEGORY_ID = "catID";
    static final String KEY_KALA_IMAGE = "image";




    static final String TAG = "DBAdapter"; //برای لاگ گذاشتن
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE_KALA = "kala";
    static final int DATABASE_VERSION = 1;


    //کوئری ساخته جدول
    static final String DATABASE_CREATE_KALA =
            "create table kala (id integer primary key, "
                    + "name text not null, date text not null, catID integer, image text);";


    //کانتکست برای دسترسی به کانتکست صفحه اصلی
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    //تابع سازنده
    public DBAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //ایجاد جدول دیتابیس: اگه بار اوله که با دیتابیس کار میکنم نیازه که ساخته بشه از واسط گرافیکی دسترسی به ساخت دسترسی نداریم پس این تابع میاد اینکارو میکنه
        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                //ایجاد جدول کالا
                db.execSQL(DATABASE_CREATE_KALA);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //برای بروزرسانی جدول از این تابع اپگرید استفاده میشه
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");

            //جدول رو در صورت وجود حذف میکنه و بعدش با استفاده از انکریتیت جدول رو دوباره ایجاد میکنه
            db.execSQL("DROP TABLE IF EXISTS kala");
            onCreate(db);
        }
    }

    //---opens the database--- دیتابیس رو باز میکنیم برای خوندن اطلاعات و یا تو حالت ویرایشی قرارش بدیم
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---
    public void close() {
        DBHelper.close();
    }








    //---insert a product into the database--- روش اول
    public long insertKala(int id , String name , String date , String image , int catID) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_KALA_ID, id);
        initialValues.put(KEY_KALA_NAME, name);
        initialValues.put(KEY_KALA_DATE, date);
        initialValues.put(KEY_KALA_IMAGE, image);
        initialValues.put(KEY_KALA_CATEGORY_ID, catID);
        return db.insert(DATABASE_TABLE_KALA, null, initialValues);
    }

    // روش دوم با استفاده از کلس کالا
    public long insertKala2(KalaClass kala) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_KALA_ID, kala.getkID());
        initialValues.put(KEY_KALA_NAME, kala.getkName());
        initialValues.put(KEY_KALA_DATE, kala.getSubmitDate());
        initialValues.put(KEY_KALA_IMAGE, kala.getImage());
        initialValues.put(KEY_KALA_CATEGORY_ID, kala.getCatID());
        return db.insert(DATABASE_TABLE_KALA, null, initialValues);
    }






    //---deletes a particular product---
    public boolean deleteKala(int kalaID) {

        //این تابع ترو یا فالس برمیگردونه که بدونم حذف کرده یا نه
        return db.delete(DATABASE_TABLE_KALA, KEY_KALA_ID + "=" + kalaID, null) > 0;
    }


    //---retrieves(get) all the product---
    public Cursor getAllKala() {

        return db.query(DATABASE_TABLE_KALA, new String[]{KEY_KALA_ID, KEY_KALA_NAME, KEY_KALA_IMAGE,
                KEY_KALA_DATE, KEY_KALA_CATEGORY_ID }, null, null, null, null, null);
    }


    //---retrieves a particular product---
    public Cursor getKala(int kalaID) throws SQLException {

        Cursor mCursor =
                db.query(true, DATABASE_TABLE_KALA, new String[]{KEY_KALA_ID, KEY_KALA_NAME, KEY_KALA_IMAGE,
                                KEY_KALA_DATE, KEY_KALA_CATEGORY_ID}, KEY_KALA_ID + "=" + kalaID, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst(); //اولین رکورد
        }
        return mCursor;
    }

    //---updates a product---
    public boolean updateKala(int kalaID, String name, String image, String catID) {
        ContentValues args = new ContentValues();
        args.put(KEY_KALA_NAME, name);
        args.put(KEY_KALA_IMAGE, image);
        args.put(KEY_KALA_CATEGORY_ID, catID);

        return db.update(DATABASE_TABLE_KALA, args, KEY_KALA_ID + "=" + kalaID, null) > 0;
    }


}

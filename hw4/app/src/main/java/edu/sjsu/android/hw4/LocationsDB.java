package edu.sjsu.android.hw4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocationsDB extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "LocationMarkers";
    static final String TABLE_NAME = "locations";
    static final String _ID = "_id";
    static final String LATITUDE = "latitude";
    static final String LONGITUDE = "longitude";
    static final String ZOOM = "zoom";
    static final int DATABASE_VERSION = 1;

    static final String CREATE_DB_TABLE =
            " CREATE TABLE " + TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " latitude DOUBLE NOT NULL, " +
                    " longitude DOUBLE NOT NULL, " +
                    " zoom INTEGER NOT NULL);";
    private SQLiteDatabase db;

    public LocationsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public long insertLocation(ContentValues values) {

        long rowID = db.insert(TABLE_NAME, "", values);
        return rowID;

    }


    public int deleteAllLocations() {
        int count = db.delete(TABLE_NAME, null, null);
        return count;
    }


    public Cursor returnAllLocations() {

        Cursor c = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        return c;
    }
}
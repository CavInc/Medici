package ru.cav.medici.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1 ;
    public static final String DATABASE_NAME = "pc_medici.db3";

    public static final String HEAD_CHAIN="head_chain";
    public static final String SPEC_CHAIN="spec_chain";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table head_chain (" +
                "_id integer not null primary key AUTOINCREMENT," +
                "title text," +
                "description text);";

        db.execSQL(sql);
        sql="create table spec_chain (" +
                "_id integer not null," +
                "position_id not null," +
                "chain_txt text," +
                "chain_time integer default 0,primary key(_id,position_id)," +
                "FOREIGN KEY (_id) REFERENCES head_chain (_id) ON DELETE CASCADE)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

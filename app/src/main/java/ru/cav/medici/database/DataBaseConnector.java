package ru.cav.medici.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


public class DataBaseConnector {
    private SQLiteDatabase database;
    private DBHelper mDBHelper;

    public DataBaseConnector(Context context){
        mDBHelper = new DBHelper(context,DBHelper.DATABASE_NAME,null,DBHelper.DATABASE_VERSION);
    }

    public void open(){
        database = mDBHelper.getWritableDatabase();
    }
    public  void close(){
        if (database!=null) {
            database.close();
        }
    }

    public Cursor getAllChainHead(){
        Cursor cursor = database.query("head_chain",new String[]{"_id","title","description"},null,null,null,null,null);
        return cursor;
    }

    // возвращает 1 запись
    public void getOneChain(int id){

    }


}

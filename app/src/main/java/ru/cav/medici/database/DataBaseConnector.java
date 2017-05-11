package ru.cav.medici.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import ru.cav.medici.models.HeadChainModel;


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
    public HeadChainModel getOneChain(int id){
        Cursor cursor = database.query("head_chain",
                null,"_id=?",new String[]{String.valueOf(id)},null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()!=0) {
            return new HeadChainModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        return null;

    }


}

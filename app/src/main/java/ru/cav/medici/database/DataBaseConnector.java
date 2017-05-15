package ru.cav.medici.database;

import android.content.ContentValues;
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
        Cursor cursor = database.query(DBHelper.HEAD_CHAIN,new String[]{"_id","title","description"},null,null,null,null,null);
        return cursor;
    }

    // возвращает данные цепочки
    public Cursor getAllChain(int rec_id){
        Cursor cursor = database.query(DBHelper.SPEC_CHAIN,new String[]{"position_id","chain_txt","chain_desc","chain_time"},"_id="+rec_id,null,null,null,"position_id");
        return cursor;
    }

    // возвращает 1 запись
    public HeadChainModel getOneChain(int id){
        Cursor cursor = database.query(DBHelper.HEAD_CHAIN,
                null,"_id=?",new String[]{String.valueOf(id)},null,null,null);
        cursor.moveToFirst();
        if (cursor.getCount()!=0) {
            return new HeadChainModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        return null;

    }

    // добавили запись (с доп данными)
    public void insertChain(HeadChainModel model){
        ContentValues newValues = new ContentValues();
        newValues.put("title",model.getTitle());
        newValues.put("description",model.getDescription());
        if (database==null) this.open();
        database.insert(DBHelper.HEAD_CHAIN, null, newValues);
        Cursor cursor=database.rawQuery("select _id from "+DBHelper.HEAD_CHAIN+" where rowid=last_insert_rowid()",null);
        cursor.moveToFirst();
        int new_id = cursor.getInt(0);

        ContentValues chainValues = new ContentValues();
        if (model.getSpec_shain()!=null){
            for (int i=0;i<model.getSpec_shain().size();i++){
                chainValues.put("_id",new_id);
                chainValues.put("position_id",i);
                chainValues.put("chain_txt",model.getSpec_shain().get(i).getChainItem());
                database.insert(DBHelper.SPEC_CHAIN,null,chainValues);
            }

        }
        this.close();
    }

    // обновили запись
    public void updateChain(HeadChainModel model){
        ContentValues updValue = new ContentValues();
        updValue.put("title",model.getTitle());
        updValue.put("description",model.getDescription());
        database.update(DBHelper.HEAD_CHAIN,updValue,"_id="+model.getId(),null);
    }

    // удалить запись
    public void deleteChain(int id){
        database.delete(DBHelper.HEAD_CHAIN,"_id="+id,null);

    }

}

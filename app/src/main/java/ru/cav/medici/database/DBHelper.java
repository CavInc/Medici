package ru.cav.medici.database;


import android.content.ContentValues;
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
                "description text," +
                "action_flg integer default 0);";

        db.execSQL(sql);
        sql="create table spec_chain (" +
                "_id integer not null," +
                "position_id not null," +
                "chain_txt text," +
                "chain_desc text,"+
                "action_flg integer default 0,"+
                "chain_time integer default 0,primary key(_id,position_id)," +
                "FOREIGN KEY (_id) REFERENCES head_chain (_id) ON DELETE CASCADE)";
        db.execSQL(sql);

        generateData(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void generateData(SQLiteDatabase db){
        String sql="insert into head_chain (title) values('Выигрыш в лотерею');";
        db.execSQL(sql);
        String chain = "Y6 B4 B5 R6 Y4 G9 G6 Y2 B9 R4 Y8 B8 B1 G4 R5 B3 G3 R8 R7 G2 G8 Y7 B2 " +
                "Y9> R2> Y5> Y3 B6 G5 B7> R9 G7> Y1 R1 G1> R3>";

        setSpecChain(db,1,chain);
        sql = "insert into head_chain(title) values('Осознаное сновидение');";
        db.execSQL(sql);
        chain = "Y6 B4 B5 R6 Y4 G9 G6 Y2 B9 R4 Y8 B8 B1 G4 R5 B3 G3 R8 R7 G2 G8 Y7 B2 " +
                "Y9> R2> Y5> Y3 B6 G5 B7> R9 G7> Y1 R1 G1> R3>";

        setSpecChain(db,2,chain);
        sql = "insert into head_chain(title) values('Остановка ВД');";
        db.execSQL(sql);
        chain = "Y6 B4 B5 R6 Y4 G9 G6 Y2 B9 R4 Y8 B8 B1 G4 R5 B3 G3 R8 R7 G2 G8 Y7 B2 " +
                "Y9> R2> Y5> Y3 B6 G5 B7> R9 G7> Y1 R1 G1> R3>";

        setSpecChain(db,3,chain);
    }

    private void setSpecChain(SQLiteDatabase db,int id,String chain){
        ContentValues newValues = new ContentValues();
        String item[]=chain.split(" ");
        for (int i=0;i<item.length;i++){
            newValues.put("_id",id);
            newValues.put("position_id",i);
            newValues.put("chain_txt",item[i]);
            db.insert(SPEC_CHAIN,null,newValues);
        }

    }
}

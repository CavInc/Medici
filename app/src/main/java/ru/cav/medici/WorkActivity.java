package ru.cav.medici;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.cav.medici.database.DataBaseConnector;

public class WorkActivity extends AppCompatActivity {

    private  int rec_id;
    private DataBaseConnector mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        rec_id = getIntent().getIntExtra("CHAIN_ID",-1);

        mDb = new DataBaseConnector(this);

    }
}

package ru.cav.medici;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import ru.cav.medici.database.DataBaseConnector;
import ru.cav.medici.models.HeadChainModel;

public class WorkActivity extends AppCompatActivity {

    private static final String TAG = "WORK AC";
    private  int rec_id;
    private DataBaseConnector mDb;

    private TextView mDescription;
    private TextView mDescriptionWorkItem;
    private Button mActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        mDescription = (TextView) findViewById(R.id.description_item);
        mDescriptionWorkItem = (TextView) findViewById(R.id.description_work_item);
        mActionButton = (Button) findViewById(R.id.active_button);

        rec_id = getIntent().getIntExtra("CHAIN_ID",-1);

        mDb = new DataBaseConnector(this);

        mDb.open();
        HeadChainModel model = mDb.getOneChain(rec_id);
        mDb.close();
        Log.d(TAG, String.valueOf(model.getId()));

        setTaskBar();
    }

    private void setTaskBar(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

}

package ru.cav.medici;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;

import ru.cav.medici.adapters.ChainAdapter;
import ru.cav.medici.database.DataBaseConnector;
import ru.cav.medici.misc.HorizontalListView;
import ru.cav.medici.models.HeadChainModel;
import ru.cav.medici.models.SpecChainModel;

public class WorkActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "WORK AC";
    private  int rec_id;
    private DataBaseConnector mDb;

    private TextView mDescription;
    private TextView mDescriptionWorkItem;
    private Button mActionButton;

    private ImageButton mEditDescChainItem;

    private HorizontalListView mListView;


    private int id;
    private ChainAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        mDescription = (TextView) findViewById(R.id.description_item);
        mDescriptionWorkItem = (TextView) findViewById(R.id.description_work_item);
        mActionButton = (Button) findViewById(R.id.active_button);
        mActionButton.setOnClickListener(this);

        mEditDescChainItem = (ImageButton) findViewById(R.id.work_chain_edit);
        mEditDescChainItem.setOnClickListener(this);

        mListView = (HorizontalListView) findViewById(R.id.work_list);

        rec_id = getIntent().getIntExtra(ConstantManager.CHAIN_ID,-1);

        mDb = new DataBaseConnector(this);

        mDb.open();
        HeadChainModel model = mDb.getOneChain(rec_id);
        mDb.close();
        if (null != model) {
            Log.d(TAG, String.valueOf(model.getId()));
            id=model.getId();
            mDescription.setText(model.getTitle());
           // mDescriptionWorkItem.setText(model.getDescription());

            ArrayList<SpecChainModel> record = getListData(id);
            mAdapter = new ChainAdapter(this,R.layout.item_work_horisontal,record);
            mAdapter.setNotifyOnChange(true);
            mListView.setAdapter(mAdapter);
        }

        setTaskBar();
    }

    private ArrayList<SpecChainModel> getListData(int id) {
        ArrayList<SpecChainModel> record = new ArrayList<>();
        mDb.open();
        Cursor cursor = mDb.getAllChain(rec_id);
        while (cursor.moveToNext()){
            //TODO Исправить на правильное время
            record.add(new SpecChainModel(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
        }
        return record;
    }

    private void setTaskBar(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_work_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_chain:
                Intent intent = new Intent(WorkActivity.this,ChangeActivity.class);
                intent.putExtra(ConstantManager.CHANGE_FLG,ConstantManager.CHANGE_EDIT);
                intent.putExtra(ConstantManager.REC_TITLE,mDescription.getText());
                //intent.putExtra(ConstantManager.REC_DESC,mDescriptionWorkItem.getText());

                startActivityForResult(intent,ConstantManager.CHANGE_CHAIN);
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ConstantManager.CHANGE_CHAIN:
                System.out.println(data!=null);
                if (resultCode == RESULT_OK && data !=null){
                    Log.d(TAG,data.getStringExtra(ConstantManager.REC_TITLE));
                    HeadChainModel lrec = new HeadChainModel(id,
                            data.getStringExtra(ConstantManager.REC_TITLE)
                            ,data.getStringExtra(ConstantManager.REC_DESC));
                    mDb.open();
                    mDb.updateChain(lrec);
                    mDb.close();

                }
                break;
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.work_chain_edit:
                Intent intent = new Intent(WorkActivity.this,ChangeActivity.class);
                intent.putExtra(ConstantManager.CHANGE_FLG,ConstantManager.CHANGE_DESC_CHAIN);
                startActivity(intent);
                break;
        }


    }

}

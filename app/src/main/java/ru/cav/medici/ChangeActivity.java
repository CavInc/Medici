package ru.cav.medici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class ChangeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mTitle;
    private EditText mDesc;
    private Button mDelete;
    private Button mSave;

    private int change_flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        mTitle = (EditText) findViewById(R.id.edit_title);
        mDesc = (EditText) findViewById(R.id.edit_desct);

        String title = getIntent().getStringExtra(ConstantManager.REC_TITLE);
        if (title!=null) {
            mTitle.setText(getIntent().getStringExtra(ConstantManager.REC_TITLE));
        }
        String desc = getIntent().getStringExtra(ConstantManager.REC_DESC);
        if (desc!=null) {
            mDesc.setText(getIntent().getStringExtra(ConstantManager.REC_DESC));
        }
        change_flag = getIntent().getIntExtra(ConstantManager.CHANGE_FLG,-1);


        mDelete = (Button) findViewById(R.id.del_button);
        mDelete.setOnClickListener(this);

        mSave = (Button) findViewById(R.id.save_button);
        mSave.setOnClickListener(this);

        switch (change_flag){
            case ConstantManager.CHANGE_INSERT:
                mDelete.setVisibility(View.GONE);
                break;
            case ConstantManager.CHANGE_DESC_CHAIN:
                mTitle.setVisibility(View.GONE);
                mDelete.setVisibility(View.GONE);
                break;
        }

        setTaskBar();
    }

    private void setTaskBar(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save_button:
                // сохраниили и отдали данные
                Intent answerIntent = new Intent();
                answerIntent.putExtra(ConstantManager.REC_TITLE,mTitle.getText().toString());
                answerIntent.putExtra(ConstantManager.REC_DESC,mDesc.getText().toString());
                answerIntent.putExtra(ConstantManager.REC_DELETE_FLG,false);
                setResult(RESULT_OK, answerIntent);
                finish();
                break;
            case R.id.del_button:
                answerIntent = new Intent();
                answerIntent.putExtra(ConstantManager.REC_DELETE_FLG,true);
                setResult(RESULT_OK, answerIntent);
                finish();
                break;
        }

    }
}

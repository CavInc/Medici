package ru.cav.medici;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class ChangeActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mDesc;
    private Button mDelete;
    private Button mSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        mTitle = (EditText) findViewById(R.id.edit_title);
        mDesc = (EditText) findViewById(R.id.edit_desct);


        mDelete = (Button) findViewById(R.id.del_button);

        mSave = (Button) findViewById(R.id.save_button);

        setTaskBar();
    }

    private void setTaskBar(){
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){

        }

    }
}

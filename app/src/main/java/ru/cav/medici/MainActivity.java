package ru.cav.medici;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import ru.cav.medici.adapters.DataAdapter;
import ru.cav.medici.models.HeadChainModel;

public class MainActivity extends AppCompatActivity {

    private Button mAddButton;
    private ListView mListView;

    private DataAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddButton = (Button) findViewById(R.id.add_button);
        mListView = (ListView) findViewById(R.id.listView);

        mAddButton.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddNewRecordActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<HeadChainModel> record = new ArrayList<>();
        // debug
        record.add(new HeadChainModel(1,"Пример 1","Всяка лажа"));
        record.add(new HeadChainModel(2,"Пример 2","Всяка лажа 2"));
        record.add(new HeadChainModel(3,"Пример 4","Всяка лажа 3"));

        mAdapter = new DataAdapter(this,R.layout.main_items,record);
        mAdapter.setNotifyOnChange(true);
        mListView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main_activity,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnButtonItemClick(View v){

        Toast.makeText(this, "Зачем вы нажали?", Toast.LENGTH_SHORT).show();
    }

}

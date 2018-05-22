package com.tperraut.apply_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements BaseAdapter.Listener{
    private RecyclerView mRecyclerView;
    private BaseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mRecyclerView = findViewById(R.id.item_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new BaseAdapter(this);
        mAdapter.setDataSet(initDataSet());
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Model> initDataSet() {
        ArrayList<Model> dataSet = new ArrayList<>();
        dataSet.add(new Model(R.string.item1_title, R.string.item1_description, R.drawable.geronimo));
        dataSet.add(new Model(R.string.item_title, R.string.item_description, R.drawable.geronimo_logo));
        dataSet.add(new Model(R.string.item1_title, R.string.item1_description, R.drawable.geronimo));
        return dataSet;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item_bt:
                mAdapter.addItem(R.string.item_title, R.string.item_description, R.drawable.geronimo);
                mRecyclerView.smoothScrollToPosition(0);
                return true;

            case R.id.remove_item_bt:
                mAdapter.removeItem();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onShareRequested(Model m) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, m.getTitleRes());
        startActivity(
                Intent.createChooser(sharingIntent, getResources().getString(R.string.share_text))
        );
    }

    @Override
    public void onDetailsRequested(Model m) {
        Toast.makeText(
                getApplicationContext(),
                m.getTitleRes(),
                Toast.LENGTH_SHORT
        ).show();
    }

}

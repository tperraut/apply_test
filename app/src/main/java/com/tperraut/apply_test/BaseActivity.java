package com.tperraut.apply_test;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity implements BaseAdapter.Listener{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mDescritpions = new ArrayList<>();
    private ArrayList<Drawable> mImages = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //TODO remove
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mRecyclerView = findViewById(R.id.item_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        initDataSet();
        mAdapter = new BaseAdapter(this, mTitles, mDescritpions, mImages);
        mRecyclerView.setAdapter(mAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //TODO remove
    private void initDataSet() {
        for (int i = 1; i <= 10; i ++) {
            mTitles.add("" + i);
            mDescritpions.add("" + i);
            mImages.add(getDrawable(R.drawable.geronimo_logo));
        }
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
                //add item
                return true;

            case R.id.remove_item_bt:
                //remove item
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        System.out.println(view);
    }
}

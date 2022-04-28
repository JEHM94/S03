package com.example.seccion_03_recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ReceiverCallNotAllowedException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = this.getAllNames();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //Linear Layout
        mLayoutManager = new LinearLayoutManager(this);
        //Grid Layout
        mLayoutManager = new GridLayoutManager(this, 2);
        mAdapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {
                //Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                deleteName(position);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_name:
                this.addName(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addName(int position) {
        names.add(position, "New Name " + (++counter));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteName(int position) {

            names.remove(position);
            mAdapter.notifyItemRemoved(position);



    }


    private List<String> getAllNames() {
        return new ArrayList<String>() {{
            add("Alejandro");
            add("José");
            add("Barrera");
            add("Rubén");
            add("Antonio");
        }};
    }
}
package com.example.seccion03_cardview.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.seccion03_cardview.models.Movie;
import com.example.seccion03_cardview.adapters.MyAdapter;
import com.example.seccion03_cardview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = this.getAllMovies();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //Linear Layout
        mLayoutManager = new LinearLayoutManager(this);
        //Grid Layout
        mLayoutManager = new GridLayoutManager(this, 2);
        mAdapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {
                // Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_LONG).show();
                removeMovie(position);
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
                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New Movie: " + (++counter), R.drawable.pollito_loe) );
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void removeMovie(int position) {

        movies.remove(position);
        mAdapter.notifyItemRemoved(position);


    }


    private List<Movie> getAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Ben Hur", R.drawable.shot_1));
            add(new Movie("DeadPool", R.drawable.shot_2));
            add(new Movie("GOTG", R.drawable.shot_3));
            add(new Movie("Warcraft", R.drawable.shot_4));
        }};
    }
}
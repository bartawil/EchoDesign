package com.example.echodesign;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

//    List<String> list;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
//
//        list = new ArrayList<>();
//        list.add("Foo");
//        list.add("Bar");
//        list.add("Baz");
//
//        ListView lvItems = findViewById(R.id.lvItems);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
//                android.R.layout.simple_list_item_1,
//                list);
//
//        lvItems.setAdapter(adapter);
//
//        Log.i("MainActivity2", "onCreate");
//    }

    private AppDB db;
    private PostDao postDao;
    private List<Post> posts;
    private ArrayAdapter<Post> adapter;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.e("On Config Change","LANSPACE");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB")
                .allowMainThreadQueries()
                .build();

        postDao = db.postDao();

        FloatingActionButton btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> {
            Intent i = new Intent(this, FormActivity.class);
            startActivity(i);
        });

        posts = new ArrayList<>();

        ListView lvPosts = findViewById(R.id.lvPosts);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, posts);

        lvPosts.setAdapter(adapter);

        lvPosts.setOnItemLongClickListener((adapterView, view, i, l) -> {
            Post post = posts.remove(i);
            postDao.delete(post);
            adapter.notifyDataSetChanged();
            return true;
        });

        lvPosts.setOnItemClickListener((adapterView, view, i ,l) -> {
            Intent intent = new Intent(this, FormActivity.class);
            intent.putExtra("id", posts.get(i).getId());
            startActivity(intent);

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity2", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity2", "onResume");
        posts.clear();
        posts.addAll(postDao.index());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity2", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity2", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity2", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity2", "onRestart");
    }
}

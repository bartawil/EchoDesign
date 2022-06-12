package com.example.echodesign;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class FormActivity extends AppCompatActivity {

    private AppDB db;
    private PostDao postDao;
    private EditText etItem;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "PostsDB")
                .allowMainThreadQueries()
                .build();

        postDao = db.postDao();

        etItem = findViewById(R.id.etItem);

        if(getIntent().getExtras() != null) {
            int id = getIntent().getExtras().getInt("id");
            post = postDao.get(id);
            etItem.setText(post.getContent());
        }
        
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
            if(post != null) {
                post.setContent(etItem.getText().toString());
                postDao.update(post);
            } else {
                post = new Post(0, etItem.getText().toString());
                postDao.insert(post);
            }
            finish();
        });
    }
}
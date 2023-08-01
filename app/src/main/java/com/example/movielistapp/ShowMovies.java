package com.example.movielistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowMovies extends AppCompatActivity {

    ListView lv;
    ArrayList<Movies> al;
    Button btnPG13;
    CustomAdapter ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        lv = findViewById(R.id.lv);
        btnPG13 = findViewById(R.id.btnPG13);

        al = new ArrayList<Movies>();
        ca = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(ca);

        DBHelper dbh = new DBHelper(ShowMovies.this);
        al.clear();
        al.addAll(dbh.getMovies());
        ca.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movies target = al.get(position);

                Intent intent = new Intent(ShowMovies.this, ModifyMovie.class);
                intent.putExtra("data", target);
                startActivity(intent);
            }
        });

        btnPG13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(ShowMovies.this);
                al.clear();
                al.addAll(dbh.getPG13());
                ca.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(ShowMovies.this);
        al.clear();
        al.addAll(dbh.getMovies());
        ca.notifyDataSetChanged();
    }
}
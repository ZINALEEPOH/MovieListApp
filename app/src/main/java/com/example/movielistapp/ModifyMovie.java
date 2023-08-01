package com.example.movielistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ModifyMovie extends AppCompatActivity {

    TextView tvTitle, tvGenre, tvYear, tvRating, tvId;
    EditText etTitle, etGenre, etYear, etId;
    Spinner spinner;
    Button btnUpdate, btnDelete, btnCancel;
    Movies data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);

        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);
        tvId = findViewById(R.id.tvId);

        etId = findViewById(R.id.etId);
        etId.setEnabled(false);
        etTitle = findViewById(R.id.etTitle);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        spinner= findViewById(R.id.spinner);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        etId.setFocusable(false);
        etId.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));

//        int pos=spinner.getSelectedItemPosition();
//        if(pos==0){
//            spinner.setSelection(0);
//        } else if (pos==1) {
//            spinner.setSelection(1);
//        }else if (pos==2) {
//            spinner.setSelection(2);
//        }else if (pos==3) {
//            spinner.setSelection(3);
//        }else if (pos==4) {
//            spinner.setSelection(4);
//        }else if (pos==5) {
//            spinner.setSelection(5);
//        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        int ratingIndx = adapter.getPosition(data.getRating());
        spinner.setSelection(ratingIndx);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyMovie.this);
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

                data.setRating(spinner.getSelectedItem().toString());
                dbh.updateMovie(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyMovie.this);
                dbh.deleteMovie(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
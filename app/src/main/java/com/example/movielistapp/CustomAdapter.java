package com.example.movielistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<Movies> {

    Context context;
    ArrayList<Movies> al;
    int resource;

    public CustomAdapter(Context context, int resource, ArrayList<Movies> al) {
        super(context, resource, al);
        this.context = context;
        this.resource = resource;
        this.al = al;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        TextView tvGenre = rowView.findViewById(R.id.tvGenre);
        ImageView ivRating=rowView.findViewById(R.id.ivRating);

        Movies current = al.get(position);

        tvTitle.setText(current.getTitle());
        tvYear.setText(String.valueOf(current.getYear()));
        tvGenre.setText(current.getGenre());
        if (current.getRating().equals("G")) {
            ivRating.setImageResource(R.drawable.rating_g);
        } else if (current.getRating().equals("PG")) {
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if(current.getRating().equals("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if (current.getRating().equals("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if (current.getRating().equals("M18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else if (current.getRating().equals("R21")) {
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}


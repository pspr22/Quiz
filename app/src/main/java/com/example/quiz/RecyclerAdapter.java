package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CardViewHolder> {

    private ArrayList<modelClass> ModelList;
    private Context context;

    public RecyclerAdapter(ArrayList<modelClass> modelList, Context context) {
        this.ModelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new CardViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, @SuppressLint("RecyclerView") int position) {

        modelClass model = ModelList.get(position);
        holder.namei.setText(model.getCategoryName());
        holder.imagei.setImageResource(context.getResources()
        .getIdentifier(model.getImageName(),"drawable", context.getPackageName()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(position == 0){
                    Intent i = new Intent(context,History.class);
                    context.startActivity(i);
                }
                if(position == 1){
                    Intent i = new Intent(context,Sports.class);
                    context.startActivity(i);
                }
                if(position == 2){
                    Intent i = new Intent(context,Films.class);
                    context.startActivity(i);
                }
                if(position == 3){
                    Intent i = new Intent(context,Olympics.class);
                    context.startActivity(i);
                }
                if(position == 4){
                    Intent i = new Intent(context,Inventors.class);
                    context.startActivity(i);
                }
                if(position == 5){
                    Intent i = new Intent(context,Countries.class);
                    context.startActivity(i);
                }
                if(position == 6){
                    Intent i = new Intent(context,Gods.class);
                    context.startActivity(i);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{


        ImageView imagei;
        TextView namei;
        CardView cardView;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            imagei = itemView.findViewById(R.id.imagei);
            namei = itemView.findViewById(R.id.namei);
            cardView = itemView.findViewById(R.id.cardView);

        }
    }

}

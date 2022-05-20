package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    interface OnCountryClickListener{
        void onCountryClick(Country state, int position);
    }
    interface OnDeleteClickListener{
        void onDeleteClick(Country state, int position);
    }

    private final OnCountryClickListener onClickListener;
    private final OnDeleteClickListener onDeleteClickListener;
    private final LayoutInflater inflater;
    private List<Country> states;

    StateAdapter(Context context, List<Country> states, OnCountryClickListener onClick,
                 OnDeleteClickListener onDelete) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener=onClick;
        this.onDeleteClickListener=onDelete;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }



    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView classView, distanceView, timeView, dateView, ave500mView, tempView;

        ViewHolder(View view) {
            super(view);
            classView=view.findViewById(R.id.classN);
            distanceView= view.findViewById(R.id.distance);
            timeView = view.findViewById(R.id.time);
            dateView = view.findViewById(R.id.date);
            ave500mView = view.findViewById(R.id.ave500m);
            tempView = view.findViewById(R.id.temp);
        }
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Country state = states.get(position);
        holder.classView.setText(state.getClas());
        holder.distanceView.setText(state.getDistance());
        holder.timeView.setText(state.getTime());
        holder.dateView.setText(state.getDate());
        holder.ave500mView.setText(state.getAve500m());
        holder.tempView.setText(state.getTemp());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onCountryClick(state,position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onDeleteClickListener.onDeleteClick(state,position);
                return true;
            }
        });
    }

}
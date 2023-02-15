package com.example.mybanjir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class sosAdapter extends RecyclerView.Adapter<sosAdapter.MyViewHolder> {

    Context context;

    ArrayList<sos> list;

    public sosAdapter(Context context, ArrayList<sos> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.layout_listsos_item,parent,false);
       return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        sos sos = list.get(position);
        holder.txtfvName.setText(sos.getFvName());
        holder.spinHelp.setText(sos.getTypeSOS());
        holder.spinStatus.setText(sos.getSosStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtfvName, spinHelp, spinStatus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtfvName = itemView.findViewById(R.id.txtfvName);
            spinHelp = itemView.findViewById(R.id.spinHelp);
            spinStatus = itemView.findViewById(R.id.spinStatus);
        }
    }
}

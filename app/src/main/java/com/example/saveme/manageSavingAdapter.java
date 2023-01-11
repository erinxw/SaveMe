package com.example.saveme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ConcurrentModificationException;
import java.util.List;

public class manageSavingAdapter extends RecyclerView.Adapter<manageSavingAdapter.ViewHolder> {

    private Context context;
    private List<Data> targeted;

    public manageSavingAdapter(Context context, List<Data> targeted) {
        this.context = context;
        this.targeted = targeted;
    }

    @NonNull
    @Override
    public manageSavingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.added_target,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull manageSavingAdapter.ViewHolder holder, int position) {
//        letak sini current target saving...codenya disini
//        total saving darab percentage pastu setkan amountnya berapa

        holder.amount.setText(targeted.get(position).getAmountTostring(targeted.get(position).getAmount()));
        holder.nama.setText(targeted.get(position).getNotes());


    }

    @Override
    public int getItemCount() {
        return targeted.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama,amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.nama_target);
            amount = itemView.findViewById(R.id.jTarget);
        }
    }
}

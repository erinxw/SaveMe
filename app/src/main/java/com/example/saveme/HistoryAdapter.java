package com.example.saveme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context mContext;
    private List<Data> spendingData;

    public HistoryAdapter(Context mContext, List<Data> spendingData) {
        this.mContext = mContext;
        this.spendingData = spendingData;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.history_item,parent,false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

         Data data = spendingData.get(position);



        holder.date.setText(data.getDate());
        holder.detail.setText(data.getNotes());
        holder.category.setText(data.getCategory());
        holder.amount.setText( data.getAmountTostring(data.getAmount()));


    }

    @Override
    public int getItemCount() {
        return spendingData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView date,detail,category,amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.amount_tv);
            date = itemView.findViewById(R.id.date_tv);
            detail = itemView.findViewById(R.id.detail_tv);
            category = itemView.findViewById(R.id.category_tv);
        }
    }
}

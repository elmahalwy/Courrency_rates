package com.example.elkholy.courrency_rates.adpaters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.elkholy.courrency_rates.MainActivity;
import com.example.elkholy.courrency_rates.R;

import java.util.ArrayList;

/**
 * Created by elkholy on 05/03/2017.
 */

public class inner_rv_adpater extends RecyclerView.Adapter<inner_rv_adpater.ViewHolder> {
    ArrayList<String> bank_name = new ArrayList<String>();
    ArrayList<Double> list_buy = new ArrayList<Double>();
    ArrayList<Double> list_sell = new ArrayList<Double>();
    public Context mContext;

    public inner_rv_adpater() {
    }

    public inner_rv_adpater(Context mContext, ArrayList<String> bank_name, ArrayList<Double> list_buy, ArrayList<Double> list_sell) {
        this.bank_name = bank_name;
        this.list_buy = list_buy;
        this.list_sell = list_sell;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_rv, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv_bank_name.setText(bank_name.get(position).toString());
        holder.tv_sell_num.setText(list_sell.get(position).toString());
        holder.tv_buy_num.setText(list_buy.get(position).toString());


    }

    @Override
    public int getItemCount() {
        return bank_name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_bank_name;
        TextView tv_sell_num;
        TextView tv_buy_num;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_bank_name = (TextView) itemView.findViewById(R.id.tv_bank_name);
            tv_buy_num = (TextView) itemView.findViewById(R.id.tv_buy_num);
            tv_sell_num = (TextView) itemView.findViewById(R.id.tv_sell_num);


        }
    }
}

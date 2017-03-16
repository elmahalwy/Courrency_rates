package com.example.elkholy.courrency_rates.adpaters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.elkholy.courrency_rates.R;
import com.example.elkholy.courrency_rates.request_queue_singletone;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by elkholy on 05/03/2017.
 */

public class card_adapter extends RecyclerView.Adapter<card_adapter.ViewHolder> {
    String Url = "https://api.curates.club/";
    Context mContext;
    ArrayList<String> courrency = new ArrayList<String>();
    ArrayList<String> bank_name = new ArrayList<String>();
    public static ArrayList<Double> list_sell = new ArrayList<Double>();
    public static ArrayList<Double> list_sell_eur = new ArrayList<Double>();
    public static ArrayList<Double> list_buy_eur = new ArrayList<Double>();
    public static ArrayList<Double> list_sell_usd = new ArrayList<Double>();
    public static ArrayList<Double> list_buy_usd = new ArrayList<Double>();
    public static ArrayList<Double> list_sell_gbp = new ArrayList<Double>();
    public static ArrayList<Double> list_buy_gbp = new ArrayList<Double>();
    public static ArrayList<Double> list_sell_sar = new ArrayList<Double>();
    public static ArrayList<Double> list_buy_sar = new ArrayList<Double>();
    public static ArrayList<Double> list_buy = new ArrayList<Double>();

public static inner_rv_adpater inner_rv_adpater;
    public card_adapter(ArrayList<String> courrency) {
        this.courrency = courrency;
    }

    @Override
    public card_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final card_adapter.ViewHolder holder, final int position) {
        holder.tv_courrency.setText(courrency.get(position).toString());
        list_sell.add(Double.valueOf("12"));
        list_sell.add(Double.valueOf("46"));
        list_sell.add(Double.valueOf("79"));
        list_sell.add(Double.valueOf("90"));

        list_buy.add(Double.valueOf("12"));
        list_buy.add(Double.valueOf("46"));
        list_buy.add(Double.valueOf("79"));
        list_buy.add(Double.valueOf("90"));
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            Iterator<String> keyes = jsonObject.keys();
                            while (keyes.hasNext()) {
                                String key = keyes.next();
                                if (!bank_name.contains(key)) {

                                    bank_name.add(key);

                                }
                                bank_name.remove("gold");
                                JSONObject jsonObject1 = (JSONObject) jsonObject.get(key);
                                JSONObject jsonObject_Courrency_rates = (JSONObject) jsonObject1.get("currency_rate");


                                Iterator<String> keys_courrency = jsonObject_Courrency_rates.keys();
                                while (keys_courrency.hasNext()) {
                                    String key_courrency = keys_courrency.next();
                                    JSONObject jsonObject_name_of_currency = (JSONObject) jsonObject_Courrency_rates.get(key_courrency);
                                    if (key_courrency.contentEquals("eur")) {

                                        Double sell_eur = jsonObject_name_of_currency.getDouble("sell");
                                        Double buy_eur = jsonObject_name_of_currency.getDouble("buy");
                                        list_sell_eur.add(sell_eur);
                                        list_buy_eur.add(buy_eur);

                                    } else if (key_courrency.contentEquals("usd")) {

                                        Double sell_usd = jsonObject_name_of_currency.getDouble("sell");
                                        Double buy_usd = jsonObject_name_of_currency.getDouble("buy");
                                        list_sell_usd.add(sell_usd);
                                        list_buy_usd.add(buy_usd);

                                    } else if (key_courrency.contentEquals("gbp")) {
                                        Double sell_gbp = jsonObject_name_of_currency.getDouble("sell");
                                        Double buy_gbp = jsonObject_name_of_currency.getDouble("buy");
                                        list_sell_gbp.add(sell_gbp);
                                        list_buy_gbp.add(buy_gbp);

                                    } else if (key_courrency.contentEquals("sar")) {
                                        Double sell_sar = jsonObject_name_of_currency.getDouble("sell");
                                        Double buy_sar = jsonObject_name_of_currency.getDouble("buy");
                                        list_sell_sar.add(sell_sar);
                                        list_buy_sar.add(buy_sar);

                                    }

                                }
                            }

                            holder.inner_rv.setLayoutManager(new LinearLayoutManager(mContext));
                            if (position == 0) {
                                inner_rv_adpater = new inner_rv_adpater(mContext, bank_name, list_buy_eur, list_sell_eur);
                                holder.inner_rv.setAdapter(inner_rv_adpater);
                            } else if (position == 1) {
                                  inner_rv_adpater = new inner_rv_adpater(mContext, bank_name, list_buy_usd, list_sell_usd);
                                holder.inner_rv.setAdapter(inner_rv_adpater);
                            } else if (position == 2) {
                                inner_rv_adpater inner_rv_adpater;  inner_rv_adpater = new inner_rv_adpater(mContext, bank_name, list_buy_gbp, list_sell_gbp);
                                holder.inner_rv.setAdapter(inner_rv_adpater);
                            } else if (position == 3) {
                                 inner_rv_adpater = new inner_rv_adpater(mContext, bank_name, list_buy_sar, list_sell_sar);
                                holder.inner_rv.setAdapter(inner_rv_adpater);
                            } else {
                                inner_rv_adpater = new inner_rv_adpater(mContext, bank_name, list_buy, list_sell);
                                holder.inner_rv.setAdapter(inner_rv_adpater);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext.getApplicationContext(), "Error......!", Toast.LENGTH_LONG).show();
            }
        });
        request_queue_singletone.get_instance(mContext).add_to_request_queue(jsonObjectRequest);

    }

    @Override
    public int getItemCount() {
        return courrency.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_courrency;
        RecyclerView inner_rv;
        EditText ed_num;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_courrency = (TextView) itemView.findViewById(R.id.tv_courrency);
            inner_rv = (RecyclerView) itemView.findViewById(R.id.inner_rv);
            ed_num = (EditText) itemView.findViewById(R.id.ed_text);
        }
    }
}

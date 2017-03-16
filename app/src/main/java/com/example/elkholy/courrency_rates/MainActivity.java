package com.example.elkholy.courrency_rates;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.elkholy.courrency_rates.adpaters.card_adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    String Url = "https://api.curates.club/";
    ArrayList<String> courrency = new ArrayList<String>();
    SwipeRefreshLayout msSwipeRefreshLayout;
    EditText ed_num;

    double val_sell_eur = 0;
    double val_buy_eur = 0;
    double res_sell_eur = 0;
    double res_buy_eur = 0;

    double val_sell = 0;
    double val_buy = 0;
    double res_sell_gold = 0;
    double res_buy_gold = 0;

    double val_sell_usd = 0;
    double val_buy_usd = 0;
    double res_sell_usd = 0;
    double res_buy_usd = 0;


    double val_sell_gbp = 0;
    double val_buy_gbp = 0;
    double res_sell_gbp = 0;
    double res_buy_gbp = 0;

    double val_sell_sar = 0;
    double val_buy_sar = 0;
    double res_sell_sar = 0;
    double res_buy_sar = 0;
    card_adapter card_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);

        /*
        msSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                // request_queue_singletone.get_instance(getApplicationContext()).add_to_request_queue(jsonObjectRequest);
                msSwipeRefreshLayout.setRefreshing(false);
            }
        });
*/
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            courrency.add("EUR");
                            courrency.add("USD");
                            courrency.add("GBP");
                            courrency.add("SAR");



                            JSONObject jsonObject = new JSONObject(String.valueOf(response));
                            Iterator<String> keyes = jsonObject.keys();
                            while (keyes.hasNext()) {
                                final String key = keyes.next();


                                JSONObject jsonObject1 = (JSONObject) jsonObject.get(key);
                                JSONObject jsonObject_Courrency_rates = (JSONObject) jsonObject1.get("currency_rate");


                                Iterator<String> keys_courrency = jsonObject_Courrency_rates.keys();
                                while (keys_courrency.hasNext()) {


                                    String key_courrency = keys_courrency.next();

                                    //   if (!courrency.contains(key_courrency)) {

                                    //  courrency.add(key_courrency);


                                    // }


                                    //Toast.makeText(getApplicationContext(),courrency.toString(),Toast.LENGTH_LONG).show();
                                }

                                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_main);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                card_adapter = new card_adapter(courrency);

                                recyclerView.setAdapter(card_adapter);


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error.....!", Toast.LENGTH_LONG).show();
            }
        });
        request_queue_singletone.get_instance(getApplicationContext()).add_to_request_queue(jsonObjectRequest);





        ed_num = (EditText) findViewById(R.id.ed_text);

        ed_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                for (int i = 0; i < card_adapter.list_sell_sar.size(); i++) {

                    try {
                        val_sell_eur = Double.valueOf(card_adapter.list_sell_eur.get(i));
                        val_buy_eur = Double.valueOf(card_adapter.list_buy_eur.get(i));
                        res_sell_eur = val_sell_eur * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_sell_eur.set(i, res_sell_eur);
                        res_buy_eur = val_buy_eur * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_buy_eur.set(i, res_buy_eur);



                        val_sell_gbp = Double.valueOf(card_adapter.list_sell_gbp.get(i));
                        val_buy_gbp = Double.valueOf(card_adapter.list_buy_gbp.get(i));
                        res_sell_gbp = val_sell_gbp * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_sell_gbp.set(i, res_sell_gbp);
                        res_buy_gbp = val_buy_gbp * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_buy_gbp.set(i, res_buy_gbp);




                        val_sell_usd = Double.valueOf(card_adapter.list_sell_usd.get(i));
                        val_buy_usd = Double.valueOf(card_adapter.list_buy_usd.get(i));
                        res_sell_usd = val_sell_usd * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_sell_usd.set(i, res_sell_usd);
                        res_buy_usd = val_buy_usd * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_buy_usd.set(i, res_buy_usd);





                        val_sell_sar = Double.valueOf(card_adapter.list_sell_sar.get(i));
                        val_buy_sar = Double.valueOf(card_adapter.list_buy_sar.get(i));
                        res_sell_sar = val_sell_sar * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_sell_sar.set(i, res_sell_sar);
                        res_buy_sar = val_buy_sar * (Double.parseDouble(ed_num.getText().toString()));
                        card_adapter.list_buy_sar.set(i, res_buy_sar);


                        card_adapter.notifyDataSetChanged();


                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        //  Toast.makeText(getApplicationContext(), "error in double number", Toast.LENGTH_LONG).show();

                    }


                }


                ed_num.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_DEL) {

                            for (int i = 0; i < card_adapter.list_sell_sar.size(); i++) {


                                try {

                                    val_sell_eur = Double.valueOf(card_adapter.list_sell_eur.get(i));
                                    val_buy_eur = Double.valueOf(card_adapter.list_buy_eur.get(i));
                                    res_sell_eur = val_sell_eur / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_sell_eur.set(i, res_sell_eur);
                                    res_buy_eur = val_buy_eur / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_buy_eur.set(i, res_buy_eur);

                                    //card_adapter.inner_rv_adpater.notifyDataSetChanged();


                                    val_sell_gbp = Double.valueOf(card_adapter.list_sell_gbp.get(i));
                                    val_buy_gbp = Double.valueOf(card_adapter.list_buy_gbp.get(i));
                                    res_sell_gbp = val_sell_gbp / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_sell_gbp.set(i, res_sell_gbp);
                                    res_buy_gbp = val_buy_gbp / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_buy_gbp.set(i, res_buy_gbp);

                                    //  card_adapter.inner_rv_adpater.notifyDataSetChanged();

                                    val_sell_usd = Double.valueOf(card_adapter.list_sell_usd.get(i));
                                    val_buy_usd = Double.valueOf(card_adapter.list_buy_usd.get(i));
                                    res_sell_usd = val_sell_usd / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_sell_usd.set(i, res_sell_usd);
                                    res_buy_usd = val_buy_usd / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_buy_usd.set(i, res_buy_usd);


                                    //  card_adapter.inner_rv_adpater.notifyDataSetChanged();


                                    val_sell_sar = Double.valueOf(card_adapter.list_sell_sar.get(i));
                                    val_buy_sar = Double.valueOf(card_adapter.list_buy_sar.get(i));
                                    res_sell_sar = val_sell_sar / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_sell_sar.set(i, res_sell_sar);
                                    res_buy_sar = val_buy_sar / (Double.parseDouble(ed_num.getText().toString()));
                                    card_adapter.list_buy_sar.set(i, res_buy_sar);


                                    card_adapter.notifyDataSetChanged();


                                } catch (NumberFormatException e) {

                                    e.printStackTrace();

                                }

                            }


                        }
                        return false;
                    }
                });


            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}

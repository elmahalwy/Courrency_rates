package com.example.elkholy.courrency_rates;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by elkholy on 06/03/2017.
 */

public class request_queue_singletone {
    private RequestQueue requestQueue;
    private static Context mctx;
    private static request_queue_singletone m_instance;

    public request_queue_singletone(Context mctx) {
        this.mctx = mctx;
        requestQueue = get_request();

    }

    public RequestQueue get_request() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized request_queue_singletone get_instance(Context context) {

        if (m_instance == null) {
            m_instance = new request_queue_singletone(context);

        }
        return m_instance;
    }

    public <T> void add_to_request_queue(Request<T> request) {
        get_request().add(request);
       // requestQueue.add(request);

    }
}

package com.example.mivolley;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // String PLACES_URL = "http://nexo.carm.es/nexo/archivos/recursos/opendata/json/Campings.json";
    // String LOG_TAG = "MainActivity";
    // Instantiate the RequestQueue
    // RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

    Button cadena;
    Button objeto;
    Button arr;
    Button imagen;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);

        cadena = (Button) findViewById(R.id.cadena);
        cadena.setOnClickListener(this);

        objeto = (Button) findViewById(R.id.objeto);
        objeto.setOnClickListener(this);

        arr = (Button) findViewById(R.id.arr);
        arr.setOnClickListener(this);

        imagen = (Button) findViewById(R.id.imagen);
        imagen.setOnClickListener(this);

    }


    public void volleyStringRequst(String url) {

        String REQUEST_TAG = "com.example.myapplication.volleyStringRequest";
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MainActivity", response.toString());

                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View showDialogView = li.inflate(R.layout.show_dialog, null);
                TextView outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setView(showDialogView);
                alertDialogBuilder
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                        .setCancelable(false)
                        .create();
                outputTextView.setText(response.toString());
                alertDialogBuilder.show();
                progressDialog.hide();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, REQUEST_TAG);
    }

    public void volleyJsonObjectRequest(String url) {

        String REQUEST_TAG = "com.example.myapplication.volleyJsonObjectRequest";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonObjectRequest jsonObjectReq = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("MainActivity", response.toString());

                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        View showDialogView = li.inflate(R.layout.show_dialog, null);
                        TextView outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setView(showDialogView);
                        alertDialogBuilder
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                                .setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        alertDialogBuilder.show();
                        progressDialog.hide();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("MainActivity", "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });

        // Adding JsonObject request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectReq, REQUEST_TAG);
    }

    public void volleyJsonArrayRequest(String url) {

        String REQUEST_TAG = "com.example.myapplication.volleyJsonArrayRequest";
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("MainActivity", response.toString());
                        LayoutInflater li = LayoutInflater.from(MainActivity.this);
                        View showDialogView = li.inflate(R.layout.show_dialog, null);
                        TextView outputTextView = (TextView) showDialogView.findViewById(R.id.text_view_dialog);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setView(showDialogView);
                        alertDialogBuilder
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                })
                                .setCancelable(false)
                                .create();
                        outputTextView.setText(response.toString());
                        alertDialogBuilder.show();
                        progressDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("MainActivity", "Error: " + error.getMessage());
                progressDialog.hide();
            }
        });

        // Adding JsonObject request to request queue

    }

    public void volleyImageLoader(String url) {
        ImageLoader imageLoader = AppSingleton.getInstance(getApplicationContext()).getImageLoader();

        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MainActivity", "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {

                    LayoutInflater li = LayoutInflater.from(MainActivity.this);
                    View showDialogView = li.inflate(R.layout.show_dialog, null);
                    ImageView outputImageView = (ImageView) showDialogView.findViewById(R.id.image_view_dialog);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertDialogBuilder.setView(showDialogView);
                    alertDialogBuilder
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            })
                            .setCancelable(false)
                            .create();
                    outputImageView.setImageBitmap(response.getBitmap());
                    alertDialogBuilder.show();
                }
            }
        });
    }

    public void volleyCacheRequest(String url) {
        Cache cache = AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache();
        Cache.Entry reqEntry = cache.get(url);
        if (reqEntry != null) {
            try {
                String data = new String(reqEntry.data, "UTF-8");
                //Handle the Data here.
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {

            //Request Not present in cache, launch a network request instead.
        }
    }

    public void volleyInvalidateCache(String url) {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().invalidate(url, true);
    }

    public void volleyDeleteCache(String url) {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().remove(url);
    }

    public void volleyClearCache() {
        AppSingleton.getInstance(getApplicationContext()).getRequestQueue().getCache().clear();
    }



    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.cadena :
                volleyStringRequst("https://androidtutorialpoint.com/api/volleyString");
                break;

            case R.id.objeto:
                volleyStringRequst("https://androidtutorialpoint.com/api/volleyJsonObject");
                break;
            case R.id.arr:
                volleyStringRequst("https://androidtutorialpoint.com/api/volleyJsonArray");
                break;
            case R.id.imagen:
                //  volleyStringRequst("https://androidtutorialpoint.com/api/lg_nexus_5x");
                break;

        }

    }
}

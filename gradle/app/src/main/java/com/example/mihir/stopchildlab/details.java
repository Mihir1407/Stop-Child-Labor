package com.example.mihir.stopchildlab;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class details extends AppCompatActivity{
    Button submit;
    EditText city,locality,shop,tim,dat;

    public String str1,str2,str3,str4,str5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        city = (EditText) findViewById(R.id.city) ;
        locality = (EditText) findViewById(R.id.locality) ;
        shop = (EditText) findViewById(R.id.name) ;
        tim = (EditText) findViewById(R.id.time) ;
        dat = (EditText) findViewById(R.id.date) ;

        submit = (Button) findViewById(R.id.submit) ;

        RequestQueue queue = Volley.newRequestQueue(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = city.getText().toString();
                str2 = locality.getText().toString();
                str3 = shop.getText().toString() ;
                str4 = tim.getText().toString() ;
                str5 = dat.getText().toString() ;
                if (str1 != null && str2!= null && str3 != null && str4 != null ) {
                    RequestQueue queue1 = Volley.newRequestQueue(details.this);
                    String url = "http://192.168.137.245:8000";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("sent")) {
                                        Toast.makeText(getApplicationContext(), "Details uploaded successfully.", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(details.this, MainActivity.class));
                                    }
                                    else if (response.equals("error")) {
                                        Toast.makeText(getApplicationContext(), "An error occurred!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error response", Toast.LENGTH_SHORT).show();
                        }

                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("str1", str1);
                            params.put("str2", str2);
                            params.put("str3", str3);
                            params.put("str4", str4);
                            params.put("str5", str5);
                            return params;

                        }
                    };
                    queue1.add(stringRequest);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}





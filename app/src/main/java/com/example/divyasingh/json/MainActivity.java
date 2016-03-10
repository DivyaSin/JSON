package com.example.divyasingh.json;

//import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
//import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.ListAdapter;
//import android.widget.ListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import org.json.JSONException;
//import org.json.JSONObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//import java.util.ArrayList;
//import java.util.HashMap;

import static java.lang.System.out;

public class MainActivity extends AppCompatActivity {

    Button button;

    TextView mFirst;
    TextView mSecond;
    TextView mThird;
    TextView mFourth;
    TextView mFifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.next);

        mFirst = (TextView) findViewById(R.id.tv_first);
        mSecond = (TextView) findViewById(R.id.tv_second);
        mThird = (TextView) findViewById(R.id.tv_third);
        mFourth = (TextView) findViewById(R.id.tv_fourth);
        mFifth = (TextView) findViewById(R.id.tv_fifth);

    }

    public void Click(View view) {
        new JSONParser().execute();
    }

    private String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String read;
        try {
            while ((read = br.readLine()) != null) {
                out.println(read);
                sb.append(read);
            }
            br.close();
        } catch (IOException e) {
            Log.v("Main Activity", "Exception");
        }
        return sb.toString();
    }

    private class JSONParser extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            String response = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://jsonview.com/example.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                // readStream(in);
                response = readStream(in);
            } catch (IOException e) {
                Log.v("Main Activity", "Exception");
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return response;
        }

        @Override
        protected void onPreExecute() {
            Log.v("MainActivity", "onPreExecute is called");
        }

        @Override
        protected void onProgressUpdate(String... args) {
            Log.v("MainActivity", "onProgressUpdate is called");

        }

        @Override
        protected void onPostExecute(String result) {
//            tv1.setText(result);
            Log.v("MainActivity", "Response is: " + result);
            HashMap<String, Object> meMap=new HashMap<>();
            try {
                JSONObject jsonObject = new JSONObject(result);
                Iterator<String> iterator = jsonObject.keys();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    meMap.put(key, jsonObject.get(key));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            mFirst.setText(meMap.get("hey").toString());
            mSecond.setText(meMap.get("anumber").toString());
            mThird.setText(meMap.get("anobject").toString());
            mFourth.setText(meMap.get("bogus").toString());
            mFifth.setText(meMap.get("link").toString());

//                ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list, );
//                ListView listView = (ListView) findViewById(R.id.List_view);
//                listView.setAdapter(adapter);
        }
    }


}

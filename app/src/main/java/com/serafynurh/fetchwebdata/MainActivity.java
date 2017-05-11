package com.serafynurh.fetchwebdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String> data;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= (ListView)findViewById(R.id.myList);
        data= new ArrayList<>();
        adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, data);
        list.setAdapter(adapter);
        fetch();

    }

    private void fetch() {
        String url = "http://appbeesafrica.com/cheka/index.php";

        AsyncHttpClient client=new AsyncHttpClient();

        client.get(url, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                Toast.makeText(MainActivity.this, "Sorry...Failed to Fetch", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onSuccess(int i, Header[] headers, String s) {
                try {
                    JSONArray array =new JSONArray(s);
                    for (int x=0; x<10; x++)
                    {
                        JSONObject obj =array.getJSONObject(x);
                        String title = obj.getString("title");
                        data.add(title);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}

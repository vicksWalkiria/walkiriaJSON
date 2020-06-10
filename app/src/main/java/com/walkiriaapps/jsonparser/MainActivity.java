package com.walkiriaapps.jsonparser;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String JSONRaw = "{\"menu\": {\n" +
                "  \"id\": \"file\",\n" +
                "  \"value\": \"File\",\n" +
                "  \"popup\": {\n" +
                "    \"menuitem\": [\n" +
                "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
                "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
                "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
                "    ]\n" +
                "  }\n" +
                "}}";

        //Step 0: Surround with try/catch
        try {

            //Step 1: Load text as JSON Object:
            JSONObject jsonObject = new JSONObject(JSONRaw);
            Log.d("WALKIRIAJSON", "The raw object is: "+jsonObject.toString());
            //Step 2: Get "Menu" object:
            JSONObject menuObject = jsonObject.getJSONObject("menu");
            Log.d("WALKIRIAJSON", "Menu object is: "+menuObject.toString());
            //Step 3: Get menu attributes and popup object:
            String id = menuObject.getString("id");
            String value = menuObject.getString("value");
            JSONObject popup = menuObject.getJSONObject("popup");
            Log.d("WALKIRIAJSON", "Menu attributes:\nid: "+id+"\nvalue: "+value+"\npopup :"+popup.toString());
            //Step 4 Get JSONArray and iterate through it:
            JSONArray menuItem = popup.getJSONArray("menuitem");
            for(int i =0; i< menuItem.length(); i++)
            {
                JSONObject menuJSONObject = menuItem.getJSONObject(i);
                String menuValue = menuJSONObject.getString("value");
                String menuOnClick = menuJSONObject.getString("onclick");
                Log.d("WALKIRIAJSON", "MenuJSONObject "+i+":\nvalue: "+menuValue+"\nonclick: "+menuOnClick);
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
package com.example.coronavirus_19;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AffectedCountries extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView backImage;
    EditText editText;
    public static ArrayList<CountryModel> exampleItems=new ArrayList<>();
    CountryModel countryModel;
    MyCustomAdapter customAdapter;
    SimpleArcLoader simpleArcLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affected_countries);
        initViews();
        fetchAllData();
        backImage.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    private void filter(String text)
    {
        ArrayList<CountryModel> filteredList=new ArrayList<>();
        for(CountryModel item:exampleItems)
        {
            if(item.getCountry().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        customAdapter.FilteredList(filteredList);
    }

    private void initViews()
    {
        simpleArcLoader=findViewById(R.id.simple_arc_affectedCountries);
        recyclerView=findViewById(R.id.listview);
        backImage=findViewById(R.id.backImage);
        editText=findViewById(R.id.editTextSearch);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
    }

    private void fetchAllData(){
        String url="https://disease.sh/v3/covid-19/countries";
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONArray jsonArray=new JSONArray(response);
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject responseObj = jsonArray.getJSONObject(i);
                    String CountryName = responseObj.getString("country");
                    String cases = responseObj.getString("cases");
                    String todayCases = responseObj.getString("todayCases");
                    String recovered = responseObj.getString("recovered");
                    String critical = responseObj.getString("critical");
                    String todayDeaths = responseObj.getString("todayDeaths");
                    String deaths = responseObj.getString("deaths");
                    String active = responseObj.getString("active");

                    JSONObject jsonObject=responseObj.getJSONObject("countryInfo");
                    String ImageURL = jsonObject.getString("flag");

                    countryModel=new CountryModel(ImageURL,CountryName,cases,todayCases,deaths,todayDeaths,active,critical,recovered);
                    exampleItems.add(countryModel);
                }
                customAdapter=new MyCustomAdapter(AffectedCountries.this,exampleItems);
                recyclerView.setAdapter(customAdapter);
            }catch (Exception e){
                e.printStackTrace();
            }
        }, error -> Toast.makeText(AffectedCountries.this, error.getMessage(), Toast.LENGTH_SHORT).show());
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
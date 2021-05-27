package com.example.coronavirus_19;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fetchData();
        button.setOnClickListener(v -> goTrackCountries());
    }

    private void initViews()
    {
        tvCases=findViewById(R.id.tvCases);
        tvRecovered=findViewById(R.id.tvRecovered);
        tvCritical=findViewById(R.id.tvCritical);
        tvActive=findViewById(R.id.tvActiveCases);
        tvTodayCases=findViewById(R.id.tvTodayCases);
        tvTodayDeaths=findViewById(R.id.tvTodayDeaths);
        tvTotalDeaths=findViewById(R.id.tvTotalDeaths);
        tvAffectedCountries=findViewById(R.id.tvAffectedCountries);
        button=findViewById(R.id.button1);
        simpleArcLoader=findViewById(R.id.simple_arc);
        scrollView=findViewById(R.id.scrollView);
        pieChart=findViewById(R.id.pieChart);
    }


    private void fetchData()
    {
            String url="https://disease.sh/v3/covid-19/all";
            simpleArcLoader.start();
            StringRequest stringRequest=new StringRequest(Request.Method.GET, url, response -> {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    tvCases.setText(jsonObject.getString("cases"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#DCC174")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#4CC851")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeaths.getText().toString()), Color.parseColor("#F82718")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#2CACE6")));
                    pieChart.startAnimation();

                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                }catch (Exception e){
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }
            }, error -> {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
            });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void goTrackCountries()
    {
        Intent intent=new Intent(getApplicationContext(),AffectedCountries.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
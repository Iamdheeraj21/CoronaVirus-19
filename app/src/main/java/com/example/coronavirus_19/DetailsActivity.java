package com.example.coronavirus_19;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {
    TextView totalCases,todayCases,totalDeaths,todayDeaths,critical,recovered,active,countryName;
    ImageView imageView,backImage;
    int positionCountry;
    String flagImageUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatails);
        initViews();

        Intent getIntent=getIntent();
//        positionCountry=getIntent.getIntExtra("position");
        flagImageUrl=AffectedCountries.exampleItems.get(positionCountry).getFlag();
        //Get and Set the Country CoronaVirus Details
        Glide.with(getApplicationContext()).load(getIntent.getStringExtra("cUrl")).placeholder(R.drawable.ic_baseline_flag_24).into(imageView);
        countryName.setText(getIntent.getStringExtra("cName"));
        totalCases.setText(getIntent.getStringExtra("cTotalCases"));
        todayCases.setText(getIntent.getStringExtra("cCases"));
        totalDeaths.setText(getIntent.getStringExtra("cTotalDeaths"));
        todayDeaths.setText(getIntent.getStringExtra("cTodayDeaths"));
        critical.setText(getIntent.getStringExtra("cCritical"));
        recovered.setText(getIntent.getStringExtra("cRecovered"));
        active.setText(getIntent.getStringExtra("cactive"));

        backImage.setOnClickListener(v -> {
            Intent intent=new Intent(DetailsActivity.this,AffectedCountries.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }

    private void initViews()
    {
        totalCases=findViewById(R.id.totalCases);
        todayCases=findViewById(R.id.todayCases);
        todayDeaths=findViewById(R.id.todayDeaths);
        totalDeaths=findViewById(R.id.totalDeaths);
        critical=findViewById(R.id.criticalCase);
        recovered=findViewById(R.id.recovered);
        countryName=findViewById(R.id.text_CountryName);
        imageView=findViewById(R.id.detailsFlagImage);
        backImage=findViewById(R.id.backImage);
        active=findViewById(R.id.activeCases);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(DetailsActivity.this,AffectedCountries.class));
        finish();
    }
}
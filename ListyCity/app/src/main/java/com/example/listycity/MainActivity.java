package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    String selected;

    EditText inputCity;
    LinearLayout inputSection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        inputCity = findViewById(R.id.input_city);
        inputSection = findViewById(R.id.input_section);
        inputSection.setVisibility((View.GONE));

        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selected = dataList.get(position);

        });
    }

    public void showInput(View view) {
        if (inputSection.getVisibility() == View.GONE) {
            inputSection.setVisibility(View.VISIBLE);
        } else {
            inputSection.setVisibility(View.GONE);
        }
    }

    public void addCity(View view) {
        String city = inputCity.getText().toString();
        dataList.add(city);
        cityList.setAdapter(cityAdapter);
        inputCity.setText(""); // Clear the input field
        inputSection.setVisibility(View.GONE);
    }


    public void deleteCity(View view) {
        dataList.remove(selected);
        cityList.setAdapter(cityAdapter);
    }

}
package com.example.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Resources.Expense;
import com.example.myapplication.Resources.ExpenseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class Main extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btn_calculate;
    private FloatingActionButton btn_add;
    private SharedPreferences mPrefs;
    private ArrayList<Expense> mExpenses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mPrefs = getSharedPreferences("shared preferences", MODE_PRIVATE);

        getExpenses();
        recyclerView = findViewById(R.id.listview);
        btn_calculate = findViewById(R.id.btn_calc);
        btn_add = findViewById(R.id.btn_new);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        linearLayout.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayout);

        ExpenseAdapter expenseAdapter = new ExpenseAdapter(Main.this,mExpenses);
        recyclerView.setAdapter(expenseAdapter);
    }

    public void getExpenses() {

        Gson gson = new Gson();
        String json = mPrefs.getString("myExpenses", null);
        Type type = new TypeToken<ArrayList<Expense>>() {
        }.getType();
        mExpenses = gson.fromJson(json, type);

        if (mExpenses == null) {
            mExpenses = new ArrayList<>();
        }
    }
}

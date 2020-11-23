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
    private FirebaseFirestore fStore;
    private FirestoreRecyclerAdapter<Expense, ItemViewHolder> expenseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fStore = FirebaseFirestore.getInstance();

        expenseAdapter = getExpenses();
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

      recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
      recyclerView.setAdapter(noteAdapter);
    }
    
    

    public FirestoreRecyclerAdapter getExpenses() {


         query = fStore.collection("Expenses");


        FirestoreRecyclerOptions<Item> allExpenses = new FirestoreRecyclerOptions.Builder<Expense>()
                .setQuery(query, Expense.class)
                .build();

        noteAdapter = new FirestoreRecyclerAdapter<Expense, ItemViewHolder>(allExpenses) {


            @Override
            protected void onBindViewHolder(@NonNull final ItemViewHolder itemViewHolder, int i, @NonNull final Expense expense) {
                final String docId = noteAdapter.getSnapshots().getSnapshot(i).getId();
                itemViewHolder.price.setText(String.valueOf(expense.getPrice()));
                itemViewHolder.date.setText(String.valueOf(expense.getContent()));
                itemViewHolder.content.setText(String.valueOf(expense.getDate()));

     
            }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense, parent, false);
                return new ItemViewHolder(view);
            }
        };
        return noteAdapter;
    }
    
 public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView price,content,date;
        View view;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.tv_price);
            content = itemView.findViewById(R.id.tv_content);
            date = itemView.findViewById(R.id.tv_date);
            view = itemView;
        }
    }
    
    
    @Override
    public void onStart() {
        super.onStart();
        expenseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (noteAdapter != null) {
            expenseAdapter.stopListening();
        }
    }
}

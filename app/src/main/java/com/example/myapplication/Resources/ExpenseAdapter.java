package com.example.myapplication.Resources;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Resources.Expense;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.List;


public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    List<Expense> mExpense;
    Context mContext;


    public ExpenseAdapter(Context mContext ,List<Expense> mExpense) {
        this.mContext = mContext;
        this.mExpense = mExpense;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View checkListView = LayoutInflater.from(mContext).inflate(R.layout.expense, parent, false);
            return new ExpenseAdapter.ViewHolder(checkListView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Expense expense = mExpense.get(position);
        System.out.println(expense.getCatagory());
        holder.price.setText(String.valueOf(expense.getPrice()));
        holder.content.setText(expense.getContent());

        holder.date.setText(expense.getDate());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mExpense.remove(position);
                System.out.println("asd");
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mExpense.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView price;
        TextView date;
        TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.tv_price);
            date = itemView.findViewById(R.id.tv_date);
            content = itemView.findViewById(R.id.tv_content);
        }
    }
}


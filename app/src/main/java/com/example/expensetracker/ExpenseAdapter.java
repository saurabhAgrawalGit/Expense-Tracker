package com.example.expensetracker;


import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder> {

    private Context context;
    public ArrayList<ExpenseTable> arrayList;
    private MainActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView mode;
        public TextView amount;
        public TextView info;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mode= itemView.findViewById(R.id.type);
            this.amount = itemView.findViewById(R.id.amount_list);
            this.info = itemView.findViewById(R.id.info_list);
        }
    }

    public ExpenseAdapter(Context context, ArrayList<ExpenseTable> arrayList, MainActivity mainActivity) {
        this.context = context;
        this.arrayList = arrayList;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.ltem_view,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.MyViewHolder holder, int position) {
         ExpenseTable expenseTable = arrayList.get(position);

        holder.mode.setText( expenseTable.isType()==true?"Expense":"Income");
        holder.amount.setText(Long.toString(expenseTable.getAmount()));
        holder.info.setText(expenseTable.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),add.class);

                intent.putExtra("amount",Long.toString(expenseTable.getAmount()));
                intent.putExtra("info",expenseTable.getDesc());
                intent.putExtra("mode",expenseTable.isType()==true?"true":"false");
                intent.putExtra("pos",position);
                MySingleton.getInstance().setMyObject(mainActivity,expenseTable);



                intent.putExtra("update",1);


              //  mainActivity.DeleteContact(expenseTable,position);

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}

package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ExpenseAdapter expenseAdapter;
    ExpenseDatabase expenseDatabase;
    TextView balance, income, expense;
    public ArrayList<ExpenseTable> ExpenseArrayList  = new ArrayList<>();
    private RecyclerView recyclerView;
    Button  add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycler_view_contacts);
        add= findViewById(R.id.bt_expense);
        balance= findViewById(R.id.balance);
        expense= findViewById(R.id.expense_tv);
        income= findViewById(R.id.income_tv);


        expenseDatabase= Room.databaseBuilder(
                        getApplicationContext(),
                        ExpenseDatabase.class,
                        "ContactDB")
                .allowMainThreadQueries()
                .build();

        // Contacts List
        ExpenseArrayList.addAll(expenseDatabase.getExpenseDAO().getContacts());
        expenseAdapter = new ExpenseAdapter(this, ExpenseArrayList,MainActivity.this);
        expenseAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(expenseAdapter);
        expenseAdapter.notifyDataSetChanged();
        balance_amount();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseAdapter.notifyDataSetChanged();
                Intent intent=new Intent(MainActivity.this,add.class);
                startActivity(intent);

                MySingleton.getInstance().setMyObjectMain(MainActivity.this);


            }
        });




    }
    public void DeleteContact( ExpenseTable expenseTablee,int position) {
        ExpenseArrayList.remove(position);
        expenseDatabase.getExpenseDAO().deleteContact(expenseTablee);
        balance_amount();
        expenseAdapter.notifyDataSetChanged();


    }
    public void Createamount( ExpenseTable expenseTablee) {
        expenseDatabase.getExpenseDAO().deleteContact(expenseTablee);
        ExpenseArrayList.add(0,expenseTablee);
        balance_amount();
        expenseAdapter.notifyDataSetChanged();
    }

    public void updateAmount(ExpenseTable expenseTablee, int position) {
        expenseDatabase.getExpenseDAO().updateContact(expenseTablee);
        ExpenseArrayList.set(position,expenseTablee);
        balance_amount();
        expenseAdapter.notifyDataSetChanged();
    }
   public  void balance_amount()
    {
        long expen = 0;
        long inco=0;
        long bal;

        for(int i =0; i<ExpenseArrayList.size();i++)
        {
            if(ExpenseArrayList.get(i).isType()==true)
            {
                expen=expen+ExpenseArrayList.get(i).getAmount();
            }
            else
            {
                inco=inco+ExpenseArrayList.get(i).getAmount();
            }
        }
        bal=inco-expen;
        balance.setText("Balance ₹ "+Long.toString(bal));
        expense.setText("Expense ₹ "+Long.toString(expen));
        income.setText("Income ₹ "+Long.toString(inco));


    }


}
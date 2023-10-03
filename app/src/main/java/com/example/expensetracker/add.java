package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class add extends AppCompatActivity{

    EditText amount , info;
    RadioButton expenseRadio,incomeRadio;
    Button added,delete;
    private  ExpenseDatabase expenseDatabase;
    ExpenseTable expenseTables= new ExpenseTable();
    MainActivity mainActivity =new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        added=findViewById(R.id.add_item);
        delete=findViewById(R.id.delete_item);
        amount=findViewById(R.id.amount);
        info=findViewById(R.id.editText);
        expenseRadio=findViewById(R.id.radioButton2);
        incomeRadio=findViewById(R.id.radioButton);



        int id= getIntent().getIntExtra("update",0);
        int position= getIntent().getIntExtra("pos",0);
        added.setText(id==1?"Update":"Add");
        if(id==1)
        {

            String bal=getIntent().getExtras().getString("amount");
            String inf=getIntent().getExtras().getString("info");
            String mod=getIntent().getExtras().getString("mode");
            String check=getIntent().getExtras().getString("mode");
            Intent myIntent = getIntent();



            amount.setText(bal);
            info.setText(inf);
            if(check.equals("true"))
            {
                expenseRadio.setChecked(true);
            }
            else
            {
                incomeRadio.setChecked(true);
            }

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity mainActivity=new MainActivity();
                    ExpenseTable obj= MySingleton.getInstance().getMyObject();
                   mainActivity=MySingleton.getInstance().getMyObjectm();
                    mainActivity.DeleteContact(obj,position);





                }
            });
        }

        added.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext()," Successfully ADD",Toast.LENGTH_LONG);
                String bal=amount.getText().toString();
                String dec=info.getText().toString();
                boolean isexpense=expenseRadio.isChecked();


                expenseTables.setAmount(Long.parseLong(bal));
                expenseTables.setType(isexpense);
                expenseTables.setDesc(dec);


              expenseDatabase= Room.databaseBuilder(
                                getApplicationContext(),
                                ExpenseDatabase.class,
                                "ContactDB")
                        .allowMainThreadQueries()
                        .build();
                expenseDatabase.getExpenseDAO().addExpense(expenseTables);
                mainActivity.ExpenseArrayList.add(0, expenseTables);
                mainActivity=MySingleton.getInstance().getMyObjectm();

                mainActivity.Createamount(expenseTables);

                 if(id==1)
                {
                    mainActivity.updateAmount(expenseTables,position);
                }


                Toast.makeText(getApplicationContext()," Successfully ADD",Toast.LENGTH_SHORT).show();

            }
        });






    }
}
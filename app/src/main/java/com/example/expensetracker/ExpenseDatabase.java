package com.example.expensetracker;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database( entities ={ ExpenseTable.class },version = 1)
public  abstract class ExpenseDatabase extends RoomDatabase {
    public  abstract ExpenseDAO getExpenseDAO();
}

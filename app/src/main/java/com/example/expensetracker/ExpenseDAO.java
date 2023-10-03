package com.example.expensetracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDAO {

    @Insert
    public Long addExpense(ExpenseTable expenseTable);

    @Update
    public void updateContact(ExpenseTable expenseTable);

    @Delete
    public void deleteContact(ExpenseTable expenseTable);

    @Query("select* from expense")
    public List<ExpenseTable> getContacts();

    @Query("select* from expense where id==:id")
    public ExpenseTable getContact(int id);
}

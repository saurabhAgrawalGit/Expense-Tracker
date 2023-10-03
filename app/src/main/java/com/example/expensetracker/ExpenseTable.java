package com.example.expensetracker;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense")
public class ExpenseTable  {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    private  String desc;
    private long amount;
    private  boolean type;

    public ExpenseTable() {
    }

    public ExpenseTable(int id, String desc, long amount, boolean type) {
        this.id = id;
        this.desc = desc;
        this.amount = amount;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
}

package com.example.expensetracker;

public class MySingleton {
    private static MySingleton instance;
    private ExpenseTable expenseTable;
    private  MainActivity mainActivity;

    private MySingleton() {}

    public static MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    public ExpenseTable getMyObject() {
        return expenseTable;
    }
    public MainActivity getMyObjectm() {
        return mainActivity;
    }

    public void setMyObject(MainActivity mainActivity,ExpenseTable expenseTable) {
        this.expenseTable = expenseTable;
        this.mainActivity=mainActivity;
    }
    public void setMyObjectMain(MainActivity mainActivity) {

        this.mainActivity=mainActivity;
    }
}
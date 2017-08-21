package com.example.pc.mobileshop2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapters.ProductsRecyclerAdapter;
import model.Products;
import sql.DatabaseHelper;

import static com.example.pc.mobileshop2.R.id.fab;

public class ProductsListActivity extends AppCompatActivity {

    private AppCompatActivity activity = ProductsListActivity.this;
    private AppCompatTextView textViewName;
    private RecyclerView recyclerViewProducts;
    private List<Products> listProducts;
    private ProductsRecyclerAdapter productsRecyclerAdapter;
    private DatabaseHelper databaseHelper;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);
        //getSupportActionBar().setTitle("");
        initViews();
        initObjects();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductsListActivity.this, AddProductActivity.class);
                startActivity(intent);

    }


    private void initViews() {
        textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewProducts = (RecyclerView) findViewById(R.id.recyclerViewProducts);
    }


    private void initObjects() {
        listProducts = new ArrayList<>();
        productsRecyclerAdapter = new ProductsRecyclerAdapter(listProducts);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProducts.setLayoutManager(mLayoutManager);
        recyclerViewProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerViewProducts.setHasFixedSize(true);
        recyclerViewProducts.setAdapter(productsRecyclerAdapter);
        databaseHelper = new DatabaseHelper(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        String name = databaseHelper.getUserName(emailFromIntent);
        textViewName.setText(name);


        //getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                    listProducts.clear();
                    listProducts.addAll(databaseHelper.getAllProducts());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                productsRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
package com.studioyuando.guess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private int NUM_LIST_ITEMS;
    private EditText mEdtTxt;
    private RecyclerView mNumbersList;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Start the New Round!", Toast.LENGTH_SHORT).show();
        mEdtTxt = (EditText) findViewById(R.id.edtTxt);
        NUM_LIST_ITEMS = Integer.parseInt(mEdtTxt.getText().toString());
        mNumbersList = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        mNumbersList.setLayoutManager(layoutManager);
        mNumbersList.setHasFixedSize(true);
        processControllers();

    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menubar, menu);
//        return true;
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.refresh:
                NUM_LIST_ITEMS = Integer.parseInt(mEdtTxt.getText().toString());
                Toast.makeText(this, "Start the New Round!", Toast.LENGTH_SHORT).show();
                processControllers();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void processControllers() {
//        mAdapter = new Adapter(NUM_LIST_ITEMS) {
//            @Override
//            public void onBindViewHolder(final ViewHolder holder, final int position) {
//                super.onBindViewHolder(holder, position);
//                holder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (mAdapter.game(position))
//                            Toast.makeText(view.getContext().getApplicationContext(), mAdapter.getanswer(), Toast.LENGTH_LONG).show();
//                        mNumbersList.setAdapter(mAdapter);
//                    }
//                });
//            }
//        };
//        mNumbersList.setAdapter(mAdapter);
        Button bt= (Button) findViewById(R.id.reset_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText) findViewById(R.id.edtTxt);
                String input = et.getText().toString();
                NUM_LIST_ITEMS =Integer.valueOf(input);
                mAdapter=new Adapter(NUM_LIST_ITEMS);
                mNumbersList.setAdapter(mAdapter);
            }
            public void onBindViewHolder(final ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                    public void onClick(View view) {
                        if (mAdapter.game(position))
                            Toast.makeText(view.getContext().getApplicationContext(), mAdapter.getanswer(), Toast.LENGTH_LONG).show();
                        mNumbersList.setAdapter(mAdapter);
                    }
                });
            }
        });

    };
}




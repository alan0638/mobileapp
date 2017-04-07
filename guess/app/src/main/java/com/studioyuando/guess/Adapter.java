package com.studioyuando.guess;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Adapter extends
        RecyclerView.Adapter<Adapter.ViewHolder> {
    private int mNumberItems;
    private int min, max, answer;
    private int[] status;

    public Adapter(int numberOfItems) {
        mNumberItems = numberOfItems;
        min = 0;
        max = numberOfItems - 1;
        answer = (int) (Math.random() * numberOfItems);
        status = new int[mNumberItems];
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTxt;
        public View rootView;

        public ViewHolder(View view) {
            super(view);
            mTxt = (TextView) itemView.findViewById(R.id.item_number);
            rootView = view;
        }
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.num_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int state) {
        holder.mTxt.setText(String.valueOf(state + 1));
        if (status[state] == 0)
            holder.itemView.setBackgroundColor(Color.WHITE);
        else if (status[state] == 1)
            holder.itemView.setBackgroundColor(Color.GRAY);
        else if (status[state] == 2)
            holder.itemView.setBackgroundColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public boolean game(int state) {
        if (state > answer) {
            for (int i = state; i <= max; i++)
                status[i] = 1;
            max = state;
        } else if (state < answer) {
            for (int i = min; i <= state; i++)
                status[i] = 1;
            min = state;
        } else if (state == answer) {
            status[state] = 2;
            return true;
        }
        return false;
    }

    public String getanswer() {
        String toast = "This Round is over, the answer is " + (answer + 1) + "!";
        return toast;
    }
}

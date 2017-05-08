package com.example.user.guessnumber;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by User on 2017/4/5.
 */

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
                R.layout.number_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTxt.setText(String.valueOf(position + 1));
        if (status[position] == 0)
            holder.itemView.setBackgroundColor(Color.WHITE);
        else if (status[position] == 1)
            holder.itemView.setBackgroundColor(Color.GRAY);
        else if (status[position] == 2)
            holder.itemView.setBackgroundColor(Color.RED);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    public boolean game(int position) {
        if (position > answer) {
            for (int i = position; i <= max; i++)
                status[i] = 1;
            max = position;
        } else if (position < answer) {
            for (int i = min; i <= position; i++)
                status[i] = 1;
            min = position;
        } else if (position == answer) {
            status[position] = 2;
            return true;
        }
        return false;
    }

    public String getanswer() {
        String toast = "本回合結束,答案就是" + (answer + 1) + "!";
        return toast;
    }
}

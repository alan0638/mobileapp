package com.studioyuando.guess;

//import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private int NumItems;
    private int min, max, answer;
    private int[] status;

    public Adapter(int quantity) {
        NumItems = quantity;
        min = 0;
        max = quantity - 1;
        answer = (int) (Math.random() * quantity);
        status = new int[NumItems];
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Txt;
        public View rootView;

        public ViewHolder(View view) {
            super(view);
            Txt = (TextView) itemView.findViewById(R.id.item_number);
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
        holder.Txt.setText(String.valueOf(state + 1));
        if (status[state] == 0) {
            holder.itemView.setBackgroundColor(Color.WHITE);
        }
        else if (status[state] == 1) {
            holder.itemView.setBackgroundColor(Color.GRAY);
        }
        else if (status[state] == 2) {
            holder.itemView.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return NumItems;
    }

    public boolean game(int choose) {
        if (choose > answer) {
            for (int i = choose; i <= max; i++) {
                status[i] = 1;
            }
            max = choose;
        }
        else if (choose < answer) {
            for (int i = min; i <= choose; i++) {
                status[i] = 1;
            }
            min = choose;
        }
        else if (choose == answer) {
            status[choose] = 2;
            return true;
        }
        return false;
    }

    public String getanswer() {
        String toast = "This Round is over, the answer is " + (answer + 1) + "!";
        return toast;
    }
}

package com.github.elwinbran.mad.gamble;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DiceRollAdapter extends RecyclerView.Adapter<DiceRollAdapter.DiceRollViewHolder> {

    private final Context context;
    private final List data;

    public DiceRollAdapter(Context context, List data)
    {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public DiceRollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.roll_log, parent, false);
        DiceRollViewHolder vh = new DiceRollViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DiceRollViewHolder holder, int position) {
        holder.textView.setText("You rolled a: " + data.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DiceRollViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public DiceRollViewHolder(View viewItem)
        {
            super(viewItem);
            try {
                textView = viewItem.findViewById(R.id.textView4);
            } catch(Throwable trouble)
            {
                Log.d("None", "DiceRollViewHolder: " + trouble.getMessage());
            }
        }
    }
}

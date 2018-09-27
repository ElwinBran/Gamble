package com.github.elwinbran.mad.gamble;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

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
        View v = LayoutInflater.from(this.context).inflate(R.layout.activity_main,parent,false);
        DiceRollViewHolder vh = new DiceRollViewHolder(v, context);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull DiceRollViewHolder holder, int position) {
        holder.textView.setText(data.get(position).toString());
        Toast.makeText(this.context,"Tayce.T",Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class DiceRollViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textView;
        public DiceRollViewHolder(View viewItem, Context context)
        {
            super(viewItem);
            textView = new TextView(context);
            Log.d("none", "DiceRollViewHolder: ");
        }
    }
}

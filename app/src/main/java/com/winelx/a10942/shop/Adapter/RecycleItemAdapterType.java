package com.winelx.a10942.shop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.winelx.a10942.shop.R;
import com.winelx.a10942.shop.bean.Classify;

import java.util.List;

/**
 * @name Shopping-master
 * @class name：com.winelx.a10942.shop.Adapter
 * @class describe
 * @anthor 10942 QQ:1032006226
 * @time 2017/4/12 0012 17:18
 * @change
 * @chang time
 * @class describe
 */
public class RecycleItemAdapterType extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Classify> results;

    //get & set
    public List<Classify> getResults() {
        return results;
    }

    public RecycleItemAdapterType(Context context, List<Classify> results) {
        this.context = context;
        this.results = results;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            bind((ItemViewHolder) holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    private void bind(ItemViewHolder holder, int position) {


        Glide.with(context).load(results.get(position).getImge()).into(holder.immg);
        holder.textView.setText(results.get(position).getTexts());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView immg;
        TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            immg = (ImageView) itemView.findViewById(R.id.immg);
            textView = (TextView) itemView.findViewById(R.id.textView4);
        }
    }
}

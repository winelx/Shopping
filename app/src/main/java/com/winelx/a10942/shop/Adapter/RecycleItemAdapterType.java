package com.winelx.a10942.shop.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
public class RecycleItemAdapterType extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            bind((ItemViewHolder) holder, position);
        }
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line1:
                int postion = (int) v.getTag();
                Toast.makeText(context, "第" + postion + "分类", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView immg;
        TextView textView;
        LinearLayout line1;

        public ItemViewHolder(View itemView) {
            super(itemView);
            immg = (ImageView) itemView.findViewById(R.id.immg);
            textView = (TextView) itemView.findViewById(R.id.textView4);
            line1 = (LinearLayout) itemView.findViewById(R.id.line1);
        }

    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}

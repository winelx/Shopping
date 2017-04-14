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
 * Created by 666 on 2017/1/3.
 * 首页分类
 */
public class TypeCategoryAdapter extends RecyclerView.Adapter<TypeCategoryAdapter.TypetypeHolder> {

    private Context mContext;

    private List<Classify> mHomeCategory;

    private LayoutInflater inflater;


    public TypeCategoryAdapter(Context mContext, List<Classify> mHomeCategory) {
        this.mContext = mContext;
        this.mHomeCategory = mHomeCategory;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TypetypeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TypetypeHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_adapter, parent, false));
    }

    @Override


    public void onBindViewHolder(TypetypeHolder holder, int position) {
        Glide.with(mContext).load(mHomeCategory.get(position).getImge()).into(holder.immg);
        holder.textView.setText(mHomeCategory.get(position).getTexts());
    }

    @Override
    public int getItemCount() {
        return mHomeCategory.size();
    }

    //中间的四个type
    public class TypetypeHolder extends RecyclerView.ViewHolder {
        ImageView immg;
        TextView textView;

        public TypetypeHolder(View itemView) {
            super(itemView);
            immg = (ImageView) itemView.findViewById(R.id.immg);
            textView = (TextView) itemView.findViewById(R.id.textView4);
        }
    }

}

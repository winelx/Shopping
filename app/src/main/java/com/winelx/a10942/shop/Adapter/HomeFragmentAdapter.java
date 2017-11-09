package com.winelx.a10942.shop.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.winelx.a10942.shop.MainActivity;
import com.winelx.a10942.shop.Manager.FullyGridLayoutManager;
import com.winelx.a10942.shop.R;
import com.winelx.a10942.shop.bean.Classify;

import java.util.ArrayList;

import static com.winelx.a10942.shop.R.id.mRecyclerView;

/**
 * @name Shopping-master
 * @class name：com.winelx.a10942.shop.Adapter
 * @class describe
 * @anthor 10942 QQ:1032006226
 * @time 2017/4/12 0012 16:34
 * @change
 * @chang time
 * @class describe
 */
public class HomeFragmentAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Classify> results;
    private ArrayList<String> Xbanner;
    private RecycleItemAdapterType mAdapterType;
    public static final int TYPE_TOP = 0xff01;
    public static final int TYPE_XBANNR = 0xff02;
    public static final int TYPE_MAIN = 0xff03;
    private int count = 2;
    private GridLayoutManager mLayoutManager;

    public HomeFragmentAdapter(Context context) {
        this.mContext = context;
        results = new ArrayList<>();
        Xbanner = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TOP:
                return new TypeTopsliderHolder(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.adapter_slider, parent, false));
            case TYPE_XBANNR:
                return new TypeBannersliderHolder(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_banner, parent, false));
            case TYPE_MAIN:
                return new TypeMainsliderHolder(LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.item_recycle, parent, false));
            default:
                Log.d("error", "viewholder w null");
                return null;
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TypeTopsliderHolder) {
            bindType1((TypeTopsliderHolder) holder, position);
        } else if (holder instanceof TypeBannersliderHolder && Xbanner.size() != 0 && ((TypeBannersliderHolder) holder).banner.getRealCount() == 0) {
            bindType2((TypeBannersliderHolder) holder, Xbanner);
        } else {

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
        return 20;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_XBANNR;
        } else if (position == 1) {
            return TYPE_TOP;
        } else {
            return TYPE_MAIN;
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    switch (type) {
                        case TYPE_TOP:
                        case TYPE_XBANNR:
                            return gridLayoutManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }


    private void bindType1(TypeTopsliderHolder holder, final int position) {
        holder.mRecycler.setLayoutManager(new FullyGridLayoutManager(holder.mRecycler.getContext(), 3, GridLayoutManager.VERTICAL, false));
        mAdapterType = new RecycleItemAdapterType(mContext, results);
        holder.mRecycler.setAdapter(mAdapterType);
        holder.mRecycler.setNestedScrollingEnabled(false);
        mAdapterType.setOnItemClickLitener(new RecycleItemAdapterType.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "第" + position + "分类",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    //轮播图的数据适配
    public void bindType2(TypeBannersliderHolder holder, final ArrayList<String> Xbanner) {
        XBanner banner = holder.banner;
        banner.setData(Xbanner, null);
        // XBanner适配数据
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(mContext).load(Xbanner.get(position)).into((ImageView) view);
            }
        });
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, int position) {
                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });

    }


    //头部Viewpager viewholder
    public class TypeTopsliderHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecycler;

        public TypeTopsliderHolder(View view) {
            super(view);
            mRecycler = (RecyclerView) view.findViewById(mRecyclerView);

        }
    }

    public class TypeMainsliderHolder extends RecyclerView.ViewHolder {
        ImageView img_header;

        public TypeMainsliderHolder(View view) {
            super(view);
            img_header = (ImageView) view.findViewById(R.id.img_header);

        }
    }

    public class TypeBannersliderHolder extends RecyclerView.ViewHolder {
        XBanner banner;

        public TypeBannersliderHolder(View itemView) {
            super(itemView);
            banner = (XBanner) itemView.findViewById(R.id.banner);
        }
    }

    public void setheaderbean(ArrayList<Classify> list) {
        results = list;
        notifyDataSetChanged();

    }

    public void setXbanner(ArrayList<String> list) {
        Xbanner = list;
        notifyDataSetChanged();

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
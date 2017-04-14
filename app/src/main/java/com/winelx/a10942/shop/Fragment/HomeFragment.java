package com.winelx.a10942.shop.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.winelx.a10942.shop.Adapter.HomeFragmentAdapter;
import com.winelx.a10942.shop.R;
import com.winelx.a10942.shop.bean.Classify;

import java.util.ArrayList;


/**
 * Created by 10942 on 2017/3/18 0018.
 */

public class HomeFragment extends Fragment {
    private View rootView;// 缓存Fragment view
    private HomeFragmentAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //避免重复绘制界面
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home, null);
        }
        // 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        mContext = getActivity();
        initData();
        return rootView;
    }

    private void initData() {
//创建线性布局
        mRecyclerView = (RecyclerView) this.rootView.findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mRecyclerView.getContext(), 2, GridLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter = new HomeFragmentAdapter(mContext));
        initview();
        initbanenr();
    }

    private void initbanenr() {
        ArrayList<String> baner = new ArrayList<>();
        baner.add("https://a-ssl.duitang.com/uploads/item/201609/12/20160912182203_nQBfs.thumb.700_0.jpeg");
        baner.add("https://a-ssl.duitang.com/uploads/item/201609/12/20160912182124_LYZcx.thumb.700_0.jpeg");
        baner.add("https://a-ssl.duitang.com/uploads/item/201609/12/20160912182057_aZfLy.thumb.700_0.jpeg");
        baner.add("https://a-ssl.duitang.com/uploads/item/201609/12/20160912181858_uJMtF.thumb.700_0.jpeg");
        baner.add("https://a-ssl.duitang.com/uploads/item/201609/12/20160912181821_TWsZn.thumb.700_0.jpeg");
        mAdapter.setXbanner(baner);
    }

    private void initview() {
        ArrayList<Classify> list = new ArrayList<>();
        list.add(new Classify("http://img1.imgtn.bdimg.com/it/u=1820076929,3421851540&fm=23&gp=0.jpg", "分类1"));
        list.add(new Classify("http://img2.imgtn.bdimg.com/it/u=3925774092,2129890416&fm=23&gp=0.jpg", "分类2"));
        list.add(new Classify("http://img0.imgtn.bdimg.com/it/u=997872387,2883297873&fm=23&gp=0.jpg", "分类3"));
        list.add(new Classify("http://img4.imgtn.bdimg.com/it/u=1918421557,2242330443&fm=23&gp=0.jpg", "分类4"));
        list.add(new Classify("http://img2.imgtn.bdimg.com/it/u=704254373,1442787759&fm=23&gp=0.jpg", "分类5"));
        list.add(new Classify("http://img3.imgtn.bdimg.com/it/u=1985615566,1072172087&fm=23&gp=0.jpg", "分类6"));
        mAdapter.setheaderbean(list);

    }
}
package com.winelx.a10942.shop;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.winelx.a10942.shop.Fragment.HomeFragment;
import com.winelx.a10942.shop.Fragment.HotFragment;
import com.winelx.a10942.shop.Fragment.MyselfFragment;
import com.winelx.a10942.shop.Fragment.ShareFragment;
import com.winelx.a10942.shop.Fragment.ShoppingFragment;
import com.winelx.a10942.shop.bean.Tab;

import java.util.ArrayList;

/**
 * 当前activity界面分析
 * 需要使用FragmentTabHost，但是界面里需要放置两个FrameLayout，一个放在FragmentTabHost里面，使用官方默认的id
 * 另一个放FragmentTabHost上面，自定义属性，进入界面找到FragmentTabHost的ID，实现setUp方法，这个一定要实现，不然
 * 界面就出出错。没法展示。里面放置（上下文，fragment管理器，ID{自定义的FrameLayout的id}），
 * 展示tab选项内容也需要布局。创建一个布局，使用view填充，使用view就需要一个view管理器（LayoutInflater），创建LayoutInflater，
 * 调用inflate添加布局，后面就是lsitview的getitem那一套，同时给展示的内容添加一个bean，为了简化代码，将内容使用一个list集合装起来。
 * 循环添加到FragmentTabHost里面
 * <p>
 * 在string里填入tab里的文字，将图片放入miamap里， drawable里面放自定的内容，
 */

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost mTabHost;

    private LayoutInflater mInflater;
    private ArrayList<Tab> mTabs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化，
        mInflater = LayoutInflater.from(this);

        initTab();
    }

    private void initTab() {
        Tab tab_home = new Tab(HomeFragment.class, R.string.home, R.drawable.tab_home_style);
        Tab tab_hot = new Tab(HotFragment.class, R.string.hot, R.drawable.tab_hot_style);
        Tab tab_share = new Tab(ShareFragment.class, R.string.sharr, R.drawable.tab_shera_style);
        Tab tab_shpping = new Tab(ShoppingFragment.class, R.string.shopping, R.drawable.tab_shopping_style);
        Tab tab_mine = new Tab(MyselfFragment.class, R.string.myself, R.drawable.tab_mine_style);
        mTabs.add(tab_home);
        mTabs.add(tab_hot);
        mTabs.add(tab_share);
        mTabs.add(tab_shpping);
        mTabs.add(tab_mine);
        //找到控件
        mTabHost = (FragmentTabHost) findViewById(R.id.mFragmentTabHost);
        for (Tab tab : mTabs) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(getString(tab.getTitle()));//获取都文字
            tabSpec.setIndicator(buildIndicator(tab));
            mTabHost.addTab(tabSpec, tab.getFragemnt(), null);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_BEGINNING);//设置分割线
        mTabHost.setCurrentTab(0);//设置默认打开的界面

    }

    private View buildIndicator(Tab tab) {
        //必须实现setup不然没法展示， getSupportFragmentManager用来装布局的
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        //mInflater记得初始化，不然会报空指针，没注意，被坑
        View view = mInflater.inflate(R.layout.tab_index, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        TextView textview = (TextView) view.findViewById(R.id.text);
        //d动态添加图片文字，类似listview 的adapter的getItem，不过本来就是
        imageView.setBackgroundResource(tab.getIcon());
        textview.setText(tab.getTitle());
        return view;
    }
}

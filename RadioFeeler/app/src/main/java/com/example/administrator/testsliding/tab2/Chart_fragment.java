package com.example.administrator.testsliding.tab2;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.testsliding.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class Chart_fragment extends Fragment {
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private TabAdapter_chart mAdapter;

    private List<Fragment> list;
    private Chart_spectrum mChart_spectrum;
    private Chart_waterfall mChart_waterfall;

    private Chart_Abnormal mChart_Abnormal;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     //   getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
        //getActivity().getActionBar().setNavigationMode(ActionBar.DISPLAY_HOME_AS_UP);
        return inflater.inflate(R.layout.chart_fragment, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();

        mViewPager.setCurrentItem(0);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.toolbar);
//        ((AppCompatActivity)(getActivity())).setSupportActionBar(toolbar);
        fragmentPagerSetting();
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) getActivity().findViewById(R.id.id_viewpager);
        tabLayout= (TabLayout) getActivity().findViewById(R.id.tabs);
        mAdapter = new TabAdapter_chart(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        tabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器

    }


    /*viewpager加载适配器，装载两个fragment*/
   private void fragmentPagerSetting() {

       mChart_spectrum=new Chart_spectrum();
       mChart_waterfall=new Chart_waterfall();

       mChart_Abnormal=new Chart_Abnormal();


       list = new ArrayList<Fragment>();

       list.add(mChart_spectrum);
       list.add(mChart_waterfall);

       list.add(mChart_Abnormal);

    }

     class TabAdapter_chart extends FragmentPagerAdapter {

        public  String[] TITLES = new String[]
                {"频谱图", "瀑布图", "异常表"};

         public TabAdapter_chart(android.support.v4.app.FragmentManager fm) {
             super(fm);
         }

//        public TabAdapter_chart(FragmentManager fm) {
//            super(fm);
//        }

         @Override
         public android.support.v4.app.Fragment getItem(int position) {
             return list.get(position);
         }


         @Override
         public int getCount() {
             // TODO Auto-generated method stub
             return list.size();
         }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

    }
}
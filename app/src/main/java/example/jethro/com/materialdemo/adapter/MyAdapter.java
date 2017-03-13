package example.jethro.com.materialdemo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import example.jethro.com.materialdemo.RecycleTabFragment;

/**
 * Created by lin on 2017/2/23.
 */

public class MyAdapter extends FragmentPagerAdapter {

    private String[] titles = {"tab1", "tab2"};
    private Context ctx;
    private List<Fragment> pages;

    public MyAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.ctx = context;
        pages = new ArrayList<Fragment>();
        pages.add(RecycleTabFragment.newInstance());
        pages.add(RecycleTabFragment.newInstance());

    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

package example.jethro.com.materialdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trycatch.mysnackbar.TSnackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.jethro.com.materialdemo.adapter.CacheFragmentStatePagerAdapter;
import example.jethro.com.materialdemo.adapter.MyAdapter;
import example.jethro.com.materialdemo.utils.DisplayUtils;
import example.jethro.com.materialdemo.utils.StatusBarUtil;
import example.jethro.com.materialdemo.view.SimpleSwipeRefreshLayout;

/**
 * Created by lin on 2017/2/22.
 */
public class TabLayoutActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.sliding_tabs)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsing)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.swipe)
    SimpleSwipeRefreshLayout swipeRefreshLayout;
    private NavigationAdapter mAdapter;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_view_snap);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.TransparentText);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(this);
        initViewPager();
    }

    private void initViewPager() {
        mAdapter = new NavigationAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                final ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content).getRootView();
                TSnackbar.make(viewGroup, "刷新成功", TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_TOP_TO_DOWN)
                        .setBackgroundColor(getResources().getColor(R.color.colorPrimary))
                        .show();
            }
        }, 2000);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        swipeRefreshLayout.setEnabled(verticalOffset == 0 ? true : false);
    }

    private static class NavigationAdapter extends CacheFragmentStatePagerAdapter {

        private static final String[] TITLES = new String[]{"Scroll View", "Butter Cookie", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Lollipop"};


        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment createItem(int position) {
            Fragment f;
            final int pattern = position % 4;
            switch (pattern) {
                case 0: {
                    f = new RecycleViewFragment();
                    break;
                }
                case 1: {
                    f = new NestedScrollViewFragment();
                    break;
                }
                case 2: {
                    f = new RecycleViewFragment();
                    break;
                }
                case 3:
                default: {
                    f = new NestedScrollViewFragment();
                    break;
                }
            }
            return f;
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
    }
}

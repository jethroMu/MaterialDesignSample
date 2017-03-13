package example.jethro.com.materialdemo;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.trycatch.mysnackbar.TSnackbar;


import butterknife.BindView;
import butterknife.ButterKnife;
import example.jethro.com.materialdemo.adapter.MainAdapter;
import example.jethro.com.materialdemo.entity.Demo;
import example.jethro.com.materialdemo.utils.StatusBarUtil;

/**
 * Created by lin on 2017/2/22.
 */
public class MainActivity extends AppCompatActivity implements MainAdapter.ItemClick, NavigationView.OnNavigationItemSelectedListener {

    Demo[] demos;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.app_bar)
    AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.btn_tran)
    Button btTran;
    @BindView(R.id.btn_over)
    Button btOver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        demos = createDemos();
        initRecycleView();
        initToolbar();
        setNavigationView();
        addListener();
        StatusBarUtil.setColorForDrawerLayout(MainActivity.this, drawer, getResources().getColor(R.color.colorPrimary), 100);
    }

    private void addListener() {
        btTran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarUtil.setColorForDrawerLayout(MainActivity.this, drawer, getResources().getColor(R.color.colorPrimary), 0);

            }
        });
        btOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 1) {
                    state = 2;
                    appBarLayout.setBackgroundDrawable(getResources().getDrawable(R.mipmap.bg_monkey_king));
                    StatusBarUtil.setTranslucentForDrawerLayout(MainActivity.this, drawer, 0);
                    toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                } else {
                    state = 1;
                    appBarLayout.setBackgroundDrawable(getResources().getDrawable(R.color.colorPrimary));
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    StatusBarUtil.setColorForDrawerLayout(MainActivity.this, drawer,
                            getResources().getColor(R.color.colorPrimary), 100);
                }
            }
        });
    }

    private int state = 1;

    private void initToolbar() {
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    private void setNavigationView() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private Demo[] createDemos() {
        return new Demo[]{
                new Demo("AppBarLayour", new Intent(this, AppBarLayoutActivity.class)),
                new Demo("CollapsingToolbarLayout", new Intent(this, CollapsingToolbarActivity.class)),
                new Demo("Fab Behavior", new Intent(this, FabShrinkActivity.class)),
                new Demo("Top SnackBar", new Intent(this, NestedScrollViewActivity.class)),
                new Demo("Bottom SnackBar", new Intent(this, NestedScrollViewActivity.class)),
                new Demo("ImageTransition", new Intent(this, ImageTransitionActivity.class)),
                new Demo("FlexibleSpace snap", new Intent(this, NestedScrollViewActivity.class)),
                new Demo("Bottom Sheet", new Intent(this, BottomSheetActivity.class)),
                new Demo("TabLayout & swipeRefreshLayout", new Intent(this, TabLayoutActivity.class)),
                new Demo("Palette", new Intent(this, PaletteActivity.class)),
                new Demo("Bottom Toolbar", new Intent(this, BottomToolbarActivity.class))
        };
    }

    private void initRecycleView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        MainAdapter mainAdapter = new MainAdapter(demos, this);
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void onClick(View v, int position) {
        if (position == 3) {
            final ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content).getRootView();
            TSnackbar.make(viewGroup, "Top SnackBar !!!!", TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_TOP_TO_DOWN)
                    .setBackgroundColor(getResources().getColor(R.color.blue))
                    .show();
            return;
        } else if (position == 4) {
            final ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content).getRootView();
            TSnackbar.make(viewGroup, "Bottom SnackBar !!!!", TSnackbar.LENGTH_SHORT, TSnackbar.APPEAR_FROM_BOTTOM_TO_TOP)
                    .setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark))
                    .show();
            return;
        }
        Intent intent = demos[position].getIntent();
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(this, v, "CardViewTransition");
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}

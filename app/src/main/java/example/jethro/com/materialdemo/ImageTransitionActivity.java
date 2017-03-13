package example.jethro.com.materialdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import example.jethro.com.materialdemo.adapter.ImageAdapter;
import example.jethro.com.materialdemo.entity.ImageItem;

/**
 * Created by lin on 2017/3/1.
 */

public class ImageTransitionActivity extends AppCompatActivity implements ImageAdapter.OnItemClickListener {

    private static List<ImageItem> items = new ArrayList<>();
    private View content;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transition);
        initRecyclerView();
        initFab();
        initToolbar();
        content = findViewById(R.id.content);
        setRecyclerAdapter(recyclerView);
        recyclerView.scheduleLayoutAnimation();
    }


    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setRecyclerAdapter(RecyclerView recyclerView) {
        ImageAdapter adapter = new ImageAdapter(items);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    private void initFab() {
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(content, "FAB Clicked", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


    @Override
    public void onItemClick(View view, ImageItem item) {
        Intent intent = new Intent(this, ImageDetailActivity.class);
        intent.putExtra(ImageDetailActivity.EXTRA_IMAGE, item.getImage());
        intent.putExtra(ImageDetailActivity.EXTRA_TITLE, "");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view.findViewById(R.id.image), ImageDetailActivity.EXTRA_IMAGE);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }


    static {
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img0.imgtn.bdimg.com/it/u=1602907013,3794136905&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img0.imgtn.bdimg.com/it/u=634651288,2136740054&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img5.imgtn.bdimg.com/it/u=1257552872,1450095753&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=3947787286,3829499254&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img1.imgtn.bdimg.com/it/u=2436767828,3806584413&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
        items.add(new ImageItem("", "http://img4.imgtn.bdimg.com/it/u=1519346946,700592218&fm=23&gp=0.jpg"));
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

}

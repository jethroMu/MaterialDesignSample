package example.jethro.com.materialdemo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.jethro.com.materialdemo.adapter.RecycleAdapter;
import example.jethro.com.materialdemo.entity.Item;

/**
 * Created by lin on 2017/2/22.
 * <p>
 * design提供三种方式实现
 */
public class BottomSheetActivity extends AppCompatActivity {

    @BindView(R.id.btn_dialog)
    Button mDialog;
    @BindView(R.id.btn_view)
    Button mView;
    @BindView(R.id.bottomSheet)
    View mBottomSheet;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.activity_main)
    CoordinatorLayout coordinatorLayout;
    private BottomSheetDialog mBottomSheetDialog;
    private BottomSheetBehavior mDialogBehavior;
    private BottomSheetBehavior mBehavior;
    private RecycleAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        ButterKnife.bind(this);

        mBehavior = BottomSheetBehavior.from(mBottomSheet);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                Log.d("sss", "" + newState);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new RecycleAdapter(createItems(), new RecycleAdapter.ItemListener() {
            @Override
            public void onItemClick(Item item) {
                mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
        recyclerView.setAdapter(mAdapter);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetView();
            }
        });
        mDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });
    }

    public List<Item> createItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(R.drawable.ic_preview_24dp, "Preview"));
        items.add(new Item(R.drawable.ic_share_24dp, "Share"));
        items.add(new Item(R.drawable.ic_link_24dp, "Get link"));
        items.add(new Item(R.drawable.ic_content_copy_24dp, "Copy"));
        return items;
    }

    private void showBottomSheetView() {
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        ViewCompat.postOnAnimation(coordinatorLayout, new Runnable() {
            @Override
            public void run() {
                ViewCompat.postInvalidateOnAnimation(coordinatorLayout);
            }
        });
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
    }


    @SuppressLint("InflateParams")
    private void showBottomSheetDialog() {
        View view = getLayoutInflater().inflate(R.layout.layout_sheet, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecycleAdapter(createItems(), new RecycleAdapter.ItemListener() {
            @Override
            public void onItemClick(Item item) {
                mDialogBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        }));

        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);
        mDialogBehavior = BottomSheetBehavior.from((View) view.getParent());

        mBottomSheetDialog.show();
        mBottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBottomSheetDialog = null;
            }
        });
    }

}

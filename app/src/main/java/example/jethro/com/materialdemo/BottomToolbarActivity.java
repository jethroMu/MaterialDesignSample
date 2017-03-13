package example.jethro.com.materialdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

import example.jethro.com.materialdemo.behavior.ToolbarBehavior;


public class BottomToolbarActivity extends TopAppListActivity {

    private float mInitialFabX;
    private float mInitialFabY;

    private View mBottomToolbar;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_fab_toolbar_custom;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBottomToolbar = findViewById(R.id.activity_fab_custom_toolbar);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(null);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) mBottomToolbar.getLayoutParams();
        ToolbarBehavior toolbarBehavior = (ToolbarBehavior) layoutParams.getBehavior();
        toolbarBehavior.setToolbarChangeListener(new ToolbarBehavior.ToolbarChangeListener() {

            @Override
            public void onToolbarCollapse() {
                hideToolbar();
            }

            @Override
            public void onToolbarShown() {
                showToolbar();
            }
        });

        mFloatingActionButton.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mInitialFabX = mFloatingActionButton.getX();
                mInitialFabY = mFloatingActionButton.getY();
                mFloatingActionButton.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void hideToolbar() {

        if (mBottomToolbar.getVisibility() == View.INVISIBLE) {
            return;
        }

        int cx = mBottomToolbar.getWidth() / 2;
        int cy = mBottomToolbar.getHeight() / 2;

        mFloatingActionButton.setVisibility(View.INVISIBLE);
        mFloatingActionButton.setX(mBottomToolbar.getX() + (mBottomToolbar.getWidth() / 2) - (mFloatingActionButton.getWidth() / 2));
        mFloatingActionButton.setY(mBottomToolbar.getY() + (mBottomToolbar.getHeight() / 2) - (mFloatingActionButton.getHeight() / 2));

        int initialRadius = mBottomToolbar.getWidth();
        int endRadius = mFloatingActionButton.getWidth();

        Animator anim = ViewAnimationUtils.createCircularReveal(mBottomToolbar, cx, cy, initialRadius, endRadius);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mBottomToolbar.setVisibility(View.INVISIBLE);

                mFloatingActionButton.setVisibility(View.VISIBLE);
                mFloatingActionButton.animate()
                        .x(mInitialFabX)
                        .y(mInitialFabY)
                        .setInterpolator(new LinearOutSlowInInterpolator())
                        .start();
            }
        });

        anim.start();
    }

    private void showToolbar() {

        if (mBottomToolbar.getVisibility() != View.INVISIBLE) {
            return;
        }

        float x = mBottomToolbar.getX() + (mBottomToolbar.getWidth() / 2) - (mFloatingActionButton.getWidth() / 2);
        float y = mBottomToolbar.getY() + (mBottomToolbar.getHeight() / 2) - (mFloatingActionButton.getHeight() / 2);

        mFloatingActionButton.animate()
                .x(x)
                .y(y)
                .setInterpolator(new LinearOutSlowInInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {

                        int initialRadius = mFloatingActionButton.getWidth();
                        int endRadius = mBottomToolbar.getWidth();

                        mBottomToolbar.setVisibility(View.VISIBLE);
                        mFloatingActionButton.setVisibility(View.INVISIBLE);

                        int cx = mBottomToolbar.getWidth() / 2;
                        int cy = mBottomToolbar.getHeight() / 2;
                        Animator anim = ViewAnimationUtils.createCircularReveal(mBottomToolbar, cx, cy, initialRadius, endRadius);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });

                        anim.start();
                    }
                })
                .start();
    }

}

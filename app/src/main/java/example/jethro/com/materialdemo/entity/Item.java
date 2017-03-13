package example.jethro.com.materialdemo.entity;

import android.support.annotation.DrawableRes;

/**
 * Created by lin on 2017/2/22.
 */

public class Item {

    private int mDrawableRes;

    private String mTitle;

    public Item(@DrawableRes int drawable, String title) {
        mDrawableRes = drawable;
        mTitle = title;
    }

    public int getDrawableResource() {
        return mDrawableRes;
    }

    public String getTitle() {
        return mTitle;
    }

}

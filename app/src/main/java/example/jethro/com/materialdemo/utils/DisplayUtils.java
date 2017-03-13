package example.jethro.com.materialdemo.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import example.jethro.com.materialdemo.R;

public class DisplayUtils {
	
	public static int getScreenHeightDip(Context context) {
		DisplayMetrics metric = context.getResources().getDisplayMetrics();
		int screenHeight = metric.densityDpi;
		return screenHeight;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics metric = context.getResources().getDisplayMetrics();
		int screenHeight = metric.heightPixels;
		return screenHeight;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics metric = context.getResources().getDisplayMetrics();
		int screenWidth = metric.widthPixels;
		return screenWidth;
	}

	public static int getScreenWidthInDip(Context context)
	{
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);

		int SCREEN_WIDTH_PIXELS;
		int SCREEN_WIDTH_DP;
		//这里我们只关心竖屏状态下的值
		SCREEN_WIDTH_PIXELS = dm.widthPixels;
		SCREEN_WIDTH_DP = (int) (SCREEN_WIDTH_PIXELS / dm.density);

		return SCREEN_WIDTH_DP;
	}

	public static int getScreenHeightInDip(Context context)
	{
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);

		int SCREEN_HEIGHT_PIXELS;
		int SCREEN_HEIGHT_DP;
		//这里我们只关心竖屏状态下的值
		SCREEN_HEIGHT_PIXELS = dm.heightPixels;
		SCREEN_HEIGHT_DP = (int) (SCREEN_HEIGHT_PIXELS / dm.density);

		return SCREEN_HEIGHT_DP;
	}

	public static int getStatusBarHeight(Context context) {
		int height = 0;
		int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			height = context.getResources().getDimensionPixelSize(resourceId);
		}
		return height;
	}

	public static int getActionBarHeight(Context context) {
		// Calculate ActionBar height
		int actionBarHeight = 0;
		TypedValue tv = new TypedValue();
		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources()
					.getDisplayMetrics());
		}
		if (actionBarHeight == 0) {
			actionBarHeight = 45;
		}
		return actionBarHeight;
	}

	public static int dp2Px(Context context, int dp){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
		return px;
	}

	public static int sp2Px(Context context, int sp){
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, dm);
		return px;
	}

	public static int getStatusBarHeightPixel(Context context) {
		int result = 0;
		int resourceId =
				context.getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	public static int getActionBarHeightPixel(Context context) {
		TypedValue tv = new TypedValue();
		if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
			return TypedValue.complexToDimensionPixelSize(tv.data,
					context.getResources().getDisplayMetrics());
		} else if (context.getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
			return TypedValue.complexToDimensionPixelSize(tv.data,
					context.getResources().getDisplayMetrics());
		} else {
			return 0;
		}
	}

	public static int getTabHeight(Context context) {
		return context.getResources().getDimensionPixelSize(R.dimen.tab_height);
	}

	public static Point getDisplayDimen(Context context) {
		Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();
		Point size = new Point();
		if (Build.VERSION.SDK_INT >= 13) {
			display.getSize(size);
		} else {
			size.x = display.getWidth();
			size.y = display.getHeight();
		}
		return size;
	}

	public static boolean isLand(Context context) {
		return context.getResources().getConfiguration().orientation ==
				Configuration.ORIENTATION_LANDSCAPE;
	}
}

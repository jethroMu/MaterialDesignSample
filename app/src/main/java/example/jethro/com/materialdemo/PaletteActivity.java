package example.jethro.com.materialdemo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaletteActivity extends AppCompatActivity {
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_Vibrant)
    TextView tv_Vibrant;
    @BindView(R.id.tv_Vibrant_dark)
    TextView tv_Vibrant_dark;
    @BindView(R.id.tv_Muted)
    TextView tv_Muted;
    @BindView(R.id.tv_Muted_dark)
    TextView tv_Muted_dark;
    private String EXTRA_IMAGE = "http://desk.fd.zol-img.com.cn/t_s1024x768c5/g5/M00/0F/09/ChMkJlauze2IPKICABzBh_ueXY0AAH9JAMQ2qUAHMGf334.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        ButterKnife.bind(this);
        Picasso.with(this).load(EXTRA_IMAGE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
    }

    private void applyPalette(Palette palette) {
        tv_Vibrant.setBackgroundColor(palette.getVibrantSwatch().getRgb());
        tv_Vibrant_dark.setBackgroundColor(palette.getDarkVibrantSwatch().getRgb());
        tv_Muted.setBackgroundColor(palette.getMutedSwatch().getRgb());
        tv_Muted_dark.setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
    }
}

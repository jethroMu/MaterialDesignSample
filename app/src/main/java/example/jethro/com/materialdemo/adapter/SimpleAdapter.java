package example.jethro.com.materialdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.jethro.com.materialdemo.R;
import example.jethro.com.materialdemo.entity.SimpleItem;
import example.jethro.com.materialdemo.utils.FileLoader;


public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleHolder> {

    private List<SimpleItem> mItems;
    private boolean mShowIcon;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(SimpleItem simpleItem);
    }

    public SimpleAdapter(Context context) {
        this(FileLoader.loadSampleItems(context), null, true);
    }

    public SimpleAdapter(List<SimpleItem> items, OnItemClickListener onItemClickListener) {
        this(items, onItemClickListener, false);
    }

    private SimpleAdapter(List<SimpleItem> items, OnItemClickListener onItemClickListener, boolean showIcon) {
        mItems = items;
        mOnItemClickListener = onItemClickListener;
        mShowIcon = showIcon;
    }

    @Override
    public SimpleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        final SimpleHolder viewHolder = new SimpleHolder(inflater.inflate(R.layout.list_item, viewGroup, false));
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener == null) {
                    return;
                }

                mOnItemClickListener.onItemClicked(mItems.get(viewHolder.getAdapterPosition()));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SimpleHolder viewHolder, int i) {
        SimpleItem item = mItems.get(i);
        viewHolder.getTextView().setText(item.getName());

        if (!mShowIcon) {
            viewHolder.getImageView().setVisibility(View.GONE);
        }

        Context context = viewHolder.itemView.getContext();
        Picasso.with(context)
            .load(item.getIconUrl())
            .into(viewHolder.getImageView());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class SimpleHolder extends RecyclerView.ViewHolder {

        private TextView mTextView;
        private ImageView mImageView;

        public SimpleHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.list_item_text);
            mImageView = (ImageView) itemView.findViewById(R.id.list_item_image);
        }

        public TextView getTextView() {
            return mTextView;
        }

        public ImageView getImageView() {
            return mImageView;
        }
    }

}

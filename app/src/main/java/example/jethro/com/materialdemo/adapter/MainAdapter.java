package example.jethro.com.materialdemo.adapter;

/**
 * Created by cuneytcarikci on 27/10/2016.
 */


import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.jethro.com.materialdemo.R;
import example.jethro.com.materialdemo.entity.Demo;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private Demo[] itemList;

    private ItemClick itemClick;

    public MainAdapter(Demo[] itemList,ItemClick itemClick) {
        this.itemList = itemList;
        this.itemClick = itemClick;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_list, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        Demo item = itemList[position];
        holder.text.setText(item.getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClick.onClick(view, holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.length;
    }

    public interface ItemClick {
        void onClick(View v, int position);
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        CardView cardView;

        MainViewHolder(View view) {
            super(view);
            text = (TextView) view.findViewById(R.id.text);
            cardView = (CardView) view.findViewById(R.id.item_root);
        }
    }
}
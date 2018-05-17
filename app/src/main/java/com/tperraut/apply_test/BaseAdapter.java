package com.tperraut.apply_test;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private ArrayList<String> mTitles;
    private ArrayList<String> mDescriptions;
    private ArrayList<Drawable> mImages;

    private Listener mListener;

    public BaseAdapter( Listener listener,
                        ArrayList<String> titles,
                        ArrayList<String> descriptions,
                        ArrayList<Drawable> images) {
        this.mTitles = titles;
        this.mDescriptions = descriptions;
        this.mListener = listener;
        this.mImages = images;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_image)
        ImageView mImage;
        @BindView(R.id.item_title)
        TextView mTitle;
        @BindView(R.id.item_description)
        TextView mDescription;
        @BindView(R.id.item_details_bt)
        Button mDetailsBt;
        @BindView(R.id.item_share_bt)
        Button mShareBt;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTitle.setText(mTitles.get(position));
        holder.mDescription.setText(mDescriptions.get(position));
        holder.mImage.setImageDrawable(mImages.get(position));
        holder.mDetailsBt.setOnClickListener(mListener);
        holder.mShareBt.setOnClickListener(mListener);
    }

    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    public interface Listener extends View.OnClickListener{
        @Override
        void onClick(View view);
    }
}

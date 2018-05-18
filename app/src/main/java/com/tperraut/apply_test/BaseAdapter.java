package com.tperraut.apply_test;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
    private Listener mListener;
    private List<Model> mDataSet;

    public BaseAdapter(Listener listener) {
        this.mListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Model mModel;

        @BindView(R.id.item_image)
        ImageView mImage;
        @BindView(R.id.item_title)
        TextView mTitle;
        @BindView(R.id.item_description)
        TextView mDescription;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @OnClick(R.id.item_details_bt)
        void onDetailsRequested() {
            if (mListener != null) {
                mListener.onDetailsRequested(mModel);
            }
        }

        @OnClick(R.id.item_share_bt)
        void onShareRequested() {
            if (mListener != null) {
                mListener.onShareRequested(mModel);
            }
        }

        public void setModel(Model model) {
            this.mModel = model;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model data = mDataSet.get(position);
        holder.mTitle.setText(data.getTitleRes());
        holder.mDescription.setText(data.getDescriptionRes());
        holder.mImage.setImageResource(data.getImageRes());
        holder.setModel(data);
    }

    @Override
    public int getItemCount() {
        if (mDataSet != null)
            return mDataSet.size();
        return 0;
    }

    public void setDataSet(@Nullable List<Model> dataSet) {
        this.mDataSet = dataSet;
    }

    public void addItem(@StringRes int titleRes,
                        @StringRes int descriptionRes,
                        @DrawableRes int imageRes) {
        if (mDataSet != null) {
            mDataSet.add(0, new Model(titleRes, descriptionRes, imageRes));
            notifyItemInserted(0);
        }
    }

    public void removeItem() {
        if (mDataSet != null && !mDataSet.isEmpty()) {
            mDataSet.remove(0);
            notifyItemRemoved(0);
        }
    }

    public interface Listener {
        void onDetailsRequested(Model m);

        void onShareRequested(Model m);
    }
}

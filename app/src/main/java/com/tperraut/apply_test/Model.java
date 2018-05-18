package com.tperraut.apply_test;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class Model {
    private @StringRes
    int mTitleRes;
    private @StringRes
    int mDescriptionRes;
    private @DrawableRes
    int mImageRes;

    public Model(int titleRes, int descriptionRes, int imageRes) {
        this.mTitleRes = titleRes;
        this.mDescriptionRes = descriptionRes;
        this.mImageRes = imageRes;
    }

    public int getTitleRes() {
        return mTitleRes;
    }

    public int getDescriptionRes() {
        return mDescriptionRes;
    }

    public int getImageRes() {
        return mImageRes;
    }
}

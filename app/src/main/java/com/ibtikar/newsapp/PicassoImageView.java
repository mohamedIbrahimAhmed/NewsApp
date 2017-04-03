package com.ibtikar.newsapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static android.text.TextUtils.isEmpty;

/**
 * Created by mohamed.ibrahim on 4/3/2017.
 */

public class PicassoImageView extends android.support.v7.widget.AppCompatImageView {

    private String mDefaultImage;
    private int mDefaultImageResource;
    private boolean mIsScale_16_9;
    private boolean mIsScale_1_1;


    public PicassoImageView(Context context) {
        super(context);
        init(context, null);
    }


    public PicassoImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PicassoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        if (attrs == null) return;

        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PicassoImageView);
        mDefaultImage = a.getString(R.styleable.PicassoImageView_default_image_url);
        mDefaultImageResource = a.getResourceId(R.styleable.PicassoImageView_default_image_resource, 0);
        mIsScale_16_9 = a.getBoolean(R.styleable.PicassoImageView_scale_16_9, false);
        mIsScale_1_1 = a.getBoolean(R.styleable.PicassoImageView_scale_1_1, false);
        a.recycle();


        Url(null); // load default image


    }


    public PicassoImageView Url(String Url) {
        if (!isEmpty(Url)) {
            Picasso.with(getContext()).load(Url).into(this);
            return this;
        }

        if (!isEmpty(mDefaultImage)) {
            Picasso.with(getContext()).load(mDefaultImage).into(this);
            return this;
        }

        if (mDefaultImageResource > 0) {
            Picasso.with(getContext()).load(mDefaultImageResource).into(this);
            return this;
        }


        return this;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (mIsScale_16_9) {
            final int width = getMeasuredWidth();
            final int height = 9 * width / 16;
            setMeasuredDimension(width, height);
        } else if (mIsScale_1_1) {
            final int width = getMeasuredWidth();
            final int height  = width ;
            setMeasuredDimension(width, height);
        }

    }

}

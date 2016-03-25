package com.lihonghao.app.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lihonghao.app.R;
import com.lihonghao.app.widget.GlideCircleTransform;

public class GlideUtils {
    /**
     * glide加载图片
     */
    public static void display(ImageView view, String url) {
        displayUrl(view, url, R.mipmap.ic_launcher);
    }


    /**
     * glide加载图片
     */
    private static void displayUrl(final ImageView view, String url, @DrawableRes int defaultImage) {
        // 不能崩
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            Glide.with(context)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(defaultImage)
                    .crossFade()
                    .centerCrop()
                    .into(view)
                    .getSize((width, height) -> {
                        if (!view.isShown()) {
                            view.setVisibility(View.VISIBLE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayNative(final ImageView view, @DrawableRes int resId) {
        // 不能崩
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            Glide.with(context)
                    .load(resId)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .centerCrop()
                    .into(view)
                    .getSize((width, height) -> {
                        if (!view.isShown()) {
                            view.setVisibility(View.VISIBLE);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void displayCircleHeader(ImageView view, @DrawableRes int res) {
        // 不能崩
        if (view == null) {
            return;
        }
        Context context = view.getContext();
        // View你还活着吗？
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }

        try {
            Glide.with(context)
                    .load(res)
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .bitmapTransform(new GlideCircleTransform(context))
                    .crossFade()
                    .into(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

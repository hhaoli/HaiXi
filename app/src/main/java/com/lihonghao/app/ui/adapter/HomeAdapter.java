package com.lihonghao.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.lihonghao.app.R;
import com.lihonghao.app.data.entity.AtsEntity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private Context mContext;
    private ArrayList<AtsEntity.DataEntity> mList;

    public HomeAdapter(Context context, ArrayList<AtsEntity.DataEntity> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home, null);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        holder.setData(mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HomeHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.home_icon)
        ImageView mHomeIcon;
        @Bind(R.id.home_name)
        TextView mHomeName;

        public HomeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(AtsEntity.DataEntity entity, int position) {
            Picasso.with(mContext).load(entity.getThumb()).into(mHomeIcon);
            mHomeName.setText("item==" + position);
        }
    }


}

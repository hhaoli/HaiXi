package com.lihonghao.app.widget.recycler;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * 作者：鸿浩
 * 邮箱：hhaoli@sina.cn
 * 时间：2016/1/26
 * 描述：
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter<T>.ViewHolder> {


    private static final int TYPE_NORMAL = 0x01;
    private static final int TYPE_HEADER = 0x02;
    private static final int TYPE_FOOTER = 0x03;
    private View mHeaderView;
    private View mFooterView;

    private SparseBooleanArray mSelectedItems;
    private OnItemClickListener<T> mOnItemClickListener;
    private List<T> mDatas;

    protected abstract int layoutResID();

    protected abstract void initView(View itemView);

    protected abstract void setData(T data);

    public BaseRecyclerAdapter() {
        init(new ArrayList<T>());
    }

    public BaseRecyclerAdapter(T[] datas) {
        init(Arrays.asList(datas));
    }

    public BaseRecyclerAdapter(List<T> datas) {
        init(datas);
    }

    private void init(List<T> datas) {
        this.mDatas = datas;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    public int getHeaderCount() {
        return mHeaderView != null ? 1 : 0;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount());
    }

    public View getFooterView() {
        return mFooterView;
    }

    public int getFooterCount() {
        return mFooterView != null ? 1 : 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {
            return null;
        } else if (mFooterView != null && viewType == TYPE_FOOTER) {
            return null;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutResID(), parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_HEADER || getItemViewType(position) == TYPE_FOOTER) {
            return;
        }

        holder.populate(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        int size = getHeaderCount() + getFooterCount();
        if (mDatas != null) {
            size = size + mDatas.size();
        }
        return size;
    }

    @Override
    public int getItemViewType(int position) {
        if (getHeaderCount() == 1 && position == 0) return TYPE_HEADER;
        if (getFooterCount() == 1 && position == getItemCount()) return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getItemViewType(position) == TYPE_HEADER
                            ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(BaseRecyclerAdapter<T>.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams
                && holder.getLayoutPosition() == 0) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    public interface OnItemClickListener<T> {

        void onItemClick(View view, T item, boolean isLongClick);
    }

    public void setOnItemClickListener(final OnItemClickListener<T> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public ViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
//            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void populate(T data) {
            setData(data);
        }

        @Override
        public void onClick(View v) {
            handleClick(v, false);
        }

        @Override
        public boolean onLongClick(View v) {
            return handleClick(v, true);
        }

        private boolean handleClick(View v, boolean isLongClick) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, mDatas.get(getAdapterPosition()), isLongClick);
                return true;
            }
            return false;
        }
    }

    public void updateList(List<T> list) {
        this.mDatas = list;
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        if (mSelectedItems.get(position, false)) {
            mSelectedItems.delete(position);
        } else {
            mSelectedItems.put(position, true);
        }
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return mSelectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(mSelectedItems.size());
        for (int i = 0; i < mSelectedItems.size(); i++) {
            items.add(mSelectedItems.keyAt(i));
        }
        return items;
    }

    //获取数据集合
    public List<T> getDatas() {
        return mDatas;
    }

    // 在集合头部添加新的数据集合（下拉从服务器获取最新的数据集合，例如新浪微博加载最新的几条微博数据）
    public void refreshDatas(List<T> datas) {
        if (datas != null) {
            mDatas.addAll(0, datas);
            notifyItemRangeInserted(0, datas.size());
        }
    }

    // 在集合尾部添加更多数据集合（上拉从服务器获取更多的数据集合，例如新浪微博列表上拉加载更晚时间发布的微博数据）
    public void loadMoreDatas(List<T> datas) {
        if (datas != null) {
            mDatas.addAll(mDatas.size(), datas);
            notifyItemRangeInserted(mDatas.size(), datas.size());
        }
    }

    //设置全新的数据集合，如果传入null，则清空数据列表（第一次从服务器加载数据，或者下拉刷新当前界面数据表）
    public void setDatas(List<T> datas) {
        if (datas != null) {
            mDatas = datas;
        } else {
            mDatas.clear();
        }
        notifyDataSetChanged();
    }

    //清空数据列表
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    // 删除指定索引数据条目
    public void removeItem(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    // 删除指定数据条目
    public void removeItem(T data) {
        removeItem(mDatas.indexOf(data));
    }

    // 在指定位置添加数据条目
    public void addItem(int position, T data) {
        mDatas.add(position, data);
        notifyItemInserted(position);
    }

    // 在集合头部添加数据条目
    public void addFirstItem(T data) {
        addItem(0, data);
    }

    //在集合末尾添加数据条目
    public void addLastItem(T data) {
        addItem(mDatas.size(), data);
    }

    //替换指定索引的数据条目
    public void setItem(int location, T newData) {
        mDatas.set(location, newData);
        notifyItemChanged(location);
    }

    //替换指定数据条目
    public void setItem(T oldData, T newData) {
        setItem(mDatas.indexOf(oldData), newData);
    }

    //移动数据条目的位置
    public void moveItem(int fromPosition, int toPosition) {
        mDatas.add(toPosition, mDatas.remove(fromPosition));
        notifyItemMoved(fromPosition, toPosition);
    }
}

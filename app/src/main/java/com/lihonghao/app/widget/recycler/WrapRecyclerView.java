package com.lihonghao.app.widget.recycler;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class WrapRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFooterViews = new ArrayList<>();
    private static final int TYPE_HEADER = 0x01;
    private static final int TYPE_NORMAL = 0x02;
    private static final int TYPE_FOOTER = 0x03;
    private WrapAdapter mWrapAdapter;

    public WrapRecyclerView(Context context) {
        this(context, null);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WrapRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) {
//        mHeaderViews.clear();
        mHeaderViews.add(view);
    }

    public void addFooterView(View view) {
//        mFooterViews.clear();
        mFooterViews.add(view);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mWrapAdapter = new WrapAdapter(mHeaderViews, mFooterViews, adapter);
        super.setAdapter(mWrapAdapter);
        mWrapAdapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }
    private final RecyclerView.AdapterDataObserver mDataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (adapter.getItemCount() < 1) {
                WrapRecyclerView.this.setVisibility(View.GONE);
            } else {
                WrapRecyclerView.this.setVisibility(View.VISIBLE);
            }
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    };

    private class WrapAdapter extends RecyclerView.Adapter<ViewHolder> {
        private ArrayList<View> headerViews;
        private ArrayList<View> footerViews;
        private RecyclerView.Adapter adapter;

        public WrapAdapter(ArrayList<View> headerViews, ArrayList<View> footerViews, Adapter adapter) {
            this.headerViews = headerViews;
            this.footerViews = footerViews;
            this.adapter = adapter;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager grid = ((GridLayoutManager) manager);
                grid.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (isHeader(position) || isFooter(position)) ? grid.getSpanCount() : 1;
                    }
                });
            }
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams &&
                    (isHeader(holder.getLayoutPosition()) || isFooter(holder.getLayoutPosition()))) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) lp;
                params.setFullSpan(true);
            }
        }

        public boolean isHeader(int position) {
            return position >= 0 && position < headerViews.size();
        }

        public boolean isFooter(int position) {
            return position < getItemCount() && position >= getItemCount() - footerViews.size();
        }

        public int getHeaderCount() {
            return headerViews.size();
        }

        public int getFooterCount() {
            return footerViews.size();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                return new WrapViewHolder(headerViews.get(0));
            } else if (viewType == TYPE_FOOTER) {
                return new WrapViewHolder(footerViews.get(0));
            }
            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (isHeader(position)) return;
            int adjPosition = position - getHeaderCount();
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    adapter.onBindViewHolder(holder, adjPosition);
                    return;
                }
            }
        }

        @Override
        public int getItemCount() {
            if (adapter != null) {
                return getHeaderCount() + getFooterCount() + adapter.getItemCount();
            } else {
                return getHeaderCount() + getFooterCount();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isHeader(position)) {
                return TYPE_HEADER;
            }
            if (isFooter(position)) {
                return TYPE_FOOTER;
            }
            int adjPosition = position - getHeaderCount();
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return adapter.getItemViewType(adjPosition);
                }
            }
            return TYPE_NORMAL;
        }

        @Override
        public long getItemId(int position) {
            if (adapter != null && position >= getHeaderCount()) {
                int adjPosition = position - getHeaderCount();
                int adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    return adapter.getItemId(adjPosition);
                }
            }
            return -1;
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            if (adapter != null) {
                adapter.unregisterAdapterDataObserver(observer);
            }
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            if (adapter != null) {
                adapter.registerAdapterDataObserver(observer);
            }
        }

        private class WrapViewHolder extends RecyclerView.ViewHolder {
            public WrapViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
}

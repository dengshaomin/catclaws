package com.coder.catclaws.widgets.codexrefreshview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.andview.refreshview.R;
import com.andview.refreshview.XRefreshView;
import com.coder.catclaws.widgets.BaseLayout;
import com.coder.catclaws.commons.GlobalMsg;

import java.util.List;

/**
 * Created by dengshaomin on 2017/7/24.
 */

public class CodeRecycleView extends BaseLayout {

    public static final int START = 0;

    public static final int END = 1;

    public static final int BOTH = 2;

    public static final int NONE = 3;

    public static final int SUCCESS = 0;

    public static final int ERROR = 1;

    public static final int EMPTY = 2;

    private static final int DEFAULT = -1;

    private static final int WhitchPositionAutoShowLoadMoreFootView = 2;    //2代表倒数第几个

    private int refreshState = DEFAULT;

    private XRefreshView xRefreshView;

    private RecyclerView recycleView;

    private HeaderAndFooterWrapper headerAndFooterWrapper;

    RecyclerView.LayoutManager layoutManager;

    RecyclerView.Adapter adapter;

    public static final int pageSize = 10;

    private int pageIndex = 1;

    private CodeRecyclerViewFooter footView;

    private XRefreshView.XRefreshViewListener xRefreshViewListener;

    private int refreMode = BOTH;

    public CodeRecycleView(Context context) {
        super(context);
    }

    public CodeRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CodeRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int setContentLayout() {
        return R.layout.code_recycle_view;
    }

    public void addHeaderView(View view) {
        if (adapter == null) {
            try {
                throw new Exception("must set adapter first");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "must set adapter first");
            }
            return;
        }
        if (headerAndFooterWrapper == null) {
            headerAndFooterWrapper = new HeaderAndFooterWrapper<>(adapter);
        }
        headerAndFooterWrapper.addHeaderView(view);
        if (!(recycleView.getAdapter() instanceof HeaderAndFooterWrapper)) {
            recycleView.setAdapter(headerAndFooterWrapper);
        }
    }

    public void setOnItemClickListener(final MultiItemTypeAdapter.OnItemClickListener onItemClickListener) {
        if (!(adapter instanceof RecyclerView.Adapter)) {
            try {
                throw new Exception("adapter must instanceof recycleview.adapter");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (adapter == null) {
            try {
                throw new Exception("must set adapter first");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "must set adapter first");
            }
            return;
        }
        if (!(adapter instanceof MultiItemTypeAdapter)) {
            try {
                throw new Exception("adapter must instanceof MultiItemTypeAdapter,if not,set click event in yourself " +
                        "adapter");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ((MultiItemTypeAdapter) adapter).setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, holder, position - headerAndFooterWrapper.getHeadersCount());
                    }
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemLongClick(view, holder, position - headerAndFooterWrapper.getHeadersCount());
                    }
                    return onItemClickListener == null ? false : true;
                }
            });
        }
    }

    /**
     * beforeloadmore为true新增footview在加载更多footview之前
     */
    public void addFootView(View view, boolean beforeLoadMore) {
        if (adapter == null) {
            try {
                throw new Exception("must set adapter first");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "must set adapter first");
            }
            return;
        }
        if (headerAndFooterWrapper == null) {
            headerAndFooterWrapper = new HeaderAndFooterWrapper<>(adapter);
        }

        if (footView != null && headerAndFooterWrapper.hasFootView(footView)) {
            headerAndFooterWrapper.addFootView(view, beforeLoadMore);
        } else {
            headerAndFooterWrapper.addFootView(view, false);
        }
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (!(adapter instanceof RecyclerView.Adapter)) {
            try {
                throw new Exception("adapter must instanceof recycleview.adapter");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        this.adapter = adapter;
        if (headerAndFooterWrapper == null) {
            headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        } else {
            headerAndFooterWrapper.setmInnerAdapter(adapter);
        }
        recycleView.setAdapter(headerAndFooterWrapper);
    }


    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        recycleView.addItemDecoration(decor);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layout) {
        this.layoutManager = layout;
        recycleView.setLayoutManager(layout);
    }

    public void setSpringBackTime(int times) {
        xRefreshView.setPinnedTime(times);
    }

    public void setAutoRefresh(boolean autoRefresh) {
        xRefreshView.setAutoRefresh(autoRefresh);
    }

    public void setSpringBackMode(int mode) {
        switch (mode) {
            case START:
                xRefreshView.enableRecyclerViewPullDown(true);
                xRefreshView.enableRecyclerViewPullUp(false);
                xRefreshView.setPullLoadEnable(false);
                break;
            case END:
                xRefreshView.enableRecyclerViewPullUp(true);
                xRefreshView.enableRecyclerViewPullDown(false);
                break;
            case BOTH:
                xRefreshView.enableRecyclerViewPullUp(true);
                xRefreshView.enableRecyclerViewPullDown(true);
                break;
            case NONE:
                xRefreshView.enableRecyclerViewPullUp(false);
                xRefreshView.enableRecyclerViewPullDown(false);
                break;
        }
    }

    public void setRefreshMode(int mode) {
        refreMode = mode;
        switch (mode) {
            case START:
                if (headerAndFooterWrapper != null && footView != null && headerAndFooterWrapper.hasFootView
                        (footView)) {
                    headerAndFooterWrapper.removeFootView(footView);
                }
                xRefreshView.setPullRefreshEnable(true);
                xRefreshView.setPullLoadEnable(false);
                break;
            case END:
                if (headerAndFooterWrapper == null) {
                    headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
                }
                if (footView == null) {
                    footView = new CodeRecyclerViewFooter(getmContext());
//                    footView.setVisibility(adapter == null || adapter.getItemCount() == 0 ? GONE : VISIBLE);
                    footView.setViewData(adapter != null && adapter.getItemCount() % pageSize == 0 ? false : true);
                }
                if (!headerAndFooterWrapper.hasFootView(footView)) {
                    headerAndFooterWrapper.addFootView(footView);
                    headerAndFooterWrapper.notifyDataSetChanged();
                }
                xRefreshView.setPullRefreshEnable(false);
                xRefreshView.setPullLoadEnable(true);
                break;
            case BOTH:
                if (headerAndFooterWrapper == null) {
                    headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
                }
                if (footView == null) {
                    footView = new CodeRecyclerViewFooter(getmContext());
//                    footView.setVisibility(adapter == null || adapter.getItemCount() == 0 ? GONE : VISIBLE);
                    footView.setViewData(adapter != null && adapter.getItemCount() % pageSize == 0 ? false : true);
                }
                if (!headerAndFooterWrapper.hasFootView(footView)) {
                    headerAndFooterWrapper.addFootView(footView);
                    headerAndFooterWrapper.notifyDataSetChanged();
                }
                xRefreshView.setPullRefreshEnable(true);
                xRefreshView.setPullLoadEnable(false);
                break;
            case NONE:
                if (headerAndFooterWrapper != null && footView != null && headerAndFooterWrapper.hasFootView
                        (footView)) {
                    headerAndFooterWrapper.removeFootView(footView);
                }
                xRefreshView.setPullRefreshEnable(false);
                xRefreshView.setPullLoadEnable(false);
                break;
        }
        footView.setNeedBootmTip(false);
    }

    public void setAutoLoadMore(boolean autoLoadMore) {
        xRefreshView.setAutoLoadMore(autoLoadMore);
    }

    public void setXRefreshViewListener(final XRefreshView.XRefreshViewListener l) {
        xRefreshViewListener = l;
        xRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {
                pageIndex = 1;
                refreshState = START;
                if (l != null) {
                    l.onRefresh(isPullDown);
                }
            }

            @Override
            public void onLoadMore(boolean isSilence, int index) {
                needLoadMore();
            }
        });
    }

    private void needLoadMore() {
        if (refreMode == START || refreMode == NONE || refreshState == END) {
            return;
        }
//        if (footView == null) {
//            footView = new CodeRecyclerViewFooter(getmContext());
//        }
//        if (headerAndFooterWrapper == null) {
//            headerAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
//        }
//        if (!headerAndFooterWrapper.hasFootView(footView)) {
//            headerAndFooterWrapper.addFootView(footView);
//            headerAndFooterWrapper.notifyDataSetChanged();
//        }
        if (adapter.getItemCount() == 0 || adapter.getItemCount() % pageSize != 0) {
            return;
        }
        if (footView != null) {
//            footView.setVisibility(adapter != null && adapter.getItemCount() != 0 ? VISIBLE : GONE);
            footView.setViewData(adapter != null && adapter.getItemCount() % pageSize == 0 ? false : true);
        }
        this.pageIndex = adapter.getItemCount() / pageSize + 1;
        refreshState = END;
        if (xRefreshViewListener != null) {
            xRefreshViewListener.onLoadMore(true, this.pageIndex);
        }
    }

    public void refreshComplete(int state) {
        if (headerAndFooterWrapper != null) {
            headerAndFooterWrapper.notifyDataSetChanged();
        } else if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
        if (refreshState == START) {
            xRefreshView.stopRefresh();
        } else if (refreshState == END) {
            xRefreshView.stopLoadMore();
        }
        refreshState = DEFAULT;
        if (footView != null) {
            footView.setViewData(adapter == null || adapter.getItemCount() % pageSize != 0 ? true : false);
        }
        switch (state) {
            case SUCCESS:
                break;
            case ERROR:
                break;
            case EMPTY:
                break;
        }
    }

    @Override
    public void initView() {
        xRefreshView = (XRefreshView) getRootView().findViewById(R.id.xRefreshView);
        recycleView = (RecyclerView) getRootView().findViewById(R.id.recycleView);
        recycleView.setNestedScrollingEnabled(false);
        xRefreshView.setPinnedTime(1000);
        xRefreshView.setMoveForHorizontal(true);
        xRefreshView.enableReleaseToLoadMore(true);
        xRefreshView.enableRecyclerViewPullUp(true);
        xRefreshView.enablePullUpWhenLoadCompleted(true);
        xRefreshView.setPullLoadEnable(false);
        xRefreshView.setAutoLoadMore(true);

        setRefreshMode(BOTH);
        setSpringBackMode(BOTH);
        //设置静默加载时提前加载的item个数
//        xefreshView1.setPreLoadCount(4);
        //设置Recyclerview的滑动监听
        xRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
//		// 实现Recyclerview的滚动监听，在这里可以自己处理到达底部加载更多的操作，可以不实现onLoadMore方法，更加自由
        xRefreshView.setOnRecyclerViewScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (dx > 0 && isScrollBottom()) {
                    needLoadMore();
                }
            }

            public void onScrollStateChanged(RecyclerView recyclerView,
                    int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (isScrollBottom()) {
                        needLoadMore();
                    }
                }
            }
        });
    }


    private boolean isScrollBottom() {
        //recyclerView.canScrollVertically(1) //是否滑动到最底部
        //recyclerView.canScrollVertically(-1)  //是否滑动到最顶部
        if (adapter == null) {
            return true;
        }
        int realyCount = adapter.getItemCount();
        if (layoutManager instanceof LinearLayoutManager) {
            return realyCount - WhitchPositionAutoShowLoadMoreFootView <= ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof GridLayoutManager) {
            return realyCount - WhitchPositionAutoShowLoadMoreFootView <= ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            int[] into = new int[0];
            ((StaggeredGridLayoutManager)
                    layoutManager).findLastVisibleItemPositions(into);
            if (into != null) {
                return realyCount - WhitchPositionAutoShowLoadMoreFootView <= into[into.length - 1];
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public void initBundleData() {

    }

    @Override
    public void getNetData() {

    }

    @Override
    public List<String> regeistEvent() {
        return null;
    }

    @Override
    public void eventComming(GlobalMsg globalMsg) {

    }


    @Override
    public void setViewData(Object data) {

    }

    public void reset() {
        if (headerAndFooterWrapper != null) {
            headerAndFooterWrapper.removeFootView();
            headerAndFooterWrapper.removeHeaderView();
        }
    }
}

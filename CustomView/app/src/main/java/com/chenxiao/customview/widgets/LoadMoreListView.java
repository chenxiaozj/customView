package com.chenxiao.customview.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.chenxiao.customview.R;


/**
 * Created by chenxiao on 14-10-20.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {
    private boolean mSupportLoadMore = false;
    private OnScrollListener mOnScrollListener;
    private OnLoadMoreListener mOnLoadMoreListener;
    private View mFooterView;
    private boolean mLastItem;
    private boolean mLoading;

    public LoadMoreListView(Context context) {
        super(context);
        init(context, null);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadMoreListView);
        mSupportLoadMore = typedArray.getBoolean(R.styleable.LoadMoreListView_supportLoadMore, false);
        typedArray.recycle();
        handleFooterView(mSupportLoadMore);
        super.setOnScrollListener(this);

    }

    public boolean isSupportLoadMore() {
        return mSupportLoadMore;
    }

    /**
     * set the listview should support loadMore function.
     * @param supportLoadMore
     */
    public void setSupportLoadMore(boolean supportLoadMore) {
        this.mSupportLoadMore = supportLoadMore;
        handleFooterView(mSupportLoadMore);
    }

    private void handleFooterView(boolean supportLoadMore) {
        if (supportLoadMore && mFooterView == null) {
            mFooterView = LayoutInflater.from(getContext()).inflate(R.layout.load_more_footer, null);
            showFooterView();
        } else if (!supportLoadMore && mFooterView != null) {
            hideFooterView();
        }

    }

    private void showFooterView() {
        if (mFooterView != null && getFooterViewsCount() == 0) {
            addFooterView(mFooterView);
        }
    }

    private void hideFooterView() {
        if (mFooterView != null && getFooterViewsCount() > 0) {
            removeFooterView(mFooterView);
        }

    }

    public View getFooterView() {
        return mFooterView;
    }

    public void setFooterView(View footerView) {
        removeFooterView(mFooterView);
        this.mFooterView = footerView;
        addFooterView(mFooterView);
    }

    public boolean isLastItem() {
        return mLastItem;
    }

    /**
     * When we have got the last item, we should let the LoadMoreListView know, and call setLastItem(true)
     *
     * @param lastItem
     */
    public void setLastItem(boolean lastItem) {
        this.mLastItem = lastItem;
    }

    /**
     * you must call loadCompleted when you have loaded the data you want
     */
    public void loadCompleted() {
        setLoading(false);
    }

    ;


    public boolean isLoading() {
        return mLoading;
    }

    /**
     * set the loading status.
     *
     * @param loading
     */
    public void setLoading(boolean loading) {
        this.mLoading = loading;
    }

    public OnScrollListener getScrollListener() {
        return mOnScrollListener;
    }

    public void setOnScrollListener(OnScrollListener scrollListener) {
        this.mOnScrollListener = scrollListener;
    }

    public OnLoadMoreListener getonLoadMoreListener() {
        return mOnLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
        }
        if (mSupportLoadMore && mLastItem && firstVisibleItem + visibleItemCount >= totalItemCount) {
            Toast.makeText(view.getContext(), "加载完毕", Toast.LENGTH_SHORT).show();
            hideFooterView();
            return;
        }
        if (mSupportLoadMore && !mLoading && !mLastItem) {
            if (firstVisibleItem + visibleItemCount + 5 >= totalItemCount) {
                if (mOnLoadMoreListener != null) {
                    showFooterView();
                    mLoading = true;
                    mOnLoadMoreListener.onLoadMore();
                }
            }
        }
    }

    public interface OnLoadMoreListener {
        public void onLoadMore();
    }
}

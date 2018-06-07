package demosample.nirav.com.demoproject.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;



public class ItemDecorationAlbumColumns extends RecyclerView.ItemDecoration {

    private int mSizeGridSpacingPx;
    private int mGridSize;

    public ItemDecorationAlbumColumns(int gridSpacingPx, int gridSize) {
        mSizeGridSpacingPx = gridSpacingPx;
        mGridSize = gridSize;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % mGridSize; // item column
        outRect.left = column * mSizeGridSpacingPx / mGridSize;
        outRect.right = mSizeGridSpacingPx - (column + 1) * mSizeGridSpacingPx / mGridSize;
        if (position >= mGridSize) {
            outRect.top = mSizeGridSpacingPx; // item top
        }
    }
}
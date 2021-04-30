package kz.smartideagroup.pillikan.common.views;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class SoftPaginationScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager layoutManager;

    public SoftPaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    public abstract void loadMoreItems();

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        // Check if the next page is not currently loading and if we actually have the next page.
        if (!isLoading() && !isLastPage()) {

            int visibleItemsCount        = layoutManager.getChildCount();
            int totalItemsCount          = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            // Check if the last visible item is last in the list, too.
            // If so, then load the next page.
            if (visibleItemsCount + firstVisibleItemPosition >= totalItemsCount && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }
}

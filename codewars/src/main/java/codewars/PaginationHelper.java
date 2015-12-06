package sk.mysko.codewars;
import java.util.List;

public class PaginationHelper<I> {
    List<I> data;
    int itemsPerPage;

    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
        this.data = collection;
    }

    /**
     * returns the number of items within the entire collection
     */
    public int itemCount() {
        return data.size();

    }

    /**
     * returns the number of pages
     */
    public int pageCount() {
        if (itemsPerPage == 1) {
            return itemCount();
        }
        return (itemCount() / itemsPerPage) + (itemCount() % itemsPerPage);
    }

    /**
     * returns the number of items on the current page. page_index is zero based.
     * this method should return -1 for pageIndex values that are out of range
     */
    public int pageItemCount(int pageIndex) {
        if (pageIndex < 0 || pageIndex >= pageCount()) {
            return -1;
        }

        if (pageIndex < pageCount() - 1) {
            return itemsPerPage;
        } else {
            return itemCount() % itemsPerPage == 0 ? itemsPerPage : itemCount() % itemsPerPage;
        }

    }

    /**
     * determines what page an item is on. Zero based indexes
     * this method should return -1 for itemIndex values that are out of range
     */
    public int pageIndex(int itemIndex) {
        if (itemIndex < 0 || itemIndex >= itemCount()) {
            return -1;
        }

        return (itemIndex / itemsPerPage);
    }
}
package itemsifters;

import java.util.stream.Stream;

public interface ISift<T> {

    /**
     * A filter is a function that the sifter will run on every item.
     * It takes an item, and returns true if the item passes the filter.
     * Example:
     *      private JFXCheckBox showValidItems;
     *      Filter<Item> validFilter = (item) -> {
     *          boolean showValidChecked = showValidItems.isSelected();
     *          boolean itemIsValid = item.isValid()
     *          boolean passesFilter = showValidChecked && itemIsValid;
     *          return passesFilter;
     *      }
     * The main idea here is that the filter is defined in the scope of any inputs needed, such as a checkbox,
     * allowing the sifter to refer to those inputs for each item whenever it updates.
     * @param <T> The type of item being filtered.
     */
    interface Filter<T> {
        boolean filter(T item);
    }

    /**
     * apply all filters to a stream of items, return the filtered stream.
     */
    Stream<T> sift(Stream<T> items);

    /**
     * Add a hard filter. An item must pass all hard filters.
     */
    void addHardFilter(Filter<T> filter);

    /**
     * Add a soft filter. An item must pass at least one soft filter.
     */
    void addSoftFilter(Filter<T> filter);

    void removeHardFilter(Filter<T> filter);

    void removeSoftFilter(Filter<T> filter);
}

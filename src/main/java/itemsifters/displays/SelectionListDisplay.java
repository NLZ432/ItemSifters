package itemsifters.displays;

import com.jfoenix.controls.JFXListView;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This is a display that maintains selected items, but keeping a list and sticking whether or not it has been
 * selected into its cell creator as a boolean. The benefit of this is you can maintain selections in between updates.
 * This is totally unnecessary, because your cellCreator, and any filters can just be defined in the scope of your
 * "selections" list, meaning you can refer to it similar to how this thing does it.
 */
public class SelectionListDisplay<Item, Cell> extends ListViewDisplay<Item, Cell> {
    SelectionCellCreator<Item, Cell> selectionCellCreator;
    List<Item> selectedItems;

    public interface SelectionCellCreator<Item, Cell> {
        Cell makeCell(Item item, boolean selected);
    }

    public SelectionListDisplay(JFXListView<Cell> listView, SelectionCellCreator<Item, Cell> selectionCellCreator) {
        this.selectedItems = new LinkedList<>();
        this.selectionCellCreator = selectionCellCreator;
        this.listView = listView;
    }

    @Override
    protected void populateListView(Stream<Item> items) {
        selectedItems.forEach(selectedItem -> {
            Cell cell = selectionCellCreator.makeCell(selectedItem, true);
            listView.getItems().add(cell);
        });
        items.filter(item -> !selectedItems.contains(item)).forEach(item -> {
            Cell cell = selectionCellCreator.makeCell(item, false);
            listView.getItems().add(cell);
        });
    }

    public void selectItem(Item item) {
        selectedItems.add(item);
    }

    public void deSelectItem(Item item) {
        selectedItems.remove(item);
    }

    public void clearSelectedItems() {
        selectedItems.clear();
    }

    public void setSelectionCellCreator(SelectionCellCreator<Item, Cell> selectionCellCreator) {
        this.selectionCellCreator = selectionCellCreator;
    }

    public List<Item> getSelectedItems() {
        return selectedItems;
    }
}

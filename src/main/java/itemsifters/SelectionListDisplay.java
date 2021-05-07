package itemsifters;

import com.jfoenix.controls.JFXListView;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public class SelectionListDisplay<Item, Cell> extends ListViewDisplay<Item, Cell> {
    SelectionCellCreator<Item, Cell> selectionCellCreator;
    List<Item> selectedItems;

    public interface SelectionCellCreator<Item, Cell> {
        Cell makeCell(Item item, boolean selected);
    }

    public SelectionListDisplay(JFXListView<Cell> listView, SelectionCellCreator<Item, Cell> selectionCellCreator) {
        this.selectionCellCreator = selectionCellCreator;
        this.listView = listView;
    }

    @Override
    protected void populateListView(Stream<Item> items) {
        items.forEach(item -> {
            if (selectedItems.contains(item)) {
                Cell cell = selectionCellCreator.makeCell(item, true);
                listView.getItems().add(1, cell);
            }
            else {
                Cell cell = selectionCellCreator.makeCell(item, false);
                listView.getItems().add(cell);
            }
        });
    }

    public void selectItem(Item item) {
        selectedItems.add(item);
    }

    public void deSelectItem(Item item) {
        selectedItems.remove(item);
    }

    public void clearSelectedItems() {
        selectedItems = new LinkedList<>();
    }
}

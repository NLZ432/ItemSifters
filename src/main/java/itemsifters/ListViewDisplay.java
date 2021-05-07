package itemsifters;

import com.jfoenix.controls.JFXListView;

import java.util.stream.Stream;

/**
 * help me improve this class to make it more useful: nllopez@wpi.edu
 * needs stuff that interacts with list view's actual cell factory
 */
public class ListViewDisplay<T, Cell> implements IDisplay<T> {
    CellCreator<T, Cell> cellCreator;
    JFXListView<Cell> listView;

    public interface CellCreator<T, Cell> {
        Cell makeCell(T item);
    }

    public ListViewDisplay(JFXListView<Cell> listView, CellCreator<T, Cell> cellCreator) {
        this.cellCreator = cellCreator;
        this.listView = listView;
    }

    @Override
    public void update(Stream<T> items) {
        clearItems();
        Stream<Cell> cellStream = items.map(string -> cellCreator.makeCell(string));
        cellStream.forEach(cell -> listView.getItems().add(cell));
    }

    public void clearItems() {
        listView.getItems().clear();
    }

}

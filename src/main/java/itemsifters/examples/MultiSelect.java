package itemsifters.examples;

import itemsifters.displays.ListViewDisplay;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import java.util.stream.Stream;
import itemsifters.ItemSifter;
import java.util.LinkedList;
import javafx.geometry.Pos;
import java.util.List;

/**
 * This is an example of how to make a machine that selects multiple items.
 * The selections will persist in between updates, because it keeps a list of selected items.
 * The benefit of the list is that you can apply different filters, causing a reprocessing of all the items,
 * while keeping your selections.
 *
 * This is also an example of using the lower level ItemSifter rather than the ItemSearch, giving us more freedom
 * in exactly what filters we want.
 */
public class MultiSelect {

    private final JFXListView<HBox> searchResultsList;
    private final ItemSifter<Paint> itemSifter;
    private List<Paint> selectedItems;

    /* some params for the cellCreator function */
    private static final double rectWidth = 200;
    private static final double rectHeight = 30;
    private static final double spacing = 15;

    public MultiSelect(JFXCheckBox selectedFilterCheckbox,
                       JFXCheckBox unselectedFilterCheckbox,
                       JFXListView<HBox> searchResultsList) {
        this.searchResultsList = searchResultsList;
        initCheckboxes(selectedFilterCheckbox, unselectedFilterCheckbox);

        /* this is an example of maintaining multiple selected items, so we make a selected items list */
        this.selectedItems = new LinkedList<>();

        /* We create an ItemSifter object.
         * This holds whatever filters we want to be applied to the incoming items.
         * It also can be bound to "IDisplay" objects, which literally just means a function or class with
         * the function update(Stream<T> items). When you update the itemSifter, it runs all the filters, and
         * sends all the passing items out to all its displays.
         */
        itemSifter = new ItemSifter<>();

        /* We add a filter to the item sifter. A filter takes an item of your defined type,
         * and uses whatever you want to use to determine if the item passes the filter.
         * The main idea here is that we define our filter in the scope of the checkboxes,
         * so that the sifter's filters can access their values every time it runs, for every item in the sifter.
         * Our filters here only filters whether or not an item was selected, so it must be defined in the scope of
         * the checkboxes, and the selections list.
         */
        itemSifter.addSoftFilter(
                (item) -> {
                    if (selectedFilterCheckbox.isSelected()) {
                        return selectedItems.contains(item);
                    }
                    else {
                        return false;
                    }
                }
        );

        /*
         * We specify that these are "soft filters" because only one of them needs to pass for the whole
         * item to pass through. We dont need to do this, as we could just look at both checkboxes in one filter,
         * but I think having the option to specify unrelated filters separately may be helpful.
         */
        itemSifter.addSoftFilter(
                (item) -> {
                    if (unselectedFilterCheckbox.isSelected()) {
                        return !selectedItems.contains(item);
                    }
                    else {
                        return false;
                    }
                }
        );

        /* We make a display object. This is an object that holds a list view, and a cellCreator function.
         * When you call update(streamOfItems) on the display, it runs the cellCreator on each item it receives,
         * and puts the output on the list for everyone to see.
         * If you bind this display to an item sifter, it will automatically update
         * whenever the item sifter updates, receiving all the items that passed the sifters filters.
         */
        ListViewDisplay<Paint, HBox> display = new ListViewDisplay<>(searchResultsList);

        /*
         * We now add the CellCreator function. It draws the item however we want with whatever we want,
         * with whatever event handlers we need attached to it. We can define this function in whatever scope we want,
         * as long as we get it to the display object.
         */
        display.setCellCreator(
                (item) -> {
                    /* make an HBox. this is what we will return to be printed. */
                    HBox hbox = new HBox();

                    /* make a checkbox. This is what we use to select the item */
                    JFXCheckBox checkBox = new JFXCheckBox();
                    checkBox.setCheckedColor(item);

                    /* set the checkBox's state based on whether or not it has been previously selected.
                     * This is the only benefit of keeping a selection list, and id appreciate it if
                     * someone came up with a way to get rid of it and maintain selections between updates.
                     */
                    checkBox.setSelected(selectedItems.contains(item));

                    /* set an event handler for the checkbox. This is how we actually select the item. */
                    checkBox.setOnAction(event -> {
                        if (checkBox.isSelected()) {
                            selectedItems.add(item); // add the item if we just selected it
                        }
                        else {
                            selectedItems.remove(item); // remove the item if we unselected it
                        }
                        itemSifter.update();
                    });

                    /* make a rectangle to display the paint. */
                    Rectangle rect = new Rectangle();
                    rect.setFill(item);
                    rect.setWidth(rectWidth);
                    rect.setHeight(rectHeight);

                    /* add them to the hBox */
                    hbox.getChildren().setAll(checkBox, rect);
                    hbox.setSpacing(spacing);
                    hbox.setAlignment(Pos.CENTER_LEFT);

                    return hbox; //return the hBox, where it will be printed to the listView
                }
        );

        /* Bind the display to the itemSifter.
         * All this does is auto send the filtered items to the display whenever we update the sifter.
         */
        itemSifter.addDisplay(display);

        /* Set an event handler on our checkboxes, to update the ItemSifter whenever we change them.
         * This is a small but very important step,
         * as the machine will not rerun the filters without calling update()
         */
        selectedFilterCheckbox.setOnAction(event -> itemSifter.update());
        unselectedFilterCheckbox.setOnAction(event -> itemSifter.update());
    }

    public void addItems(Stream<Paint> paintStream) {
        itemSifter.addItems(paintStream);
    }

    public void addItem(Paint paint) {
        itemSifter.addItem(paint);
    }

    public void update() {
        itemSifter.update();
    }

    private void initCheckboxes(JFXCheckBox selectedFilterCheckbox, JFXCheckBox unselectedFilterCheckbox) {
        selectedFilterCheckbox.setCheckedColor(Color.BLUE);
        selectedFilterCheckbox.setSelected(true);
        unselectedFilterCheckbox.setCheckedColor(Color.BLUE);
        unselectedFilterCheckbox.setSelected(true);
    }
}


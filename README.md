# ItemSifters

This is an API that provides a few tools to easilly make responsive JavaFX objects that filter, sort, and display items.
Please look at the youtube playlist that explains it:
    https://www.youtube.com/playlist?list=PLFAUPeFYQFkZkhSDDbq8MvKDl_xCcdXGe

## Installation

Copy the files in located in src/main/java/itemsifters/ into your project to use the classes.

This repository is a gradle application that runs a simple example of a string search box.
I hope to add more examples here soon.

## Usage

ItemSearcher - give it a JFXTextField and a JFXListView, as well as functions to determine if text matches the item, and to create a cell for the list view.
This is an abstraction of the ItemSifter class, which is a more general and much more configurable tool.
More documentation is coming here soon, but much of the classes are documented in the actual code.
```java
public class ItemSearch<Item, ListCell> {
    private final ItemSifter<Item> itemSifter;

    /**
     * This is a function that will be called every time the ItemSearch updates. The machine will run this function
     * on every item, to determine if the item matches your text. It takes an item, and the search text.
     * Here is an example: (person, searchText) -> return person.getName().contains(searchText)
     * This will return true if the text contains part of their name. This example is case sensitive!
     */
    public interface TextMatcher<T> {
        boolean isMatching(T item, String text);
    }

    /**
     * This is a function that will be called for every item that passes the filters and is ready to be displayed.
     * It takes in the item, and returns a javaFX item to be put into the list view.
     * Example: (item) -> return new Label(item);
     */
    public interface CellCreator<T, Cell> {
        Cell makeCell(T item);
    }

    /**
     * An object that handles searching and displaying items.
     * Give it your search input, and a list to display the search results.
     * @param searchBar a JFXTextField object that you wish to act as the search bar.
     * @param searchResultsList a JFXListView<Item> object that will list the search results and allow selection.
     * @param matcher This is a function that will be called every time the ItemSearch updates. The machine will run this function
     *                on every item, to determine if the item matches your text. It takes an item, and the search text.
     *                Here is an example: (person, searchText) -> return person.getName().contains(searchText)
     *                This will return true if the text contains part of their name. This example is case sensitive!
     * @param cellCreator a function that defines the list items.
     *                    It must take an item, and return the list cell.
     *                    The returned cell's type must be the same as the cells of the ListView you are using.
     */
    public ItemSearch(JFXTextField searchBar,
                      JFXListView<ListCell> searchResultsList,
                      TextMatcher<Item> matcher,
                      CellCreator<Item, ListCell> cellCreator) {

        /*
         * Make an ItemSifter object. This is the core of the API.
         * Add a hard filter to the sifter that gets the text from the search bar,
         * and uses the text with your matching function to filter each item.
         */
        itemSifter = new ItemSifter<>();
        itemSifter.addHardFilter(item -> matcher.isMatching(item, searchBar.getText()));

        /*
         * Create a list view display object, which takes the cell creator.
         * This object will receive items from the itemSifter when it updates, and display them.
         */
        IDisplay<Item> display = new ListViewDisplay<>(searchResultsList, cellCreator::makeCell);
        itemSifter.addDisplay(display);

        searchBar.setOnKeyTyped(event -> update());
    }

```

## Contributing
All contributes are totally welcome, please help me improve this stuff for future students. Email me at nllopez@wpi.edu or simply open a pull request to help!



package views;

import com.jfoenix.controls.*;
import itemsifters.examples.MultiSelect;
import itemsifters.examples.StringSearcher;
import itemsifters.examples.TableEditor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;
import java.net.URL;

public class Examples implements Initializable {

    @FXML
    private JFXRadioButton stringSearchButton;

    @FXML
    private VBox stringSearchVBox;

    @FXML
    private JFXTextField stringSearchBar;

    @FXML
    private JFXListView<Label> stringListView;

    @FXML
    private JFXRadioButton tableEditorButton;

    @FXML
    private VBox tableEditorVBox;

    @FXML
    private JFXTextField tableSearchBar;

    @FXML
    private JFXListView<HBox> tableListView;

    @FXML
    private JFXRadioButton multiSelectButton;

    @FXML
    private VBox multiSelectVBox;

    @FXML
    private JFXListView<HBox> multiSelectView;

    @FXML
    private JFXCheckBox showSelectedCheckBox;

    @FXML
    private JFXCheckBox showUnselectedCheckBox;

    private StringSearcher stringSearcher;
    private TableEditor tableEditor;
    private MultiSelect multiSelect;

    private void hideWindows() {
        stringSearchVBox.setVisible(false);
        tableEditorVBox.setVisible(false);
        multiSelectVBox.setVisible(false);
    }

    private void showStringSearch() {
        hideWindows();
        stringSearchButton.setSelected(true);
        tableEditorButton.setSelected(false);
        multiSelectButton.setSelected(false);
        stringSearchVBox.setVisible(true);
    }

    private void showTableEditor() {
        hideWindows();
        stringSearchButton.setSelected(false);
        tableEditorButton.setSelected(true);
        multiSelectButton.setSelected(false);
        tableEditorVBox.setVisible(true);
    }

    private void showMultiSelect() {
        hideWindows();
        stringSearchButton.setSelected(false);
        tableEditorButton.setSelected(false);
        multiSelectButton.setSelected(true);
        multiSelectVBox.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        stringSearchButton.setOnAction(event -> showStringSearch());
        tableEditorButton.setOnAction(event -> showTableEditor());
        multiSelectButton.setOnAction(event -> showMultiSelect());

        stringSearcher = new StringSearcher(stringSearchBar, stringListView);
        stringSearcher.addString("Bartholomew");
        stringSearcher.addString("Matthew");
        stringSearcher.addString("Andrew");
        stringSearcher.addString("Philip");
        stringSearcher.addString("Thomas");
        stringSearcher.addString("James");
        stringSearcher.addString("Judas");
        stringSearcher.addString("Simon");
        stringSearcher.addString("Peter");
        stringSearcher.addString("James");
        stringSearcher.addString("Jude");
        stringSearcher.addString("John");
        stringSearcher.update();

        tableEditor = new TableEditor(tableSearchBar, tableListView);
        tableEditor.addThing(100,  300, "Mercury");
        tableEditor.addThing(300,  300, "Venus");
        tableEditor.addThing(300,  300, "Earth");
        tableEditor.addThing(250,  300, "Mars");
        tableEditor.addThing(1000, 300, "Jupiter");
        tableEditor.addThing(900,  300, "Saturn");
        tableEditor.addThing(600,  300, "Uranus");
        tableEditor.addThing(700,  300, "Neptune");
        tableEditor.addThing(30,   300, "Pluto");
        tableEditor.update();

        multiSelect = new MultiSelect(showSelectedCheckBox, showUnselectedCheckBox, multiSelectView);
        multiSelect.addItem(Color.RED);
        multiSelect.addItem(Color.WHITE);
        multiSelect.addItem(Color.BLUE);
        multiSelect.addItem(Color.BLACK);
        multiSelect.addItem(Color.TEAL);
        multiSelect.addItem(Color.PURPLE);
        multiSelect.addItem(Color.GREY);
        multiSelect.addItem(Color.FUCHSIA);
        multiSelect.addItem(Color.GREEN);
        multiSelect.addItem(Color.YELLOW);
        multiSelect.addItem(Color.ORANGE);
        multiSelect.update();

        showStringSearch();
    }
}

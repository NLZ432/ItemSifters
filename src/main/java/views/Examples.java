package views;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import itemsifters.examples.StringSearcher;
import itemsifters.examples.TableEditor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ResourceBundle;
import java.net.URL;

public class Examples implements Initializable {

    @FXML
    private JFXTextField stringSearchBar;

    @FXML
    private JFXListView<Label> stringListView;

    @FXML
    private JFXTextField tableSearchBar;

    @FXML
    private JFXListView<HBox> tableListView;

    private StringSearcher stringSearcher;
    private TableEditor tableEditor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
    }
}

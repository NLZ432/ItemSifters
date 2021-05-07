package views;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import itemsifter.examples.StringSearcher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.util.ResourceBundle;
import java.net.URL;

public class Examples implements Initializable {

    @FXML
    private JFXTextField searchBar;

    @FXML
    private JFXListView<Label> listView;

    private StringSearcher stringSearcher;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stringSearcher = new StringSearcher(searchBar, listView);
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
    }
}

package merger;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.collections.FXCollections;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ListView<String> listview;

    ArrayList<String> fileNames;
    ArrayList<File> fileList;
    String saveLocation;

    private void setFileNameItems(){
        ObservableList<String> observableList = FXCollections.observableArrayList(fileNames);
        listview.setItems(observableList);
    }

    public void onClickLoadFiles(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF Files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
        try {
            List<File> files = fileChooser.showOpenMultipleDialog(Main.window);
            for (File file : files) {
                fileNames.add(file.getName());
                fileList.add(file);
            }
            setFileNameItems();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void onClickRemove(){
        try{
            int index = listview.getSelectionModel().getSelectedIndex();
            if(index > -1){
                fileNames.remove(index);
                fileList.remove(index);
                setFileNameItems();
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void onClickMerge(){
        try{
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Save Location");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf"));
            saveLocation = fileChooser.showSaveDialog(Main.window).getPath();

            if(!saveLocation.isEmpty()){
                MergeDialog.display(fileList, saveLocation);
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileList = new ArrayList<>();
        fileNames = new ArrayList<>();
        saveLocation = "";
    }
}

package merger;

import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class MergeDialog {
    public static void display(ArrayList<File> fileList, String saveLocation){
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.setTitle("Saving (Please wait)...");
        stage.setWidth(200);
        stage.setHeight(200);
        stage.setOnCloseRequest(Event::consume);
        ProgressIndicator pi = new ProgressIndicator();
        pi.paddingProperty().setValue(new Insets(4));
        Label label = new Label("Please wait...");
        label.paddingProperty().setValue(new Insets(4));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(pi, label);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();

        try{
            MergePDFs mergePDFs = new MergePDFs();
            mergePDFs.merge(fileList, saveLocation);
        }catch (Exception e){
            System.out.println(e);
        }

        stage.close();
    }
}

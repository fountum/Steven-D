package UI.Controllers;

import Model.MusicFile;
import Model.MusicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class EditMetadata implements Initializable {
    public TextField textTitle;
    public TextField textArtist;
    public TextField textAlbum;
    public TextField textTrackNum;
    public TableView tableMusic;
    public TableColumn colNum;
    public TableColumn colTitle;
    public TableColumn colArtist;
    public TableColumn colAlbum;
    public ImageView imageArt;
    public AnchorPane editMetadata;
    private ObservableList<MusicFile> musicFiles = FXCollections.observableArrayList();

    private MusicManager manager = new MusicManager();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intiCol();
        tableMusic.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);


    }

    private void intiCol() {
        colNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        colAlbum.setCellValueFactory(new PropertyValueFactory<>("album"));
    }

    public void transferFiles(ArrayList<AudioFile> audioFiles) {
        ArrayList<AudioFile> audioFiles1 = audioFiles;
        Iterator<AudioFile> afIter = audioFiles.iterator();
        while (afIter.hasNext()) {
            AudioFile file = afIter.next();
            tableMusic.getItems().add(new MusicFile(file));
        }
    }


    public void editAlbumArt(ActionEvent actionEvent) throws FieldDataInvalidException, IOException {
        File newArt = manager.selectImage(editMetadata);
        ObservableList<MusicFile> selectedFiles = tableMusic.getSelectionModel().getSelectedItems();
        Iterator<MusicFile> sfIter = selectedFiles.iterator();
        Artwork art = ArtworkFactory.getNew();
        art.setFromFile(newArt);
        while (sfIter.hasNext()) {
            try {
                AudioFile audioFile = sfIter.next().getAudioFile();
                Tag tag = audioFile.getTag();
                System.out.println(tag.getFirst(FieldKey.TITLE));
                tag.deleteField(FieldKey.COVER_ART);
                tag.addField(art);
                audioFile.commit();
            } catch (CannotWriteException e) {
                e.printStackTrace();
            }

        }
    }

    public void updateMetadata(ActionEvent actionEvent) {
        ObservableList<MusicFile> selectedFiles = tableMusic.getSelectionModel().getSelectedItems();
        Iterator<MusicFile> sfIter = selectedFiles.iterator();
        if (selectedFiles.size() > 1) {
            /*
            Source: https://stackoverflow.com/questions/8309981/how-to-create-and-show-common-dialog-error-warning-confirmation-in-javafx-2
            Creates a popup that asks the user if they want to change multiple files to have the title they entered.
             */

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Change " + selectedFiles.size() + " files' titles to " + textTitle.getText() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.NO) {
                return;
            }
        }

        while (sfIter.hasNext()) {
            AudioFile audioFile = sfIter.next().getAudioFile();
            Tag tag = audioFile.getTag();
            try {
                tag.setField(FieldKey.TITLE, textTitle.getText());
                tag.setField(FieldKey.ARTIST, textArtist.getText());
                tag.setField(FieldKey.TRACK, textTrackNum.getText());
                tag.setField(FieldKey.ALBUM, textAlbum.getText());
                AudioFileIO.write(audioFile);
            } catch (FieldDataInvalidException e) {
                e.printStackTrace();
            } catch (CannotWriteException e) {
                e.printStackTrace();
            }
        }

        refreshMetadata();
    }

    public void showInfo() {
        if (tableMusic.getSelectionModel().getSelectedItems().size() == 1) {
            MusicFile selectedMusicFile = (MusicFile) tableMusic.getSelectionModel().getSelectedItems().get(0);
            AudioFile selectedAudioFile = selectedMusicFile.getAudioFile();
            Tag tag = selectedAudioFile.getTag();

            textTitle.setText(tag.getFirst(FieldKey.TITLE));
            textAlbum.setText(tag.getFirst(FieldKey.ALBUM));
            textArtist.setText(tag.getFirst(FieldKey.ARTIST));
            textTrackNum.setText(tag.getFirst(FieldKey.TRACK));
        } else {
            textTitle.setText("");
            textAlbum.setText("");
            textArtist.setText("");
            textTrackNum.setText("");
        }

        manager.showCoverArt(tableMusic, imageArt);
    }

    private void refreshMetadata() {
        Iterator<MusicFile> mfIter = musicFiles.iterator();

        while (mfIter.hasNext()) {
            mfIter.next().updateMetadata();
        }

        tableMusic.refresh();
    }

}

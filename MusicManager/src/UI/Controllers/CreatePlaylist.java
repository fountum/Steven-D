package UI.Controllers;

import Model.MusicFile;
import Model.MusicManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import static UI.Controllers.MainWindow.SERIALIZED_MIME_TYPE;


public class CreatePlaylist implements Initializable {
    public TableView tableMusic;
    public TableColumn colNum;
    public TableColumn colTitle;
    public TableColumn colArtist;
    public TableColumn colAlbum;
    public AnchorPane createPlaylist;
    public TextField textPlaylistName;
    private MusicManager manager = new MusicManager();
    private ObservableList<MusicFile> musicFiles = FXCollections.observableArrayList();
    private FileChooser fc = new FileChooser();
    private File playlist;
    private FileReader fr;
    private BufferedReader br;
    private FileWriter fw;
    private BufferedWriter bw;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intiCol();
        tableMusic.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text File", "*.txt"));
        initDragNDrop();
    }

    private void intiCol() {
        colNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        colAlbum.setCellValueFactory(new PropertyValueFactory<>("album"));
    }

    //copy of the one in MainWindow with some things removed
    private void initDragNDrop() {
        tableMusic.setRowFactory(tv -> {
            TableRow<MusicFile> row = new TableRow<>();

            /*
            drag detected:
            Row contents user selects is copied to clipboard -> dragboard to move
             */
            row.setOnDragDetected(event -> {
                if (!row.isEmpty()) {
                    int index = row.getIndex();
                    Dragboard db = row.startDragAndDrop(TransferMode.MOVE);
                    db.setDragView(row.snapshot(null, null));
                    ClipboardContent cc = new ClipboardContent();
                    cc.put(SERIALIZED_MIME_TYPE, index);
                    db.setContent(cc);
                    event.consume();
                }
            });


            /*
            drag over:
            when the cursor moves over another row while having a row selected the transfer mode changes for moving
             */
            row.setOnDragOver(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    if (row.getIndex() != (Integer) db.getContent(SERIALIZED_MIME_TYPE)) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        event.consume();
                    }
                }
            });

            /*
            drop
            Removes playlist in DB from tableMusic and adds it to the row user's cursor is hovered over
             */
            row.setOnDragDropped(event -> {
                Dragboard db = event.getDragboard();
                if (db.hasContent(SERIALIZED_MIME_TYPE)) {
                    boolean update = false;
                    int draggedIndex = (int) db.getContent(SERIALIZED_MIME_TYPE); //index of dragged Row
                    MusicFile draggedMF = (MusicFile) tableMusic.getItems().remove(draggedIndex); //MusicFile associated with draggedIndex

                    //ObservableList to use when song currently playing is displaced
                    ObservableList<MusicFile> temp = FXCollections.observableArrayList();
                    temp.addAll(musicFiles);

                    temp.remove(draggedMF);

                    int dropIndex;

                    if (row.isEmpty()) {
                        dropIndex = tableMusic.getItems().size();
                    } else {
                        dropIndex = row.getIndex();
                    }

                    tableMusic.getItems().add(dropIndex, draggedMF);
                    temp.add(dropIndex, draggedMF);

                    musicFiles.remove(draggedMF);
                    musicFiles.add(dropIndex, draggedMF);

                    event.setDropCompleted(true);
                    tableMusic.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row;
        });
    }

    private void addFiles(ArrayList<AudioFile> audioFiles) {
        tableMusic.getItems().clear();
        Iterator<AudioFile> afIter = audioFiles.iterator();
        while (afIter.hasNext()) {
            AudioFile selectedFile = afIter.next();
            MusicFile mf = new MusicFile(selectedFile);
            musicFiles.add(mf);
        }
        tableMusic.getItems().addAll(musicFiles);
    }

    public void selectFiles() {
        addFiles(manager.getAudioFiles(tableMusic));
    }

    public void selectFolder() {
        addFiles(manager.selectFolder(tableMusic));
    }

    public void selectPlaylist(ActionEvent actionEvent) {
        tableMusic.getItems().clear();
        musicFiles.clear();
        Iterator<AudioFile> afIter = manager.selectPlaylist(createPlaylist).iterator();
        while (afIter.hasNext()) {
            musicFiles.add(new MusicFile(afIter.next()));
        }
        tableMusic.getItems().addAll(musicFiles);
    }

    public void createPlaylist(ActionEvent actionEvent) {
        try {
            fw = new FileWriter(textPlaylistName.getText() + ".txt", false);
            bw = new BufferedWriter(fw);

            Iterator<MusicFile> mfIter = musicFiles.iterator();

            while (mfIter.hasNext()) {
                MusicFile selectedFile = mfIter.next();
                bw.write(selectedFile.getAudioFile().getFile().toString() + "\r");
            }
            bw.write(";");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToPlaylist(ActionEvent actionEvent) {
        try {
            if (playlist.exists()) {
                String fileName = playlist.getName();

                playlist.delete();

                fw = new FileWriter(playlist, false);
                bw = new BufferedWriter(fw);

                Iterator<MusicFile> mfIter = musicFiles.iterator();

                while (mfIter.hasNext()) {
                    MusicFile selectedFile = mfIter.next();
                    bw.write(selectedFile.getAudioFile().getFile().toString() + "\r");
                }
                bw.write(";");
                bw.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeSongs(ActionEvent actionEvent) {
        ObservableList<MusicFile> selectedFiles = FXCollections.observableArrayList();
        selectedFiles.addAll(tableMusic.getSelectionModel().getSelectedItems());
        Iterator<MusicFile> sfIter = selectedFiles.iterator();
        while (sfIter.hasNext()) {
            MusicFile selectedFile = sfIter.next();
            musicFiles.remove(selectedFile);
            tableMusic.getItems().remove(selectedFile);
            System.out.println(selectedFile + " removed.");
        }
    }
}

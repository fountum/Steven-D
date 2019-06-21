package UI.Controllers;

import Model.MusicFile;
import Model.MusicManager;
import Model.MusicPlayer;
import Model.Time;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {

    public BorderPane root;
    public TableView tableMusic;
    public TableColumn colNum;
    public TableColumn colTitle;
    public TableColumn colArtist;
    public TableColumn colAlbum;
    public TableColumn colLength;
    public ImageView imageArt;
    public Slider sliderTime;
    public Label labelTime;
    public Label labelPlaying;
    public Slider sliderVolume;
    public ImageView imagePlayPause;
    private boolean timelineOn = false;
    private Timeline millisTick;
    private MusicManager manager = new MusicManager();
    private ObservableList<MusicFile> musicFiles = FXCollections.observableArrayList();
    private ArrayList<AudioFile> audioFiles = new ArrayList<>();
    private MusicPlayer player = new MusicPlayer(musicFiles);
    public static final DataFormat SERIALIZED_MIME_TYPE = new DataFormat("application/x-java-serialized-object"); //for drag-and-drop.


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
        tableMusic.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableMusic.setOnMouseClicked( event -> {
            if (event.getClickCount() == 2) {
                pickSong();
            }
        });

        initDragNDrop();

        /*
        source: https://stackoverflow.com/questions/9966136/javafx-periodic-background-task
        does actions every millisecond
         */
        millisTick = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!player.isNull()) {
                    if (player.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                        sliderTime.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
                        labelTime.setText(Time.formatTime((int) player.getCurrentTime().toSeconds()) + "/" + Time.formatTime((int) player.getTotalDuration().toSeconds()));
                        labelPlaying.setText("Now playing: " + player.getCurrentSong().toString());
                    }
                    if (player.getTotalDuration().equals(player.getCurrentTime())) {
                        player.stop();
                    }
                }
            }
        }));

        initSliders();

    }

    //UI/data loading methods
    private void initCol() {
        colNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colArtist.setCellValueFactory(new PropertyValueFactory<>("artist"));
        colAlbum.setCellValueFactory(new PropertyValueFactory<>("album"));
        colLength.setCellValueFactory(new PropertyValueFactory<>("length"));

        colNum.setSortable(false);
        colTitle.setSortable(false);
        colArtist.setSortable(false);
        colAlbum.setSortable(false);
        colLength.setSortable(false);
    }

    private void initDragNDrop() {
        /*
        source: https://stackoverflow.com/questions/28603224/sort-tableview-with-drag-and-drop-rows
        adds drag-and-drop functionality to tableMusic to reorder the rows.
        */

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
            Removes file in DB from tableMusic and adds it to the row user's cursor is hovered over
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

                    //finds new position in queue after a song is moved. Only affects current song playing
                    if (player.getStatus().equals(MediaPlayer.Status.PLAYING) || player.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                        MusicFile currentSong = player.getCurrentSong();
                        player.setSongIndex(temp.indexOf(currentSong));
                    }

                    musicFiles.remove(draggedMF);
                    musicFiles.add(dropIndex, draggedMF);
                    player.updateQueue();

                    event.setDropCompleted(true);
                    tableMusic.getSelectionModel().select(dropIndex);
                    event.consume();
                }
            });

            return row;
        });
    }

    private void initSliders(){
        //sliderTime
        //adds ability to seek position in song by draggin the thumb of the slider
        sliderTime.setMax(100);
        sliderTime.setMin(0);
        sliderTime.setOnMousePressed(event -> {
            if (!player.isNull()) {
                stopTimeline();
            } else {
                sliderTime.setValue(0);
            }

        });
        sliderTime.setOnMouseReleased(event -> {
            if (!player.isNull()) {
                double newMillis = sliderTime.getValue() / 100 * player.getTotalDuration().toMillis();
                player.seek(newMillis);
                startTimeline();
            }

        });


        //Volume Controls
        //Allows user to control volume by draggin thumb
        sliderVolume.setMax(100);
        sliderVolume.setMin(0);
        sliderVolume.setValue(100);
        sliderVolume.setOnMouseReleased(event -> {
            if (!player.isNull()) {
                player.setVolume(sliderVolume.getValue() / 100);
            }
        });
    }

    public void addFiles(ArrayList<AudioFile> audioFiles) {
        tableMusic.getItems().clear();
        this.audioFiles.addAll(audioFiles);
        Iterator<AudioFile> afIter = audioFiles.iterator();
        while (afIter.hasNext()) {
            MusicFile mf = new MusicFile(afIter.next());
            musicFiles.add(mf);
        }
        tableMusic.getItems().addAll(musicFiles);

    }

    public void selectFiles() {
        addFiles(manager.getAudioFiles(root));
    }

    public void selectFolder() {
        addFiles(manager.selectFolder(root));
    }

    private void setImage() {
        manager.showCoverArt(player.getCurrentSong(), imageArt);
    }

    private Stage loadWindow(String location, String title, Parent p) {
        try {
            Parent parent;
            if (p == null) {
                parent = FXMLLoader.load(getClass().getResource(location));
            } else {
                parent = p;
            }
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.setAlwaysOnTop(true);
            stage.show();
            return stage;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void openEditMetadata(ActionEvent actionEvent) {
        /*
        source: https://www.genuinecoder.com/javafx-communication-between-controllers/
        Allows communication between windows, in this case transferring selected files
        */

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layouts/EditMetadata.fxml/"));
        Parent parent = null;
        try {
            parent = loader.load();
            EditMetadata em = loader.getController();
            em.transferFiles(audioFiles);
            Stage stage = loadWindow("../Layouts/EditMetadata.fxml/", "Edit Metadata", parent);
            stage.setOnCloseRequest(event -> {
                refreshMetadata();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCreatePlaylist(ActionEvent actionEvent) {
        loadWindow("../Layouts/CreatePlaylist.fxml/", "Create Playlist", null);
    }

    public void openMainWindowHelp(){
        loadWindow("../Layouts/MainWindowHelp.fxml/", "Help", null);
    }

    private void startTimeline() {
        millisTick.setCycleCount(Timeline.INDEFINITE);
        millisTick.play();
        timelineOn = true;
    }

    private void stopTimeline()  {
        millisTick.stop();
        timelineOn = false;
    }

    public void prevSong(ActionEvent actionEvent) {
        player.prevSong();
        startTimeline();
        setImage();
    }

    public void pausePlaySong( ) throws MalformedURLException {
        if (player.isNull()){
            player.startPlayer();
        } else {
            player.changeSongStatus();
        }

        setImage();
        if (!timelineOn) {
            startTimeline();
            File image = new File("D:\\JAVA\\1. hw\\MusicManager\\src\\UI\\Layouts\\Icons\\pause-button-512.png");
            imagePlayPause.setImage(new Image(String.valueOf(image.toURI().toURL())));
        } else {
            stopTimeline();
        }
        System.out.println("Player started");
    }

    public void nextSong(ActionEvent actionEvent) {
        player.nextSong();
        startTimeline();
        setImage();
    }

    private void refreshMetadata() {
        Iterator<MusicFile> mfIter = musicFiles.iterator();

        while (mfIter.hasNext()) {
            mfIter.next().updateMetadata();
        }

        tableMusic.refresh();
    }

    public void pickSong() {
        MusicFile selectedSong = (MusicFile) tableMusic.getSelectionModel().getSelectedItem();
        player.setSongIndex(selectedSong);
        //if (player.isNull() ||player.getStatus().equals(MediaPlayer.Status.PAUSED) || player.getStatus().equals(MediaPlayer.Status.STOPPED)){
        if (!player.isNull()){
            player.fullStop();
        }
        player.playSong();

        setImage();
        if (!timelineOn) {
            startTimeline();
        }

    }

    public void removeSong(ActionEvent actionEvent) {
        ObservableList<MusicFile> selectedFiles = FXCollections.observableArrayList();
        selectedFiles.addAll(tableMusic.getSelectionModel().getSelectedItems());
        Iterator<MusicFile> sfIter = selectedFiles.iterator();
        while (sfIter.hasNext()) {
            MusicFile selectedFile = sfIter.next();
            if (player.getCurrentSong().equals(selectedFile) && !player.isNull()) {
                player.fullStop();
                player.setToNull();
                stopTimeline();
            }
            musicFiles.remove(selectedFile);
            tableMusic.getItems().remove(selectedFile);
            System.out.println(selectedFile + " removed.");
        }
        player.updateQueue();
    }

    public void openPlaylist(ActionEvent actionEvent) {
        audioFiles = (ArrayList<AudioFile>) manager.selectPlaylist(root).clone();
        Iterator<AudioFile> afIter = audioFiles.iterator();
        if (!player.isNull()) {
            player.fullStop();
            stopTimeline();
            player.setToNull();
        }
        tableMusic.getItems().clear();
        musicFiles.clear();
        while (afIter.hasNext()) {
            musicFiles.add(new MusicFile(afIter.next()));
        }
        tableMusic.getItems().addAll(musicFiles);
        player.updateQueue();
    }
}

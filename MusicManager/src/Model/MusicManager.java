package Model;

import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.images.Artwork;

import java.awt.image.BufferedImage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class MusicManager implements Initializable {
    private FileChooser fc = new FileChooser();
    private FileReader fr;
    private BufferedReader br;

    public MusicManager() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Audio Files", "*.mp3"));
    }

    public ArrayList<AudioFile> getAudioFiles(Node fxID) {
        /*
        source: https://docs.oracle.com/javase/8/javafx/api/javafx/stage/FileChooser.html
        and https://stackoverflow.com/questions/25491732/how-do-i-open-the-javafx-filechooser-from-a-controller-class/25491787

        File chooser built into JavaFX. Opens a window that allows the user to select the permitted file types.
        The ExtensionFilter limits what file types are allowed.
        */
        HashSet<File> files = new HashSet<>();

        files.addAll(fc.showOpenMultipleDialog((fxID.getScene().getWindow())));
        try {
            ArrayList<AudioFile> audioFiles = new ArrayList<>();
            Iterator<File> filesIter = files.iterator();
            while (filesIter.hasNext()) {
                audioFiles.add(AudioFileIO.read(filesIter.next()));
            }
            return audioFiles;
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("No file selected");
        }
        return null;
    }

    public ArrayList<AudioFile> selectFolder(Node fxID) {
        DirectoryChooser dc = new DirectoryChooser();
        File folder;
        try {
            folder = dc.showDialog((fxID.getScene().getWindow()));
        } catch (NullPointerException e) {
            return null;
        }

        ArrayList<AudioFile> audioFiles = new ArrayList<>();
        int rejectedFiles = 0;

        try {
            for (File file : folder.listFiles()) {
                String extension = file.getName().substring(file.getName().lastIndexOf("."));

                    if (extension.equals(".mp3")) {
                        AudioFile audioFile = AudioFileIO.read(file);
                        audioFiles.add(audioFile);
                        System.out.println(file.getName() + " added");
                    } else {
                        rejectedFiles++;
                    }
            }
            System.out.println(rejectedFiles + " files were rejected.");
            return audioFiles;
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            System.out.println("Some files are read only.");
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<AudioFile> selectPlaylist(Node fxID) {
        ArrayList<AudioFile> audioFiles = new ArrayList<>();
        File playlist = fc.showOpenDialog(fxID.getScene().getWindow());
        try {
            fr = new FileReader(playlist);
            br = new BufferedReader(fr);
            String line;

            while (!(line = br.readLine()).equals(";")) {
                audioFiles.add(AudioFileIO.read(new File(line)));
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        return audioFiles;
    }

    public File selectImage(Node fxID) {
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File file = fc.showOpenDialog((fxID.getScene().getWindow()));
        return file;
    }

    //Sets imageArt to the artwork of the currentSong; used only for a single file
    public void showCoverArt(MusicFile currentSong, ImageView imageArt) {
        AudioFile audioFile = currentSong.getAudioFile();
        Tag tag = audioFile.getTag();

        Artwork art = tag.getFirstArtwork();
        Image image = null;
        try {
            image = SwingFXUtils.toFXImage((BufferedImage) art.getImage(), null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println("No artwork found for \"" + audioFile.getFile().getName() + "\"");
        }
        imageArt.setImage(image);
    }

    //Same purpose as previous, but used in tables when multiple songs can be selected, thus there can be a conflict in artwork
    public void showCoverArt(TableView tableMusic, ImageView imageArt) {
        //to do: improve the functionality of multiple conflicting artworks
        ObservableList<MusicFile> selectedFiles = tableMusic.getSelectionModel().getSelectedItems();
        Iterator<MusicFile> sfIter = selectedFiles.iterator();

        Tag previousFile = null;
        while (sfIter.hasNext()) {
            AudioFile audioFile = sfIter.next().getAudioFile();
            Tag tag = audioFile.getTag();
            System.out.println(tag.getFirst(FieldKey.TITLE));
            if (previousFile == null) {
                Artwork art = tag.getFirstArtwork();
                Image image = null;
                try {
                    image = SwingFXUtils.toFXImage((BufferedImage) art.getImage(), null);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    System.out.println("No artwork found for \"" + audioFile.getFile().getName() + "\"");
                }
                imageArt.setImage(image);
                previousFile = audioFile.getTag();
            } else if (!tag.getFirst(FieldKey.ALBUM).equals(previousFile.getFirst(FieldKey.ALBUM)) && !tag.getFirst(FieldKey.ARTIST).equals(previousFile.getFirst((FieldKey.ARTIST)))) {
                imageArt.setImage(null);
                break;
            }

        }
    }
}


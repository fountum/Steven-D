package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class MusicPlayer {
    MediaPlayer player = null;
    ArrayList<File> queue = new ArrayList<>();
    int songIndex = 0;
    ObservableList<MusicFile> musicFiles = FXCollections.observableArrayList();

    public MusicPlayer(ObservableList<MusicFile> musicFiles) {
        this.musicFiles = musicFiles;
    }

    public MediaPlayer createPlayer(File file) {
        Media media = new Media(file.toURI().toString());

        return (new MediaPlayer(media));
    }


    public void updateQueue(){
        Iterator<MusicFile> musicIter = musicFiles.iterator();
        queue.clear();
        while (musicIter.hasNext()) {
            MusicFile mf = musicIter.next();
            queue.add(mf.getAudioFile().getFile());
        }
        System.out.println("Queue loaded");
    }

    public void setMusicFiles(ObservableList<MusicFile> musicFiles){
        this.musicFiles = musicFiles;
    }

    public void changeSongStatus() {
        if (player != null) {
            if (player.getStatus().equals(MediaPlayer.Status.PLAYING)) {
                player.pause();
                return;
            } else if (player.getStatus().equals(MediaPlayer.Status.PAUSED)) {
                player.play();
                return;
            }
        }
    }

    public void startPlayer() {
        //Restarts the queue if its empty or it has reached the end of the queue
        if (queue.size() == 0 || (player != null && songIndex == queue.size() - 1 && player.getStatus().equals(MediaPlayer.Status.STOPPED))) {
            songIndex = 0;
            updateQueue();
            if (queue.size() == 0) {
                return;
            }
        }


        if (player == null || player.getStatus().equals(MediaPlayer.Status.STOPPED)) {
            playSong();
        }
    }

    public void nextSong() {
        if (songIndex < queue.size() - 1) {
            songIndex++;
            player.pause();
            System.out.println(songIndex);
            if (queue.size() - 1 < songIndex) {
                System.out.println("Queue Ended");
            } else {
                playSong();
            }
        }


    }

    public void prevSong() {
        if (songIndex > 0) {
            songIndex--;
            player.pause();
            playSong();
        } else {
            System.out.println("@ Start of queue");
        }

    }

    public void playSong() {
        if (queue.size() - 1 >= songIndex) {
            File file = queue.get(songIndex);
            if (queue.size() < 1) {
                System.out.println("No songs in queue");
                return;
            }
            player = createPlayer(file);
            player.setOnStopped(() ->
                    nextSong()
            );
            player.play();
            System.out.println("Now playing: " + file.getName());

        }

    }

    public Duration getTotalDuration(){
        return player.getTotalDuration();
    }

    public Duration getCurrentTime() {
        return player.getCurrentTime();
    }

    public MediaPlayer.Status getStatus() {
        return player.getStatus();
    }

    public void stop() {
        player.stop();
    }

    //stops the play and prevents the playing from triggering the nextSong
    public void fullStop() {
        player.setOnStopped(() -> {
            return;
        });
        player.stop();
    }

    public void setSongIndex(int index) {
        songIndex = index;
    }

    public void setSongIndex(MusicFile mf){
        updateQueue();
        songIndex = queue.indexOf(mf.getAudioFile().getFile());
    }

    public MusicFile getCurrentSong() {
        return (musicFiles.get(songIndex));
    }

    public void setVolume(double volume) {
        player.setVolume(volume);
    }

    public void seek(double millis) {
        player.seek(new Duration(millis));
    }

    public boolean isNull() {
        return player == null;
    }

    public void setToNull() {
        player = null;
    }

}

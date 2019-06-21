package Model;

import javafx.beans.property.SimpleStringProperty;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.util.Objects;

public class MusicFile {
    private final SimpleStringProperty num;
    private final SimpleStringProperty title;
    private final SimpleStringProperty artist;
    private final SimpleStringProperty album;
    private final SimpleStringProperty length;
    private AudioFile audioFile;

    public MusicFile(AudioFile audioFile) {
        Tag tag = audioFile.getTag();
        this.num = new SimpleStringProperty(tag.getFirst(FieldKey.TRACK));
        this.title = new SimpleStringProperty(tag.getFirst(FieldKey.TITLE));
        this.artist = new SimpleStringProperty(tag.getFirst(FieldKey.ARTIST));
        this.album = new SimpleStringProperty(tag.getFirst(FieldKey.ALBUM));
        this.length = new SimpleStringProperty(getTime(Integer.toString(audioFile.getAudioHeader().getTrackLength())));
        this.audioFile = audioFile;
    }

    private String getTime(String time) {
        int totalSeconds = Integer.parseInt(time);
        return Time.formatTime(totalSeconds);
    }

    public AudioFile toAudioFile() {
        return null;
    }

    public String getNum() {
        return num.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getArtist() {
        return artist.get();
    }

    public String getAlbum() {
        return album.get();
    }

    public String getLength() {
        return length.get();
    }

    public void updateMetadata() {
        Tag tag = audioFile.getTag();
        num.setValue(tag.getFirst(FieldKey.TRACK));
        title.setValue(tag.getFirst(FieldKey.TITLE));
        artist.setValue(tag.getFirst(FieldKey.ARTIST));
        album.setValue(tag.getFirst(FieldKey.ALBUM));
    }

    public AudioFile getAudioFile() { return audioFile;}

    public boolean equals(MusicFile MF) {
        if (audioFile.getFile().toURI().equals(MF.getAudioFile().getFile().toURI())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof MusicFile) {
            MusicFile other = (MusicFile) o;
            if (other.hashCode() == this.hashCode()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return audioFile.getFile().toURI().hashCode();
    }

    public String toString() {
        Tag tag = audioFile.getTag();
        String title = tag.getFirst(FieldKey.TITLE);
        if (title.replace(" ", "").isEmpty()) {
            title = "[No Title]";
        }

        String artist = tag.getFirst(FieldKey.ARTIST);
        if (artist.replace(" ", "").isEmpty()) {
            artist = "[No Artist]";
        }

        return (title + " by " + artist );
    }
}

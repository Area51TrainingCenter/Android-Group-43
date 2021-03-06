package pe.area51.notepad.data.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(
        tableName = "notes",
        indices = @Index(
                value = "title",
                unique = false
        )
)
public class Note {

    @PrimaryKey(autoGenerate = true)
    private final long id;
    private final String title;
    private final String content;
    private final long creationTimestamp;

    @Ignore
    public Note(String title, String content, long creationTimestamp) {
        this.id = 0;
        this.title = title;
        this.content = content;
        this.creationTimestamp = creationTimestamp;
    }

    public Note(long id, String title, String content, long creationTimestamp) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationTimestamp = creationTimestamp;
    }

    @Ignore
    public Note(long id) {
        this.id = id;
        this.title = "";
        this.content = "";
        this.creationTimestamp = 0;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }
}

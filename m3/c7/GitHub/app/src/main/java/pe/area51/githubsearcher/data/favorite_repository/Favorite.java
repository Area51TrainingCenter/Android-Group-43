package pe.area51.githubsearcher.data.favorite_repository;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "favoriteProjects")
public class Favorite {

    @PrimaryKey(autoGenerate = true)
    public final long id;
    @NonNull
    public final String gitHubProjectRemoteId;
    public final long favoriteTimestamp;

    public Favorite(long id, @NonNull String gitHubProjectRemoteId, long favoriteTimestamp) {
        this.id = id;
        this.gitHubProjectRemoteId = gitHubProjectRemoteId;
        this.favoriteTimestamp = favoriteTimestamp;
    }
}

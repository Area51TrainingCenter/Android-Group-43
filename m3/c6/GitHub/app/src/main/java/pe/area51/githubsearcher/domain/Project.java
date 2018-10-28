package pe.area51.githubsearcher.domain;

import android.support.annotation.NonNull;

import java.util.Objects;

public class Project {

    @NonNull
    private final String id;
    @NonNull
    private final String name;
    @NonNull
    private final String description;
    @NonNull
    private final String webUrl;
    @NonNull
    private final String userName;
    private final long creationTimestamp;
    private final long lastUpdateTimestamp;
    private final int popularity;
    private final boolean isFavorite;
    private final long favoriteTimestamp;

    public Project(@NonNull String id, @NonNull String name, @NonNull String description, @NonNull String webUrl, @NonNull String userName, long creationTimestamp, long lastUpdateTimestamp, int popularity, boolean isFavorite, long favoriteTimestamp) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.webUrl = webUrl;
        this.userName = userName;
        this.creationTimestamp = creationTimestamp;
        this.lastUpdateTimestamp = lastUpdateTimestamp;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.favoriteTimestamp = favoriteTimestamp;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    @NonNull
    public String getWebUrl() {
        return webUrl;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public long getLastUpdateTimestamp() {
        return lastUpdateTimestamp;
    }

    public int getPopularity() {
        return popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public long getFavoriteTimestamp() {
        return favoriteTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return creationTimestamp == project.creationTimestamp &&
                lastUpdateTimestamp == project.lastUpdateTimestamp &&
                popularity == project.popularity &&
                isFavorite == project.isFavorite &&
                favoriteTimestamp == project.favoriteTimestamp &&
                Objects.equals(id, project.id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(webUrl, project.webUrl) &&
                Objects.equals(userName, project.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, webUrl, userName, creationTimestamp, lastUpdateTimestamp, popularity, isFavorite, favoriteTimestamp);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", webUrl='" + webUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", creationTimestamp=" + creationTimestamp +
                ", lastUpdateTimestamp=" + lastUpdateTimestamp +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", favoriteTimestamp=" + favoriteTimestamp +
                '}';
    }
}

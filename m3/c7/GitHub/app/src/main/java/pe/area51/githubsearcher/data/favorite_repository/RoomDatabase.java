package pe.area51.githubsearcher.data.favorite_repository;


import android.arch.persistence.room.Database;

@Database(entities = {Favorite.class}, version = 1, exportSchema = false)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {

    public abstract FavoriteDao getFavoriteDao();

}
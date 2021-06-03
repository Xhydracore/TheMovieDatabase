package com.xhydracore.themoviedatabase.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.utils.DbConverter
import com.xhydracore.themoviedatabase.utils.SingletonHolder

@Database(
    entities = [MovieEntity::class, TvShowEntity::class, GenreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DbConverter::class)
abstract class BookmarkRoomDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object : SingletonHolder<BookmarkRoomDatabase, Context>({
        Room.databaseBuilder(
            it.applicationContext, BookmarkRoomDatabase::class.java, "movie.db"
        ).build()
    })
}
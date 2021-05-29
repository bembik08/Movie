package ru.geekbrains.movie.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.movie.data.model.Movie
import ru.geekbrains.movie.data.source.local.database.dao.MovieDao

@Database(
    entities = [Movie::class],
    version = DatabaseConfig.DATABASE_VERSION,
    exportSchema = DatabaseConfig.EXPORT_SCHEME
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}

object DatabaseConfig {
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "movie_db"
    const val EXPORT_SCHEME = false
}

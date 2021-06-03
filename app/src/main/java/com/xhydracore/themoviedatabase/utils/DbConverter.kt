package com.xhydracore.themoviedatabase.utils

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class DbConverter {
    @TypeConverter
    fun fromList(genreIds: List<Int>): String = Json.encodeToString(genreIds)

    @TypeConverter
    fun toList(genreIdsString: String): List<Int> = Json.decodeFromString(genreIdsString)
}
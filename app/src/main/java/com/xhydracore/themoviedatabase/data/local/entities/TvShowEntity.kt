package com.xhydracore.themoviedatabase.data.local.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_tv_show")
@Parcelize
class TvShowEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tv_show_id")
    @SerializedName("id")
    val tvShowId: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name = "genre_ids")
    @field:SerializedName("genre_ids")
    val genreIds: List<Int>,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name = "backdrop_path")
    @field:SerializedName("backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    val firstAirDate: String,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "is_bookmark")
    var isBookmark: Boolean = false
) : Parcelable
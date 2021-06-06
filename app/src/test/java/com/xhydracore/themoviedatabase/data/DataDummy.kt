package com.xhydracore.themoviedatabase.data

import com.xhydracore.themoviedatabase.data.local.entities.GenreEntity
import com.xhydracore.themoviedatabase.data.local.entities.MovieEntity
import com.xhydracore.themoviedatabase.data.local.entities.TvShowEntity
import com.xhydracore.themoviedatabase.utils.Constant.Companion.TYPE_MOVIE
import com.xhydracore.themoviedatabase.utils.Constant.Companion.TYPE_TV

object DataDummy {
    fun getPopularMovieDummy(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                529203, "The Croods: A New Age",
                "After leaving their cave, the Croods encounter their biggest threat since leaving: another family called the Bettermans, who claim and show to be better and evolved. Grug grows suspicious of the Betterman parents, Phil and Hope,  as they secretly plan to break up his daughter Eep with her loving boyfriend Guy to ensure that their daughter Dawn has a loving and smart partner to protect her.  Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
                listOf(12, 14, 10751, 16),
                "/tK1zy5BsCt1J4OzoDicXmr0UTFH.jpg",
                "/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
                "2020-11-25", 8.1,
                originalLanguage = "en",
                isBookmark = true
            )
        )

        movies.add(
            MovieEntity(
                553604, "Honest Thief",
                "A bank robber tries to turn himself in because he's falling in love and wants to live an honest life...but when he realizes the Feds are more corrupt than him, he must fight back to clear his name.",
                listOf(28, 53, 80, 18),
                "/zeD4PabP6099gpE0STWJrJrCBCs.jpg",
                "/tYkMtYPNpUdLdzGDUTC5atyMh9X.jpg",
                "2020-09-03", 7.0,
                originalLanguage = "en",
                isBookmark = true
            )
        )

        movies.add(
            MovieEntity(
                577922, "Tenet",
                "Armed with only one word - Tenet - and fighting for the survival of the entire world, the Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                listOf(28, 53, 878),
                "/k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                "/wzJRB4MKi3yK138bJyuL9nx47y6.jpg",
                "2020-08-22", 7.4,
                originalLanguage = "en"
            )
        )

        movies.add(
            MovieEntity(
                733317, "Monsters of Man",
                "A robotics company vying to win a lucrative military contract team up with a corrupt CIA agent to conduct an illegal live field test. They deploy four weaponized prototype robots into a suspected drug manufacturing camp in the Golden Triangle, assuming they'd be killing drug runners that no one would miss. Six doctors on a humanitarian mission witness the brutal slaughter and become prime targets.",
                listOf(878),
                "/1f3qspv64L5FXrRy0MF8X92ieuw.jpg",
                "/zbD96UExL9hl8TNihhs16vTBPEn.jpg",
                "2020-11-19", 7.7,
                originalLanguage = "en",
            )
        )

        movies.add(
            MovieEntity(
                646593, "Wander",
                "After getting hired to probe a suspicious death in the small town of Wander, a mentally unstable private investigator becomes convinced the case is linked to the same 'conspiracy cover up' that caused the death of his daughter.",
                listOf(53, 80, 9648),
                "/2AwPvNHphpZBJDqjZKVuMAbvS0v.jpg",
                "/wk58aoyWpMTVkKkdjw889XfWGdL.jpg",
                "2020-12-04", 5.5,
                originalLanguage = "en"
            )
        )

        movies.add(
            MovieEntity(
                524047, "Greenland",
                "John Garrity, his estranged wife and their young son embark on a perilous journey to find sanctuary as a planet-killing comet hurtles toward Earth. Amid terrifying accounts of cities getting levelled, the Garrity's experience the best and worst in humanity. As the countdown to the global apocalypse approaches zero, their incredible trek culminates in a desperate and last-minute flight to a possible safe haven.",
                listOf(28, 53),
                "/bNo2mcvSwIvnx8K6y1euAc1TLVq.jpg",
                "/2Fk3AB8E9dYIBc2ywJkxk8BTyhc.jpg",
                "2020-07-29", 7.1,
                originalLanguage = "en"
            )
        )

        movies.add(
            MovieEntity(
                602211, "Fatman",
                "A rowdy, unorthodox Santa Claus is fighting to save his declining business. Meanwhile, Billy, a neglected and precocious 12 year old, hires a hit man to kill Santa after receiving a lump of coal in his stocking.",
                listOf(28, 35, 14),
                "/4n8QNNdk4BOX9Dslfbz5Dy6j1HK.jpg",
                "/ckfwfLkl0CkafTasoRw5FILhZAS.jpg",
                "2020-11-13", 5.9,
                originalLanguage = "en"
            )
        )

        movies.add(
            MovieEntity(
                464052, "Wonder Woman 1984",
                "Wonder Woman comes into conflict with the Soviet Union during the Cold War in the 1980s and finds a formidable foe by the name of the Cheetah.",
                listOf(14, 28, 12),
                "/lNVHB85FUDZqLzvug3k6FA07RIr.jpg",
                "/srYya1ZlI97Au4jUYAktDe3avyA.jpg",
                "2020-12-16", 7.2,
                originalLanguage = "en"
            )
        )

        movies.add(
            MovieEntity(
                581392, "Peninsula",
                "A soldier and his team battle hordes of post-apocalyptic zombies in the wastelands of the Korean Peninsula.",
                listOf(28, 27, 53),
                "/sy6DvAu72kjoseZEjocnm2ZZ09i.jpg",
                "/d2UxKyaJ5GgzuHaSsWinFfv3g6L.jpg",
                "2020-07-15", 6.9,
                originalLanguage = "en"
            )
        )

        movies.add(
            MovieEntity(
                400160, "The SpongeBob Movie: Sponge on the Run",
                "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
                listOf(16, 14, 12, 35, 10751),
                "/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
                "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
                "2020-08-14", 7.9,
                originalLanguage = "en"
            )
        )

        return movies
    }

    fun getPopularTvShowDummy(): List<TvShowEntity> {

        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                82856, "The Mandalorian",
                "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
                listOf(10765, 10759),
                "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
                "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
                "2019-11-12", 8.5,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                71712, "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
                listOf(18),
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "/zlXPNnnUlyg6KyvvjGd2ZxG6Tnw.jpg",
                "2017-09-25", 8.6,
                originalLanguage = "en",
                isBookmark = true
            )
        )

        tvShows.add(
            TvShowEntity(
                63174, "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                listOf(80, 10765),
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                "2016-01-25", 8.5,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                456, "The Simpsons",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                listOf(16, 35, 10751, 18),
                "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg",
                "/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg",
                "1989-12-16", 7.8,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                71789, "SEAL Team",
                "The lives of the elite Navy Seals as they train, plan and execute the most dangerous, high-stakes missions our country can ask.",
                listOf(10759, 18, 10768),
                "/uTSLeQTeHevt4fplegmQ6bOnE0Z.jpg",
                "/6lOtF3yx8iurvaBVz1ZVhwcRgmD.jpg",
                "2017-09-27", 7.8,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                87739, "The Queen's Gambit",
                "In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.",
                listOf(18),
                "/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
                "/34OGjFEbHj0E3lE2w0iTUVq0CBz.jpg",
                "2020-10-23", 8.7,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                1399, "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                listOf(10765, 18, 10759, 9648),
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "2011-04-17", 8.4,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                75006, "The Umbrella Academy",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                listOf(10759, 10765, 18),
                "/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                "/mE3zzMkpP8yqlkzdjPsQmJHceoe.jpg",
                "2019-02-15", 8.7,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                76479, "The Boys",
                "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
                listOf(10765, 10759),
                "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
                "2019-07-25", 8.5,
                originalLanguage = "en"
            )
        )

        tvShows.add(
            TvShowEntity(
                61889, "Marvel's Daredevil",
                "Lawyer-by-day Matt Murdock uses his heightened senses from being blinded as a young boy to fight crime at night on the streets of Hell’s Kitchen as Daredevil.",
                listOf(28, 80),
                "/QWbPaDxiB6LW2LjASknzYBvjMj.jpg",
                "/qsnXwGS7KBbX4JLqHvICngtR8qg.jpg",
                "2015-04-10", 7.9,
                originalLanguage = "en"
            )
        )

        return tvShows
    }

    fun generateMovieGenre(): List<GenreEntity> {
        val movieGenres = ArrayList<GenreEntity>()

        movieGenres.add(GenreEntity(1, 28, "Action", TYPE_MOVIE))
        movieGenres.add(GenreEntity(2, 12, "Adventure", TYPE_MOVIE))
        movieGenres.add(GenreEntity(3, 16, "Animation", TYPE_MOVIE))
        movieGenres.add(GenreEntity(3, 35, "Comedy", TYPE_MOVIE))
        movieGenres.add(GenreEntity(4, 80, "Crime", TYPE_MOVIE))
        movieGenres.add(GenreEntity(5, 99, "Documentary", TYPE_MOVIE))
        movieGenres.add(GenreEntity(6, 18, "Drama", TYPE_MOVIE))
        movieGenres.add(GenreEntity(7, 10751, "Family", TYPE_MOVIE))
        movieGenres.add(GenreEntity(8, 14, "Fantasy", TYPE_MOVIE))
        movieGenres.add(GenreEntity(9, 36, "History", TYPE_MOVIE))
        movieGenres.add(GenreEntity(10, 27, "Horror", TYPE_MOVIE))
        movieGenres.add(GenreEntity(11, 10402, "Music", TYPE_MOVIE))
        movieGenres.add(GenreEntity(12, 9648, "Mystery", TYPE_MOVIE))
        movieGenres.add(GenreEntity(13, 10749, "Romance", TYPE_MOVIE))
        movieGenres.add(GenreEntity(14, 878, "Science Fiction", TYPE_MOVIE))
        movieGenres.add(GenreEntity(15, 10770, "TV Movie", TYPE_MOVIE))
        movieGenres.add(GenreEntity(16, 53, "Thriller", TYPE_MOVIE))
        movieGenres.add(GenreEntity(17, 10752, "War", TYPE_MOVIE))
        movieGenres.add(GenreEntity(18, 37, "Western", TYPE_MOVIE))

        return movieGenres
    }

    fun generateTvGenre(): List<GenreEntity> {
        val tvGenres = ArrayList<GenreEntity>()

        tvGenres.add(GenreEntity(1, 10759, "Action & Adventure", TYPE_TV))
        tvGenres.add(GenreEntity(2, 16, "Animation", TYPE_TV))
        tvGenres.add(GenreEntity(3, 35, "Comedy", TYPE_TV))
        tvGenres.add(GenreEntity(4, 80, "Crime", TYPE_TV))
        tvGenres.add(GenreEntity(5, 99, "Documentary", TYPE_TV))
        tvGenres.add(GenreEntity(6, 18, "Drama", TYPE_TV))
        tvGenres.add(GenreEntity(7, 10751, "Family", TYPE_TV))
        tvGenres.add(GenreEntity(8, 10762, "Kids", TYPE_TV))
        tvGenres.add(GenreEntity(9, 9648, "Mystery", TYPE_TV))
        tvGenres.add(GenreEntity(10, 10763, "News", TYPE_TV))
        tvGenres.add(GenreEntity(11, 10764, "Reality", TYPE_TV))
        tvGenres.add(GenreEntity(12, 10765, "Sci-Fi & Fantasy", TYPE_TV))
        tvGenres.add(GenreEntity(13, 10766, "Soap", TYPE_TV))
        tvGenres.add(GenreEntity(14, 10767, "Talk", TYPE_TV))
        tvGenres.add(GenreEntity(15, 10768, "War & Politics", TYPE_TV))
        tvGenres.add(GenreEntity(16, 37, "Western", TYPE_TV))

        return tvGenres
    }

    fun generateBookmarkMovieData(): List<MovieEntity> {
        val movieBookmarked = ArrayList<MovieEntity>()
        getPopularMovieDummy().forEach {
            if (it.isBookmark) movieBookmarked.add(it)
        }
        return movieBookmarked
    }

    fun generateBookmarkTvShowData(): List<TvShowEntity> {
        val tvShowBookmarked = ArrayList<TvShowEntity>()
        getPopularTvShowDummy().forEach {
            if (it.isBookmark) tvShowBookmarked.add(it)
        }
        return tvShowBookmarked
    }

}

package com.xhydracore.themoviedatabase.data

import com.xhydracore.themoviedatabase.models.movie.ResponseDetailMovie
import com.xhydracore.themoviedatabase.models.movie.ResultsMovie
import com.xhydracore.themoviedatabase.models.tvshow.ResponseDetailTvShow
import com.xhydracore.themoviedatabase.models.tvshow.ResultsTvShow

object DataDummy {
    fun getPopularMovieDummy(): List<ResultsMovie> {
        return listOf(
            ResultsMovie(
                overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                title = "Godzilla vs. Kong",
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                releaseDate = "2021-03-24",
                voteAverage = 8.3,
                id = 399566
            ),
            ResultsMovie(
                id = 791373,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                title = "Zack Snyder's Justice League",
                releaseDate = "2021-03-18",
                voteAverage = 8.5,
                overview = "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions."
            ),
            ResultsMovie(
                id = 412656,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/9kg73Mg8WJKlB9Y2SAJzeDKAnuB.jpg",
                title = "Chaos Walking",
                releaseDate = "2021-02-24",
                voteAverage = 7.5,
                overview = "Two unlikely companions embark on a perilous adventure through the badlands of an unexplored planet as they try to escape a dangerous and disorienting reality, where all inner thoughts are seen and heard by everyone."
            ),
            ResultsMovie(
                id = 527774,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                title = "Raya and the Last Dragon",
                releaseDate = "2021-03-03",
                voteAverage = 8.3,
                overview = "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."
            ),
            ResultsMovie(
                id = 544401,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg",
                title = "Cherry",
                releaseDate = "2021-02-26",
                voteAverage = 7.6,
                overview = "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world."
            )
        )
    }

    fun getPopularTvShowDummy(): List<ResultsTvShow> {
        return listOf(
            ResultsTvShow(
                id = 88396,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                name = "The Falcon and the Winter Soldier",
                firstAirDate = "2021-03-19",
                voteAverage = 7.8,
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            ),
            ResultsTvShow(
                id = 791373,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                name = "The Good Doctor",
                firstAirDate = "2017-09-25",
                voteAverage = 8.6,
                overview = "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
            ),
            ResultsTvShow(
                id = 60735,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                name = "The Flash",
                firstAirDate = "2014-10-07",
                voteAverage = 7.5,
                overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
            ),
            ResultsTvShow(
                id = 69050,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                name = "Riverdale",
                firstAirDate = "2017-01-26",
                voteAverage = 8.6,
                overview = "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
            ),
            ResultsTvShow(
                id = 1416,
                posterPath = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                name = "Grey's Anatomy",
                firstAirDate = "2005-03-27",
                voteAverage = 8.2,
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            ),
        )
    }

    fun getDetailMovie(id: Int): ResponseDetailMovie {
        val findData = getPopularMovieDummy().find { it.id == id } as ResultsMovie
        return ResponseDetailMovie(
            title = findData.title,
            id = findData.id,
            voteCount = 0,
            overview = findData.overview,
            originalTitle = "",
            runtime = 123,
            posterPath = findData.posterPath,
            releaseDate = findData.releaseDate,
            voteAverage = findData.voteAverage,
            tagline = "-",
            status = "Released"
        )
    }

    fun getDetailTvShow(id: Int): ResponseDetailTvShow {
        val findData = getPopularTvShowDummy().find { it.id == id } as ResultsTvShow
        return ResponseDetailTvShow(
            numberOfEpisodes = 8,
            popularity = 2.2,
            id = findData.id,
            voteCount = 20,
            firstAirDate = findData.firstAirDate,
            overview = findData.overview,
            posterPath = findData.posterPath,
            voteAverage = findData.voteAverage,
            name = findData.name,
            tagline = "-",
            lastAirDate = "22",
            status = "Finished"
        )
    }
}

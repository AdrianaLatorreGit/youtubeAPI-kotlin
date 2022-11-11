package com.adrianalatorre.youtubeapi.api

import com.adrianalatorre.youtubeapi.model.FilmeResposta
import retrofit2.Response
import retrofit2.http.GET

interface FilmeAPI {

    //Endpoint - ROTA
    @GET("movie/popular?api_key=bdc97f545c95104bb9d253f0e606d4e6")
    suspend fun recuperarFilmesPopulares(): Response<FilmeResposta>

}
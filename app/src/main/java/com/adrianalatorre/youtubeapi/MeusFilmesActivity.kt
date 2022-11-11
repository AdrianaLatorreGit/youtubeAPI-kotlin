package com.adrianalatorre.youtubeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.adrianalatorre.youtubeapi.adapter.FilmeAdapter
import com.adrianalatorre.youtubeapi.api.FilmeAPI
import com.adrianalatorre.youtubeapi.databinding.ActivityMeusFilmesBinding
import com.adrianalatorre.youtubeapi.model.FilmeResposta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MeusFilmesActivity : AppCompatActivity() {

    //Base url:https://api.themoviedb.org/3/
    //Rota ou Endpoint: movie/popular?api_key=**key**



    private val filmeAPI by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FilmeAPI::class.java)
    }

    private val binding by lazy {
        ActivityMeusFilmesBinding.inflate(layoutInflater)
    }

    private val TAG = "info_filme"
    private var filmeAdapter: FilmeAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        filmeAdapter = FilmeAdapter()
        binding.rvFilmes.adapter = filmeAdapter
        binding.rvFilmes.layoutManager = GridLayoutManager(this, 2)

    }

    override fun onStart() { //carregar dados
        super.onStart()
        recuperarFilmesPopulares()
    }

    private fun recuperarFilmesPopulares() {



       CoroutineScope(Dispatchers.IO).launch {

            var retorno: Response<FilmeResposta>? = null

            try {

                retorno = filmeAPI.recuperarFilmesPopulares()

            } catch (e: Exception) {
                e.printStackTrace()
            }

            if (retorno != null) {
                if (retorno.isSuccessful) {

                    val filmeResposta = retorno.body()
                    val listaFilmes = filmeResposta?.results

                    listaFilmes?.forEach { filme ->
                        Log.i(TAG, "título: ${filme.title}")
                    }

                    withContext(Dispatchers.Main) {
                        filmeAdapter?.adicionarListaFilmes(listaFilmes)
                    }

                } else {
                    Log.i(TAG, "erro: ${retorno.code()}")
                }
            }

        }

    }
}
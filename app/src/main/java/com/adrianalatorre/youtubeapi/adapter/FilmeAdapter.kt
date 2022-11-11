package com.adrianalatorre.youtubeapi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.adrianalatorre.youtubeapi.databinding.ItemFilmeBinding
import com.adrianalatorre.youtubeapi.model.Filme
import com.squareup.picasso.Picasso

class FilmeAdapter : RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    private var listaFilmes: List<Filme>? = null

    fun adicionarListaFilmes( lista: List<Filme>? ){
        listaFilmes = lista
        notifyDataSetChanged()
    }

    inner class FilmeViewHolder(itemView: ItemFilmeBinding)
        : RecyclerView.ViewHolder(itemView.root){

            private val binding: ItemFilmeBinding

            init {
                binding = itemView
            }

        fun bind( filme: Filme ) {

            binding.textTitulo.text = filme.title
            Log.i("info_dados", "titulo: ${filme.title}")

            //carregar imagem Picasso
            //https://image.tmdb.org/t/p/w500/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg

            val urlBaseImagem = "https://image.tmdb.org/t/p/"
            val tamanho = "w780"
            val nomeImagem = filme.backdrop_path

            val link = urlBaseImagem + tamanho + nomeImagem

            Picasso.get()
                .load( link )
                .into( binding.imageFilme )

        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {

        val layoutInflater = LayoutInflater.from( parent.context )
        val itemFilmeBinding = ItemFilmeBinding.inflate(
            layoutInflater, parent, false
        )

        return FilmeViewHolder( itemFilmeBinding )


    }

    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {

        val filme = listaFilmes?.get(position)

        if ( filme != null ){
            holder.bind( filme )
        }

    }

    override fun getItemCount(): Int {

        if( listaFilmes != null ) {
            val total = listaFilmes!!.size
            Log.i("info_dados", "lista: $total")
            return listaFilmes!!.size

        }
        Log.i("info_dados", "lista: 0")
        return 0
    }


}
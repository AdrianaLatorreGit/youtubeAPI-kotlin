package com.adrianalatorre.youtubeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adrianalatorre.youtubeapi.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val autenticacao = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.textCadastro.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        val intent = Intent(this, MeusFilmesActivity::class.java)
        startActivity(intent)

        binding.btnLogar.setOnClickListener {

            val email = binding.editEmailLogin.text.toString()
            val senha = binding.editSenhaLogin.text.toString()

            autenticacao.signInWithEmailAndPassword(
                email, senha
            ).addOnSuccessListener {

                val intent = Intent(this, MeusFilmesActivity::class.java)
                startActivity(intent)

                Toast.makeText(
                    this,
                    "Sucesso ao fazer Login",
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener {
                Toast.makeText(
                    this,
                    "Erro ao fazer Login, usuário ou senha inválido",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }
}
package com.adrianalatorre.youtubeapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adrianalatorre.youtubeapi.databinding.ActivityCadastroBinding
import com.adrianalatorre.youtubeapi.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CadastroActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private val autenticacao = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.title = "Cadástro de usuário"

        binding.btnCadastrar.setOnClickListener {

            val email = binding.editEmailCadastro.text.toString()
            val senha = binding.editSenhaCadastro.text.toString()

            autenticacao.createUserWithEmailAndPassword(
                email, senha
            ).addOnSuccessListener {

                val intent = Intent(this, MainActivity::class.java)
            startActivity( intent )

                Toast.makeText(
                    this,
                    "Usuário cadastrado com sucesso",
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener{
                Toast.makeText(
                    this,
                    "Erro ao cadastrar usuário",
                    Toast.LENGTH_SHORT
                )
            }

        }
    }
}


package com.example.minilemonspaceapp

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inisialisasi elemen UI
        usernameEditText = findViewById(R.id.form_username)
        passwordEditText = findViewById(R.id.form_password)
        loginButton = findViewById(R.id.btn_login)

        // Atur pendengar klik untuk tombol login
        loginButton.setOnClickListener(View.OnClickListener {
            // Dapatkan username dan password yang dimasukkan oleh pengguna
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validasi username dan password (Anda dapat menambahkan logika validasi Anda di sini)
            if (isValidLogin(username, password)) {
                // Login berhasil, Anda dapat navigasi ke aktivitas berikutnya atau melakukan tindakan lain
                // Sebagai contoh, mulai aktivitas baru:
                // val intent = Intent(this, NextActivity::class.java)
                // startActivity(intent)

                // Untuk contoh ini, kita akan menampilkan pesan toast
                showToast("Login Berhasil!")
            } else {
                // Login tidak valid, tampilkan pesan kesalahan
                showToast("Username atau password tidak valid")
            }
        })
    }

    // Tambahkan logika validasi login Anda di sini
    private fun isValidLogin(username: String, password: String): Boolean {
        // Anda dapat mengimplementasikan logika otentikasi Anda di sini
        // Untuk kesederhanaan, kita akan mengasumsikan login valid jika kedua kolom tidak kosong
        return !username.isEmpty() && !password.isEmpty()
    }

    // Metode bantu untuk menampilkan pesan toast
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

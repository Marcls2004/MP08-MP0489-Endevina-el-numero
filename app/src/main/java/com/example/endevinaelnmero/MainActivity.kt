package com.example.endevinaelnmero

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // tipo de letra
        val face = android.graphics.Typeface.MONOSPACE

        val b = findViewById<Button>(R.id.button)
        val tn = findViewById<EditText>(R.id.editTextNumber)

        var num_random = (Math.random() * 10).toInt() + 1
        var attempts = 0

        val tv = findViewById<TextView>(R.id.textView)
        val tvAttempts = findViewById<TextView>(R.id.textView2)
        //Para hacer el scroll sin el ScrollView = tv.movementMethod = ScrollingMovementMethod()

        // Para aplicar estilos
        tv.setText("You haven't made any attempts")
        tvAttempts.setText("Attempts: " + attempts.toString())

        // para poner tipo de letra y tamaño
        tv.typeface = face

        tvAttempts.typeface = face

        // Añade esto debajo de tv.gravity = Gravity.CENTER
        tv.maxLines = 10

        // funcion lambda
        b.setOnClickListener {
            // tn.text para sacar el texto que tiene
            if (tn.text.toString().toInt() > num_random){
                val text: CharSequence = "The number is smaller"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, text, duration)
                toast.show()

                attempts++
                tvAttempts.setText("Attempts: " + attempts.toString())
                tv.setText(tv.getText().toString() + "\nAttempt " + attempts.toString() + ", the number is smaller")



            } else if(tn.text.toString().toInt() < num_random){
                val text: CharSequence = "The number is larger"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, text, duration)
                toast.show()

                attempts++
                tvAttempts.setText("Attempts: " + attempts.toString())
                tv.setText(tv.getText().toString() + "\nAttempt " + attempts.toString() + ", the number is larger")


            } else if (tn.text.toString().toInt() == num_random){
                num_random = (Math.random() * 10).toInt() + 1

                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder
                    .setMessage("Do you want to play again?")
                    .setTitle("You guessed the number, congratulations!")
                    .setPositiveButton("Yes") { dialog, which ->
                        tv.setText("You haven't made any attempts")
                        tv.scrollTo(0, 0) // Resetea el scroll al inicio
                        attempts = 0
                        tvAttempts.setText("Attempts: " + attempts.toString())
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, which ->
                        finish()
                    }

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            else{
                tv.setText("Put a number")
            }
        }
    }
}

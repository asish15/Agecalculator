package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.agecalculator.R.id.but1
import com.example.agecalculator.R.id.yourdate
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selecteddate: TextView? = null
    private var ageinminutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selecteddate = findViewById(R.id.yourdate)
        ageinminutes = findViewById(R.id.result)

        val btndatepicker = findViewById<Button>(R.id.but1)
        btndatepicker.setOnClickListener {
            clickdatepicker()
        }
    }

    fun clickdatepicker() {
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val mon = mycalendar.get(Calendar.MONTH)
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val dates = "$dayOfMonth/${month + 1}/$year"

                selecteddate?.text = dates

                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val thedate = sdf.parse(dates)
                val selecteddateinminute = thedate.time / 60000
                val currentdate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentdateinminutes = currentdate.time / 60000
                val differenceintime = currentdateinminutes - selecteddateinminute

                ageinminutes?.text = differenceintime.toString()
            },
            year,
            mon,
            day
        ).show()
    }
}

package com.shpakovskiy.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        selectDateButton.setOnClickListener {
            datePicker(it)
        }
    }

    private fun datePicker(view: View) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${selectedMonth + 1}/$selectedYear"
                selectedDateView.text = selectedDate

                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val date = simpleDateFormat.parse(selectedDate)
                val dateInMinutes = date!!.time / 60000
                val currentDayInMinutes = simpleDateFormat
                    .parse(simpleDateFormat.format(System.currentTimeMillis()))!!.time / 60000

                val difference = currentDayInMinutes - dateInMinutes

                ageInMinutes.text = (difference).toString()
                ageInHours.text = (difference / 60).toString()
                ageInDays.text = (difference / 1440).toString()

        }, year, month, day)

        datePickerDialog.datePicker.maxDate = Date().time - 86400000
        datePickerDialog.show()
    }
}

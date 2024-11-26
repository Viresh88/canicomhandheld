package com.example.canicomhandheld.fragments



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.canicomhandheld.CalendarAdapter
import com.example.canicomhandheld.R
import com.example.canicomhandheld.entity.SharedViewModel
import com.google.android.material.tabs.TabLayout
import java.util.Calendar


class CalendarFragment : Fragment() {

    private lateinit var spinnerMonth: Spinner
    private lateinit var spinnerYear: Spinner
    private lateinit var calendarRecyclerView: RecyclerView
    private var selectedMonth = Calendar.getInstance().get(Calendar.MONTH)
    private var selectedYear = Calendar.getInstance().get(Calendar.YEAR)
    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val calendarView = view.findViewById<CalendarView>(R.id.calendarView)

        spinnerMonth = view.findViewById(R.id.spinnerMonth)
        spinnerYear = view.findViewById(R.id.spinnerYear)
        calendarRecyclerView = view.findViewById(R.id.calendarRecyclerView)


        val daysTabLayout: TabLayout = view.findViewById(R.id.days)

        val calendar = Calendar.getInstance()
        val currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

// Convert currentDayOfWeek to match tab positions
// Assuming your TabLayout order starts with Mon (1), Tue (2), ... Sun (7)
        val tabIndex = when (currentDayOfWeek) {
            Calendar.MONDAY -> 0
            Calendar.TUESDAY -> 1
            Calendar.WEDNESDAY -> 2
            Calendar.THURSDAY -> 3
            Calendar.FRIDAY -> 4
            Calendar.SATURDAY -> 5
            Calendar.SUNDAY -> 6
            else -> 0 // Default to Monday if something goes wrong
        }

// Select the tab
        daysTabLayout.getTabAt(tabIndex)?.select()

        calendarRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), 7) // 7 columns for 7 days

        setupSpinners()
        updateCalendarDays()



        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Set data when selection changes
        sharedViewModel.selectedMonth.value = selectedMonth
        sharedViewModel.selectedYear.value = selectedYear

        spinnerMonth.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedMonth = position // Update selectedMonth based on the selected position
                updateCalendarDays() // Update calendar
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Listener for year spinner
        spinnerYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedYear = spinnerYear.selectedItem.toString()
                    .toInt() // Update selectedYear based on the selected value
                updateCalendarDays() // Update calendar
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }


        }
    }

    fun formatSelectedDate(day: Int, month: Int, year: Int): String {
        // Ensure the day and month are formatted with leading zeros if necessary
        val formattedDay = if (day < 10) "0$day" else day.toString()
        val formattedMonth = if (month < 10) "0$month" else month.toString()

        // Return the formatted date string
        return "$year-$formattedMonth-$formattedDay"
    }



    private fun updateCalendarDays() {
        val daysInMonth = getDaysInMonth(selectedMonth, selectedYear)
        val daysList = mutableListOf<String>()

        // Get the current date
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentYear = calendar.get(Calendar.YEAR)

        // Check if the selected month and year are the current month and year
        val isCurrentMonthAndYear = (selectedMonth == currentMonth && selectedYear == currentYear)

        // Get the day of the week for the 1st day of the month
        calendar.set(Calendar.MONTH, selectedMonth)
        calendar.set(Calendar.YEAR, selectedYear)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        // Add empty spaces before the first day
        for (i in 1 until firstDayOfWeek) {
            daysList.add("")
        }

        // Add the actual days
        for (day in 1..daysInMonth) {
            daysList.add(day.toString())
        }

        // Pass the daysList and current day info to the adapter
        val adapter = CalendarAdapter(requireContext(), daysList, today, isCurrentMonthAndYear) { selectedDay ->
            if (selectedDay.isNotEmpty()) {
                val formattedDate = formatSelectedDate(selectedDay.toInt(), selectedMonth + 1, selectedYear)

            }
        }
        calendarRecyclerView.adapter = adapter
    }





    private fun getDaysInMonth(month: Int, year: Int): Int {
        return when (month) {
            Calendar.JANUARY, Calendar.MARCH, Calendar.MAY, Calendar.JULY,
            Calendar.AUGUST, Calendar.OCTOBER, Calendar.DECEMBER -> 31
            Calendar.APRIL, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.NOVEMBER -> 30
            Calendar.FEBRUARY -> if (isLeapYear(year)) 29 else 28
            else -> 30
        }
    }

    private fun isLeapYear(year: Int): Boolean {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }


    private fun setupSpinners() {
        val months = arrayOf(
           "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December"
        )

        val monthAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_cal_item,R.id.textItem, months)
        monthAdapter.setDropDownViewResource(R.layout.custom_spinner_cal_item) // Use custom layout for dropdown as well
        spinnerMonth.adapter = monthAdapter

// Set default to the current month
        spinnerMonth.setSelection(Calendar.getInstance().get(Calendar.MONTH))

// Populate the year spinner dynamically
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val startYear = currentYear - 20
        val years = (startYear..currentYear).toList().sortedDescending().map { it.toString() }.toTypedArray()

        val yearAdapter = ArrayAdapter(requireContext(), R.layout.custom_spinner_cal_item,R.id.textItem, years)
        yearAdapter.setDropDownViewResource(R.layout.custom_spinner_cal_item) // Use custom layout for dropdown as well
        spinnerYear.adapter = yearAdapter

// Set default to the current year
        spinnerYear.setSelection(0)
    }
}

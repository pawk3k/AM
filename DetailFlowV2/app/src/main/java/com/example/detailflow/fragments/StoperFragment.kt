package com.example.detailflow.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.detailflow.R
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StopperFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoperFragment : Fragment(), View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val LAST_RECORD_INDEX = 0
    private val LAST_RECORD = "Last Record"
    private val BEST_RECORD = "Best Record"
    private val BEST_RECORD_INDEX = 1
    private var item: String? = null
    private var seconds: Int = 0;
    private var running = false;
    private var wasRunning = false;
    private var score: String? = "brak";
    private var scoreLast: String? = "brak";
    private var scoreBest: String? = "brak";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(JoggingDetailFragment.ARG_ITEM_ID)) {
                item = it.getString(JoggingDetailFragment.ARG_ITEM_ID)
            }
        }


        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }


    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val layout: View = inflater.inflate(R.layout.fragment_stopper, container, false)
        runStoper(layout)

        layout.findViewById<TextView>(R.id.last_result).text =
            getRecord()[LAST_RECORD_INDEX].toString()
        layout.findViewById<TextView>(R.id.best_result).text =
            getRecord()[BEST_RECORD_INDEX].toString()
        val startButton: Button = layout.findViewById<View>(R.id.start_button) as Button
        startButton.setOnClickListener(this)
        val stopButton: Button = layout.findViewById<View>(R.id.stop_button) as Button
        stopButton.setOnClickListener(this)
        val resetButton: Button = layout.findViewById<View>(R.id.reset_button) as Button
        resetButton.setOnClickListener(this)

        val saveButton: Button = layout.findViewById<View>(R.id.save_button) as Button
        saveButton.setOnClickListener(this)
        // Inflate the layout for this fragment
        return layout
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    fun onClickStart() {
        running = true
    }

    fun onClickStop() {
        running = false
    }

    fun onClickReset() {
        running = false
        seconds = 0
    }

    fun onClickSave() {
        activity?.findViewById<TextView>(R.id.last_result)?.text =
            "Date :${getCurrentDate()}: ${activity?.findViewById<TextView>(R.id.time_view)?.text}"

        activity?.findViewById<TextView>(R.id.best_result)?.text =
            "Date :${getCurrentDate()}: ${activity?.findViewById<TextView>(R.id.time_view)?.text}"
        setRecord()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
            R.id.save_button -> onClickSave()
        }
    }

    fun getCurrentDate(): String {

        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate = sdf.format(Date())
        return currentDate
    }

    fun setRecord() {
        val preferences = this.activity!!
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        val edit = preferences.edit()
        edit.putString(
            "${item}",
            "Date :${getCurrentDate()}: ${activity?.findViewById<TextView>(R.id.time_view)?.text}"
        )

        edit.putString(
            "${item}$BEST_RECORD",
            "Date :${getCurrentDate()}: ${activity?.findViewById<TextView>(R.id.time_view)?.text}"
        )

//        scoreBest = preferences.getString("${item}:${BEST_RECORD}", "")
        edit.apply()
    }

    fun getRecord(): Array<String?> {

        val preferences = this.activity!!
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
        score = preferences.getString("${item}", "")
        scoreBest = preferences.getString("${item}${BEST_RECORD}", "")

        return arrayOf(score, scoreBest)
    }

    private fun runStoper(view: View) {
        val timeView = view.findViewById<View>(R.id.time_view) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }


    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

}
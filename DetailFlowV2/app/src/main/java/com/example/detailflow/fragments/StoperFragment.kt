package com.example.detailflow.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.detailflow.R


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
    private var seconds: Int = 0;
    private var running = false;
    private var wasRunning = false;
    private var score = 32;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
//        getRecord()
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

        layout.findViewById<TextView>(R.id.last_result).text = getRecord().toString()
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
        seconds += 20
        setRecord(seconds)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
            R.id.save_button -> onClickSave()
        }
    }

    fun setRecord(appliedValue: Int) {
        val preferences = this.activity!!
            .getSharedPreferences("pref", Context.MODE_PRIVATE)

//        val sharedScore = getSharedPreferences("com.example.myapplication.shared", 0)
        val edit = preferences.edit()
        edit.putInt("${}", appliedValue)
        edit.apply()
    }

    fun getRecord(): Int {

        val preferences = this.activity!!
            .getSharedPreferences("pref", Context.MODE_PRIVATE)
//        val sharedScore = this.getSharedPreferences("com.example.myapplication.shared", 0)

        score = preferences.getInt("score", 0)
        return score
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
package com.example.detailflow.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.detailflow.fragments.RunDetailFragment
import com.example.detailflow.R

class RunDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_detail)

        if (savedInstanceState == null) {
            val fragment = RunDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        RunDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(RunDetailFragment.ARG_ITEM_ID)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

}
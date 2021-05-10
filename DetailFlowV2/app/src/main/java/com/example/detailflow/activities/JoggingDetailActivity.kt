package com.example.detailflow.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.detailflow.fragments.JoggingDetailFragment
import com.example.detailflow.R

class JoggingDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_detail)

        if (savedInstanceState == null) {
            val fragment = JoggingDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(
                        JoggingDetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(JoggingDetailFragment.ARG_ITEM_ID)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

}
package com.example.detailflow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.detailflow.R


class JoggingDetailFragment : Fragment() {
    private var item: String? = null
    private var joggingId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                item = it.getString(ARG_ITEM_ID)
            }
        }

        if (savedInstanceState == null) {
            val stoper = StoperFragment();
            val fm =
                childFragmentManager.beginTransaction().add(R.id.stoper_container, stoper).commit()


//            supportFragmentManager.beginTransaction()
//                .add(R.id.item_detail_container, fragment)
//                .commit()
//            ft.add(R.id.stoper_container, stoper);
//            ft.addToBackStack(null);
//            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//            ft.commit();
        } else {
            joggingId = savedInstanceState.getInt("joggingId");
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_jogging_detail, container, false)
        item?.let {
            rootView.findViewById<TextView>(R.id.item_detail).text = it
        }
        return rootView
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }

}
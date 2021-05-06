package com.example.detailflow.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.FrameLayout
import com.example.detailflow.R
import com.example.detailflow.adapters.RunRecyclerAdapter


class RunListActivity : AppCompatActivity() {
    private var twoPane: Boolean = false

    public var data = HashMap<String, String>()

    fun initializeData() {
        data.put(
            "Rusalka",
            "Trasa 'Rusalka' jest trasą zajmującu 6 km , bieg odbywa się wokół jeziora "
        )
        data.put(
            "Malta",
            "Trasa 'Malta' jest trasą zajmującu około 8 km , bieg odbywa się wokół jeziora które jak sama trasa wskazuje 'Malta'"
        )
        data.put(
            "Junior",
            "Trasa 'Junior' jest trasą zajmującu 2 km , bieg odbywa się wzdluz ulicy marcelinskiej od przystanku most Swiętego Rocha do przystanku Szyamnowskiego"
        )
        data.put(
            "Not so Junior",
            "Trasa 'Not so Junior' jest trasą zajmującu 4 km , trasa przebiege 3 razy wokół Politechniki Poznanskiej "
        )
        data.put(
            "Senior",
            "Trasa 'Senior' jest trasą zajmującu 12 km , bieg odbywa się wokół jeziora 2 razy wokół  jeziora 'Rusalka'  "
        )
        data.put(
            "Survival Race",
            "Trasa 'Rusalka' jest trasą zajmującu 6 km , bieg odbywa się wokół jeziora "
        )
        data.put(
            "Lasek Marceliński",
            " W Lasku Marcelińskim jest sporo ścieżek do biegania. W jednym miejscu jest niewielkie wzniesienie. Są także wytyczone dwie trasy biegowe, jedna o długości 4,12 km a druga 2,45."
        )
        data.put(
            "Cytadela",
            "Biegi odbywają się przez cały rok. Park Cytadela położony jest na Wzgórzu Winiarskim, a więc są tu spore różnice w nachyleniu terenu. Jest sporo alejek, w większości asfaltowych, ale są też wąskie parkowe ścieżki."
        )
        data.put(
            "Wzdluż Wartz Left Side",
            "Bieg odbywa sie wzdluż warty po lewej jej stronie jeśli wstać w srodku warty i patrzyć na Katedrę"
        )

        data.put(
            "Wzdluż Wartz Right Side",
            "Bieg odbywa sie wzdluż warty po prawej jej stronie jeśli wstać w srodku warty i patrzyć na Katedrę"
        )


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_run_list)
        setupRecyclerView(findViewById(R.id.item_list))
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        val list = ArrayList<String>()
        for (key in data.keys) {
            list.add(key)
        }

        val detailContainer: FrameLayout? =
            findViewById(R.id.item_detail_container)
        recyclerView.adapter =
            RunRecyclerAdapter(this, list, data, detailContainer != null)
    }
}
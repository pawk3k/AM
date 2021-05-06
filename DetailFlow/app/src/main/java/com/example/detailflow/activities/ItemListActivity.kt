package com.example.detailflow.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.FrameLayout
import com.example.detailflow.R
import com.example.detailflow.adapters.RunRecyclerAdapter

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ItemDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ItemListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
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
            "Rusalka1",
            "Trasa 'Rusalka' jest trasą zajmującu 6 km , bieg odbywa się wokół jeziora "
        )
        data.put(
            "Rusalka2",
            "Trasa 'Rusalka' jest trasą zajmującu 6 km , bieg odbywa się wokół jeziora "
        )
        data.put(
            "Rusalka3",
            "Trasa 'Rusalka' jest trasą zajmującu 6 km , bieg odbywa się wokół jeziora "
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initializeData()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_list)
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

//    class SimpleItemRecyclerViewAdapter(private val parentActivity: ItemListActivity,
//                                        private val values: List<String>,
//                                        private val data : HashMap<String,String>
//                                        ) :
//            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_list_content, parent, false)
//            return ViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            val item = values[position]
//            holder.contentView.text = item.toString()
//            val detailContainer: NestedScrollView? = holder.itemView.findViewById(R.id.item_detail_container)
//
//
//            with(holder.itemView) {
//                tag = item
//                setOnClickListener(){
//                    val item = this.tag as String
//                    val description = data[item]
//                    println(description)
//                    if (detailContainer == null) {
//                        val fragment = ItemDetailFragment().apply {
//                            arguments = Bundle().apply {
//                                putString(ItemDetailFragment.ARG_ITEM_ID, description)
//                            }
//                        }
//                        parentActivity.supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.item_detail_container, fragment)
//                            .commit()
//                    } else {
//                        val intent = Intent(this.context, ItemDetailActivity::class.java).apply {
//                            putExtra(ItemDetailFragment.ARG_ITEM_ID, description )
//                        }
//                        this.context.startActivity(intent)
//                    }
//                }
//            }
//        }
//
//        override fun getItemCount() = values.size
//
//        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//            val contentView: TextView = view.findViewById(R.id.content)
//        }
//    }
}
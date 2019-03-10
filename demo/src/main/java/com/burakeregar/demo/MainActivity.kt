package com.burakeregar.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.burakeregar.demo.model.ContactModel
import com.burakeregar.demo.viewholder.ContactViewHolder
import com.burakeregar.easiestgenericrecycleradapter.base.GenericAdapterBuilder
import android.support.v7.widget.LinearLayoutManager
import com.burakeregar.demo.model.ContactColourModel
import com.burakeregar.demo.viewholder.ContactColourViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import com.burakeregar.easiestgenericrecycleradapter.base.GenericRecyclerAdapter
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter: GenericRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()

        adapter.setList(createDummyData())
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this) //set your layout manager
        adapter = GenericAdapterBuilder()
                .addModel(
                        R.layout.row_contact, //set your row's layout file
                        ContactViewHolder::class.java, //set your view holder class
                        ContactModel::class.java // set your model class(If you use just String list, it can be just String.class)
                )
                .addModel(
                        R.layout.row_contact,
                        ContactColourViewHolder::class.java,
                        ContactColourModel::class.java
                )
                .execute()
        recyclerView.adapter = adapter
    }

    private fun createDummyData(): List<Any> {
        val list = ArrayList<Any>()
        for (i in 1..15) {
            list.add(ContactModel("Name$i", "Surname$i"))
        }
        for (i in 16..30) {
            list.add(ContactColourModel("Name$i", "Surname$i", Color.RED))
        }
        return list
    }
}

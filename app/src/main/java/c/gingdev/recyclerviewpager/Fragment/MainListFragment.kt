package c.gingdev.recyclerviewpager.Fragment

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ListFragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import c.gingdev.recyclerviewpager.R

class MainListFragment: ListFragment() {

    private lateinit var itemClickListener: onItemClickListener

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        itemClickListener = activity as onItemClickListener
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //        values/String에 있는 MainItems가져옴.
        val items = resources.getStringArray(R.array.Main_list)
        //        안드로이드 기본레이아웃중 하나인 simplelist에맞춰서 어댑터설정.
        //        처음 recycler뷰가 그리드로보여줄지 리스트로보여줄지 확인.
        val adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, items)
        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        itemClickListener.onListitemClicked(position)
    }

    interface onItemClickListener {
        fun onListitemClicked(position: Int)
    }
}
package c.gingdev.recyclerviewpager.Fragment

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import c.gingdev.recyclerviewpager.Adapter.autoScrollAdapter
import c.gingdev.recyclerviewpager.R
import kotlinx.android.synthetic.main.auto_scrolling_rc.*

class autoScrollingRc: Fragment() {

    private lateinit var rc: RecyclerView
    private lateinit var adapter: autoScrollAdapter

    private lateinit var handler: Handler
    private lateinit var lm: LinearLayoutManager
    private lateinit var additemThread: AddItemThread


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            return inflater.inflate(R.layout.auto_scrolling_rc, container, false)
        }else {
            return super.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler = Handler { msg: Message? -> ChkMessage(msg) }
        lm = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        adapter = autoScrollAdapter()
        rc = recycler
        rc.adapter = adapter
        rc.layoutManager = lm

        rc.setOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastposition = 0
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
            }
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                lastposition = lm.findLastVisibleItemPosition()
                dyValue.setText("dy : ${dy}")
            }
        })

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rc)
        indicator.attachToRecyclerView(rc)
        additemThread = AddItemThread(handler)
        additemThread.isDaemon = true
        additemThread.start()
    }

    private fun ChkMessage(msg: Message?): Boolean {
        when (msg!!.what) {
            1 -> {
                adapter.AddItems("item : ${msg.obj}")
                return true
            }
            else -> return false
        }
    }
}

class AddItemThread: Thread {
    var handler: Handler
    constructor(handler: Handler) {
        this.handler = handler
    }

    override fun run() {
        var i: Int = 0
        while (i < 50) {
            i++
            handler.sendMessage(handler.obtainMessage(1, i))
            Thread.sleep(250)
            Log.i("thread", "msgSend")
        }
        Log.i("thread", "end")
    }
}
package c.gingdev.recyclerviewpager.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import c.gingdev.recyclerviewpager.Holder.autoScrollHolder
import c.gingdev.recyclerviewpager.R
import kotlinx.android.synthetic.main.auto_scrolling_rc.view.*

class autoScrollAdapter: RecyclerView.Adapter<autoScrollHolder>() {

    private val items : ArrayList<String> = ArrayList()
    private var itemHeight: Int = 0
    fun AddItems(item: String) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): autoScrollHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.auto_scrolling_rc_item, parent, false)
        val vh = autoScrollHolder(v)
        return vh
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItemHeight(): Int {
        return itemHeight
    }

    override fun onBindViewHolder(holder: autoScrollHolder, position: Int) {
        holder.contentView.setText(items.get(position))
        itemHeight = holder.itemView.height
    }

}
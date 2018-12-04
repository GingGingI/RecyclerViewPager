package c.gingdev.recyclerviewpager.Fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.*
import c.gingdev.recyclerviewpager.R
import kotlinx.android.synthetic.main.tap_checker.*

class TapChecker: Fragment(), View.OnTouchListener {

    lateinit var gd: GestureDetector

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tap_checker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gd = GestureDetector(activity, gestureDetector(activity))
        TouchPanel.setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when(v!!) {
            TouchPanel -> {
                gd.onTouchEvent(event)
            }
        }
        return true
    }
}
class gestureDetector(activity: FragmentActivity?): GestureDetector.SimpleOnGestureListener() {

    val ac = activity

    override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
        ac!!.TouchPanel.setBackgroundColor(Color.GREEN)
        return true
    }

    override fun onDoubleTap(e: MotionEvent?): Boolean {
        ac!!.TouchPanel.setBackgroundColor(Color.BLUE)
        return true
    }
}
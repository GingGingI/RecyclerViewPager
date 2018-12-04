package c.gingdev.recyclerviewpager

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import c.gingdev.recyclerviewpager.Fragment.MainListFragment
import c.gingdev.recyclerviewpager.Fragment.TapChecker
import c.gingdev.recyclerviewpager.Fragment.autoScrollingRc

class MainActivity : AppCompatActivity(), MainListFragment.onItemClickListener {

    private lateinit var prefs : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences("c.gingdev.recyclerviewpager", Context.MODE_PRIVATE)

        if (prefs.getBoolean("isFirst", true)) {
            Toast.makeText(this, "첫번째 실행", Toast.LENGTH_LONG).show()
            prefs.edit().putBoolean("isFirst", false).commit()
        }

        if (savedInstanceState == null) {
            val fragment = MainListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.contentPanel, fragment)
                    .commit()
        }
    }

    override fun onListitemClicked(position: Int) {
        var fragment: Fragment
        when(position) {
            0 -> {
                fragment = autoScrollingRc()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.contentPanel, fragment)
                        .addToBackStack(null)
                        .commit()
            }
            1 -> {
                fragment = TapChecker()
                supportFragmentManager.beginTransaction()
                        .replace(R.id.contentPanel, fragment)
                        .addToBackStack(null)
                        .commit()
            }
        }
    }

}

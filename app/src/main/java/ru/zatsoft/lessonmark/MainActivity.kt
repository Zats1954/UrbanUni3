package ru.zatsoft.lessonmark

import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import ru.zatsoft.lessonmark.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toolBar: Toolbar
    private lateinit var callView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerForContextMenu(binding.etMark)
        registerForContextMenu(binding.tvRandom)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        if (v != null) {
            callView = v
        }
        menuInflater.inflate(R.menu.color_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_color -> {
                if (callView == binding.etMark) {
                    setColorEditText()
                } else if(callView == binding.tvRandom) {
                    setColorTextView()
                }
            }
            R.id.exit -> finish()
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    private fun setColorTextView() {
        binding.tvRandom.setBackgroundColor(
            when (binding.tvRandom.text.toString()) {
                in "1".."10" -> Color.RED
                in "11".."20" -> Color.parseColor("#FFA500")
                in "21".."30" -> Color.YELLOW
                in "31".."40" -> Color.GREEN
                in "41".."50" -> Color.BLUE
                else -> Color.WHITE
            }
        )
    }

    private fun setColorEditText() {
        binding.etMark.setBackgroundColor(
            when (binding.etMark.text.toString()) {
                "1" -> Color.parseColor("#FFA500")
                "2" -> Color.YELLOW
                "3" -> Color.GREEN
                "4" -> Color.BLUE
                "5" -> Color.RED
                else -> Color.WHITE
            }
        )
    }

    fun onButtonClick(view: View) {
        val random = (1..50).random()
        binding.tvRandom.text = random.toString()
    }
}
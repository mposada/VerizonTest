package co.mposadar.verizontest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import co.mposadar.verizontest.R
import co.mposadar.verizontest.ui.adapter.TagsAdapter
import co.mposadar.verizontest.utils.HelperFunctions
import co.mposadar.verizontest.utils.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*
import co.mposadar.verizontest.utils.setupAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.setupAdapter(this)
        val adapter = TagsAdapter(ArrayList())
        recyclerView.adapter = adapter

        val factory = InjectorUtils.provideTagViewModel(this)
        val model = ViewModelProviders.of(this, factory).get(TagViewModel::class.java)
        model.getTags().observe(this, Observer { tags ->
            tags?.let {
                if (it.isEmpty()) {
                    emptyListTextView.visibility = View.VISIBLE
                } else {
                    adapter.swapPosts(it)
                    emptyListTextView.visibility = View.GONE
                }
            }
        })

        addButton.setOnClickListener {
            val alertDialog = AlertDialog.Builder(this@MainActivity)
            alertDialog.setTitle(getString(R.string.add_tag_title))

            val editText = EditText(this@MainActivity)
            val layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            )
            editText.layoutParams = layoutParams
            alertDialog.setView(editText)
            alertDialog.setPositiveButton(getString(R.string.save)) { _, _ ->
                val repository = InjectorUtils.provideRepository(this@MainActivity)
                val name = editText.text.toString()
                if (HelperFunctions.validateTagName(name = name)) {
                    repository.saveTag(name = name)
                } else {
                    Snackbar.make(it, getString(R.string.saving_tag_error), Snackbar.LENGTH_LONG).show()
                }
            }
            alertDialog.setNegativeButton(getString(R.string.cancel)) { dialog, _ -> dialog?.cancel() }
            alertDialog.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            R.id.action_second_screen -> {
                val intent = Intent(this, FunctionalityActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

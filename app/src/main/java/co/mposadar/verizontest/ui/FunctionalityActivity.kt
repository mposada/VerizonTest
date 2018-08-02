package co.mposadar.verizontest.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import co.mposadar.verizontest.R
import co.mposadar.verizontest.ui.adapter.TagsAdapter
import co.mposadar.verizontest.utils.InjectorUtils
import co.mposadar.verizontest.utils.OrderTags
import co.mposadar.verizontest.utils.setupAdapter
import kotlinx.android.synthetic.main.activity_functionality.*

class FunctionalityActivity : AppCompatActivity() {

    companion object {
        var factory: TagViewModelFactory? = null
        var model: TagViewModel? = null
        var adapter: TagsAdapter? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_functionality)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView.setupAdapter(this)
        adapter = TagsAdapter(ArrayList())
        recyclerView.adapter = adapter

        factory = InjectorUtils.provideTagViewModel(this)
        model = ViewModelProviders.of(this, factory).get(TagViewModel::class.java)
        model?.getTags()?.observe(this, Observer { tags ->
            tags?.let {
                adapter?.swapPosts(it)
                val message = "${resources.getString(R.string.number_tags)} ${it.size}"
                numberTagsTextView.text = message
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.functionality_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val tags = model?.getTags()?.value

        return when(item?.itemId) {
            R.id.action_asc -> {
                // do something else
                tags?.let {
                    //adapter?.swapPosts(OrderTags.asd(it))
                    co.mposadar.verizonlib.OrderTags.asd(it)
                    adapter?.swapPosts(it)
                }

                true
            }
            R.id.action_desc -> {
                tags?.let {
                    // adapter?.swapPosts(OrderTags.desc(it))
                    co.mposadar.verizonlib.OrderTags.desc(it)
                    adapter?.swapPosts(it)
                }
                true
            }
            R.id.action_concurring -> {
                tags?.let {
                    adapter?.swapPosts(OrderTags.byFrequency(it))
                }
                true
            }
            R.id.action_duplicate -> {
                val repository = InjectorUtils.provideRepository(this@FunctionalityActivity)
                tags?.let {
                    repository.bulkInsert(it)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

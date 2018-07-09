package co.mposadar.verizontest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.mposadar.verizontest.R
import co.mposadar.verizontest.data.database.TagEntry
import co.mposadar.verizontest.utils.inflate
import kotlinx.android.synthetic.main.list_item_tag.view.*

class TagsAdapter(private val tagList: ArrayList<TagEntry>): RecyclerView.Adapter<TagsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.list_item_tag)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = tagList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(tagList[position])
    }

    fun swapPosts(newData: List<TagEntry>) {
        if (!tagList.isEmpty()) {
            tagList.clear()
        }
        tagList.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(tag: TagEntry) {
            with(tag) {
                itemView.nameTextView.text = name
                itemView.tagImageView.setColorFilter(color)
            }
        }
    }
}
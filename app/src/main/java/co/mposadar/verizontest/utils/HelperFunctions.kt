package co.mposadar.verizontest.utils

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*
import java.util.regex.Pattern

open class HelperFunctions {
    companion object {
        fun validateTagName(name: String): Boolean {
            val regex = Pattern.compile("[^A-Za-z0-9]")
            val matcher = regex.matcher(name)
            return !matcher.find()
        }

        fun getColor(): Int {
            val random = Random()
            return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        }
    }
}

/**
 * extension functions
 */

fun ViewGroup.inflate(layoutResource: Int): View {
    return LayoutInflater.from(context).inflate(layoutResource, this, false)
}

fun RecyclerView.setupAdapter(context: Context) {
    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.layoutManager = layoutManager
    val dividerItemDecoration = DividerItemDecoration(context,
            layoutManager.orientation)
    this.addItemDecoration(dividerItemDecoration)
}

fun ClosedRange<Int>.random() =
        Random().nextInt((endInclusive + 1) - start) +  start
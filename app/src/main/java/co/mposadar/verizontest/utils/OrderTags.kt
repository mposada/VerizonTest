package co.mposadar.verizontest.utils

import android.graphics.Color
import co.mposadar.verizontest.data.database.TagEntry
import java.util.*

class TagsComparator(private val descOrder: Boolean): Comparator<TagEntry> {
    override fun compare(o1: TagEntry?, o2: TagEntry?): Int {
        return if (descOrder) {
            o2!!.name!!.compareTo(o1!!.name!!)
        } else {
            o1!!.name!!.compareTo(o2!!.name!!)
        }
    }
}

class OrderTags {
    companion object {
        fun asd(tags: List<TagEntry>): List<TagEntry> {
            Collections.sort(tags, TagsComparator(false))
            return tags
        }

        fun desc(tags: List<TagEntry>): List<TagEntry> {
            Collections.sort(tags, TagsComparator(true))
            return tags
        }

        fun byFrequency(tags: List<TagEntry>): List<TagEntry> {
            // 1) Use a sorting algorithm to sort the elements O(nlogn)
            Collections.sort(tags, TagsComparator(false))

            // 2) Scan the sorted array and construct a 2D array of element and count O(n).
            var elements: HashMap<String, Int> = HashMap()

            for ((index, tag) in tags.withIndex()) {
                if (index != 0) {
                    if (tag.name == tags[index - 1].name) {
                        val prev: Int = elements[tag.name]!!
                        elements[tag.name] = prev + 1
                    } else {
                        elements[tag.name] = 1
                    }
                } else {
                    elements[tag.name] = 1
                }
            }

            // 3) Sort the 2D array according to count O(nlogn).
            elements = sortHasMap(elements)

            var newList: ArrayList<TagEntry> = ArrayList()

            for (element in elements) {
                for (i in element.value downTo 1) {
                    val random = Random()
                    val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                    val tag = TagEntry(name = element.key, color = color)
                    newList.add(tag)
                }
            }

            return newList
        }

        private fun sortHasMap(elements: HashMap<String, Int>): HashMap<String, Int> {
            val list = LinkedList<Map.Entry<String, Int>>(elements.entries)
            list.sortWith(Comparator { o1, o2 -> o2!!.value.compareTo(o1!!.value) })
            val sortedMap = LinkedHashMap<String, Int>()
            for (entry in list) {
                sortedMap[entry.key] = entry.value
            }
            return sortedMap
        }


    }
}
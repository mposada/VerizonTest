package co.mposadar.verizonlib

import java.util.*

class GenericComparator(private val descOrder: Boolean): Comparator<Any> {
    override fun compare(p0: Any?, p1: Any?): Int {
        val nameP0 = p0!!.javaClass?.getMethod("getName").invoke(p0)
        val nameP1 = p1!!.javaClass?.methods[10].invoke(p1)
        return if (descOrder) {
            nameP1.toString().compareTo(nameP0.toString())
        } else {
            nameP0.toString().compareTo(nameP1.toString())
        }
    }


}

class OrderTags {
    companion object {
        fun <T> asd(list: List<T>): List<T> {
            Collections.sort(list as List<Any>, GenericComparator(false))
            return list
        }

        fun <T> desc(list: List<T>): List<T> {
            Collections.sort(list as List<Any>, GenericComparator(true))
            return list
        }
    }

}
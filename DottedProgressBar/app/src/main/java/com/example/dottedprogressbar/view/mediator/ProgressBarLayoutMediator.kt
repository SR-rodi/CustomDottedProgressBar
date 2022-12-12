package com.example.dottedprogressbar.view.mediator

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.dottedprogressbar.view.DottedProgressBar

class ProgressBarLayoutMediator(
    private var progressBar: DottedProgressBar,
    private val viewPager: ViewPager2,
){

    private var onPageChangeCallback: DottedProgressOnPageProgressChangeCallback? = null

    private var adapter: RecyclerView.Adapter<*>? = null

    private var attached = false

    fun attach() {
        check(!attached) { throw IllegalStateException("ProgressBar is already attached") }
        adapter = viewPager.adapter
        checkNotNull(adapter) {
            throw IllegalStateException(
                "ProgressBar attached before ViewPager2 has an " + "adapter") }
        attached = true

        onPageChangeCallback = DottedProgressOnPageProgressChangeCallback(progressBar)
        viewPager.registerOnPageChangeCallback(onPageChangeCallback!!)

        Log.e("Kart", "${viewPager.adapter?.itemCount}")

        progressBar.setProgressWithViewPager(viewPager.currentItem)
        if (adapter!=null)
            progressBar.setProgressSize(viewPager.adapter!!.itemCount)

        viewPager.setCurrentItem(progressBar.getProgress(),true)
    }
}
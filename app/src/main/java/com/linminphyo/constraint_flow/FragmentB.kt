package com.linminphyo.constraint_flow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_linear.*

/**
 * Created by lin min phyo on 2019-09-07.
 */


class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_linear , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object {
        var fragmentA : FragmentB? = null

        fun getInstance() : FragmentB{
            if(fragmentA == null) {
                fragmentA = FragmentB()
            }
            return fragmentA!!
        }
    }
}
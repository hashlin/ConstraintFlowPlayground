package com.linminphyo.constraint_flow

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.layout_flow_wrap.*

/**
 * Created by lin min phyo on 2019-09-07.
 */


class FragmentA : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_flow_wrap , container , false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_group_wrap_modes.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_wrap_mode_none -> flow.setWrapMode(Flow.WRAP_NONE)
                    R.id.button_wrap_mode_chain -> flow.setWrapMode(Flow.WRAP_CHAIN)
                    R.id.button_wrap_mode_align -> flow.setWrapMode(Flow.WRAP_ALIGNED)
                }
            }
        }

        button_group_chain_style.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_chain_style_packed -> flow.setHorizontalStyle(Flow.CHAIN_PACKED)
                    R.id.button_chain_style_spread -> flow.setHorizontalStyle(Flow.CHAIN_SPREAD)
                    R.id.button_chain_style_spread_inside -> flow.setHorizontalStyle(Flow.CHAIN_SPREAD_INSIDE)
                }
            }
        }

        seekbar_bias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                flow.setHorizontalBias((p1 / 100f))
            }

        })


        button_group_vertical_alignment.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_vertical_alignment_top -> flow.setVerticalAlign(Flow.VERTICAL_ALIGN_TOP)
                    R.id.button_vertical_alignment_center -> flow.setVerticalAlign(Flow.VERTICAL_ALIGN_CENTER)
                    R.id.button_vertical_alignment_bottom -> flow.setVerticalAlign(Flow.VERTICAL_ALIGN_BOTTOM)
                    R.id.button_vertical_alignment_baseline -> flow.setVerticalAlign(Flow.VERTICAL_ALIGN_BASELINE)
                }
            }
        }

        button_group_flow_style.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_flow_horizontal -> flow.setOrientation(Flow.HORIZONTAL)
                    R.id.button_flow_vertical-> flow.setOrientation(Flow.VERTICAL)
                }
            }
        }
    }
    companion object {

        fun getInstance() : FragmentA{
            return FragmentA()
        }
    }
}
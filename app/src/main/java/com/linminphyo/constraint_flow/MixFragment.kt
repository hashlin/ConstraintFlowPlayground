package com.linminphyo.constraint_flow

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


class MixFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_flow_wrap, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        button_group_wrap_modes.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button_wrap_mode_chain -> Flow.WRAP_CHAIN
                    R.id.button_wrap_mode_align -> Flow.WRAP_ALIGNED
                    else -> Flow.WRAP_NONE
                }.let{
                }
            }
        }

        button_group_chain_style.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_chain_style_spread -> Flow.CHAIN_SPREAD
                    R.id.button_chain_style_spread_inside -> Flow.CHAIN_SPREAD_INSIDE
                    else -> Flow.CHAIN_PACKED
                }.let {
                }

            }
        }

        seekbar_bias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }

        })


        button_group_alignments.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button_vertical_alignment_top -> Flow.VERTICAL_ALIGN_TOP
                    R.id.button_vertical_alignment_bottom -> Flow.VERTICAL_ALIGN_BOTTOM
                    R.id.button_vertical_alignment_baseline -> Flow.VERTICAL_ALIGN_BASELINE
                    R.id.button_vertical_alignment_center -> Flow.VERTICAL_ALIGN_CENTER
                    R.id.button_horizontal_alignment_start -> Flow.HORIZONTAL_ALIGN_START
                    R.id.button_horizontal_alignment_center-> Flow.HORIZONTAL_ALIGN_CENTER
                    R.id.button_horizontal_alignment_end -> Flow.HORIZONTAL_ALIGN_END
                    else -> return@addOnButtonCheckedListener
                }.let {
                }
            }
        }

        button_group_flow_style.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button_flow_vertical -> Flow.VERTICAL
                    else -> Flow.HORIZONTAL
                }.let {
                }
            }
        }

    }

    companion object {

        fun getInstance(): MixFragment {
            return MixFragment()
        }
    }
}
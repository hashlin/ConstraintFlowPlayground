package com.linminphyo.constraint_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.layout_verrtical_flow.*

/**
 * Created by lin min phyo on 2019-09-24.
 */

class VerticalFlowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.layout_verrtical_flow, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        flow.setOrientation(Flow.VERTICAL)

        button_group_wrap_modes.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button_wrap_mode_chain -> Flow.WRAP_CHAIN
                    R.id.button_wrap_mode_align -> Flow.WRAP_ALIGNED
                    else -> Flow.WRAP_NONE
                }.let {
                    TransitionManager.beginDelayedTransition(parent)
                    flow.setWrapMode(it)
                }
            }
        }

        button_group_chain_style.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_chain_style_spread -> Flow.CHAIN_SPREAD
                    R.id.button_chain_style_spread_inside -> Flow.CHAIN_SPREAD_INSIDE
                    else -> Flow.CHAIN_PACKED
                }.let {
                    TransitionManager.beginDelayedTransition(parent)
                    flow.setVerticalStyle(it)
                }

            }
        }

        seekbar_horizontal_bias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                flow.setVerticalBias(p1/100f)
            }

        })

        button_group_horizontal_alignments.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_horizontal_alignment_end -> Flow.HORIZONTAL_ALIGN_END
                    R.id.button_horizontal_alignment_start -> Flow.HORIZONTAL_ALIGN_START
                    else -> Flow.HORIZONTAL_ALIGN_CENTER
                }.let {
                    flow.setHorizontalAlign(it)
                }
            }
        }
    }
}
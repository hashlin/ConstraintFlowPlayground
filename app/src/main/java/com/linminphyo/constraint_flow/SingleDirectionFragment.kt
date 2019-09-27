package com.linminphyo.constraint_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.layout_horizontal_flow.*

/**
 * Created by lin min phyo on 2019-09-24.
 */

class SingleDirectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.layout_horizontal_flow, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val flowDirection = arguments?.getInt(KEY_ORIENTATION) ?: Flow.HORIZONTAL
        val isHorizontalFlow = flowDirection == Flow.HORIZONTAL

        flow.setOrientation(flowDirection)

        button_group_wrap_modes.addOnButtonCheckedListener { group, checkedId, isChecked ->
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

        button_group_chain_style.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_chain_style_spread -> Flow.CHAIN_SPREAD
                    R.id.button_chain_style_spread_inside -> Flow.CHAIN_SPREAD_INSIDE
                    else -> Flow.CHAIN_PACKED
                }.let {
                    TransitionManager.beginDelayedTransition(parent)
                    if(isHorizontalFlow){
                        flow.setHorizontalStyle(it)
                    }else{
                        flow.setVerticalStyle(it)
                    }
                }

            }
        }

        seekbar_bias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if(isHorizontalFlow){
                    flow.setHorizontalBias(p1 / 100f)
                } else {
                    flow.setVerticalBias(p1/100f)
                }
            }

        })

        button_group_alignments.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_vertical_alignment_top -> Flow.VERTICAL_ALIGN_TOP
                    R.id.button_vertical_alignment_bottom -> Flow.VERTICAL_ALIGN_BOTTOM
                    R.id.button_vertical_alignment_baseline -> Flow.VERTICAL_ALIGN_BASELINE
                    else -> Flow.VERTICAL_ALIGN_CENTER
                }.let {
                    flow.setVerticalAlign(it)
                }
            }
        }
    }


    companion object {
        val KEY_ORIENTATION = "key_orientation"
        public fun newInstance(orientation: Int): SingleDirectionFragment {
            val bundle = Bundle()
            bundle.putInt(KEY_ORIENTATION , orientation)

            val fragment = SingleDirectionFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

}
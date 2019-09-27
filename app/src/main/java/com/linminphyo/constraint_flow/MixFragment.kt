package com.linminphyo.constraint_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.TransitionManager
import kotlinx.android.synthetic.main.layout_flow_wrap.*

/**
 * Created by lin min phyo on 2019-09-07.
 */


class MixFragment : Fragment() {


    private val viewModel by lazy {
        ViewModelProviders.of(this).get(FlowViewModel::class.java)
    }

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
                    viewModel.setWrapMode(it)
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
                    viewModel.setChainStyle(it)
                }

            }
        }

        seekbar_bias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewModel.setBias((p1 / 100f))
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
                    viewModel.setAlignment(it)
                }
            }
        }

        button_group_flow_style.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button_flow_vertical -> Flow.VERTICAL
                    else -> Flow.HORIZONTAL
                }.let {
                    viewModel.setOrientation(it)
                }
            }
        }
        viewModel.flowOrientation.observe( this , Observer {
            TransitionManager.beginDelayedTransition(parent)
            flow.setOrientation(it)
            if(it == Flow.VERTICAL){
                button_vertical_alignment_baseline.visibility = View.GONE
                button_vertical_alignment_bottom.visibility = View.GONE
                button_vertical_alignment_center.visibility = View.GONE
                button_vertical_alignment_top.visibility = View.GONE

                button_horizontal_alignment_start.visibility = View.VISIBLE
                button_horizontal_alignment_end.visibility = View.VISIBLE
                button_horizontal_alignment_center.visibility = View.VISIBLE
            }else{
                button_vertical_alignment_baseline.visibility = View.VISIBLE
                button_vertical_alignment_bottom.visibility = View.VISIBLE
                button_vertical_alignment_center.visibility = View.VISIBLE
                button_vertical_alignment_top.visibility = View.VISIBLE

                button_horizontal_alignment_start.visibility = View.GONE
                button_horizontal_alignment_end.visibility = View.GONE
                button_horizontal_alignment_center.visibility = View.GONE
            }
        })

        viewModel.flowWrapMode.observe(this , Observer {
            TransitionManager.beginDelayedTransition(parent)
            flow.setWrapMode(it)
        })

        viewModel.flowChainStyle.observe(this , Observer {
            TransitionManager.beginDelayedTransition(parent)
            if (it.first == Flow.VERTICAL) {
                flow.setVerticalStyle(it.second)
            } else {
                flow.setHorizontalStyle(it.second)
            }
        })

        viewModel.flowAlignment.observe(this , Observer {
            TransitionManager.beginDelayedTransition(parent)
            // Alignment is in the opposite direction of flow orientation
            if (it.first == Flow.VERTICAL) {
                flow.setHorizontalAlign(it.second)
            } else {
                flow.setVerticalAlign(it.second)
            }
        })

        viewModel.flowBias.observe(this, Observer {
            TransitionManager.beginDelayedTransition(parent)
            if (it.first == Flow.VERTICAL) {
                flow.setVerticalBias(it.second)
                flow.setHorizontalBias(0f)
            } else {
                flow.setVerticalBias(0f)
                flow.setHorizontalBias(it.second)
            }
        })
    }

    companion object {

        fun getInstance(): MixFragment {
            return MixFragment()
        }
    }
}
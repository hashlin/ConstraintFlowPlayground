package com.linminphyo.constraint_flow

import androidx.constraintlayout.helper.widget.Flow
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by lin min phyo on 2019-09-15.
 */

class FlowViewModel : ViewModel() {
    val flowWrapMode = MutableLiveData<Int>()
    val flowChainStyle = MutableLiveData<Pair<Int , Int>>()
    val flowAlignment = MutableLiveData<Pair<Int , Int>>()
    val flowBias = MutableLiveData<Pair<Int , Float>>()
    val flowOrientation by lazy {
        MutableLiveData<Int>().apply {
            value = Flow.HORIZONTAL
        }
    }

    fun setWrapMode(wrapMode : Int){
        flowWrapMode.postValue(wrapMode)
    }

    fun setChainStyle(chainStyle : Int){
        flowChainStyle.postValue(flowOrientation.value!! to chainStyle)
    }

    fun setAlignment(alignment : Int) {
        flowAlignment.postValue(flowOrientation.value!! to alignment)
    }

    fun setBias(bias : Float){
        flowBias.postValue(flowOrientation.value!! to bias)
    }

    fun setOrientation(orientation: Int){
        flowOrientation.postValue(orientation)
        flowWrapMode.value?.let { setWrapMode(it) }
        flowChainStyle.value?.let { setChainStyle(it.second) }
        flowAlignment.value?.let { setAlignment(it.second) }
        flowBias.value?.let { setBias(it.second) }
    }

//    data class FlowAttributes( val wrapMode : Int , val chainStyle : Int, val alignment : Int , val bias : Float)
}
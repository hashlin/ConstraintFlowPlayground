package com.linminphyo.constraint_flow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by lin min phyo on 2019-09-15.
 */

class FlowViewModel : ViewModel() {
    val flowStyleLiveData = MutableLiveData<Int>()
    val flowWrapMode = MutableLiveData<Int>()
    val flowChainStyle = MutableLiveData<Int>()
    val flowBias = MutableLiveData<Float>()

}
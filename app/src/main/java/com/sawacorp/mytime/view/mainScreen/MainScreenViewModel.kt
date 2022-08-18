package com.sawacorp.mytime.view.mainScreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawacorp.mytime.model.PieChartData
import com.sawacorp.mytime.repository.SliceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: SliceRepository
) : ViewModel() {

    val allSlice = repository.allSlice
    val activeSlice: MutableLiveData<PieChartData.Slice> = MutableLiveData(null)

    private val coroutineContext = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", throwable.toString())
    }

    fun insert(sliceList: List<PieChartData.Slice>) = viewModelScope.launch(coroutineContext) {
        repository.insert(sliceList)
    }

    fun addTask(slice: PieChartData.Slice) {
        val slices = allSlice.value?.toMutableList() ?: mutableListOf()
        slices.add(slice)
        insert(slices)
    }

    fun setActiveSlice(slice: PieChartData.Slice) = viewModelScope.launch(coroutineContext) {
        activeSlice.postValue(slice)
    }

}
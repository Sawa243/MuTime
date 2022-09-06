package com.sawacorp.mytime.view.mainScreen

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sawacorp.mytime.getDateToInt
import com.sawacorp.mytime.model.PieChartData
import com.sawacorp.mytime.repository.SliceRepository
import com.sawacorp.mytime.service.TimerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: SliceRepository
) : ViewModel() {

    val allSlice = repository.allSlice
    val activeSlice: MutableLiveData<PieChartData.Slice> = MutableLiveData(null)
    val period: MutableLiveData<String> = MutableLiveData("Сегодня")
    val sliceByDate: MutableLiveData<List<PieChartData.Slice>> = MutableLiveData(listOf())

    val time = MutableLiveData(0.0)
    val timeInString = MutableLiveData("")
    private val timerStarted = MutableLiveData(false)

    private val coroutineContext = CoroutineExceptionHandler { _, throwable ->
        Log.e("TAG", throwable.toString())
    }

    private fun insert(sliceList: List<PieChartData.Slice>) =
        viewModelScope.launch(coroutineContext) {
            repository.insert(sliceList)
        }

    fun addTask(slice: PieChartData.Slice) {
        val slices = allSlice.value?.toMutableList() ?: mutableListOf()
        slices.add(slice)
        insert(slices)
    }

    val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time.value = intent.getDoubleExtra(TimerService.TIME_EXTRA, 0.0)
            timeInString.value = getTimeStringFromDouble(time.value ?: 0.0)
        }
    }

    private fun makeTimeString(hour: Int, min: Int, sec: Int): String =
        String.format("%02d:%02d:%02d", hour, min, sec)

    fun getTimeStringFromDouble(time: Double): String {
        val resultInt = time.roundToInt()
        val hours = resultInt % 86400 / 3600
        val minutes = resultInt % 86400 % 3600 / 60
        val seconds = resultInt % 86400 % 3600 % 60

        return makeTimeString(hours, minutes, seconds)
    }

    fun stopTimer(nameTask: String) {
        timerStarted.value = false
        saveNewList(nameTask)
        activeSlice.value = null
    }

    private fun saveNewList(nameTask: String) {
        val newList = allSlice.value ?: mutableListOf()
        newList.forEach {
            if (it.name == nameTask) {
                it.value = time.value?.toFloat() ?: 0f
            }
        }
        insert(newList)
    }

    fun startTimer(slice: PieChartData.Slice) =
        viewModelScope.launch(coroutineContext) {
            timerStarted.value = true
            time.value = slice.value.toDouble()
            activeSlice.value = slice
        }

    fun editTask(sliceForEdit: PieChartData.Slice, newNameTask: String, newColorTask: Color) {
        viewModelScope.launch(coroutineContext) {
            val slices = allSlice.value?.toMutableList() ?: mutableListOf()
            slices.removeIf { slice -> slice.name == sliceForEdit.name }
            slices.add(
                PieChartData.Slice(
                    newNameTask.ifEmpty { sliceForEdit.name },
                    sliceForEdit.value, newColorTask.toArgb(),
                    sliceForEdit.date
                )
            )
            insert(slices)
        }
    }

    fun deleteTask(oldSlice: PieChartData.Slice) {
        viewModelScope.launch(coroutineContext) {
            val slices = allSlice.value?.toMutableList() ?: mutableListOf()
            slices.removeIf { slice -> slice.name == oldSlice.name }
            insert(slices)
        }
    }

    fun setPeriod(newPeriod: String) {
        viewModelScope.launch(coroutineContext) {
            period.postValue(newPeriod)
            sliceByDate.postValue(slicesByDate(newPeriod))
        }
    }

    private fun slicesByDate(period: String): List<PieChartData.Slice> {
        return when (period) {
            "Сегодня" -> allSlice.value?.filter { slice -> slice.date == getDateToInt() }
                ?: listOf()
            "Вчера" -> allSlice.value?.filter { slice -> slice.date <= (getDateToInt() - 1) }
                ?: listOf()
            "Неделя" -> allSlice.value?.filter { slice -> slice.date <= (getDateToInt() - 7) }
                ?: listOf()
            else -> listOf()
        }
    }

}
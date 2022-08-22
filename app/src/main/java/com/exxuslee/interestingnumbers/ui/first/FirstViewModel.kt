package com.exxuslee.interestingnumbers.ui.first

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.exxuslee.domain.usecases.GetNumberUseCase
import com.exxuslee.domain.utils.HandleResult
import com.exxuslee.interestingnumbers.R
import com.exxuslee.testprofitof.utils.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstViewModel(private val getIDUseCase: GetNumberUseCase.Base) : ViewModel() {

    private val _ids = MutableLiveData<List<String>?>()
    val ids = _ids.asLiveData()

    private val _selectedID = MutableLiveData(0)
    val selectedID = _selectedID.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    fun remoteRandom() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val handleResult = object : HandleResult<String> {
                override fun handleError(message: String) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(false)
                }

                override fun handleSuccess(data: String) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(true)
                    _ids.value = _ids.value?.plus(data)
                }
            }
            withContext(Dispatchers.IO) {
                getIDUseCase.getRandom().handle(handleResult)
            }
        }
    }


    fun selectID(id: Int) {
        _selectedID.postValue(id)
    }

    fun pressButton(direction: Boolean) {
        var cur = _selectedID.value
        if (cur != null) {
            if (direction) cur += 1 else cur -= 1
            if (cur >= _ids.value?.size!!) cur = 0
            if (cur < 0) cur = _ids.value?.size!! - 1
            _selectedID.postValue(cur)
        }

    }

    private fun loadNumber(number: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val handleResult = object : HandleResult<String> {
                override fun handleError(message: String) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(false)
                }

                override fun handleSuccess(data: String) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(true)
                    _ids.value = _ids.value?.plus(data)
                }
            }
            withContext(Dispatchers.IO) {
                getIDUseCase.getNumber(number).handle(handleResult)
            }
        }
    }

    fun navigate(content: String, navController: NavController) {
        val bundle = Bundle()
        bundle.putString("content", content)
        navController.navigate(R.id.action_FirstFragment_to_SecondFragment,
            bundle)
    }

    companion object {
        const val TAG = "testProfit"
    }
}
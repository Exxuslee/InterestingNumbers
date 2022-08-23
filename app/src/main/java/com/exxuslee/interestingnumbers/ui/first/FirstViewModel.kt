package com.exxuslee.interestingnumbers.ui.first

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.exxuslee.domain.usecases.GetNumberUseCase
import com.exxuslee.domain.utils.HandleResult
import com.exxuslee.interestingnumbers.R
import com.exxuslee.testprofitof.utils.asLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstViewModel(private val getIDUseCase: GetNumberUseCase.Base) : ViewModel() {

    private val _ids = MutableLiveData<Map<Int, String>?>()
    val ids = _ids.asLiveData()

    private val _selectedID = MutableLiveData(0)
    val selectedID = _selectedID.asLiveData()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading.asLiveData()

    private val _dataFetchState = MutableLiveData<Boolean>()
    val dataFetchState = _dataFetchState.asLiveData()

    fun getRandomNumber() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val handleResult = object : HandleResult<Pair<Int, String>> {
                override fun handleError(message: String) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(false)
                }

                override fun handleSuccess(data: Pair<Int, String>) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(true)
                    _ids.postValue(_ids.value?.plus(data) ?: mapOf(data))
                }
            }
            withContext(Dispatchers.IO) {
                getIDUseCase.getRandom().handle(handleResult)
            }
        }
    }

    fun getNumber(number: Int) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            val handleResult = object : HandleResult<Pair<Int, String>> {
                override fun handleError(message: String) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(false)
                }

                override fun handleSuccess(data: Pair<Int, String>) {
                    _isLoading.postValue(false)
                    _dataFetchState.postValue(true)
                    _ids.postValue(_ids.value?.plus(data) ?: mapOf(data))
                }
            }
            withContext(Dispatchers.IO) {
                getIDUseCase.getNumber(number).handle(handleResult)
            }
        }
    }

    fun navigate(id: Int, content: String, view: View) {
        _selectedID.postValue(id)
        val bundle = Bundle()
        bundle.putString("content", content)
        Navigation.findNavController(view).navigate(R.id.action_1fragment_to_2frafment, bundle)
    }

    fun removeNumber(key: Int) {
        _ids.postValue(_ids.value?.minus(key))
        Log.d(TAG, _ids.value.toString())
    }

    companion object {
        const val TAG = "testNumbers"
    }
}
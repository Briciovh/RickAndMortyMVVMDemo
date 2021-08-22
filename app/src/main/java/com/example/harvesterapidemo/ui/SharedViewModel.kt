package com.example.harvesterapidemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harvesterapidemo.model.rickmorty.CharacterResponse
import com.example.harvesterapidemo.model.rickmorty.Results
import com.example.harvesterapidemo.repository.MainRepository
import com.example.harvesterapidemo.repository.PickRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val mainRepository: MainRepository,
    private val pickRepository: PickRepository
) : ViewModel() {

    private val mTextLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String>
        get() = mTextLiveData

    private val mCharacterList = MutableLiveData<List<Results>>()
    val characterList: LiveData<List<Results>>
        get() = mCharacterList

    private val mCurrentCharacter = MutableLiveData<Results?>()
    val currentCharacter: LiveData<Results?>
        get() = mCurrentCharacter

    private val mCurrentId = MutableLiveData<Int>()
    val currentId: LiveData<Int>
        get() = mCurrentId

    private val mLoadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = mLoadingState

    private var currentCharacterResponse: CharacterResponse? = null

    var currentPage: Int = 0

    init {
        mCurrentId.value = 0
        setLoadingState(true)
    }

    fun getCharacters() {
        viewModelScope.launch {
            setLoadingState(true)
            mainRepository.getCharacters(currentPage).let {
                if (it.isSuccessful) {
                    currentCharacterResponse = it.body()
                    mCharacterList.value = it.body()?.results
                    currentPage++
                    setLoadingState(false)
                } else {
                    mTextLiveData.value = it.errorBody().toString()
                    setLoadingState(false)
                }
            }
        }
    }

    fun setCurrentId(id: Int) {
        mCurrentId.value = id
        mCurrentCharacter.value = null
    }

    fun getCharacterById(charId: Int) {
        viewModelScope.launch {
            setLoadingState(true)
            mainRepository.getCharacterById(charId).let {
                if (it.isSuccessful) {
                    mCurrentCharacter.value = it.body()
                    setLoadingState(false)
                } else {
                    mTextLiveData.value = it.errorBody().toString()
                    setLoadingState(false)
                }
            }
        }
    }

    fun setLoadingState(isLoading: Boolean){
        mLoadingState.value = isLoading
    }

    //Still to complete the headers and query parameters
    private fun getPickOrderHeaders() {
        viewModelScope.launch {
            pickRepository.getPickOrderHeaders().let {
                if (it.isSuccessful) {
                    mTextLiveData.value = it.body().toString()
                } else {
                    mTextLiveData.value = it.errorBody().toString()
                }
            }
        }
    }

    fun lastPage(): Boolean {
        currentCharacterResponse?.let {
            return currentPage == it.info.pages
        }
        return true
    }

}
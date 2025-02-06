package com.project.namu.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.project.namu.data.model.StoreData
import com.project.namu.data.remote.ApiClient
import com.project.namu.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val authRepository: AuthRepository,  // ✅ AT & RT 관리용 Repository 추가
    private val authInterceptor: AuthInterceptor) : ViewModel () {

    private val apiService = ApiClient.getInstance(authRepository, authInterceptor)

    private val _query = MutableStateFlow("")
    val query : StateFlow<String> = _query

    private val _isSearching = MutableStateFlow(false) // 검색 중 여부
    val isSearching: StateFlow<Boolean> = _isSearching

    private val _searchResults = MutableStateFlow<List<StoreData>>(emptyList()) //검색 결과 가게 리스트 받아오기
    val searchResults : StateFlow<List<StoreData>> = _searchResults

    fun onQueryChange(newQuery: String) {
        _query.value = newQuery
    }

    fun searchStores(navController: NavController){
        viewModelScope.launch {
            try{

                _isSearching.value = true


                Log.d("StoreSearch", "search 정보 보내는중 ${_query.value}")




                val response = apiService.searchStores(_query.value)
                _searchResults.value = response.data

                if(response.isSuccess == false){
                    _isSearching.value = false
                }else{
                    navController.navigate(Screen.Search.route)

                }



                //Log.d("StoreSearch", "화면 전환 전 서칭 값 ${_isSearching.value}")




                //Log.d("StoreSearch", "화면 전환 후 서칭 값 ${_isSearching.value}")


                //Log.d("StoreSearch", "search 정보 받아오기 성공 ${response}")




            } catch (e:Exception) {
                Log.d("StoreSearch", "search 정보 가져오기 실패")

            }
        }
    }
}
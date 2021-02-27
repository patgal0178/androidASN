package com.example.ricknmortyquiz.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ricknmortyquiz.R

//data class Question(
     //   val id:Int,
     //   val answer:Boolean,var answered:Boolean =false
//)
data class Question(
        val id:Int,
        val answer:Boolean,
        var answered:Boolean = false,
        var answerUser:Boolean = false
)
class GameViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private  val _answeredItem = MutableLiveData<Boolean>()
    val Answereditem: LiveData<Boolean>
        get() = _answeredItem

   // private lateinit var questionBank: MutableList<Question>


    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}
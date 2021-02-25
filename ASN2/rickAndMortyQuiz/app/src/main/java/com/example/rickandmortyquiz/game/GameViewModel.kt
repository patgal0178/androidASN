package com.example.rickandmortyquiz.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rickandmortyquiz.R

data class Question(
    val qId:Int,
    val answer:Boolean,
    var attempt:Boolean = false,
    var answered:Boolean= false
)
class GameViewModel : ViewModel() {
    private var qIndex =0
    private lateinit var questionBank: MutableList<Question>

    private val _scoreS = MutableLiveData<String>()
    val scoreS: LiveData<String>
        get() = _scoreS

    private val _question = MutableLiveData<Int>()
    val question: LiveData<Int>
        get() = _question

    private val _checkTrue = MutableLiveData<Boolean>()
    val checkTrue: LiveData<Boolean>
        get() = _checkTrue

    private val _checkFalse = MutableLiveData<Boolean>()
    val checkFalse: LiveData<Boolean>
        get() = _checkFalse

    private val _isCorrect = MutableLiveData<Boolean>()
    val isCorrect: LiveData<Boolean>
        get() = _isCorrect

    private val _finished = MutableLiveData<Boolean>()
    val Finished: LiveData<Boolean>
        get() = _finished

    private val _attempt = MutableLiveData<Boolean>()
    val Attempt: LiveData<Boolean>
        get() = _attempt

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    init {
        _score.value = 0
        new()
    }
    fun onGameFinished(){
        _finished.value=true
    }

    fun onGameFinishedDone(){
        _finished.value=false
    }

    fun new(){
        resetQuiz()
        qIndex=0
        _finished.value=false
    }

    fun answered(ANanswer:Boolean){
        _question.value = questionBank[qIndex].qId
        _attempt.value = questionBank[qIndex].attempt
        _isCorrect.value = questionBank[qIndex].answer == ANanswer
    }

    fun nextQ(){
        if(qIndex<20){
        qIndex+=1
        }
        _question.value = questionBank[qIndex].qId
    }

    fun prevQ(){
        if(qIndex>0){
            qIndex-=1
        }
        _question.value = questionBank[qIndex].qId
    }

    private fun resetQuiz(){
        questionBank = mutableListOf(
            Question(R.string.question_1, false),
            Question(R.string.question_2, true),
            Question(R.string.question_3, true),
            Question(R.string.question_4, false),
            Question(R.string.question_5, false),
            Question(R.string.question_6, true),
            Question(R.string.question_7, false),
            Question(R.string.question_8, true),
            Question(R.string.question_9, false),
            Question(R.string.question_10, false),
            Question(R.string.question_11, false),
            Question(R.string.question_12, true),
            Question(R.string.question_13, false),
            Question(R.string.question_14, true),
            Question(R.string.question_15, false),
            Question(R.string.question_16, false),
            Question(R.string.question_17, true),
            Question(R.string.question_18, false),
            Question(R.string.question_19, false),
            Question(R.string.question_20, true)
        )
        questionBank.shuffle()
    }

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime


}
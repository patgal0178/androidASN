package com.example.ricknmortyquiz.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.ricknmortyquiz.R
import com.example.ricknmortyquiz.databinding.GameFragmentBinding


class GameFragment : Fragment() {

    private var  qIndex:Int =0
    private lateinit var questionBank: MutableList<Question>
    //private lateinit var viewModel: GameViewModel
    private var scores:Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<GameFragmentBinding>(inflater,
                R.layout.game_fragment,container,false)
        newGame()
        binding.txtDisplay.text = context?.getText(questionBank[qIndex].id)
        binding.RdoTrue.setOnClickListener { onRadioButtonClicked(binding.RdoTrue,binding) }
        binding.RdoFalse.setOnClickListener { onRadioButtonClicked(binding.RdoFalse, binding) }
        binding.iBtnPrev.setOnClickListener { prevQuestion(binding.txtDisplay,binding) }
        binding.iBtnNext.setOnClickListener { nextQuestion(binding.txtDisplay,binding) }
        return binding.root
    }

    //override fun onActivityCreated(savedInstanceState: Bundle?) {
        //super.onActivityCreated(savedInstanceState)
      //  viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        // TODO: Use the ViewModel
    //}
    private fun newGame(){
        qIndex = 0
        questionBank = resetQuiz()
    }

    private fun nextQuestion(txtDisplay: TextView, binding: GameFragmentBinding) {
        if(qIndex==19){
            qIndex = 0
            txtDisplay.text = context?.getText(questionBank[qIndex].id)
        }else{
            qIndex += 1
            txtDisplay.text = context?.getText(questionBank[qIndex].id)
        }

        if(checkForAnswered(qIndex)){
            binding.wrongone.visibility = View.GONE
            binding.rightImage.visibility = View.GONE
            binding.RdoFalse.isEnabled = false
            binding.RdoTrue.isEnabled = false

            if(questionBank[qIndex].answerUser){
                binding.RdoTrue.isChecked = true
                binding.RdoFalse.isChecked = false
                if(checkAnswer(questionBank[qIndex].answerUser)){
                    binding.rightImage.visibility = View.VISIBLE
                    binding.wrongone.visibility = View.GONE
                }else{
                    binding.wrongone.visibility = View.VISIBLE
                    binding.rightImage.visibility = View.GONE
                }
            }else{
                binding.RdoTrue.isChecked = false
                binding.RdoFalse.isChecked = true
                if(checkAnswer(questionBank[qIndex].answerUser)){
                    binding.rightImage.visibility = View.VISIBLE
                    binding.wrongone.visibility = View.GONE
                }else{
                    binding.wrongone.visibility = View.VISIBLE
                    binding.rightImage.visibility = View.GONE
                }
            }
        }else{
            binding.wrongone.visibility = View.GONE
            binding.rightImage.visibility = View.GONE
            binding.RdoFalse.isEnabled = true
            binding.RdoTrue.isEnabled = true
            binding.RdoFalse.isChecked = false
            binding.RdoTrue.isChecked = false
        }
    }

    private fun prevQuestion(txtDisplay: TextView, binding: GameFragmentBinding) {
        if(qIndex==0){
            qIndex = 19
            txtDisplay.text = context?.getText(questionBank[qIndex].id)
        }else{
            qIndex -= 1
            txtDisplay.text = context?.getText(questionBank[qIndex].id)
        }

        if(checkForAnswered(qIndex)){
            binding.wrongone.visibility = View.GONE
            binding.rightImage.visibility = View.GONE
            binding.RdoFalse.isEnabled = false
            binding.RdoTrue.isEnabled = false

            if(questionBank[qIndex].answerUser){
                binding.RdoTrue.isChecked = true
                binding.RdoFalse.isChecked = false
                if(checkAnswer(questionBank[qIndex].answerUser)){
                    binding.rightImage.visibility = View.VISIBLE
                    binding.wrongone.visibility = View.GONE
                }else{
                    binding.wrongone.visibility = View.VISIBLE
                    binding.rightImage.visibility = View.GONE
                }
            }else{
                binding.RdoTrue.isChecked = false
                binding.RdoFalse.isChecked = true
                if(checkAnswer(questionBank[qIndex].answerUser)){
                    binding.rightImage.visibility = View.VISIBLE
                    binding.wrongone.visibility = View.GONE
                }else{
                    binding.wrongone.visibility = View.VISIBLE
                    binding.rightImage.visibility = View.GONE
                }
            }
        }else{
            binding.wrongone.visibility = View.GONE
            binding.rightImage.visibility = View.GONE
            binding.RdoFalse.isEnabled = true
            binding.RdoTrue.isEnabled = true
            binding.RdoFalse.isChecked = false
            binding.RdoTrue.isChecked = false
        }
    }

    private fun checkAnswer(value: Boolean):Boolean{
        var qa: Boolean = questionBank[qIndex].answer
        return value==qa
    }

    private fun changeDisplayedImg(binding: GameFragmentBinding, value: Boolean){
        if(checkAnswer(value)){
            binding.rightImage.visibility = View.VISIBLE
            binding.wrongone.visibility = View.GONE
            binding.RdoFalse.isEnabled = false
            binding.RdoTrue.isEnabled = false
        }else{
            binding.wrongone.visibility = View.VISIBLE
            binding.rightImage.visibility = View.GONE
            binding.RdoFalse.isEnabled = false
            binding.RdoTrue.isEnabled = false
        }
        questionBank[qIndex].answered = true
    }

    private fun checkAllAnsweredQuestion(){
        var i:Int =0
        for(q in questionBank){
            if(q.answered==true){
                i+=1
            }
        }
        if(i==20){
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameOverFragment(scores))
        }
    }
    private fun checkForAnswered(id: Int):Boolean{
        return questionBank[id].answered
    }

    private fun onRadioButtonClicked(button: RadioButton, binding: GameFragmentBinding) {
        when (button.id) {
            R.id.RdoTrue ->
                if (button.isChecked) {
                    changeDisplayedImg(binding,true)
                    questionBank[qIndex].answerUser =true
                    if(checkAnswer(true)){
                        scores+=1
                    }
                }
            R.id.RdoFalse ->
                if (button.isChecked) {
                    changeDisplayedImg(binding,false)
                    questionBank[qIndex].answerUser =false
                    if(checkAnswer(true)){
                        scores+=1
                    }
                }
        }
        checkAllAnsweredQuestion()
    }

    private fun resetQuiz(): MutableList<Question> {
        var questionBank = mutableListOf(
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
        return questionBank
    }
}
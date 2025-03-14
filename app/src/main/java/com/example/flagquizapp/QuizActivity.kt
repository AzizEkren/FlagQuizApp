package com.example.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flagquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var questions:ArrayList<Flags>
    private lateinit var wrongchoice:ArrayList<Flags>
    private lateinit var trueQuestion:Flags
    private lateinit var allChoice:HashSet<Flags>
    private lateinit var binding:ActivityQuizBinding
    private lateinit var db:DatabaseHelper

    private var questionsCounter=0
    private var correctCounter=0
    private var wrongCounter=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db=DatabaseHelper(this)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        questions=Flagsdao().bring5randomflags(db)
        questionLoad()


        binding.buttonA.setOnClickListener {
            correctControl(binding.buttonA)
            questionCounterControl()
        }
        binding.buttonB.setOnClickListener {
            correctControl(binding.buttonB)
            questionCounterControl()
        }
        binding.buttonC.setOnClickListener {
            correctControl(binding.buttonC)
            questionCounterControl()
        }
        binding.buttonD.setOnClickListener {
            correctControl(binding.buttonD)
            questionCounterControl()
        }
    }
    fun questionLoad(){
        binding.textViewQuestionNumber.text="${questionsCounter+1}.Soru"

        trueQuestion=questions.get(questionsCounter)

        binding.imageViewFlag.setImageResource(resources.getIdentifier(trueQuestion.bayrak_resim,"drawable",packageName))

        wrongchoice=Flagsdao().bring3falsechoiceflags(db,trueQuestion.bayrak_id)

        allChoice= HashSet()
        allChoice.add(trueQuestion)
        allChoice.add(wrongchoice.get(0))
        allChoice.add(wrongchoice.get(1))
        allChoice.add(wrongchoice.get(2))

        binding.buttonA.text=allChoice.elementAt(0).bayrak_ad
        binding.buttonB.text=allChoice.elementAt(1).bayrak_ad
        binding.buttonC.text=allChoice.elementAt(2).bayrak_ad
        binding.buttonD.text=allChoice.elementAt(3).bayrak_ad



    }
fun questionCounterControl(){
    questionsCounter++

    if (questionsCounter!=5){
        questionLoad()
    }else{
        val intent=Intent(this@QuizActivity,ResultActivity::class.java)
        intent.putExtra("correctCounter",correctCounter)
        startActivity(intent)
        finish()


    }
}
    fun correctControl(button: Button){
        val buttonText=button.text.toString()
        val correctAnswer=trueQuestion.bayrak_ad

        if (buttonText==correctAnswer){
            correctCounter++
        }else{
            wrongCounter++
        }
        binding.textViewTrue.text="CORRECT:$correctCounter"
        binding.textViewFalse.text="WRONG:$wrongCounter"

    }


}
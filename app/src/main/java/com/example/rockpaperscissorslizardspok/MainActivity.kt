package com.example.rockpaperscissorslizardspok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rockpaperscissorslizardspok.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val tools = arrayOf("камень","бумага","ножницы","ящерица","спок")
    var personchoice = ""
    var winCounter = 0
    var loseCounter = 0
    var drawCounter = 0
    var Timer = System.currentTimeMillis()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            binding.btnLizard.isEnabled = true
            binding.btnSpok.isEnabled = true
            binding.btnPaper.isEnabled = true
            binding.btnRock.isEnabled = true
            binding.btnScissors.isEnabled = true
            binding.txtChoise.visibility = View.VISIBLE
            binding.RulesImage.visibility = View.VISIBLE
            binding.btnStop.isEnabled = true
            binding.btnStart.isEnabled = false
            binding.txtChoise.text="Сделайте ход"
            Timer = System.currentTimeMillis()
        }

        binding.btnStop.setOnClickListener {
            winCounter = 0
            loseCounter = 0
            drawCounter = 0

            binding.btnLizard.isEnabled = false
            binding.btnSpok.isEnabled = false
            binding.btnPaper.isEnabled = false
            binding.btnRock.isEnabled = false
            binding.btnScissors.isEnabled = false
            binding.UserImage.visibility = View.INVISIBLE
            binding.ComputerImage.visibility = View.INVISIBLE
            binding.txtChoise.visibility = View.INVISIBLE
            binding.btnRepeat.visibility = View.INVISIBLE
            binding.btnStatistics.visibility = View.INVISIBLE
            binding.RulesImage.visibility = View.INVISIBLE
            binding.btnStop.isEnabled = false
            binding.btnStart.isEnabled = true
        }
        binding.btnRepeat.setOnClickListener()
        {
            binding.btnLizard.isEnabled = true
            binding.btnSpok.isEnabled = true
            binding.btnPaper.isEnabled = true
            binding.btnRock.isEnabled = true
            binding.btnScissors.isEnabled = true
            binding.txtChoise.visibility = View.VISIBLE
            binding.btnStatistics.visibility = View.INVISIBLE
            binding.txtChoise.text="Сделайте ход"
        }

        binding.btnStatistics.setOnClickListener()
        {
            if (binding.textView.visibility == View.VISIBLE) {
                VisibilityFalse()
                binding.btnStop.isEnabled = true
                binding.btnRepeat.isEnabled = true
            } else {
                VisibilityTrue()
            }
        }

        binding.btnRock.setOnClickListener()
        {
            personchoice = "камень"
            checking()
        }
        binding.btnScissors.setOnClickListener()
        {
            personchoice = "ножницы"
            checking()
        }
        binding.btnPaper.setOnClickListener()
        {
            personchoice = "бумага"
            checking()
        }
        binding.btnLizard.setOnClickListener()
        {
            personchoice = "ящерица"
            checking()
        }
        binding.btnSpok.setOnClickListener()
        {
            personchoice = "спок"
            checking()
        }
    }
        fun checking() {
            val compchoice = tools.random()
            binding.UserImage.visibility = View.VISIBLE
            binding.ComputerImage.visibility = View.VISIBLE
            binding.btnRepeat.visibility = View.VISIBLE
            binding.txtChoise.visibility = View.VISIBLE
            when {
                (personchoice == "камень" && compchoice == "камень") ||
                        (personchoice == "бумага" && compchoice == "бумага") ||
                        (personchoice == "ножницы" && compchoice == "ножницы") ||
                        (personchoice == "ящерица" && compchoice == "ящерица") ||
                        (personchoice == "спок" && compchoice == "спок") -> {
                    binding.txtChoise.text = "Ничья!"
                    ++drawCounter
                    images(compchoice)
                    repeat()
                }
                (personchoice == "камень" && compchoice == "ящерица") ||
                        (personchoice == "камень" && compchoice == "ножницы") ||
                        (personchoice == "бумага" && compchoice == "камень") ||
                        (personchoice == "бумага" && compchoice == "спок") ||
                        (personchoice == "ножницы" && compchoice == "бумага") ||
                        (personchoice == "ножницы" && compchoice == "ящерица") ||
                        (personchoice == "ящерица" && compchoice == "бумага") ||
                        (personchoice == "ящерица" && compchoice == "спок") ||
                        (personchoice == "спок" && compchoice == "камень") ||
                        (personchoice == "спок" && compchoice == "ножницы") -> {
                    binding.txtChoise.text = "Вы выиграли!"
                    ++winCounter

                    images(compchoice)
                    repeat()
                }
                else -> {
                    binding.txtChoise.text = "Выиграл компьютер!"
                    ++loseCounter
                    images(compchoice)
                    repeat()
                }
            }
        }

        fun images(choicecomp:String)
        {
            when (choicecomp){
                "камень" -> binding.ComputerImage.setImageResource(R.drawable.rock)
                "ножницы" -> binding.ComputerImage.setImageResource(R.drawable.scissors)
                "бумага" -> binding.ComputerImage.setImageResource(R.drawable.paper)
                "ящерица" -> binding.ComputerImage.setImageResource(R.drawable.lizard)
                "спок" -> binding.ComputerImage.setImageResource(R.drawable.spok)
            }
            when (personchoice){
                "камень" -> binding.UserImage.setImageResource(R.drawable.rock)
                "ножницы" ->  binding.UserImage.setImageResource(R.drawable.scissors)
                "бумага" ->  binding.UserImage.setImageResource(R.drawable.paper)
                "ящерица" ->  binding.UserImage.setImageResource(R.drawable.lizard)
                "спок" ->  binding.UserImage.setImageResource(R.drawable.spok)
            }
        }


        fun VisibilityFalse()
        {
            binding.tableLayout2.visibility = View.VISIBLE
            binding.textView.visibility = View.INVISIBLE
            binding.btnStatistics.text="Показать счет"
        }

        fun VisibilityTrue()
        {
            binding.textView.visibility = View.VISIBLE
            binding.tableLayout2.visibility = View.INVISIBLE
            binding.btnStop.isEnabled = false
            binding.btnRepeat.isEnabled = false
            binding.btnStatistics.text="Скрыть счет"
            binding.textView.text = "Побед: ${winCounter}\nПоражений: ${loseCounter}\nНичьих: ${drawCounter}\n" +
                    "\nВремя игры\n${GetTime(System.currentTimeMillis() - Timer).first} час(-ов)   " +
                    "${GetTime(System.currentTimeMillis() - Timer).second} мин.   " +
                    "${GetTime(System.currentTimeMillis() - Timer).third} сек."
        }

        fun GetTime (Mill:Long):Triple<Long,Long,Long>
        {
            var Hour:Long = Mill/3600000
            var Min:Long = (Mill-(Hour*3600000))/60000
            var Sec = (Mill-Hour*3600000-Min*60000)/1000
            return Triple(first = Hour, second = Min, third = Sec)
        }

        fun repeat()
        {
            binding.btnStatistics.visibility = View.VISIBLE
            binding.btnSpok.isEnabled=false
            binding.btnRock.isEnabled=false
            binding.btnPaper.isEnabled=false
            binding.btnScissors.isEnabled=false
            binding.btnLizard.isEnabled=false
        }
    }
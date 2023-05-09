package com.example.calculatorlinear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatViewInflater
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView?=null
    var lastNumeric: Boolean=false
    var lastDot: Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)
        tvInput=findViewById(R.id.tvInput)
    }
    fun Ondigit(view: View)


    {
        lastDot=false
        lastNumeric=true
      tvInput?.append((view as Button).text)
       //Toast.makeText(this,"Button clicked",Toast.LENGTH_LONG).show()
    }
    fun Onclear(view: View)
    {
        tvInput?.text=""
    }
    fun onDecimalPoint(view: View)
    {
        if(lastNumeric && !lastDot)
        {
            tvInput?.text="."
            lastNumeric=false
            lastDot=true
        }
    }
    fun onOperator(view: View){
        tvInput?.text?.let{

            if(lastNumeric && !isOperatorAdded(it.toString()))
            {
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }

    }
    fun onEqual(view : View) {
        if (lastNumeric)

        {
            var prefix=""
            var tvValue =tvInput?.text.toString()
            try{
                if(tvValue.startsWith("-"))
                {
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    tvInput?.text=removeZero((one.toDouble()-two.toDouble()).toString())

                }else if(tvValue.contains("+"))
                {
                    val splitValue=tvValue.split("+")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    tvInput?.text=removeZero((one.toDouble()+two.toDouble()).toString())

                }
                else if(tvValue.contains("*"))
                {
                    val splitValue=tvValue.split("*")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    tvInput?.text=removeZero((one.toDouble()*two.toDouble()).toString())

                }
                else
                {
                    val splitValue=tvValue.split("/")
                    var one=splitValue[0]
                    var two=splitValue[1]
                    if(prefix.isNotEmpty())
                    {
                        one=prefix + one
                    }
                    tvInput?.text=removeZero((one.toDouble()/two.toDouble()).toString())

                }



            }
            catch (e: ArithmeticException)
            {
                e.printStackTrace()
            }
        }
    }
    private fun removeZero(result: String): String
    {
        var value=result
        if(result.contains(".0"))
        {
            value=result.substring(0,result.length-2)
        }
        return value
    }

    private fun isOperatorAdded(value:String):Boolean{
     return if(value.startsWith("-"))
     {
         false
     }
        else
     {
         value.contains("+")|| value.contains("/")|| value.contains("*")
                 || value.contains("-")

     }
    }



}
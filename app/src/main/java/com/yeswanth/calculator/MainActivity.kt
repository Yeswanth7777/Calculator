package com.yeswanth.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput:TextView?=null
    private var lastNumeric:Boolean=false
    private var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.tvInput)

    }
    fun onDigit(view: View) {
            tvInput?.append((view as Button).text)
            lastDot=false
            lastNumeric=true
    }
    fun onClear(view: View){
        tvInput?.text=""
        lastDot=false
        lastNumeric=false
    }
    fun onDecimalPoint(view: View)
    {
        if(lastNumeric&& !lastDot){
            tvInput?.append(".")
            lastNumeric=false
            lastDot=true
        }
        if(tvInput?.text==""){
            tvInput?.append("0.")
        }
    }
    fun onOperator(view: View)
    {

        print("onOperation")
        tvInput?.text?.let{
        if(it==""){
            if((view as Button).text=="-")  tvInput?.append((view as Button).text)
        }
            if(lastNumeric&&!isOperatorAdded(it.toString())) {
            tvInput?.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
        }
    }
    fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")) false
        else {
                (value.contains("+"))
                        || (value.contains("-"))
                        || (value.contains("/"))
                        || (value.contains("*"))}
    }
    private fun removeLastZero( value:String):String{
        if(value[value.length-1]=='0'){
            return value.substring(0,value.length-2)
        }
        return value
    }
    fun onEqual(view: View){
        if(lastNumeric){
            var prefix=""
            var tvValue=tvInput?.text.toString()
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
            if(tvValue.contains("-")){
            val splitValue=tvValue.split("-")
                var one= splitValue[0]
                var two= splitValue[1]
                if(prefix.isNotEmpty())     one=prefix+one
                tvInput?.text=removeLastZero((one.toDouble()-two.toDouble()).toString())
            }
                else if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    var one= splitValue[0]
                    var two= splitValue[1]
                    if(prefix.isNotEmpty())     one=prefix+one
                    tvInput?.text=removeLastZero((one.toDouble()+two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    val splitValue=    tvValue.split("/")
                    var one= splitValue[0]
                    var two= splitValue[1]
                    if(prefix.isNotEmpty())     one=prefix+one
                    tvInput?.text=removeLastZero((one.toDouble()/two.toDouble()).toString())
                }
                else if(tvValue.contains("*")){
                    val splitValue=    tvValue.split("*")
                    var one= splitValue[0]
                    var two= splitValue[1]
                    if(prefix.isNotEmpty())     one=prefix+one
                    tvInput?.text=removeLastZero((one.toDouble()*two.toDouble()).toString())
                }
        }catch (e:ArithmeticException){
            e.printStackTrace()
        }
        }
    }
}
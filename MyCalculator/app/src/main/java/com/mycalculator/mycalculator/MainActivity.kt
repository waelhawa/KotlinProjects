package com.mycalculator.mycalculator

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_display.*
import kotlinx.android.synthetic.main.fragment_key_pad.*
import java.math.RoundingMode
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    var result : Double = 0.0                //holds the result for the calculation
    var value1 : Double = 0.0                //holds the first value
    var value2 : Double = 0.0                //holds the second value
    var sign : String = ""                   //holds the sign
    var nextSign : String = ""               //holds a copy
    var currentScreen : String = ""          //holds the text on the screen for orientation change
    var typingValue1 : Boolean = true        //checks if typing for value1
    var typingValue2 : Boolean = false       //checks if typing for value2
    var resetValueInput : Boolean = false    //mainly to reset the logic gate
    var newInput : Boolean = true            //checks if typing a new input
    var typing : Boolean = false             //checks if typing for period insetion
    var boolPeriod: Boolean = false          //gate to insert period

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment.display.setTextSize(50f)
        fragment.display.setText(result.toString())
    }

    fun onPlus (view : View) {
        nextSign = "+"
        multipleOperations(view)
    }

    fun onMinus (view : View) {
        nextSign = "-"
        multipleOperations(view)
    }

    fun onModulus (view :View) {
        nextSign = "%"
        multipleOperations(view)
    }

    fun onSquareRoot (view : View) {
        if (!fragment.display.getText().toString().equals("Error")) {
            nextSign = "sqr"
            sign = nextSign
            typingValue2 = false
            typingValue1 = false
            onEquals(view)
        }
    }

    fun onDivide (view : View) {
        nextSign = "/"
        multipleOperations(view)
    }

    fun onMultiply (view : View) {
        nextSign = "*"
        multipleOperations(view)
    }

    fun multipleOperations (view : View){
        newInput = true
        typing = false
        boolPeriod = false
        if (!fragment.display.getText().toString().equals("Error")) {

            if (typingValue2) {
                typingValue2 = false
                resetValueInput = true
                onEquals(view)
                sign = nextSign
//                value2 = fragment.display.getText().toString().toDouble()
            }

            if (typingValue1) {
                typingValue1 = false
                typingValue2 = true
                sign = nextSign
//                value1 = fragment.display.getText().toString().toDouble()
//                fragment.display.setText(value2.toString())

            }

            if (resetValueInput) {
                resetValueInput = false
                typingValue1 = true
            }
        }
    }

    fun digitLimit (num : String, view : View) {
        if (!fragment.display.getText().toString().equals("Error")) {
            if (newInput) {
                fragment.display.setText("")
                newInput = false
            }
            if (boolPeriod) {
                fragment.display.setText(fragment.display.getText().toString() + ".")
                boolPeriod = false
            }
            if (fragment.display.getText().length < 10 && !num.equals("")) {
                fragment.display.setText(fragment.display.getText().toString() + num)
            }


            if (typingValue1) {
                value1 = fragment.display.getText().toString().toDouble()
            }

            if (typingValue2) {
                value2 = fragment.display.getText().toString().toDouble()
            }
        }

    }

    fun onPlusMinus (view : View) {
        if (fragment.display.getText().toString().toDouble() != 0.0 && !fragment.display.getText().toString().equals("Error")) {
            nextSign = "+-"
            sign = nextSign
            typingValue2 = false
            typingValue1 = false
            onEquals(view)
        }
    }

    fun onClearEntry (view : View) {
        if (!(fragment.display.getText().toString().equals("Error"))) {
            fragment.display.setText(fragment.display.getText().toString().dropLast(1))
        }
        else {
            fragment.display.setText("")
        }
    }

    fun onClear (view : View) {
        result = 0.0
        value1 = 0.0
        value2 = 0.0
        sign = ""
        nextSign = ""
        typingValue1 = true
        typingValue2 = false
        resetValueInput = false
        newInput = true
        typing = false
        boolPeriod = false
        fragment.display.setText(result.toString())
    }

    fun onEquals (view : View) {
//        multipleOperations(view)
        if (typingValue2) {
            typingValue2 = false
            value2 = fragment.display.getText().toString().toDouble()
        }
        if (sign.equals("+-")){
            result = fragment.display.getText().toString().toDouble() * -1
        }
        if (sign.equals("+")){
            result = value1 + value2
        }
        if (sign.equals("-")){
            result = value1 - value2
        }
        if (sign.equals("/")){
            if (value2 != 0.0) {
                result = value1.div(value2)
                System.out.println(value1)
                System.out.println(value2)
                System.out.println(result)
            }
            else {
                fragment.display.setText("Error")
            }
        }
        if (sign.equals("*")){
            result = value1 * value2
        }
        if (sign.equals("%")){
            result = value1 % value2
        }
        if (sign.equals("sqr")){
            result = Math.sqrt(fragment.display.getText().toString().toDouble())
        }

        if (result.toString().length > 10) {
            result = result.toBigDecimal().setScale(8, RoundingMode.HALF_EVEN).toDouble()//toString().substring(0, 10).toDouble()
        }

        if (!fragment.display.getText().toString().equals("Error")) {
            fragment.display.setText(result.toString())
            resetValueInput = true
            value1 = result
            multipleOperations(view)
            sign = ""
        }
    }


    fun onPeriod (view : View) {
        if (!typing) {
            typing = true;
            boolPeriod = true
        }
    }

    fun onOne (view : View) {
        digitLimit("1", view)
    }

    fun onTwo (view : View) {
        digitLimit("2", view)
    }

    fun onThree (view : View) {
        digitLimit("3", view)
    }

    fun onFour (view : View) {
        digitLimit("4", view)
    }

    fun onFive (view : View) {
        digitLimit("5", view)
    }

    fun onSix (view : View) {
        digitLimit("6", view)
    }

    fun onSeven (view : View) {
        digitLimit("7", view)
    }

    fun onEight (view : View) {
        digitLimit("8", view)
    }

    fun onNine (view : View) {
        digitLimit("9", view)
    }

    fun onZero (view : View) {
        digitLimit("0", view)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        currentScreen = fragment.display.getText().toString()
        outState.putString("sign", sign)
        outState.putString("nextSign", nextSign)
        outState.putString("currentScreen", currentScreen)
        outState.putBoolean("typingValue1", typingValue1)
        outState.putBoolean("typingValue2", typingValue2)
        outState.putBoolean("resetValueInput", resetValueInput)
        outState.putBoolean("newInput", newInput)
        outState.putBoolean("boolPeriod", boolPeriod)
        outState.putBoolean("typing", typing)
        outState.putDouble("result", result)
        outState.putDouble("value1", value1)
        outState.putDouble("value2", value2)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        value1 = savedInstanceState.getDouble("value1")
        value2 = savedInstanceState.getDouble("value2")
        result = savedInstanceState.getDouble("result")
        typingValue1 = savedInstanceState.getBoolean("typingValue1")
        typingValue2 = savedInstanceState.getBoolean("typingValue2")
        resetValueInput = savedInstanceState.getBoolean("resetValueInput")
        newInput = savedInstanceState.getBoolean("newInput")
        boolPeriod = savedInstanceState.getBoolean("boolPeriod")
        typing = savedInstanceState.getBoolean("typing")
        sign = savedInstanceState.getString("sign").toString()
        nextSign = savedInstanceState.getString("nextSign").toString()
        currentScreen = savedInstanceState.getString("currentScreen").toString()
        fragment.display.setText(currentScreen)
    }

}
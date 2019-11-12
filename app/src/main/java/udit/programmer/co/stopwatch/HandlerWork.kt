package udit.programmer.co.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class HandlerWork : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()

        handler.postDelayed(object : Runnable, View.OnClickListener {
            override fun onClick(view: View) {
                when (view.id) {

                    R.id.btn -> {

                        val minu = min.text.toString().toInt()
                        val seco = sec.text.toString().toInt()

                        val res = minu * 60 + seco
                        logic(res)
                    }

                    R.id.reset -> {

                        min.setText("0")
                        sec.setText("0")
                        timer.setText("")

                    }
                }
            }

            override fun run() {
                btn.setOnClickListener(this)
                reset.setOnClickListener(this)
            }
        }, 100)


    }

    fun wait1sec() {

        val ct = System.currentTimeMillis()
        while (ct + 100 > System.currentTimeMillis()) {

        }
    }

    fun waitNsec(n: Int) {

        for (i in 0..n) {
            wait1sec()
        }
    }

    fun logic(res: Int) {

        //var number: Int? = res
        for (i in res downTo 0) {
            waitNsec(1)
            if (!i.toString().equals("0")) {
                var seconds = i
                var minutes = if (seconds != null) {
                    seconds / 60
                } else {
                    0
                }
                var countseconds = if (seconds != null) {
                    seconds % 60
                } else {
                    0
                }

                var str: String
                if (minutes >= 0 && minutes <= 9) {
                    if (countseconds >= 0 && countseconds <= 9) {
                        str =
                            "0" + minutes.toString() + " : " + "0" + countseconds.toString()
                    } else {
                        str =
                            "0" + minutes.toString() + " : " + countseconds.toString()
                    }
                } else {
                    if (countseconds >= 0 && countseconds <= 9) {
                        str =
                            minutes.toString() + " : " + "0" + countseconds.toString()
                    } else {
                        str = minutes.toString() + " : " + countseconds.toString()
                    }
                }
                timer.setText(str)

            } else {
                timer.setText("Time up" + "i" + i)
            }
        }

    }
}
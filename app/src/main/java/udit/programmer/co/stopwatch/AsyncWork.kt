package udit.programmer.co.stopwatch

import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class AsyncWork : AppCompatActivity(), View.OnClickListener {
    override fun onClick(view: View) {

        //waitNsec(10)
        //ll.setBackgroundColor(Color.BLUE)
        val ctask = CountTask()

        when (view.id) {

            R.id.btn -> {

                val minu = min.text.toString().toInt()
                val seco = sec.text.toString().toInt()

                val res = minu * 60 + seco

                ctask.execute(res)

            }

            R.id.reset -> {

                min.setText("0")
                sec.setText("0")
                timer.setText("")
                val handler = Handler().postDelayed({
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }, 5000)

                handler

                //Toast.makeText(this,"Stopp",T)

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn.setOnClickListener(this)
        reset.setOnClickListener(this)

    }

    fun wait1sec() {

        val ct = System.currentTimeMillis()

        while (ct + 500 > System.currentTimeMillis()) {

        }
    }

    fun waitNsec(n: Int) {

        for (i in 0..n) {
            wait1sec()
        }
    }

    inner class CountTask : AsyncTask<Int, Int, Void>() {


        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            if (values[0].toString() != "0") {
                var seconds = values[0]
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
                var str = minutes.toString() + " : " + countseconds.toString()

                if (minutes >= 0 && minutes <= 9) {
                    if (countseconds >= 0 && countseconds <= 9) {
                        str = "0" + minutes.toString() + " : " + "0" + countseconds.toString()
                    } else {
                        str = "0" + minutes.toString() + " : " + countseconds.toString()
                    }
                } else {
                    if (countseconds >= 0 && countseconds <= 9) {
                        str = minutes.toString() + " : " + "0" + countseconds.toString()
                    } else {
                        str = minutes.toString() + " : " + countseconds.toString()
                    }
                }
                timer.setText(str)

            } else {
                timer.setText("Time up")
            }
        }

        override fun doInBackground(vararg number: Int?): Void? {

            var number: Int? = number[0]
            for (i in number!! downTo 0) {
                waitNsec(1)
                publishProgress(i)

            }
            return null
        }


    }
}
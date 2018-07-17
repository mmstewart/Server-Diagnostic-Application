package com.example.administrator.serverapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import okhttp3.*
import java.io.IOException

open class ServerReportScreen : Fragment() {
//Use OkHttp or Retrofit to access the network server, comes out as a JSON text. Test using a button first
//then have a line where the user can enter their ip address and submit using the button.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.activity_server_report_screen, container,false)

        val button : Button = view.findViewById(R.id.button1)
        val button2 : Button = view.findViewById(R.id.button3)
        val textResult : TextView = view.findViewById(R.id.textView2)
        val ipText : EditText = view.findViewById(R.id.editText)
        val authenticationText : EditText = view.findViewById(R.id.editText2)
        val rL : RelativeLayout = view.findViewById(R.id.relative_layout)

        (activity as AppCompatActivity).supportActionBar!!.title = "Server Diagnostic Report"

        //Submit Button
        button.setOnClickListener {
            webService()
        }

        //Hides keyboard on touch outside of EditText
        rL.setOnClickListener {
            hideKeyboard()
        }

        //Clear Button
        button2.setOnClickListener {
            textResult.text = "Results"
            ipText.hint = "IP Address"
            ipText.setText("")
            authenticationText.hint = "Token (Optional)"
            authenticationText.setText("")
        }
        return view
    }


    //Method that hides the keyboard on touch outside of EditText. Corresponds to the Android Device version.
    private fun hideKeyboard() {
        val view = activity!!.currentFocus
        val imm : InputMethodManager = context!!.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if(android.os.Build.VERSION.SDK_INT >= 26) {
            view.post {
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } else {
            if(view != null) {
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    //Method downloads a URL and prints the JSON file into a string
    private fun webService() {
        val textResult : TextView = view!!.findViewById(R.id.textView2)
        val client = OkHttpClient()
        var textURL = "https://reqres.in/api/users?page=2"

        //hides keyboard on submit
        val i : InputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        i.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, 0)

        val request : Request = Request.Builder()
                .url(textURL)
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                activity!!.runOnUiThread {
                    textResult.text = "Failure"
                }
            }
            override fun onResponse(call: Call, response: Response) {
                activity!!.runOnUiThread {
                    try {
                        textResult.text = response.body()!!.string()
                    } catch (e: IOException) {
                        textResult.text = "Error during get body"
                    }
                }
            }
        })
    }
}
package com.example.administrator.serverapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.URLUtil
import android.widget.*
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import java.net.MalformedURLException
import java.net.SocketTimeoutException
import java.net.URL
import kotlin.concurrent.thread

class ServerReportScreen : Fragment() {
/* Use OkHttp or Retrofit to access the network server, comes out as a JSON text. Test using a button first
then have a line where the user can enter their ip address and submit using the button. */

    //VIEW
    private var button : Button? = null
    private var button2 : Button? = null
    private var urlInput : TextView? = null
    private var textResult : TextView? = null
    private var spin : Spinner? = null

    //OKHTTP
    private var client : OkHttpClient? = null
    private var request : Request? = null
    private var http = OkHttp()

    //JSON RETURN
    private var json : String? = null
    private var jsonResp = JSONArray()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.activity_server_report_screen, container,false)

        val rL : RelativeLayout = view.findViewById(R.id.relative_layout)
        val progressBar : ProgressBar = view.findViewById(R.id.PB)
        val locationList = ArrayList<Location>()

        urlInput = view.findViewById<View>(R.id.editText) as TextView
        spin = view.findViewById<View>(R.id.comboBox) as Spinner
        textResult= view.findViewById(R.id.textView2) as TextView
        button = view.findViewById(R.id.button1) as Button
        button2 = view.findViewById(R.id.button3) as Button

        //Buttons are disabled initially
        button!!.isEnabled = false
        button2!!.isEnabled = false

        //Once a character is typed, buttons are enabled
        urlInput!!.addTextChangedListener(object: TextWatcher {

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().trim() == "") {
                    button!!.isEnabled = false
                    button2!!.isEnabled = false
                } else {
                    button!!.isEnabled = true
                    button2!!.isEnabled = true
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun afterTextChanged(p0: Editable?) { }
        })

        //Sets Home Screen title of action bar to "Server Diagnostic Report"
        (activity as AppCompatActivity).supportActionBar!!.title = "Server Diagnostic Report"

        //Progress Bar/Loading symbol is disabled at default
        progressBar.visibility = View.GONE

        //Submit Button
        button!!.setOnClickListener {
            //Progress Bar is enabled when searching for the URL
            progressBar.visibility = View.VISIBLE

            textResult!!.text = "Waiting for response..."
            thread {
                try {
                    client = http.client
                    request = http.getRequest(urlInput!!.text.toString())
                    json = http.getURL(client, request)

                    //Hides keyboard when the submit button is pressed
                    val imm : InputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, 0)

                    activity!!.runOnUiThread {
                        try {
                            jsonResp = org.json.JSONArray(json)
                            var s = ""
                            locationList.clear()
                            for (i in 0 until jsonResp.length()) {
                                val r = Location()
                                s += "Location: " + jsonResp.getJSONObject(i).getString("locationName") + "\n" +
                                        "Latitude: " + jsonResp.getJSONObject(i).getString("latitude") + "\n" +
                                        "Longitude: " + jsonResp.getJSONObject(i).getString("longitude") + "\n\n"

                                r.setLocationName(jsonResp.getJSONObject(i).getString("locationName"))
                                r.setLatitude(jsonResp.getJSONObject(i).getString("latitude"))
                                r.setLongitude(jsonResp.getJSONObject(i).getString("longitude"))

                                locationList.add(r)
                            }
                            val locationString = ArrayList<String>()
                            for (i in locationList){

                                locationString.add(i.getLocationName().toString())
                            }
                            textResult!!.text = s

                            spin!!.adapter = ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, locationString)

                            spin!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                                override fun onNothingSelected (parent: AdapterView<*>?) {
                                    textResult!!.text = "Select an option"
                                }
                                override fun onItemSelected (parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                                    //
                                }
                            }
                        } catch (e : JSONException) {
                            textResult!!.text = json
                            //Progress Bar is disabled once the URL is loaded
                            progressBar.visibility = View.GONE
                        } catch (e : SocketTimeoutException) {
                            textResult!!.text = e.message
                        }
                    }
                } catch (e : IllegalArgumentException) {
                    textResult!!.text = e.message
                }
            }
        }

        //Hides keyboard on touch outside of EditText
        rL.setOnTouchListener { view, motionEvent ->
            rL.hideKeyboard(view)
            return@setOnTouchListener false
        }

        //Clear Button
        button2!!.setOnClickListener {
            textResult!!.text = "Results Appear Here"
            urlInput!!.text = ""
        }
        return view
    }

    //Method that hides the keyboard on touch outside of EditText.
    private fun View.hideKeyboard(view : View) {
        val imm : InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

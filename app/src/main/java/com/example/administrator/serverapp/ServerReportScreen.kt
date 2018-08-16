package com.example.administrator.serverapp

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import org.json.JSONArray
import org.json.JSONException
import java.net.*
import java.util.*
import kotlin.concurrent.thread

class ServerReportScreen : Fragment() {
/* Use OkHttp or Retrofit to access the network server, comes out as a JSON text. Test using a button first
then have a line where the user can enter their ip address and submit using the button. */

    //VIEW
    private var button: Button? = null
    private var button2: Button? = null
    private var urlInput: EditText? = null
    private var user: EditText? = null
    private var pass: EditText? = null
    private var token: EditText? = null
    private var textResult: TextView? = null
    private var spin: Spinner? = null

    //OKHTTP
    private var http = OkHttp()

    //JSON RETURN
    private var json: String? = null
    private var jsonResp = JSONArray()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.activity_server_report_screen, container, false)

        urlInput = view.findViewById<View>(R.id.editText) as EditText
        token = view.findViewById(R.id.editText2) as EditText
        user = view.findViewById(R.id.editText3) as EditText
        pass = view.findViewById(R.id.editText4) as EditText
        spin = view.findViewById<View>(R.id.comboBox) as Spinner
        textResult = view.findViewById(R.id.textView2) as TextView
        button = view.findViewById(R.id.button1) as Button
        button2 = view.findViewById(R.id.button3) as Button

        val rL: RelativeLayout = view.findViewById(R.id.relative_layout)
        val progressBar: ProgressBar = view.findViewById(R.id.PB)
        val locationList = ArrayList<Location>()

        //Sets Home Screen title of action bar to "Server Diagnostic Report"
        (activity as AppCompatActivity).supportActionBar!!.title = "Server Diagnostic Report"

        //Progress Bar/Loading symbol is disabled at default
        progressBar.visibility = View.INVISIBLE

        //Submit Button
        button!!.setOnClickListener {

            //Progress Bar is disabled until correct URL
            progressBar.visibility = View.INVISIBLE

            textResult!!.text = "Waiting for GET Request..."

            if (urlInput!!.text.toString().isEmpty()) {

                //Sets the Error symbol
                textResult!!.error = ""

                /* When Submit button is pressed with no text in URL
                text result states "Enter a URL" */
                textResult!!.text = "Enter a URL  "

            } else if (!Patterns.WEB_URL.matcher(urlInput!!.text.toString().toLowerCase(Locale.US)).matches()) {
                textResult!!.error = ""
                textResult!!.text = "Invalid URL, try again  "
            } else if (urlInput!!.text.toString().startsWith("https://")) {
                textResult!!.error = ""
                textResult!!.text = "Invalid URL, remove https:// and try again  "
            } else if (urlInput!!.text.toString().startsWith("http://")) {
                textResult!!.error = ""
                textResult!!.text = "Invalid URL, remove http:// and try again  "
            } else if (urlInput!!.text.toString().startsWith("w.")) {
                textResult!!.error = ""
                textResult!!.text = "Invalid URL, remove w. and try again  "
            } else if (urlInput!!.text.toString().startsWith("ww.")) {
                textResult!!.error = ""
                textResult!!.text = "Invalid URL, remove ww. and try again  "
            } else {
                thread {
                    kotlin.run {
                        try {
                            json = http.getURL(urlInput!!.text.toString(), user!!.text.toString(), pass!!.text.toString(), token!!.text.toString())

                            //Hides keyboard when the submit button is pressed
                            val imm: InputMethodManager = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                            imm.hideSoftInputFromWindow(activity!!.currentFocus.windowToken, 0)

                            activity!!.runOnUiThread {
                                try {
                                    jsonResp = JSONArray(json)
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
                                    textResult!!.text = s
                                } catch (e: JSONException) {
                                    textResult!!.text = json

                                    //Progress Bar is disabled once the URL is loaded
                                    progressBar.visibility = View.INVISIBLE
                                } catch (e: SocketTimeoutException) {
                                    textResult!!.text = e.message
                                }
                            }
                        } catch (e: IllegalArgumentException) {
                            textResult!!.text = e.message
                        }
                    }
                }
                progressBar.visibility = View.VISIBLE
                textResult!!.error = null
            }
        }

        //Hides keyboard on touch outside of EditText
        rL.setOnTouchListener { view, motionEvent ->
            rL.hideKeyboard(view)
            return@setOnTouchListener false
        }

        //Clear Button
        button2!!.setOnClickListener {
            textResult!!.text = "JSON From Server"
            textResult!!.error = null
            urlInput!!.text.clear()
            user!!.text.clear()
            pass!!.text.clear()
            token!!.text.clear()
            progressBar.visibility = View.INVISIBLE
        }
        return view
    }

    //Method that hides the keyboard on touch outside of EditText.
    private fun View.hideKeyboard(view: View) {
        val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

}

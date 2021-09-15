package com.example.animequotes

import android.net.Uri
import android.net.sip.SipSession
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.animequotes.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_anime.*
import kotlinx.android.synthetic.main.fragment_character_name.*
import kotlinx.android.synthetic.main.fragment_quote.*
import org.json.JSONArray
import org.json.JSONObject

import java.util.*
import java.lang.*
import java.net.URL


class MainActivity : AppCompatActivity() {

    var quote: String? = null
    var response : String? = null

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frag3.quoteContent.isVisible = false
        task().execute()
    }

    fun onClick(view: View) {
            task().execute()
        frag3.quoteContent.isVisible = true
            var result : String? = response
            try {
                var jsonObj = JSONObject(result)
                var anime = jsonObj.getString("anime")
                var character = jsonObj.getString("character")
                var quote = jsonObj.getString("quote")
                frag1.animeName.text = anime
                frag2.characterName.text = character
                frag3.quoteContent.text = quote
                //textView.setText(anime + "\n" + character + "\n" + quote)
            } catch (e: Exception) {
                frag1.animeName.text = "Connection Lost"
                frag2.characterName.text = "Internet"
                frag3.quoteContent.text = "Could not load quotes. Please check your network connection."
            }
        }

    inner class task() : AsyncTask<String, Void, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response = URL("https://animechan.vercel.app/api/random").readText(
                        Charsets.UTF_8
                )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            response = result
//            try{
//                var jsonObj = JSONObject(result)
//                var quote = jsonObj.getString("quote")
//                textView.setText(quote)
//            } catch (e : Exception) {
//                textView.setText("Didn't work " + e.message)
//            }
        }
//       https://animechan.vercel.app/api/random
    }
}


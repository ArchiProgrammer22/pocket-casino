package com.example.casinoapp.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.casinoapp.Utils
import com.example.casinoapp.databinding.FragmentWebViewBinding
import com.example.casinoapp.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WebViewFragment : Fragment() {

    private var binding: FragmentWebViewBinding? = null

    private var uploadMessages: ValueCallback<Array<Uri>>? = null
    private var path: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        changeBehaviourBackButton()
        binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initWebView()
        updateFirebaseDb()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && path != null) {
            uploadMessages?.onReceiveValue(arrayOf(Uri.parse(path)))
        } else {
            val dataString = data?.dataString
            if (dataString != null) {
                uploadMessages!!.onReceiveValue(arrayOf(Uri.parse(dataString)))
            }
        }
        uploadMessages = null
    }

    private fun changeBehaviourBackButton() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Toast.makeText(context, "You sure?", Toast.LENGTH_SHORT).show()
            }
        }
        activity?.onBackPressedDispatcher
            ?.addCallback(viewLifecycleOwner, callback)
    }

    private fun updateFirebaseDb() {
        (activity as MainActivity).loadingViewModel
            .setLink(binding?.webView?.url ?: Utils.DEFAULT_LINK)
    }

    private fun initWebView() {
        val link = (activity as MainActivity).loadingViewModel.getLink()
        binding?.webView?.apply {
            loadUrl(link)
            settings.javaScriptEnabled = true
            settings.allowContentAccess = true
            settings.userAgentString = Utils.BROWSER_CONF

            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    webView: WebView?,
                    filePathCallback: ValueCallback<Array<Uri>>?,
                    fileChooserParams: FileChooserParams?
                ): Boolean {
                    if (uploadMessages != null) {
                        uploadMessages?.onReceiveValue(null)
                        uploadMessages = null
                    }

                    uploadMessages = filePathCallback

                    val intent: Intent? = fileChooserParams!!.createIntent()
                    startActivityForResult(intent, Utils.SELECT_FILE)
                    return true
                }
            }
        }
    }
}

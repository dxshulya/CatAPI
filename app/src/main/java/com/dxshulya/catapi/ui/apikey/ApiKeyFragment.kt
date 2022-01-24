package com.dxshulya.catapi.ui.apikey

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.dxshulya.catapi.R
import com.dxshulya.catapi.validators.ApiKeyValidator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class ApiKeyFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var apikey: TextInputEditText

    private val apiKeyViewModel: ApiKeyViewModel by viewModels()

    private fun showErrorWindow(message: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.error_window_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.error_window_button)) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun checkErrorKey() {
        apiKeyViewModel.eApiKeyData.observe(viewLifecycleOwner) {
            if (apiKeyViewModel.checkKeyStatus()) {
                apiKeyViewModel.updateApiKey("")
                showErrorWindow(it.message)
            }
        }
        Log.e("CHECK_ERROR_KEY", "" + apiKeyViewModel.checkKeyStatus().toString())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton = view.findViewById(R.id.next_button_apikey)
        nextButton.isEnabled = false

        apikey = view.findViewById(R.id.apikey)

        val textWatcher = ApiKeyValidator(apikey, nextButton)
        apikey.addTextChangedListener(textWatcher)

        nextButton.setOnClickListener {
            val action = ApiKeyFragmentDirections.actionApiKeyFragmentToCatFragment()
            checkErrorKey()
            apiKeyViewModel.apikeyData.observe(viewLifecycleOwner) {
                Navigation.findNavController(requireView()).navigate(action)
            }
            apiKeyViewModel.apply {
                updateApiKey(apikey.text.toString())
                getApiKeyRequest()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_api_key, container, false)
    }
}
package com.dxshulya.catapi.ui.apikey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.dxshulya.catapi.ApiKeyValidator
import com.dxshulya.catapi.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class ApiKeyFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var apikey: TextInputEditText

    private val viewModel: ApiKeyViewModel by viewModels()

    private fun showErrorWindow(message: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("Ошибка")
                .setMessage(message)
                .setPositiveButton("Понятно") { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextButton = view.findViewById(R.id.next_button_api_key)
        nextButton.isEnabled = false

        apikey = view.findViewById(R.id.api_key)

        val textWatcher = ApiKeyValidator(apikey, nextButton)
        apikey.addTextChangedListener(textWatcher)

        nextButton.setOnClickListener {
            val action = ApiKeyFragmentDirections.actionApiKeyFragmentToCatFragment()
            viewModel.getApiKey(apikey.text.toString())
            viewModel.apiKey.observe(viewLifecycleOwner) {
                if (it.status == 401) showErrorWindow(it.message)
                else Navigation.findNavController(view).navigate(action)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_api_key, container, false)
        return view
    }
}
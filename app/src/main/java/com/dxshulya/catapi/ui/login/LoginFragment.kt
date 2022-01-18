package com.dxshulya.catapi.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.dxshulya.catapi.R
import com.dxshulya.catapi.SharedPreference
import com.dxshulya.catapi.validators.LoginValidator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var email: TextInputEditText
    private lateinit var description: TextInputEditText

    private val loginViewModel: LoginViewModel by viewModels()

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

        nextButton = view.findViewById(R.id.next_button_email)
        nextButton.isEnabled = false

        email = view.findViewById(R.id.email)

        description = view.findViewById(R.id.description)

        val edList = arrayOf(email, description)
        val textWatcher = LoginValidator(edList, nextButton)
        for (editText in edList) editText.addTextChangedListener(textWatcher)

        nextButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToApiKeyFragment()
            val actionNow = LoginFragmentDirections.actionLoginFragmentToCatFragment()

            val sharedPreference = SharedPreference(requireContext())

            if (email.text.toString() == sharedPreference.email && sharedPreference.apikey != "") {
                Navigation.findNavController(view).navigate(actionNow)
            } else {
                loginViewModel.loginData.observe(viewLifecycleOwner) {
                    if (it.status == 400) {
                        showErrorWindow(it.message)
                        loginViewModel.updateEmail("")
                        loginViewModel.updateDescription("")
                    } else {
                        Navigation.findNavController(view).navigate(action)
                    }
                }
                loginViewModel.updateEmail(email.text.toString())
                loginViewModel.updateDescription(description.text.toString())
                loginViewModel.postLoginInRequest()
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        return view
    }
}

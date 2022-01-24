package com.dxshulya.catapi.ui.login

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
import com.dxshulya.catapi.validators.LoginValidator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlin.math.log

class LoginFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var email: TextInputEditText
    private lateinit var description: TextInputEditText

    private val loginViewModel: LoginViewModel by viewModels()

    private fun showErrorWindow(message: String) {
        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(getString(R.string.error_window_title))
                .setMessage(message)
                .setPositiveButton(getString(R.string.error_window_button)) { dialog, _ -> dialog.dismiss() }
                .show()
        }
    }

    private fun checkErrorLogin() {
        val action = LoginFragmentDirections.actionLoginFragmentToApiKeyFragment()
        loginViewModel.loginData.observe(viewLifecycleOwner) {
            if (loginViewModel.checkLoginStatus()) {
                showErrorWindow(it.message)
                loginViewModel.apply {
                    updateEmail("")
                    updateDescription("")
                }
            } else {
                Navigation.findNavController(requireView()).navigate(action)
            }
            Log.e("CHECK_ERROR_LOGIN","" + loginViewModel.checkLoginStatus().toString())
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (loginViewModel.sharedPreference.email != "") {
            val actionNow = LoginFragmentDirections.actionLoginFragmentToCatFragment()
            Navigation.findNavController(view).navigate(actionNow)
        }

        nextButton = view.findViewById(R.id.next_button_email)
        nextButton.isEnabled = false

        email = view.findViewById(R.id.email)
        description = view.findViewById(R.id.description)

        val edList = arrayOf(email, description)
        val textWatcher = LoginValidator(edList, nextButton)
        for (editText in edList) editText.addTextChangedListener(textWatcher)

        nextButton.setOnClickListener {
            checkErrorLogin()
            loginViewModel.apply {
                updateEmail(email.text.toString())
                updateDescription(description.text.toString())
                postLoginInRequest()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
}

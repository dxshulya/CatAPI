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
import com.dxshulya.catapi.LoginValidator
import com.dxshulya.catapi.R
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
        val textWatcher = LoginValidator(edList = edList, nextButton)
        for (editText in edList) editText.addTextChangedListener(textWatcher)

        nextButton.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToApiKeyFragment()
            loginViewModel.getEmail(email.text.toString())
            loginViewModel.getDescription(description.text.toString())
            loginViewModel.loginIn.observe(viewLifecycleOwner) {
                Log.e("Error", "" + it.status)
                if (it.status == 400) showErrorWindow(it.message)
                else Navigation.findNavController(view).navigate(action)
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
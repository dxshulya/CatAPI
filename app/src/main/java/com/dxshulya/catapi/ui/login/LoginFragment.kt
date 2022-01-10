package com.dxshulya.catapi.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.dxshulya.catapi.LoginValid
import com.dxshulya.catapi.R
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment() {

    private lateinit var nextButton: Button
    private lateinit var email: TextInputEditText
    private lateinit var description: TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        nextButton = view!!.findViewById(R.id.next_button_email)
        nextButton.isEnabled = false
        email = view.findViewById(R.id.email)
        description = view.findViewById(R.id.description)
        val edList = arrayOf(email, description)
        val textWatcher = LoginValid(edList = edList, nextButton)
        for (editText in edList) editText.addTextChangedListener(textWatcher)

        nextButton.setOnClickListener{
            val action = LoginFragmentDirections.actionLoginFragmentToApiKeyFragment()
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }

}
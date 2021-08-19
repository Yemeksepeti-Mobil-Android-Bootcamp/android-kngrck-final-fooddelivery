package com.kngrck.fooddeliveryfinal.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kngrck.fooddeliveryfinal.databinding.FragmentLoginBinding
import com.kngrck.fooddeliveryfinal.ui.MainActivity
import com.kngrck.fooddeliveryfinal.utils.AuthListener
import com.kngrck.fooddeliveryfinal.utils.FirebaseAuthManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BottomSheetDialogFragment() {
    private lateinit var _binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        _binding.loginButton.setOnClickListener {
            val email = _binding.emailTextInput.editText?.text.toString()
            val password = _binding.passwordTextInput.editText?.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                 _binding.emailTextInput.error = ""
                 _binding.passwordTextInput.error = ""
                FirebaseAuthManager.signIn(email, password, object : AuthListener {
                    override fun isAuthSuccess(success: Boolean) {
                        Log.d("LOGIN","success $success")
                        if (success) {
                            FirebaseAuthManager.getCurrentUser()?.getIdToken(true)
                                ?.addOnCompleteListener { result ->
                                    if (result.isSuccessful) {
                                        val accessToken = result.result?.token
                                        accessToken?.let {
                                            viewModel.saveToken(it)
                                            val intent = Intent(context, MainActivity::class.java)
                                            startActivity(intent)
                                            requireActivity().finish()
                                        }
                                    } else {
                                        Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                                    }
                                }

                        } else {
                            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            } else {

                if (email.isEmpty()) _binding.emailTextInput.error = "Do not leave blank."
                if (password.isEmpty()) _binding.passwordTextInput.error = "Do not leave blank."
            }
        }
    }


}
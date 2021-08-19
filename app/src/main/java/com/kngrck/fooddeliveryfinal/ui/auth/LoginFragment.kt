package com.kngrck.fooddeliveryfinal.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

//    _binding.loginButton.setOnClickListener {
//        FirebaseAuthManager.signIn(object : AuthListener {
//            override fun isAuthSuccess(success: Boolean) {
//                if (success) {
//                    FirebaseAuthManager.getCurrentUser()?.getIdToken(true)
//                        ?.addOnCompleteListener {
//                            if (it.isSuccessful) {
//                                val accessToken=  it.result?.token
//                                accessToken?.let {
//                                    viewModel.saveToken(it)
//                                    val intent = Intent(context, MainActivity::class.java)
//                                    startActivity(intent)
//                                    requireActivity().finish()
//                                }
//                            } else {
//                                Log.d("AUTH", "Token failed")
//                            }
//                        }
//
//                } else {
//                    Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//        })
//    }




}
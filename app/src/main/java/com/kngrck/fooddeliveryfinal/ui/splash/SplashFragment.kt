package com.kngrck.fooddeliveryfinal.ui.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kngrck.fooddeliveryfinal.R
import com.kngrck.fooddeliveryfinal.databinding.FragmentSplashBinding
import com.kngrck.fooddeliveryfinal.ui.MainActivity
import com.kngrck.fooddeliveryfinal.utils.FirebaseAuthManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var _binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseAuthManager.initialize(requireContext())


        _binding.splashAnimation.addAnimatorListener(object: Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                checkUserAndLogin()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })



    }

    private fun checkUserAndLogin(){
        FirebaseAuthManager.getCurrentUser()?.let {
            it.getIdToken(true).addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    val accessToken = result.result?.token
                    accessToken?.let {
                        viewModel.saveToken(accessToken)
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                    }

                } else {
                    findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
                }

            }.addOnFailureListener {
                findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
            }
        } ?: run {
            findNavController().navigate(R.id.action_splashFragment_to_onBoardingFragment)
        }
    }


}
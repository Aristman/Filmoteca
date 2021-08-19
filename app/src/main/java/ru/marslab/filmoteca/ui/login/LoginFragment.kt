package ru.marslab.filmoteca.ui.login

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentLoginBinding
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.ui.util.showMessage
import ru.marslab.filmoteca.ui.util.viewHide
import ru.marslab.filmoteca.ui.util.viewShow

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!

    private val loginBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            loginHandler(intent?.extras)
        }

    }

    private fun loginHandler(extras: Bundle?) {
        extras?.let {
            when {
                it.getBoolean(GUEST_LOGIN_SUCCESSFUL) ||
                        it.getBoolean(USER_LOGIN_SUCCESSFUL) -> {
                    val action =
                        LoginFragmentDirections.actionLoginFragmentToWelcomeScreenFragment()
                    findNavController().navigate(action)
                }
                it.getBoolean(LOGIN_ERROR) -> {
                    requireView().showMessage(getString(R.string.login_error))
                    showMainView()
                }
                it.getBoolean(TOKEN_ERROR) -> {
                    requireView().showMessage(getString(R.string.token_error))
                    showMainView()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LocalBroadcastManager
            .getInstance(requireContext())
            .registerReceiver(loginBroadcastReceiver, IntentFilter(LOGIN_INTENT_FILTER))
        initListeners()
        initView()
    }

    private fun initView() {
        binding.apply {
            loadingIndicator.viewHide()
            mainView.viewShow()
        }
    }

    private fun initListeners() {
        binding.run {
            guestBtn.setOnClickListener {
                showLoading()
                startLoginService()
            }
            loginBtn.setOnClickListener {
                showLoading()
                startLoginService(
                    User(
                        binding.login.text.toString(),
                        binding.password.text.toString()
                    )
                )
            }
        }

    }

    private fun startLoginService(user: User? = null) {
        requireContext().let {
            val serviceIntent = Intent(it, LoginService::class.java)
            user?.let {
                serviceIntent.putExtra(USER_EXTRA, user)
            }
            it.startService(serviceIntent)
        }
    }

    private fun showLoading() {
        binding.apply {
            loadingIndicator.viewShow()
            mainView.viewHide()
        }
    }

    private fun showMainView() {
        binding.apply {
            mainView.viewShow()
            loadingIndicator.viewHide()
        }
    }

    override fun onDestroyView() {
        _binding = null
        LocalBroadcastManager.getInstance(requireContext())
            .unregisterReceiver(loginBroadcastReceiver)
        super.onDestroyView()
    }
}
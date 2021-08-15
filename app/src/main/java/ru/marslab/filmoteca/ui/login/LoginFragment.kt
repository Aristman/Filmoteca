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
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentLoginBinding
import ru.marslab.filmoteca.domain.model.User
import ru.marslab.filmoteca.ui.util.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()

    private val loginBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.extras?.let {
                when {
                    it.getBoolean(GUEST_LOGIN_SUCCESSFUL) -> {
                        val action =
                            LoginFragmentDirections.actionLoginFragmentToGuestFragment()
                        findNavController().navigate(action)
                    }
                    it.getBoolean(USER_LOGIN_SUCCESSFUL) -> {
                        TODO("Запуск сеанса зарегестрированного пользователя")
                    }
                    it.getBoolean(LOGIN_ERROR) -> {
                        this@LoginFragment.requireView()
                            .showMessage(getString(R.string.login_error))
                        showMainView()
                    }
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
        context?.let {
            LocalBroadcastManager.getInstance(it).registerReceiver(
                loginBroadcastReceiver, IntentFilter(
                    LOGIN_INTENT_FILTER
                )
            )
        }
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
        binding.apply {
            guestBtn.setOnClickListener {
                showLoading()
                context?.let {
                    it.startService(Intent(it, LoginService::class.java))
                }
            }
            loginBtn.setOnClickListener {
                loginViewModel.userLogin(
                    User(
                        binding.login.text.toString(),
                        binding.password.text.toString()
                    )
                )
            }
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
        context?.let {
            LocalBroadcastManager.getInstance(it).unregisterReceiver(loginBroadcastReceiver)
        }
        super.onDestroyView()
    }
}
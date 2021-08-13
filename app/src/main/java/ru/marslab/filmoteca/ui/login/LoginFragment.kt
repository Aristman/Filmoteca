package ru.marslab.filmoteca.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentLoginBinding
import ru.marslab.filmoteca.ui.util.*

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
        initView()
    }

    private fun initView() {
        binding.apply {
            loadingIndicator.viewHide()
            mainView.viewShow()
        }
        if (loginViewModel.isNotConnected()) {
            loginViewModel.guestSessionConnect()
        }
    }

    private fun initObservers() {
        loginViewModel.guestSession.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ViewState.LoadError -> {
                    requireView().showMessageWithAction(
                        viewState.message,
                        getString(R.string.repeat)
                    ) {
                        loginViewModel.guestSessionConnect()
                    }
                }
                is ViewState.Loading -> {
                    showLoading()
                }
                is ViewState.Successful<*> -> {
                    val isSessionConnected = viewState.data as? OnEvent<Boolean>
                    isSessionConnected?.getContentIfNotHandled()?.let {
                        showMainView()
                    }
                }
            }
        }
    }

    private fun initListeners() {
        binding.guestBtn.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToGuestFragment()
            findNavController().navigate(action)
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
        super.onDestroyView()
    }
}
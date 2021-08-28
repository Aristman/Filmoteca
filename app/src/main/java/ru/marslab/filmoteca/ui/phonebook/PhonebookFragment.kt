package ru.marslab.filmoteca.ui.phonebook

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.marslab.filmoteca.R
import ru.marslab.filmoteca.databinding.FragmentPhonebookBinding
import ru.marslab.filmoteca.ui.phonebook.adapter.PhonebookAdapter
import ru.marslab.filmoteca.ui.phonebook.adapter.PhonebookItem
import ru.marslab.filmoteca.ui.util.PermissionAccessLevel
import ru.marslab.filmoteca.ui.util.RequestPermission
import ru.marslab.filmoteca.ui.util.showMessage


class PhonebookFragment : Fragment() {

    private var _binding: FragmentPhonebookBinding? = null
    private val binding: FragmentPhonebookBinding
        get() = checkNotNull(_binding) { getString(R.string.binding_not_init) }
    private val phonebookAdapter: PhonebookAdapter by lazy {
        PhonebookAdapter { phoneItem ->
            callingToNumber(phoneItem.number)
        }
    }
    private val contactsRequestPermission: RequestPermission by lazy {
        RequestPermission(
            this,
            R.string.permission_contacts_dialog_title,
            R.string.permission_contacts_dialog_message
        )
    }

    private fun callingToNumber(number: String) {
        val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
        startActivity(callIntent)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhonebookBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initRv()
        contactsRequestPermission.checkPermission(Manifest.permission.READ_CONTACTS)
    }

    private fun initObservers() {
        contactsRequestPermission.permission.observeForever { permissionStatus ->
            when (permissionStatus) {
                PermissionAccessLevel.Granted -> {
                    getContacts()
                }
                PermissionAccessLevel.Denied -> {
                    requireView().showMessage(R.string.permission_contacts_dialog_message)
                }
                PermissionAccessLevel.Error -> {
                }
            }
        }
    }

    private fun initRv() {
        binding.rvPhonebook.run {
            adapter = phonebookAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun getContacts() {
        val contentResolver = requireContext().contentResolver
        val cursorNames = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME
        )
        val phonebookList: MutableList<PhonebookItem> = mutableListOf()
        cursorNames?.let { cursor ->
            cursor.moveToFirst()
            do {
                val hasNumber = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                )
                if (hasNumber == "1") {
                    val contactId = cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID)
                    )
                    val cursorNumbers = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
                        null,
                        null
                    )
                    cursorNumbers?.let { curNumbers ->
                        curNumbers.moveToFirst()
                        phonebookList.add(
                            PhonebookItem(
                                cursor.getString(
                                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                                ),
                                curNumbers.getString(
                                    curNumbers.getColumnIndex((ContactsContract.CommonDataKinds.Phone.NUMBER))
                                )
                            )
                        )
                        curNumbers.close()
                    }
                }
            } while (cursor.moveToNext())
            phonebookAdapter.submitList(phonebookList)
            cursor.close()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
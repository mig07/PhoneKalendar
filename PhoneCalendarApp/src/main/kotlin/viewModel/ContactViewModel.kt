package viewModel

import model.ContactDetailModel
import model.ContactTableModel
import tornadofx.ViewModel
import tornadofx.asObservable

class ContactViewModel : ViewModel() {
    val contactsTableLiveData = getTableContacts().asObservable()
    var selectedTableContactModel: String = ""

    fun createContact(contactModel: ContactDetailModel) {
        TODO()
    }


    // TODO catch exception
    fun readSelectedContact(): ContactDetailModel {
        TODO()
    }

    fun updateSelectedContact(contactModel: ContactDetailModel) {
        TODO()
    }

    fun removeSelectedContact() {
        TODO()
    }

    // TODO
    private fun getTableContacts(): List<ContactTableModel> {
        return emptyList()
    }
}
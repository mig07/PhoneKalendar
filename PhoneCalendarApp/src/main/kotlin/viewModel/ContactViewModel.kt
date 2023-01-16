package viewModel

import contactsDao
import entity.DetailedContact
import entity.TableContact
import javafx.collections.ObservableList
import mappers.ContactMappers
import tornadofx.ViewModel
import tornadofx.observableListOf

class ContactViewModel : ViewModel() {
    val contactMappers = ContactMappers()
    var contactsTableLiveData: ObservableList<TableContact> = observableListOf()
    var selectedTableContactMainNumber: String = ""

    fun createContact(detailedContact: DetailedContact) {
        contactsDao.createContact(contactMappers.mapDetailedContactToContact(detailedContact))
    }

    // TODO catch exception
    fun readSelectedContact(): DetailedContact {
        return contactMappers.mapContactToDetailedContact(
            contactsDao.readContact(selectedTableContactMainNumber) ?: throw Exception("Contact not found")
        )
    }

    fun updateSelectedContact(detailedContact: DetailedContact) {
        TODO()
    }

    fun removeSelectedContact() {
        TODO()
    }

    // TODO
    fun setTableContacts() {
        val tableContacts = contactMappers.mapListContactToListTableContact(contactsDao.readContacts())
        contactsTableLiveData.setAll(tableContacts)
    }
}
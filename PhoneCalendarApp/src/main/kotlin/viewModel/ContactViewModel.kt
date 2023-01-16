package viewModel

import contactsDao
import entity.DetailedContact
import entity.TableContact
import exception.ContactNotFoundException
import javafx.collections.ObservableList
import mappers.ContactMappers
import tornadofx.ViewModel
import tornadofx.observableListOf

class ContactViewModel : ViewModel() {
    private val contactMappers = ContactMappers()
    var contactsTableLiveData: ObservableList<TableContact> = observableListOf()
    var selectedDetailedContact: DetailedContact? = null

    fun createContact(detailedContact: DetailedContact): Boolean {
        if (contactsTableLiveData.find { it.mainPhone == detailedContact.numbers.mainNumber } != null) {
            return false
        }
        contactsDao.createContact(contactMappers.mapDetailedContactToContact(detailedContact))
        return true
    }

    fun setSelectedContact(tableContact: TableContact?) {
        selectedDetailedContact = tableContact?.mainPhone?.let { readSelectedContact(it) }
    }

    private fun readSelectedContact(selectedContactMainPhone: String): DetailedContact {
        return contactMappers.mapContactToDetailedContact(
            contactsDao.readContact(selectedContactMainPhone)
                ?: throw ContactNotFoundException("The selected contact with number $selectedContactMainPhone does not exist.")
        )
    }

    fun updateSelectedContact(detailedContact: DetailedContact) {
        contactsDao.updateContact(
            mainPhoneNumber = selectedDetailedContact!!.numbers.mainNumber,
            contact = contactMappers.mapDetailedContactToContact(detailedContact)
        )
        setTableContactsLiveData()
    }

    fun removeSelectedContact() {
        // Remove from config file
        contactsDao.removeContact(selectedDetailedContact!!.numbers.mainNumber)
        // Update live data
        setTableContactsLiveData()
    }

    fun setTableContactsLiveData() {
        val tableContacts = contactMappers.mapListContactToListTableContact(contactsDao.readContacts())
        contactsTableLiveData.setAll(tableContacts)
    }
}


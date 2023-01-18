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

    fun createContact(detailedContact: DetailedContact) {
        // If the contact already exists, return for now
        if (contactsTableLiveData.find { it.mainPhone == detailedContact.numbers.mainNumber } != null) {
            return
        }
        // Data source contact creation
        contactsDao.createContact(contactMappers.mapDetailedContactToContact(detailedContact)) {
            // Update live data
            contactsTableLiveData.add(contactMappers.mapDetailedContactToTableContact(detailedContact))
        }

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
        // Mapping passed DetailedContact to TableContact, to update the livedata
        val updatedTableContact = contactMappers.mapDetailedContactToTableContact(detailedContact)

        // Get index of the contact to be updated inside the livedata
        val contactToUpdateIndex = contactsTableLiveData.indexOfFirst { tableContact ->
            tableContact.mainPhone == selectedDetailedContact!!.numbers.mainNumber
        }
        // Data source contact update
        contactsDao.updateContact(
            mainPhoneNumber = selectedDetailedContact!!.numbers.mainNumber,
            contact = contactMappers.mapDetailedContactToContact(detailedContact)
        ) {
            // Update live data
            contactsTableLiveData[contactToUpdateIndex] = updatedTableContact
        }

    }

    fun removeSelectedContact() {
        // Find contact to be removed from live data
        val contactToRemove = contactsTableLiveData.find { tableContact ->
            tableContact.mainPhone == selectedDetailedContact!!.numbers.mainNumber
        }
        // Data source contact removal
        contactsDao.removeContact(selectedDetailedContact!!.numbers.mainNumber) {
            // Update live data
            contactsTableLiveData.remove(contactToRemove)
        }

    }

    fun setTableContactsLiveData() {
        val tableContacts = contactMappers.mapListContactToListTableContact(contactsDao.readContacts())
        contactsTableLiveData.setAll(tableContacts)
    }
}


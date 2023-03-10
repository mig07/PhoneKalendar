package dataAccess

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import model.Contact
import model.Contacts
import tornadofx.runAsync
import tornadofx.ui
import java.io.File

const val CONTACTS_DATA_FILE = "./config/data.json"

class ContactsDao {
    // Initialize Jackson object mapper to read json objects
    private val jsonMapper: ObjectMapper = jacksonObjectMapper()

    // File where contacts are stored
    private val contactsFile = File(CONTACTS_DATA_FILE)

    // Read the file where contacts are stored
    private var contactCalendar: Contacts = jsonMapper.readValue(contactsFile)

    fun createContact(contact: Contact, onSuccess: () -> Unit = {}) {
        val mainPhoneNumber = contact.phoneNumbers.mainNumber
        if (this.contactCalendar.contacts.find { it.phoneNumbers.mainNumber === mainPhoneNumber } != null) {
            println("The contact with number $mainPhoneNumber already exists!")
            return
        }
        this.contactCalendar.contacts.add(contact)
        writeToFileAsync(onSuccess)
    }

    fun readContacts(): Iterable<Contact> = this.contactCalendar.contacts

    fun readContact(mainPhoneNumber: String) =
        this.contactCalendar.contacts.find { contact -> contact.phoneNumbers.mainNumber === mainPhoneNumber }

    fun updateContact(mainPhoneNumber: String, contact: Contact, onSuccess: () -> Unit = {}) {
        if (!removeIfExists(mainPhoneNumber)) return
        createContact(contact)
        writeToFileAsync(onSuccess)
    }

    fun removeContact(mainPhoneNumber: String, onSuccess: () -> Unit = {}) {
        if (!removeIfExists(mainPhoneNumber)) return
        writeToFileAsync(onSuccess)
    }

    private fun removeIfExists(mainPhoneNumber: String): Boolean {
        return this.contactCalendar.contacts.removeIf { contact -> contact.phoneNumbers.mainNumber === mainPhoneNumber }
    }

    // Asynchronous method to write into the contacts file (does not block UI thread)
    private fun writeToFileAsync(onSuccess: () -> Unit) {
        val contactCalendarJson = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactCalendar)
        runAsync {
            contactsFile.writeText(contactCalendarJson)
        } ui {
            // TODO - add onError to handle write errors
            onSuccess()
        }
    }
}
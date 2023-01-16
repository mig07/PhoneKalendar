package dataAccess

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import model.Contact
import model.Contacts
import tornadofx.runAsync
import java.io.File

const val CONTACTS_DATA_FILE = "./config/data.json"

class ContactsDao {
    // Initialize Jackson object mapper to read json objects
    private val jsonMapper: ObjectMapper = jacksonObjectMapper()

    // File where contacts are stored
    private val contactsFile = File(CONTACTS_DATA_FILE)

    // Read the file where contacts are stored
    private var contactAgenda: Contacts = jsonMapper.readValue(contactsFile)

    fun createContact(contact: Contact) {
        val mainPhoneNumber = contact.phoneNumbers.mainNumber
        if (this.contactAgenda.contacts.find { it.phoneNumbers.mainNumber === mainPhoneNumber } != null) {
            println("The contact with number $mainPhoneNumber already exists!")
            return
        }
        this.contactAgenda.contacts.add(contact)
        writeToFileAsync()
    }

    fun readContacts(): Iterable<Contact> = this.contactAgenda.contacts

    fun readContact(mainPhoneNumber: String) =
        this.contactAgenda.contacts.find { contact -> contact.phoneNumbers.mainNumber === mainPhoneNumber }

    fun updateContact(mainPhoneNumber: String, contact: Contact) {
        if (!removeIfExists(mainPhoneNumber)) return
        createContact(contact)
    }

    fun removeContact(mainPhoneNumber: String) {
        if (!removeIfExists(mainPhoneNumber)) return
        writeToFileAsync()
    }

    private fun removeIfExists(mainPhoneNumber: String): Boolean {
        return this.contactAgenda.contacts.removeIf { contact -> contact.phoneNumbers.mainNumber === mainPhoneNumber }
    }

    // Asynchronous method to write into the contacts file (does not block UI thread)
    private fun writeToFileAsync() {
        val contactAgendaJson = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactAgenda)
        runAsync {
            contactsFile.writeText(contactAgendaJson)
        }
    }
}
package dataAccess

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import dto.ContactDto
import tornadofx.runAsync
import java.io.File

const val CONTACTS_DATA_FILE = "./config/data.json"

class ContactsDao {
    // Initialize Jackson object mapper to read json objects
    private val jsonMapper: ObjectMapper = jacksonObjectMapper()

    // File where contacts are stored
    private val contactsFile = File(CONTACTS_DATA_FILE)

    // Read the file where contacts are stored
    private var contactAgenda: MutableList<ContactDto> = jsonMapper.readValue(contactsFile)

    fun createContact(contactModel: ContactDto) {
        val mainPhoneNumber = contactModel.phoneNumbers.mainNumber
        if (this.contactAgenda.find { it.phoneNumbers.mainNumber === mainPhoneNumber } != null) {
            println("The contact with number $mainPhoneNumber already exists!")
            return
        }
        this.contactAgenda.add(contactModel)
        writeToFileAsync()
    }

    fun readContacts(): Iterable<ContactDto> = this.contactAgenda

    fun readContact(mainPhoneNumber: String) =
        this.contactAgenda.find { contact -> contact.phoneNumbers.mainNumber === mainPhoneNumber }

    fun updateContact(mainPhoneNumber: String, contactDto: ContactDto) {
        removeIfExists(mainPhoneNumber)
        createContact(contactDto)
    }

    fun removeContact(mainPhoneNumber: String) {
        removeIfExists(mainPhoneNumber)
        writeToFileAsync()
    }

    private fun removeIfExists(mainPhoneNumber: String) {
        if (!this.contactAgenda.removeIf { contact -> contact.phoneNumbers.mainNumber === mainPhoneNumber }) {
            println("Contact with number $mainPhoneNumber does not exist")
            return
        }
    }

    // Asynchronous method to write into the contacts file (does not block UI thread)
    private fun writeToFileAsync() {
        val contactAgendaJson = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactAgenda)
        runAsync {
            contactsFile.writeText(contactAgendaJson)
        }
    }
}
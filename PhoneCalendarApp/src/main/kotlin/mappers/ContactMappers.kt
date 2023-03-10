package mappers

import entity.*
import model.Contact
import model.EmailsSection
import model.IdentificationSection
import model.NumbersSection

class ContactMappers {
    fun mapListContactToListTableContact(contacts: Iterable<Contact>) = contacts.map { contact ->
        mapContactToTableContact(contact)
    }.toList()

    private fun mapContactToTableContact(contact: Contact) =
        TableContact(
            firstName = contact.identificationSection.firstName,
            lastName = contact.identificationSection.lastName,
            mainPhone = contact.phoneNumbers.mainNumber
        )

    fun mapDetailedContactToTableContact(contact: DetailedContact) =
        TableContact(
            firstName = contact.identification.firstName,
            lastName = contact.identification.lastName,
            mainPhone = contact.numbers.mainNumber
        )

    /**
     * For consistency reasons: mapContactToDetailedContact and mapDetailedContactToContact do the same for thing for now,
     * but it may change in the future
     */
    fun mapContactToDetailedContact(contact: Contact) =
        DetailedContact(
            identification = Identification(
                firstName = contact.identificationSection.firstName,
                lastName = contact.identificationSection.lastName,
                middleNames = contact.identificationSection.middleNames,
                nickName = contact.identificationSection.nickName
            ),
            numbers = Numbers(
                mainNumber = contact.phoneNumbers.mainNumber,
                numbers = contact.phoneNumbers.numbers
            ),
            emails = Emails(contact.emails!!.emails)
        )

    fun mapDetailedContactToContact(contact: DetailedContact) =
        Contact(
            identificationSection = IdentificationSection(
                firstName = contact.identification.firstName,
                lastName = contact.identification.lastName,
                middleNames = contact.identification.middleNames,
                nickName = contact.identification.nickName
            ),
            phoneNumbers = NumbersSection(
                mainNumber = contact.numbers.mainNumber,
                numbers = contact.numbers.numbers
            ),
            emails = EmailsSection(contact.emails?.emails)
        )
}
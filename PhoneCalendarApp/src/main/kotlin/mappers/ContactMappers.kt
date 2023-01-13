package mappers

import dto.ContactDto
import model.ContactTableModel

class ContactMappers {

    fun listContactDtoToListContactTableModel(contactDtos: Iterable<ContactDto>) = contactDtos.map { contactDto ->
        contactDtoToContactTableModel(contactDto)
    }.toList()

    fun contactDtoToContactTableModel(contactDto: ContactDto) =
        ContactTableModel(
            firstName = contactDto.identificationSectionDto.firstName,
            lastName = contactDto.identificationSectionDto.lastName,
            mainPhone = contactDto.phoneNumbers.mainNumber
        )
}
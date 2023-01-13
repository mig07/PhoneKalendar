package dto

class ContactDto(
    val identificationSectionDto: IdentificationSectionDto,
    val phoneNumbers: NumbersSectionDto,
    val emails: EmailsSectionDto? = null
)

class IdentificationSectionDto(
    val firstName: String,
    val lastName: String,
    val middleNames: List<String>? = null,
    val nickName: String? = null
)

class NumbersSectionDto(
    val mainNumber: String,
    val numbers: List<Pair<String, String>>? = null
)

class EmailsSectionDto(val emails: List<Pair<String, String>>)
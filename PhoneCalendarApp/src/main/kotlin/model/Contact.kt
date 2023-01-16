package model

class Contact(
    val identificationSection: IdentificationSection,
    val phoneNumbers: NumbersSection,
    val emails: EmailsSection? = null
)

class IdentificationSection(
    val firstName: String,
    val lastName: String,
    val middleNames: List<String>? = null,
    val nickName: String? = null
)

class NumbersSection(
    val mainNumber: String,
    val numbers: List<Pair<String, String>>? = null
)

class EmailsSection(val emails: List<Pair<String, String>>?)
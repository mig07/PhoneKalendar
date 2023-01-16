package entity

class DetailedContact(
    val identification: Identification,
    val numbers: Numbers,
    val emails: Emails? = null
)

class Identification(
    val firstName: String,
    val lastName: String,
    val middleNames: List<String>? = null,
    val nickName: String? = null
)

class Numbers(
    val mainNumber: String,
    val numbers: List<Pair<String, String>>? = null
)

class Emails(val emails: List<Pair<String, String>>?)


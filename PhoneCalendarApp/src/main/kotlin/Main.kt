import dataAccess.ContactsDao
import tornadofx.App
import tornadofx.launch
import view.HomeView

fun main() {
    launch<PhoneCalendarApp>()
}

val contactsDao = ContactsDao()

class PhoneCalendarApp : App(HomeView::class)

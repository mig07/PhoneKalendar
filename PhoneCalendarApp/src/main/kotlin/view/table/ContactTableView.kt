package view.table

import entity.TableContact
import tornadofx.View
import tornadofx.onUserSelect
import tornadofx.readonlyColumn
import tornadofx.tableview
import viewModel.ContactViewModel

class ContactTableView : View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = tableview(
        contactViewModel.contactsTableLiveData
    ) {
        contactViewModel.setTableContacts()
        readonlyColumn("First name", TableContact::firstName)
        readonlyColumn("Last name", TableContact::lastName)
        readonlyColumn("Main phone", TableContact::mainPhone)
    }
}
package view.table

import entity.TableContact
import tornadofx.View
import tornadofx.onSelectionChange
import tornadofx.readonlyColumn
import tornadofx.tableview
import viewModel.ContactViewModel

class ContactTableView : View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = tableview(
        contactViewModel.contactsTableLiveData
    ) {
        contactViewModel.setTableContactsLiveData()
        readonlyColumn("Main phone", TableContact::mainPhone)
        readonlyColumn("First name", TableContact::firstName)
        readonlyColumn("Last name", TableContact::lastName)
        onSelectionChange {
            contactViewModel.setSelectedContact(it)
        }
    }
}
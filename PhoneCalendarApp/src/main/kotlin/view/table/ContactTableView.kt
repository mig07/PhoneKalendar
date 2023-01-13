package view.table

import model.ContactTableModel
import tornadofx.View
import tornadofx.readonlyColumn
import tornadofx.tableview
import viewModel.ContactViewModel

class ContactTableView : View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = tableview(
        contactViewModel.contactsTableLiveData
    ) {
        readonlyColumn("First name", ContactTableModel::firstName)
        readonlyColumn("Last name", ContactTableModel::lastName)
        readonlyColumn("Main phone", ContactTableModel::mainPhone)
    }
}
package view

import tornadofx.View
import tornadofx.button
import tornadofx.hbox
import view.form.ContactCreationWindow
import view.form.ContactUpdateWindow
import viewModel.ContactViewModel

class AppButtonsView: View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = hbox {
        button("Add contact").setOnAction {
            ContactCreationWindow().openWindow()
        }

        button("Delete contact").setOnAction {
            contactViewModel.removeSelectedContact()
            contactViewModel.setTableContacts()
        }

        button("Update contact").setOnAction {
            ContactUpdateWindow().openWindow()
        }
    }
}
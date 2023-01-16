package view

import tornadofx.*
import view.form.ContactCreationWindow
import view.form.ContactUpdateWindow
import viewModel.ContactViewModel

class AppButtonsView : View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = hbox {
        button("Add contact").setOnAction {
            ContactCreationWindow().openWindow()
        }

        button("Delete contact")
            .setOnAction {
                if (contactViewModel.selectedDetailedContact != null)
                    contactViewModel.removeSelectedContact()
            }

        button("Update contact")
            .setOnAction {
                if (contactViewModel.selectedDetailedContact != null)
                    ContactUpdateWindow().openWindow()
            }
    }
}
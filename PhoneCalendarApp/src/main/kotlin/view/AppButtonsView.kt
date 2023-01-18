package view

import tornadofx.*
import view.floatingWindow.detail.ContactDetailView
import view.floatingWindow.form.ContactCreationWindow
import view.floatingWindow.form.ContactUpdateWindow
import viewModel.ContactViewModel

class AppButtonsView : View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = hbox {
        button("Create contact").setOnAction {
            ContactCreationWindow().openWindow()
        }

        button("Read contact").setOnAction {
            if (contactViewModel.selectedDetailedContact != null)
                ContactDetailView(contactViewModel.selectedDetailedContact!!).openWindow()
        }

        button("Update contact").setOnAction {
            if (contactViewModel.selectedDetailedContact != null)
                ContactUpdateWindow().openWindow()
        }

        button("Delete contact").setOnAction {
            if (contactViewModel.selectedDetailedContact != null)
                contactViewModel.removeSelectedContact()
        }
    }
}
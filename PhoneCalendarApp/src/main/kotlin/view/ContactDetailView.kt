package view

import tornadofx.View
import tornadofx.label
import tornadofx.vbox
import viewModel.ContactViewModel

class ContactDetailView : View() {

    val contactViewModel: ContactViewModel by inject()

    override val root = vbox {
        label("Name: ${contactViewModel.selectedDetailedContact?.identification?.firstName ?: ""}")
        label("Full name: ${contactViewModel.selectedDetailedContact?.identification ?: ""}")
        label("Main phone number: ")
        label("Other phone numbers: ")
        label("Emails: ")
    }
}
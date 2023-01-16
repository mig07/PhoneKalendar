package view

import exception.ContactNotFoundException
import tornadofx.*
import viewModel.ContactViewModel

class ContactDetailView : View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = vbox {
        label("Full name: ${contactViewModel.selectedDetailedContact?.identification ?: throw ContactNotFoundException()}")
        label("Main phone number: ${contactViewModel.selectedDetailedContact?.numbers?.mainNumber ?: throw ContactNotFoundException()}")
    }
}


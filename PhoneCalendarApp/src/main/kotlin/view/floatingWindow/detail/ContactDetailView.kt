package view.floatingWindow.detail

import entity.DetailedContact
import javafx.geometry.Pos
import tornadofx.*

const val DETAIL_WINDOW_NAME = "Contact details"

class ContactDetailView(detailedContact: DetailedContact) : View(DETAIL_WINDOW_NAME) {

    override val root = form {
        label("Name: ${detailedContact.identification.firstName} ${detailedContact.identification.lastName}")
        label("Full name: ${detailedContact.identification}")
        label("Main phone number: ")
        label("Other phone numbers: ")
        label("Emails: ")

        vbox {
            alignment = Pos.CENTER
            button("Close").action { close() }
        }
    }


}
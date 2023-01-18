package view.floatingWindow.detail

import entity.DetailedContact
import javafx.geometry.Pos
import tornadofx.*

const val DETAIL_WINDOW_NAME = "Contact details"

class ContactDetailView(detailedContact: DetailedContact) : View(DETAIL_WINDOW_NAME) {

    override val root = form {
        fieldset("Identification") {
            label("Name: ${detailedContact.identification.firstName} ${detailedContact.identification.lastName}")
            label("Full name: ${detailedContact.identification}")
        }
        fieldset("Numbers") {
            label("Main phone number: ${detailedContact.numbers.mainNumber}")
            label("Other phone numbers: ")
            detailedContact.numbers.numbers?.map { label(" - ${it.first}: ${it.second}") }
        }
        fieldset("Emails") {
            detailedContact.emails?.emails?.map { label(" - ${it.first}: ${it.second}") }
        }

        vbox {
            alignment = Pos.CENTER
            button("Close").action { close() }
        }
    }
}
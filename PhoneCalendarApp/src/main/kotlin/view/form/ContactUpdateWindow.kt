package view.form

import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import tornadofx.*
import viewModel.ContactViewModel

const val UPDATE_WINDOW_NAME = "Update contact"

class ContactUpdateWindow : View(UPDATE_WINDOW_NAME) {

    private val contactViewModel: ContactViewModel by inject()

    private var firstName = SimpleObjectProperty<String>()
    private var lastName = SimpleObjectProperty<String>()
    private var mainPhoneNumber = SimpleObjectProperty<String>()

    override val root = form {
        alignment = Pos.CENTER;

        fieldset(WINDOW_FUNCTION) {

            field("First name") {
                textfield(firstName)
            }

            field("Last name") {
                textfield(lastName)
            }

            field("Main phone") {
                textfield(mainPhoneNumber)
            }
        }

        button("Update").action {
            // TODO
            /*contactViewModel.updateSelectedContact(

            )*/
            contactViewModel.setTableContacts()
            close()
        }
    }
}
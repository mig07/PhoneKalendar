package view.floatingWindow.form

import entity.DetailedContact
import entity.Identification
import entity.Numbers
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import tornadofx.*
import viewModel.ContactViewModel

const val CREATION_WINDOW_NAME = "Add new contact"

class ContactCreationWindow : View(CREATION_WINDOW_NAME) {

    private val contactViewModel: ContactViewModel by inject()

    private var firstName = SimpleObjectProperty<String>()
    private var lastName = SimpleObjectProperty<String>()
    private var mainPhoneNumber = SimpleObjectProperty<String>()

    override val root = form {
        alignment = Pos.CENTER

        fieldset(CREATION_WINDOW_NAME) {

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

        button("Create").action {
            val detailedContact = DetailedContact(
                identification = Identification(
                    firstName.value,
                    lastName.value
                ), numbers = Numbers(mainNumber = mainPhoneNumber.value, null)
            )
            contactViewModel.createContact(detailedContact)
            close()
        }
    }
}
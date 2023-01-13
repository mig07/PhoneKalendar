package view.form

import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import tornadofx.*
import viewModel.ContactViewModel

const val WINDOW_FUNCTION = "Add new contact"

class ContactCreationWindow : View(WINDOW_FUNCTION) {

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

        button("Create").action {
            // TODO
            /*contactViewModel.createContact(
                ContactDetailModel(
                    identificationSection = IdentificationSection(
                        firstName.value,
                        lastName.value
                    ), phoneNumbers = NumbersSection(mainNumber = mainPhoneNumber.value, null)
                )
            )*/
            close()
        }
    }
}
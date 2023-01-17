package view.floatingWindow.form

import entity.DetailedContact
import entity.Emails
import entity.Identification
import entity.Numbers
import javafx.beans.property.SimpleObjectProperty
import javafx.geometry.Pos
import tornadofx.*
import viewModel.ContactViewModel

const val UPDATE_WINDOW_NAME = "Update contact"

class ContactUpdateWindow : View(UPDATE_WINDOW_NAME) {

    private val contactViewModel: ContactViewModel by inject()

    val selectedContact = contactViewModel.selectedDetailedContact!!

    private var firstName = SimpleObjectProperty(selectedContact.identification.firstName)
    private var lastName = SimpleObjectProperty(selectedContact.identification.lastName)
    private var mainPhoneNumber = SimpleObjectProperty(selectedContact.numbers.mainNumber)

    override val root = form {
        alignment = Pos.CENTER

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
            val updatedContact = DetailedContact(
                identification = Identification(
                    firstName = firstName.value,
                    lastName = lastName.value,
                    middleNames = null,
                    nickName = null
                ),
                numbers = Numbers(
                    mainNumber = mainPhoneNumber.value,
                    numbers = null
                ),
                emails = Emails(null)
            )
            contactViewModel.updateSelectedContact(updatedContact)
            close()
        }
    }
}
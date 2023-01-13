package view

import tornadofx.View
import tornadofx.button
import tornadofx.hbox
import view.form.ContactCreationWindow

class AppButtonsView: View() {
    override val root = hbox {
        button("Add contact").setOnAction {
            ContactCreationWindow().openWindow()
        }
    }
}
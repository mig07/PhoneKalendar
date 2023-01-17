package view

import tornadofx.View
import tornadofx.borderpane
import view.table.ContactTableView

class HomeView : View() {

    override val root = borderpane {
        try {
            // Top app buttons bar
            top<AppButtonsView>()

            // Contacts' table
            center<ContactTableView>()

            // Contact's detail
            right<ContactDetailView>()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
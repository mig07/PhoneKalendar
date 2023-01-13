package view

import tornadofx.View
import tornadofx.borderpane
import view.table.ContactTableView
import viewModel.ContactViewModel

const val DEFAULT_WINDOW_WIDTH = 800.0
const val DEFAULT_WINDOW_HEIGHT = 600.0

class HomeView : View() {

    private val contactViewModel: ContactViewModel by inject()

    override val root = borderpane {
        setPrefSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT)

        // Top app buttons bar
        top<AppButtonsView>()

        // Contacts' table (left pane)
        left<ContactTableView>()

        // Contact detail (right pane)
        right<ContactDetailView>()
    }
}
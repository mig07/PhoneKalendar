package view

import tornadofx.View
import tornadofx.borderpane
import view.table.ContactTableView

const val DEFAULT_WINDOW_WIDTH = 800.0
const val DEFAULT_WINDOW_HEIGHT = 600.0

class HomeView : View() {

    override val root = borderpane {
        try {
            setPrefSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT)

            // Top app buttons bar
            top<AppButtonsView>()

            // Contacts' table (left pane)
            left<ContactTableView>()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
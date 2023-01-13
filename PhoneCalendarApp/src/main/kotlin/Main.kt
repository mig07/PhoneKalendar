import tornadofx.App
import tornadofx.launch
import view.HomeView

fun main() {
    launch<PhoneCalendarApp>()
}

class PhoneCalendarApp : App(HomeView::class)
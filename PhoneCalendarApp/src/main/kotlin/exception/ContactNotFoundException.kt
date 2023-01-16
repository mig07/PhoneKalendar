package exception

class ContactNotFoundException(val msg: String = "The selected contact does not exist."): Exception(msg)
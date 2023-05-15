package com.example.data_structure
import javafx.application.Application
import javafx.collections.FXCollections
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.BorderPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage

data class Contact(val name: String, val phoneNumber: String, val email: String, val address: String)

val startTime = System.nanoTime()
val endTime = System.nanoTime()

class ContactsApp : Application() {

    private val contacts = arrayOf(
        Contact("Jessie", "141", "jessie@gmail.com", "Cavite"),
        Contact("Julia", "111", "julia@gmail.com", "Binan"),
        Contact("Jezon", "171", "jezon@gmail.com", "Quezon"),
        Contact("Helix", "151", "helix@gmail.com", "Manila")

    )
    private val searchField = TextField()
    override fun start(primaryStage: Stage?) {
        // creating UI components or Controls of the App
        val nameLabel = Label("Name:")
        val phoneLabel = Label("Phone:")
        val emailLabel = Label("Email:")
        val addressLabel = Label("Address:")
        val nameField = TextField()
        val phoneField = TextField()
        val emailField = TextField()
        val addressField = TextField()
        val searchButton = Button("SEARCH")
        val clearButton = Button("CLEAR")
        val searchResultLabel = Label()

        startTime
        // set up event handlers for buttons
        searchButton.setOnAction {
            val searchTerm = searchField.text
            val foundContacts = contacts.filter {
                it.name.contains(searchTerm, ignoreCase = true) ||
                        it.phoneNumber.contains(searchTerm, ignoreCase = true) ||
                        it.email.contains(searchTerm, ignoreCase = true) ||
                        it.address.contains(searchTerm, ignoreCase = true)
            }
            if (foundContacts.isNotEmpty()) {
                val contact = foundContacts[0]
                nameField.text = contact.name
                phoneField.text = contact.phoneNumber
                emailField.text = contact.email
                addressField.text = contact.address
                searchResultLabel.text = "Found ${foundContacts.size} contacts matching '$searchTerm'"
            } else {
                searchResultLabel.text = "No contacts found matching '$searchTerm'"
            }

        }
        endTime
        clearButton.setOnAction {
            nameField.text = " "
            phoneField.text = " "
            emailField.text = " "
            addressField.text = " "
            searchResultLabel.text = " "
        }


        val form = GridPane().apply {
            hgap = 14.0
            vgap = 14.0
            add(nameLabel, 4, 3)
            add(nameField, 5, 3)
            add(phoneLabel, 4, 4)
            add(phoneField, 5, 4)
            add(emailLabel, 4, 5)
            add(emailField, 5, 5)
            add(addressLabel, 4, 6)
            add(addressField, 5, 6)

        }

        val searchBox = HBox().apply {
            alignment = Pos.CENTER_LEFT
            spacing = 14.0
            children.addAll(searchField, searchButton, clearButton)
        }
        val searchResultBox = VBox().apply {
            spacing = 14.0
            children.addAll(searchResultLabel, form)
        }
        val root = BorderPane().apply {
            padding =Insets(10.0)
            top = searchBox
            center = searchResultBox
        }
   
        val scene = Scene(root, 410.0, 310.0)
        primaryStage?.title = "Search & Contacts App"
        primaryStage?.scene = scene
        primaryStage?.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(ContactsApp::class.java, *args)
    val searchTime = endTime - startTime
    println("Search Time: $searchTime")
}

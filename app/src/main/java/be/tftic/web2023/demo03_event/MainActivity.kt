package be.tftic.web2023.demo03_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.text.MessageFormat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var btnMsg1 : Button
    lateinit var btnMsg2 : Button
    lateinit var btnMsg3 : Button
    lateinit var tvMsg : TextView

    lateinit var inputFormData : EditText
    lateinit var btnFormValid : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liaison entre le code et la vue
        btnMsg1 = findViewById(R.id.btn_main_message_popup_1)
        btnMsg2 = findViewById(R.id.btn_main_message_popup_2)
        btnMsg3 = findViewById(R.id.btn_main_message_popup_3)
        tvMsg = findViewById(R.id.tv_main_message_popup)

        inputFormData = findViewById(R.id.et_main_form_data)
        btnFormValid = findViewById(R.id.btn_main_form_valid)

        // Cas 1 : Gestion de l'event "Click" via l'activité qui implemente "View.OnClickListener"
        btnMsg1.setOnClickListener(this)
        btnMsg2.setOnClickListener(this)
        btnMsg3.setOnClickListener(this)

        // Cas 2 : Gestion de l'event "Click" via une lambda
        btnFormValid.setOnClickListener { processForm() }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_main_message_popup_1 -> showMessage("Salut =)")
            R.id.btn_main_message_popup_2 -> showMessage("Good Bye ♥")
            R.id.btn_main_message_popup_3 -> showMessage("Thibault")
            else -> throw RuntimeException("Button not implemented !")
        }
    }

    private fun showMessage(msg : String) : Unit {

        // Modifier du text
        tvMsg.setText(msg)
    }

    private fun processForm() {

        // Récuperation de la valeur de l'EditText
        val formData : String = inputFormData.text.toString()

        // Exemple sans extraire les chaines de caracteres :(
        // val toastMsg1 : String = MessageFormat.format("Numéro : {0}", formData)
        // val toastMsg2 : String = "Numéro : %s".format(formData)

        // Exemple avec les chaines de caracteres stocké en resource :)
        val toastMsg1 : String = MessageFormat.format(getString(R.string.toast_form_msg1), formData)
        val toastMsg2 : String = getString(R.string.toast_form_msg2).format(formData)


        // Création d'un toast
        val toast : Toast = Toast.makeText(this, toastMsg1, Toast.LENGTH_LONG)

        // Gestion de la position du toast → No-Op depuis Android R
        // (Ne fonctionne que sur des anciennes versions d'android)
        toast.setGravity(Gravity.CENTER, 50, 0)

        // Affichage du toast
        toast.show()


        // Création du toast - Plus rapide
        // Toast.makeText(this, toastMsg1, Toast.LENGTH_LONG).show()
    }

}
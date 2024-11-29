package mx.inclusionyaccesibilidad.app_lsm.ui.learn

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import mx.inclusionyaccesibilidad.app_lsm.databinding.FragmentExerciseMatchingBinding

class ExerciseFragmentMatching : Fragment() {

    private lateinit var leftContainer: LinearLayout
    private lateinit var rightContainer: LinearLayout
    private lateinit var continueButton: Button
    private val leftItems = mutableListOf<String>("A", "B", "C", "D", "E")
    private val rightItems = mutableListOf<String>("Meaning A", "Meaning B", "Meaning C", "Meaning D", "Meaning E")
    private var selectedLeftItem: String? = null
    private var selectedRightItem: String? = null
    private val correctPairs = mutableListOf<Pair<String, String>>() // Guarda las parejas correctas

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val binding = FragmentExerciseMatchingBinding.inflate(inflater, container, false)

        // Inicializamos las vistas
        leftContainer = binding.leftContainer
        rightContainer = binding.rightContainer
        continueButton = binding.continueButton

        // Rellenar los contenedores de la izquierda y derecha
        fillContainer(leftContainer, leftItems)
        fillContainer(rightContainer, rightItems)

        // Configurar el botón "Continuar"
        continueButton.setOnClickListener {
            // Continuar, por ejemplo, navegar a otro fragmento o actividad
            Toast.makeText(context, "Correcto", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun fillContainer(container: LinearLayout, items: List<String>) {
        container.removeAllViews()

        for (item in items) {
            val button = Button(context).apply {
                text = item
                textSize = 16f
                setPadding(16, 16, 16, 16)
                setOnClickListener { onItemSelected(it as Button) }
            }
            container.addView(button)
        }
    }

    private fun onItemSelected(view: Button) {
        // Verificar si ya hay una selección hecha
        if (selectedLeftItem == null) {
            selectedLeftItem = view.text.toString()  // Guardamos el elemento seleccionado de la izquierda
            view.setBackgroundColor(Color.LTGRAY)  // Cambiar color de fondo del elemento seleccionado
        } else if (selectedRightItem == null) {
            selectedRightItem = view.text.toString()  // Guardamos el elemento seleccionado de la derecha
            view.setBackgroundColor(Color.LTGRAY)  // Cambiar color de fondo del elemento seleccionado
            checkMatch()  // Verificamos si la pareja seleccionada es correcta
        }
    }

    private fun checkMatch() {
        if (selectedLeftItem != null && selectedRightItem != null) {
            // Verificar si el emparejamiento es correcto
            if (selectedLeftItem == "A" && selectedRightItem == "Meaning A" ||
                selectedLeftItem == "B" && selectedRightItem == "Meaning B" ||
                selectedLeftItem == "C" && selectedRightItem == "Meaning C" ||
                selectedLeftItem == "D" && selectedRightItem == "Meaning D" ||
                selectedLeftItem == "E" && selectedRightItem == "Meaning E") {
                correctPairs.add(Pair(selectedLeftItem!!, selectedRightItem!!))
                Toast.makeText(context, "Correcto!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Incorrecto", Toast.LENGTH_SHORT).show()
            }

            // Resetear las selecciones para permitir una nueva
            selectedLeftItem = null
            selectedRightItem = null

            // Verificar si todos los emparejamientos son correctos
            if (correctPairs.size == leftItems.size) {
                continueButton.isEnabled = true  // Habilitar el botón de continuar cuando todo esté correcto
            }
        }
    }
}

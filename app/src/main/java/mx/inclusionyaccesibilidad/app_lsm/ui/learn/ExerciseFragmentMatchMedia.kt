package mx.inclusionyaccesibilidad.app_lsm.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import mx.inclusionyaccesibilidad.app_lsm.R

class ExerciseFragmentMatchMedia : Fragment() {

    private var selectedOption: Button? = null // Cambiar a tipo nullable
    private var isAnswerChecked = false // Estado del botón comprobar/continuar
    private val correctAnswer = "Opción 2" // Respuesta correcta (puedes cambiar esto dinámicamente)

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_match_media, container, false)

        val options = listOf<Button>(
            view.findViewById(R.id.option1),
            view.findViewById(R.id.option2),
            view.findViewById(R.id.option3),
            view.findViewById(R.id.option4)
        )
        val resultMessage = view.findViewById<TextView>(R.id.result_message)
        val verifyButton = view.findViewById<Button>(R.id.verify_button)

        // Configurar opciones
        options.forEach { button ->
            button.setOnClickListener {
                // Desmarcar la opción seleccionada previamente, si hay alguna
                selectedOption?.isSelected = false

                // Asignar la nueva opción seleccionada
                selectedOption = button

                // Resaltar la nueva opción
                button.isSelected = true
            }
        }

        // Configurar lógica del botón Comprobar/Continuar
        verifyButton.setOnClickListener {
            if (!isAnswerChecked) {
                // Verificar respuesta
                val message = if (selectedOption != null && selectedOption!!.text == correctAnswer) {
                    "¡Correcto!"
                } else {
                    "Incorrecto. La respuesta era: $correctAnswer"
                }
                resultMessage.text = message
                verifyButton.text = "Continuar"
                isAnswerChecked = true
            } else {
                // Continuar a la siguiente actividad o fragmento
                Toast.makeText(requireContext(), "Continuar", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    companion object {
        fun newInstance(): ExerciseFragmentMatchMedia {
            return ExerciseFragmentMatchMedia()
        }
    }
}


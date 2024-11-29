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
import mx.inclusionyaccesibilidad.app_lsm.data.models.Leccion
import mx.inclusionyaccesibilidad.app_lsm.data.models.Unidad

class StartLessonFragment : Fragment() {

    private var lessonId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recuperar el ID de la lección desde los argumentos del Fragment
        arguments?.let {
            lessonId = it.getInt("LESSON_ID", -1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflar el diseño del fragmento
        val view = inflater.inflate(R.layout.fragment_start_lesson, container, false)

        // Referencias a los elementos del layout
        val tvLessonNo = view.findViewById<TextView>(R.id.tvLessonNo)
        val tvLessonName = view.findViewById<TextView>(R.id.tvLessonName)
        val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
        val btnStartLesson = view.findViewById<Button>(R.id.btnStartLesson)

        // Cargar la lección seleccionada (aquí puedes usar tu fuente de datos real)
        val leccion = getLessonById(lessonId)
        //Toast.makeText(requireContext(), leccion.toString(), Toast.LENGTH_SHORT).show()

        if (leccion != null) {
            // Mostrar los datos de la lección
            tvLessonNo.text = "Lección ${leccion.id}"
            tvLessonName.text = leccion.grupo
            tvDescription.text = leccion.descripcion
        } else {
            Toast.makeText(requireContext(), "Lección no encontrada", Toast.LENGTH_SHORT).show()
        }

        // Configurar el botón para comenzar la lección
        btnStartLesson.setOnClickListener {
            // Cambiar al siguiente fragmento (por ahora solo un ejemplo)
            (activity as? LessonActivity)?.loadFragment(ExerciseFragmentLearnSigns())
        }

        return view
    }

    // Simulación de recuperación de datos de la lección
    private fun getLessonById(id: Int): Leccion? {
        try {
            // Obtener el contexto para acceder a los recursos
            val context = requireContext()

            // Leer el archivo JSON desde raw/data.json
            val inputStream = context.resources.openRawResource(R.raw.data)
            val jsonString = inputStream.bufferedReader().use { it.readText() }

            // Parsear el JSON a una lista de objetos Unidad
            val gson = com.google.gson.Gson()
            val unidades = gson.fromJson(jsonString, Array<Unidad>::class.java).toList()

            // Buscar la lección por ID
            for (unidad in unidades) {
                val leccion = unidad.lecciones.find { it.id == id }
                if (leccion != null) {
                    return leccion
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }

        // Retornar null si no se encuentra la lección
        return null
    }


    companion object {
        // Método para crear una instancia del fragmento con argumentos
        fun newInstance(lessonId: Int): StartLessonFragment {
            val fragment = StartLessonFragment()
            val args = Bundle()
            args.putInt("LESSON_ID", lessonId)
            fragment.arguments = args
            return fragment
        }
    }
}

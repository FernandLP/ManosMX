package mx.inclusionyaccesibilidad.app_lsm.ui.learn

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.inclusionyaccesibilidad.app_lsm.R
import java.io.InputStreamReader
import mx.inclusionyaccesibilidad.app_lsm.data.models.Unidad
import mx.inclusionyaccesibilidad.app_lsm.data.models.Leccion

class LearnFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflar el layout para el fragmento
        val view = inflater.inflate(R.layout.fragment_learn, container, false)
        val gridLayout = view.findViewById<GridLayout>(R.id.gridLayout)

        // Leer y analizar el archivo JSON
        val jsonString = requireContext().resources.openRawResource(R.raw.data).use { inputStream ->
            InputStreamReader(inputStream).readText()
        }
        val type = object : TypeToken<List<Unidad>>() {}.type
        val unidades: List<Unidad> = Gson().fromJson(jsonString, type)

        // Crear widgets dinámicamente basado en los datos del JSON
        for (unidad in unidades) {
            for (leccion in unidad.lecciones) {
                val widgetView = inflater.inflate(R.layout.widget_block_item, gridLayout, false) as LinearLayout

                val tvName = widgetView.findViewById<TextView>(R.id.tvName)
                val tvLessonName = widgetView.findViewById<TextView>(R.id.tvLessonName)
                val tvExtraInfo = widgetView.findViewById<TextView>(R.id.tvExtraInfo)

                tvName.text = leccion.grupo
                tvLessonName.text = "Unidad: " + unidad.unidad + " - " + unidad.titulo
                tvExtraInfo.text = leccion.descripcion

                // Configurar el click para iniciar la lección
                widgetView.setOnClickListener {
                    val intent = Intent(requireContext(), LessonActivity::class.java)
                    //Toast.makeText(requireContext(), "Lección seleccionada: ${leccion.id}", Toast.LENGTH_SHORT).show()
                    intent.putExtra("LESSON_ID", leccion.id) // Pasar el ID de la lección
                    startActivity(intent)
                }

                val layoutParams = GridLayout.LayoutParams().apply {
                    width = GridLayout.LayoutParams.MATCH_PARENT
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                }
                widgetView.layoutParams = layoutParams
                gridLayout.addView(widgetView)
            }
        }

        return view
    }
}

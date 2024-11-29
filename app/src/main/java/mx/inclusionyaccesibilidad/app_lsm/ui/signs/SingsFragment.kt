package mx.inclusionyaccesibilidad.app_lsm.ui.signs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import mx.inclusionyaccesibilidad.app_lsm.R
import mx.inclusionyaccesibilidad.app_lsm.databinding.FragmentSingsBinding
import mx.inclusionyaccesibilidad.app_lsm.data.models.Termino
import java.io.InputStreamReader

class SingsFragment : Fragment() {

    private var _binding: FragmentSingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_sings, container, false)
        val gridLayout = view.findViewById<GridLayout>(R.id.gridLayout)

        // Cargar los datos del archivo JSON
        val inputStream = resources.openRawResource(R.raw.glosary) // Asumimos que el JSON está en raw
        val reader = InputStreamReader(inputStream)
        val gson = Gson()

        // Deserializar el JSON a una lista de objetos Termino
        val terminoListType = object : TypeToken<List<Termino>>() {}.type
        val terminos: List<Termino> = gson.fromJson(reader, terminoListType)

        // Ordenar la lista alfabéticamente por "termino"
        val sortedTerminos = terminos.sortedBy { it.termino }

        // Inflar los widgets dinámicamente
        for (termino in sortedTerminos) {
            val widgetView = inflater.inflate(R.layout.widget_glosary_item, gridLayout, false) as LinearLayout

            // Configurar el contenido del widget
            val tvDicName = widgetView.findViewById<TextView>(R.id.tvDicName)
            tvDicName.text = termino.termino

            // Establecer layoutParams para el widget en el GridLayout
            val layoutParams = GridLayout.LayoutParams().apply {
                width = 0  // Para que se distribuya proporcionalmente
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(0, 1f) // Una sola columna
                rowSpec = GridLayout.spec(GridLayout.UNDEFINED)
            }
            widgetView.layoutParams = layoutParams

            // Agregar el widget al GridLayout
            gridLayout.addView(widgetView)
        }

        return view
    }
}

package mx.inclusionyaccesibilidad.app_lsm.ui.learn

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import mx.inclusionyaccesibilidad.app_lsm.R

class ExerciseFragmentLearnSigns : Fragment() {

    private lateinit var continueButton: Button
    private lateinit var mediaContainer: ViewGroup
    private var mediaIsReady = false

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_learn_signs, container, false)

        // Referencias a los elementos de la UI
        val continueButton = view.findViewById<Button>(R.id.continue_button)
        val mediaContainer = view.findViewById<FrameLayout>(R.id.media_container)

        // Inicializar el botón como deshabilitado
        continueButton.isEnabled = false

        // Configurar el medio que se mostrará (puede ser imagen o video)
        setupMedia()

        // Habilitar el botón después de 5 segundos
        Handler().postDelayed({
            continueButton.isEnabled = true
        }, 5000) // 5000 milisegundos = 5 segundos

        // Configurar el comportamiento del botón continuar
        continueButton.setOnClickListener {
            // Aquí va la lógica para pasar a la siguiente pantalla, actividad o fragmento
            // Por ejemplo, cambiar a otro fragmento o a una actividad
            Toast.makeText(requireContext(), "Continuar", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun setupMedia() {
        // Aquí puedes poner la lógica para mostrar una imagen o un video
        // Si el recurso es una imagen
//        val imageView = ImageView(requireContext())
//        imageView.setImageResource(R.drawable.sign_image) // Cambiar por el recurso de la seña
//        mediaContainer.addView(imageView)

        // Si el recurso es un video (descomenta esto si quieres usar un VideoView)
        /*
        val videoView = VideoView(requireContext())
        videoView.setVideoURI(Uri.parse("android.resource://" + requireContext().packageName + "/" + R.raw.sign_video))
        mediaContainer.addView(videoView)
        videoView.start()
        */
    }

    companion object {
        fun newInstance(): ExerciseFragmentLearnSigns {
            return ExerciseFragmentLearnSigns()
        }
    }
}

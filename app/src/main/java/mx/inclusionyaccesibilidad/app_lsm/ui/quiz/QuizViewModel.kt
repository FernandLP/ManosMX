package mx.inclusionyaccesibilidad.app_lsm.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pantalla de pruebas / juegos"
    }
    val text: LiveData<String> = _text
}
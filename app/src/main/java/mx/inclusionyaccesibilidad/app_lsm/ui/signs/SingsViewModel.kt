package mx.inclusionyaccesibilidad.app_lsm.ui.signs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pantalla del diccionario"
    }
    val text: LiveData<String> = _text
}
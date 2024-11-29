package mx.inclusionyaccesibilidad.app_lsm.ui.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pantalla de perfil"
    }
    val text: LiveData<String> = _text
}
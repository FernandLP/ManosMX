package mx.inclusionyaccesibilidad.app_lsm.data.models

data class Unidad(
    val unidad: Int,
    val titulo: String,
    val lecciones: List<Leccion>
)

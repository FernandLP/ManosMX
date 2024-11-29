package mx.inclusionyaccesibilidad.app_lsm.data.models

data class Leccion(
    val id: Int,
    val grupo: String,
    val descripcion: String,
    val campo_semantico: List<String>? = null,
    val nota: String? = null
){
    //toString
    override fun toString(): String {
        return "Leccion(id=$id, grupo='$grupo', descripcion='$descripcion', campo_semantico=$campo_semantico, nota=$nota)"
    }
}



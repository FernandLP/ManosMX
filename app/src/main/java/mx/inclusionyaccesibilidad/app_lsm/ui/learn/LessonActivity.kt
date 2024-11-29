package mx.inclusionyaccesibilidad.app_lsm.ui.learn

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import mx.inclusionyaccesibilidad.app_lsm.R

class LessonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)

        val lessonId = intent.getIntExtra("LESSON_ID", -1)

        if (savedInstanceState == null) {
            loadFragment(StartLessonFragment.newInstance(lessonId))
        }
    }

    fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}

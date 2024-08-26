package novocurso.rodrigo.com.sorteio

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var texto: TextView
    val frases = arrayOf(
            "O importante não é vencer todos os dias, mas lutar sempre.",
            "Maior que a tristeza de não haver vencido é a vergonha de não ter lutado!",
            "É melhor conquistar a si mesmo do que vencer mil batalhas.",
            "Enquanto houver vontade de lutar haverá esperança de vencer.")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun gerarFrase(view : View){
        val totalItensArray = frases.size

        val random = Random().nextInt(totalItensArray)

        textoFrase.text = frases[random]

    }
}

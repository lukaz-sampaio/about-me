package android.lab.aboutme

import android.content.Context
import android.lab.aboutme.databinding.ActivityMainBinding
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    /*
        Usar databinding torna o código mais rápido. Quando uma View (activity)
        é criada/recriada o componente (seja um TextView, EditText, Button e
        etc) é referenciada no método "findViewById(View)" é buscado novamente.
        Isso porque o comportamento de "findViewyId()" é buscar um componente em
        tempo de execução. E dependendo do tamanho da árvore de views a
        aplicação pode ficar lenta já que o findViewById() percorre toda a
        árvore até achar o componente referenciado. Usando databinding, uma
        classe é criada pelo compilador e referenciada em tempo de compilação.
        Ou seja, o elemento é referenciado apenas uma vez.

        No uso ao invés de "findViewById<View>(R.id.View)", fica
        "binding.name[Button, EditText, TextView e etc]".
     */
    private lateinit var binding: ActivityMainBinding

    private var myName =
        MyName("Lucas Sampaio de Souza")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        findViewById<Button>(R.id.done_button).setOnClickListener {
//            addNickname(it)
//        }

        // informando para o databinding qual activity vai ser usada
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // "ativando" o binding usando a classe de binding (MyName.kt)
        binding.myName = this.myName

        // definindo um evento no botão usando databinding
        binding.doneButton.setOnClickListener {
            addNickname(it)
        }
    }

    private fun addNickname(view: View) {
        binding.apply {
            /*
                myName é a _data class_ usada para o databinding.
                (vale uma atenção no _Elvis Operator_ (sinal de interrogação))
             */
            myName?.nickname = nicknameEdit.text.toString()

            // quando os dados mudarem o binding "apaga" tudo e faz uma nova requisição
            invalidateAll()

            nicknameEdit.visibility = View.GONE
            view.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }

        //hide keyboard
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

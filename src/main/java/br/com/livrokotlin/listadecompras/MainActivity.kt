package br.com.livrokotlin.listadecompras

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var listViewProdutos: ListView
    private lateinit var txtTotal: TextView
    private lateinit var produtosAdapter: ProdutoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewProdutos = findViewById(R.id.list_view_produtos)
        txtTotal = findViewById(R.id.txt_total)
        val btnAbrirCadastro = findViewById<Button>(R.id.btn_abrir_cadastro)

        produtosAdapter = ProdutoAdapter(this)
        listViewProdutos.adapter = produtosAdapter

        btnAbrirCadastro.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }

        listViewProdutos.setOnItemLongClickListener { _, _, position, _ ->
            val item = produtosAdapter.getItem(position)
            if (item != null) {
                AlertDialog.Builder(this)
                    .setTitle("Remover Produto")
                    .setMessage("Tem certeza que deseja remover ${item.nome}?")
                    .setPositiveButton("Sim") { _, _ ->
                        produtosGlobal.remove(item) // Remove da lista global primeiro
                        produtosAdapter.remove(item) // Remove do adapter (para atualizar a view)
                        // produtosAdapter.notifyDataSetChanged() // adapter.remove() já notifica
                        updateTotalSumInView() // Atualiza o total
                    }
                    .setNegativeButton("Não", null)
                    .show()
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        // Sempre que a MainActivity voltar ao foco, atualiza a lista
        produtosAdapter.clear() // Limpa os itens atuais do adapter
        produtosAdapter.addAll(produtosGlobal) // Adiciona todos os itens da lista global
        // produtosAdapter.notifyDataSetChanged() // addAll geralmente notifica, mas pode ser redundante ou útil
        updateTotalSumInView() // Recalcula e exibe o novo total
    }

    private fun updateTotalSumInView() {
        // Calcula a soma multiplicando o valor unitário pela quantidade de cada produto
        val soma = produtosGlobal.sumOf { it.valor * it.quantidade } // Use sumOf para Double
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txtTotal.text = "TOTAL: ${f.format(soma)}"
    }
}
package br.com.livrokotlin.listadecompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

class ProdutoAdapter(context: Context) : ArrayAdapter<Produto>(context, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val v: View
        val localItem = getItem(position)

        if (convertView != null) {
            v = convertView
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item, parent, false)
        }

        val txtProduto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txtQtd = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txtValor = v.findViewById<TextView>(R.id.txt_item_valor)
        val imgProduto = v.findViewById<ImageView>(R.id.img_item_foto)

        if (localItem != null) {
            txtProduto.text = localItem.nome
            txtQtd.text = "Qtde: ${localItem.quantidade}" // Adicionei "Qtde:" para clareza

            val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            txtValor.text = f.format(localItem.valor * localItem.quantidade) // Valor total do item

            if (localItem.foto != null) {
                imgProduto.setImageBitmap(localItem.foto)
            } else {
                // Certifique-se de ter um drawable placeholder ou use um do Android
                // Se você criou img_item_foto_placeholder.png na pasta drawable:
                // imgProduto.setImageResource(R.drawable.img_item_foto_placeholder)
                // Caso contrário, para teste rápido:
                imgProduto.setImageResource(android.R.drawable.ic_menu_gallery)
            }
        }
        return v
    }
}
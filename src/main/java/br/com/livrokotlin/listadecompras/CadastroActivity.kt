package br.com.livrokotlin.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileNotFoundException

class CadastroActivity : AppCompatActivity() {

    private val COD_IMAGE = 101
    private var imageBitMap: Bitmap? = null

    private lateinit var imgFotoProduto: ImageView
    private lateinit var txtProduto: EditText
    private lateinit var txtQtd: EditText
    private lateinit var txtValor: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        imgFotoProduto = findViewById(R.id.img_foto_produto)
        txtProduto = findViewById(R.id.txt_produto)
        txtQtd = findViewById(R.id.txt_qtd)
        txtValor = findViewById(R.id.txt_valor)
        val btnInserir = findViewById<Button>(R.id.btn_inserir)


        imgFotoProduto.setOnClickListener {
            openGallery()
        }

        btnInserir.setOnClickListener {
            val nomeProduto = txtProduto.text.toString()
            val qtdProdutoStr = txtQtd.text.toString()
            val valorProdutoStr = txtValor.text.toString()

            if (nomeProduto.isNotEmpty() && qtdProdutoStr.isNotEmpty() && valorProdutoStr.isNotEmpty()) {
                try {
                    val quantidade = qtdProdutoStr.toInt()
                    val valor = valorProdutoStr.toDouble()

                    if (quantidade <= 0) {
                        txtQtd.error = "Quantidade deve ser maior que zero"
                        Toast.makeText(this, "Quantidade deve ser maior que zero.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                    if (valor <= 0) {
                        txtValor.error = "Valor deve ser maior que zero"
                        Toast.makeText(this, "Valor deve ser maior que zero.", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }


                    val prod = Produto(
                        nomeProduto,
                        quantidade,
                        valor,
                        imageBitMap
                    )
                    produtosGlobal.add(prod)

                    Toast.makeText(this, "${prod.nome} cadastrado!", Toast.LENGTH_SHORT).show()

                    txtProduto.text.clear()
                    txtQtd.text.clear()
                    txtValor.text.clear()
                    imgFotoProduto.setImageResource(android.R.drawable.ic_menu_camera) // Reset image
                    imageBitMap = null // Reset bitmap
                    txtProduto.requestFocus() // Set focus to the first field

                    // finish() // Descomente se quiser voltar para MainActivity automaticamente
                } catch (e: NumberFormatException) {
                    // Este erro é menos provável com inputType="number" e "numberDecimal", mas bom ter.
                    Toast.makeText(this, "Formato de quantidade ou valor inválido.", Toast.LENGTH_SHORT).show()
                    if (qtdProdutoStr.toIntOrNull() == null) txtQtd.error = "Quantidade inválida"
                    if (valorProdutoStr.toDoubleOrNull() == null) txtValor.error = "Valor inválido"
                }

            } else {
                txtProduto.error = if (nomeProduto.isEmpty()) "Preencha o nome do produto" else null
                txtQtd.error = if (qtdProdutoStr.isEmpty()) "Preencha a quantidade" else null
                txtValor.error = if (valorProdutoStr.isEmpty()) "Preencha o valor" else null
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        // Verifica se há alguma activity que possa lidar com esta Intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, COD_IMAGE)
        } else {
            Toast.makeText(this, "Nenhum aplicativo de galeria encontrado.", Toast.LENGTH_SHORT).show()
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API", ReplaceWith("registerForActivityResult(...)"))
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.data != null) {
                try {
                    val inputStream = contentResolver.openInputStream(data.data!!)
                    // Decodifica a imagem para um tamanho razoável para evitar OutOfMemoryError
                    val options = BitmapFactory.Options()
                    options.inSampleSize = 2 // Reduz a imagem pela metade (ajuste conforme necessário)
                    imageBitMap = BitmapFactory.decodeStream(inputStream, null, options)
                    imgFotoProduto.setImageBitmap(imageBitMap)
                    inputStream?.close()
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Arquivo de imagem não encontrado.", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Erro ao carregar imagem.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
package com.example.campominado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import android.widget.Toast

class Tela_OJogo : AppCompatActivity() {
    private lateinit var game: MineSweeperGame
    private lateinit var buttons: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_ojogo)

        val gridLayout: GridLayout = findViewById(R.id.gridLayout)
        game = MineSweeperGame(5, 5, 5)
        buttons = Array(5) { row ->
            Array(5) { col ->
                Button(this).apply {
                    layoutParams = GridLayout.LayoutParams().apply {
                        rowSpec = GridLayout.spec(row)
                        columnSpec = GridLayout.spec(col)
                        width = 110
                        height = 110
                        setMargins(4, 4, 4, 4)
                    }
                    text = "🚪"
                    setOnClickListener {
                        val cell = game.getCell(row, col)
                        game.revealCell(cell, this@Tela_OJogo)
                    }
                    setOnLongClickListener {
                        val cell = game.getCell(row, col)
                        game.toggleMark(cell)
                        updateButton(cell, this)
                        true // Indica que o evento foi consumido
                    }
                    gridLayout.addView(this)
                }
            }
        }
    }
    fun onGameOver(victory: Boolean) {
        if (victory) {
            Toast.makeText(this, "Parabéns! Você venceu!", Toast.LENGTH_LONG).show()
            // Outras ações para vitória, como mudar a cor do tabuleiro, etc.
        } else {
            Toast.makeText(this, "Que pena! Você perdeu.", Toast.LENGTH_LONG).show()
            revealAllMines()
            disableAllButtons()
        }
    }

    private fun revealAllMines() {
        for (row in 0 until 5) {
            for (col in 0 until 5) {
                val cell = game.getCell(row, col)
                if (cell.isMine) {
                    buttons[row][col].text = "💣"
                }
            }
        }
    }

    private fun disableAllButtons() {
        for (row in buttons) {
            for (button in row) {
                button.isEnabled = false
            }
        }
    }

    fun updateButtons() {
        for (row in 0 until 5) {
            for (col in 0 until 5) {
                updateButton(game.getCell(row, col), buttons[row][col])
            }
        }
    }

     fun updateButton(cell: Cell, button: Button) {
        if (cell.isRevealed) {
            if (cell.isMine) {
                button.text = "💣" // Simbolizando mina
            } else {
                button.text = cell.minesAround.toString()
            }
        } else if (cell.isMarked) {
            button.text = "🚩" // Simbolizando marcado
        } else {
            button.text = "🚪"
        }
    }
}


class Cell(val row: Int, val col: Int) {
    var isMine: Boolean = false
    var isRevealed: Boolean = false
    var isMarked: Boolean = false
    var minesAround: Int = 0
}


class MineSweeperGame(private val rows: Int, private val cols: Int, private val numMines: Int) {
    private var firstMove = true

    fun getCell(row: Int, col: Int): Cell {
        return grid[row][col]
    }

    val grid = Array(rows) { row ->
        Array(cols) { col -> Cell(row, col) }
    }

    init {

        calculateMinesAround()
    }


    private fun calculateMinesAround() {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                var count = 0
                for (i in -1..1) {
                    for (j in -1..1) {
                        if (i == 0 && j == 0) continue
                        val newRow = row + i
                        val newCol = col + j
                        if (newRow in 0 until rows && newCol in 0 until cols && grid[newRow][newCol].isMine) {
                            count++
                        }
                    }
                }
                grid[row][col].minesAround = count
            }
        }
    }



    private fun placeMines(firstCell: Cell) {
        var minesPlaced = 0
        while (minesPlaced < numMines) {
            val r = (0 until rows).random()
            val c = (0 until cols).random()

            if (!grid[r][c].isMine) {
                grid[r][c].isMine = true
                minesPlaced++
            }
        }
    }

    fun toggleMark(cell: Cell) {
        if (!cell.isRevealed) {
            cell.isMarked = !cell.isMarked
        }
    }

    fun revealCell(cell: Cell, activity: Tela_OJogo) {
        if (cell.isRevealed || cell.isMarked) return

        if (firstMove) {
            placeMines(cell)
            calculateMinesAround()
            firstMove = false
        }

        cell.isRevealed = true

        // Expande apenas para células com 0, 1 ou 2 minas ao redor
        if ((cell.minesAround in 0..2) && !cell.isMine) {
            for (i in -1..1) {
                for (j in -1..1) {
                    if (i == 0 && j == 0) continue
                    val newRow = cell.row + i
                    val newCol = cell.col + j
                    if (newRow in 0 until rows && newCol in 0 until cols && !grid[newRow][newCol].isRevealed && !grid[newRow][newCol].isMine) {
                        grid[newRow][newCol].isRevealed = true // Revela apenas vizinhos imediatos
                    }
                }
            }
        }

        activity.updateButtons()
        checkGameOver(activity)
    }

    fun checkGameOver(activity: Tela_OJogo): Boolean {
        // Verifica derrota
        for (row in grid) {
            for (cell in row) {
                if (cell.isRevealed && cell.isMine) {
                    activity.onGameOver(false) // Derrota
                    return true
                }
            }
        }

        // Verifica vitória
        for (row in grid) {
            for (cell in row) {
                if (!cell.isMine && !cell.isRevealed) {
                    return false // Jogo ainda não terminou
                }
            }
        }

        activity.onGameOver(true) // Vitória
        return true
    }



}



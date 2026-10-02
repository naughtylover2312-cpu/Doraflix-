package com.example.mylivetv

import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.*
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val bg = Color.rgb(16, 27, 45)
    private val card = Color.rgb(25, 42, 67)
    private val blue = Color.rgb(57, 139, 255)
    private val white = Color.WHITE

    private val categories = listOf(
        "Sports", "India",
        "News", "International",
        "Cartoon", "Movies",
        "Entertainment", "Music",
        "Documentary", "Discovery",
        "Bangladesh", "Pakistan",
        "Free TV", "Premium TV",
        "Regional", "Kids"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showCategories()
    }

    private fun makeBackground(color: Int, stroke: Int): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            setStroke(2, stroke)
            cornerRadius = 28f
        }
    }

    private fun showCategories() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
        }

        val header = TextView(this).apply {
            text = "☰      MyLiveTV       ☆      ⌕"
            textSize = 23f
            setTextColor(white)
            gravity = Gravity.CENTER_VERTICAL
            setPadding(20, 20, 20, 20)
            setBackgroundColor(Color.rgb(13, 22, 37))
        }
        root.addView(header)

        val title = TextView(this).apply {
            text = "Categories"
            textSize = 25f
            setTextColor(white)
            setTypeface(null, Typeface.BOLD)
            setPadding(18, 18, 18, 12)
        }
        root.addView(title)

        val scroll = ScrollView(this)
        val grid = GridLayout(this).apply {
            columnCount = 2
            setPadding(8, 4, 8, 12)
        }

        categories.forEach { name ->
            val item = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL
                background = makeBackground(card, blue)
                setPadding(12, 16, 8, 16)
                isClickable = true
                isFocusable = true
                setOnClickListener { showChannels(name) }
            }

            val icon = TextView(this).apply {
                text = when (name) {
                    "Sports" -> "⚽"
                    "India" -> "🇮🇳"
                    "News" -> "📰"
                    "International" -> "🌍"
                    "Cartoon", "Kids" -> "🎨"
                    "Movies" -> "🎬"
                    "Music" -> "🎵"
                    "Documentary", "Discovery" -> "🌿"
                    "Bangladesh" -> "🇧🇩"
                    "Pakistan" -> "🇵🇰"
                    "Free TV" -> "📺"
                    "Premium TV" -> "⭐"
                    "Regional" -> "🌐"
                    else -> "📡"
                }
                textSize = 25f
            }

            val label = TextView(this).apply {
                text = name
                textSize = 17f
                setTextColor(white)
                gravity = Gravity.CENTER
                setTypeface(null, Typeface.BOLD)
            }

            item.addView(icon)
            item.addView(label, LinearLayout.LayoutParams(
                0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f
            ))

            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = 94
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(4, 5, 4, 5)
            }
            grid.addView(item, params)
        }

        scroll.addView(grid)
        root.addView(scroll, LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f
        ))

        setContentView(root)
    }

    private fun showChannels(category: String) {
        val names = when (category) {
            "Sports" -> listOf("Sports Channel 1", "Sports Channel 2", "Free Sports")
            "News" -> listOf("News Channel 1", "News Channel 2")
            "Cartoon", "Kids" -> listOf("Kids Channel 1", "Cartoon Channel")
            "Movies" -> listOf("Movie Channel 1", "Movie Channel 2")
            else -> listOf("$category Channel 1", "$category Channel 2")
        }

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
            setPadding(16, 16, 16, 16)
        }

        val back = TextView(this).apply {
            text = "‹   $category"
            textSize = 24f
            setTextColor(white)
            setPadding(4, 12, 4, 20)
            setOnClickListener { showCategories() }
        }
        root.addView(back)

        names.forEach { channel ->
            val row = TextView(this).apply {
                text = "📺     $channel"
                textSize = 19f
                setTextColor(white)
                gravity = Gravity.CENTER_VERTICAL
                setPadding(16, 20, 16, 20)
                background = makeBackground(card, blue)
                setOnClickListener {
                    Toast.makeText(
                        this@MainActivity,
                        "Authorized stream link is needed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 76
            )
            params.setMargins(0, 6, 0, 6)
            root.addView(row, params)
        }

        setContentView(root)
    }
}

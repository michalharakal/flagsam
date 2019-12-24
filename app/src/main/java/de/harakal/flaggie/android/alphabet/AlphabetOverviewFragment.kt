package de.harakal.flaggie.android.alphabet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import de.harakal.flaggie.android.R
import de.harakal.flaggie.ui.stickman.StickmanView

/**
 * Fragments  showing a [StickmanView] for particular char from Flag alphabet
 */
class AlphabetOverviewFragment(private val characterIndex: Int) : Fragment() {

    val armAngles = listOf(
        Pair(45, -90),
        Pair(0, -90),
        Pair(-45, -90),
        Pair(-90, -90),
        Pair(90, 45),
        Pair(90, 0),
        Pair(90, -45)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_alphabet_slide_page, container, false)
        val chars = view.findViewById<TextView>(R.id.characters)
        val stickmanView = view.findViewById<StickmanView>(R.id.stickman)
        stickmanView.angleLeft = armAngles[characterIndex].second.toFloat() - 180
        stickmanView.angleRight = armAngles[characterIndex].first.toFloat()
        chars.text = (characterIndex + 65).toChar().toString()
        return view
    }
}

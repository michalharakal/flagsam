package de.harakal.flaggie.android.pipeline

import android.widget.TextView
import de.harakal.flaggie.pipeline.Step

// Just print char and forward input to output
class TextViewLetterPrinter(val textView: TextView) : Step<Char?, Char?> {

    override fun process(input: Char?): Char? {
        input?.let {
            textView.append(it.toString())
        }
        return input
    }
}

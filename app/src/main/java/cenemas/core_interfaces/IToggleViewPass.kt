package cenemas.core_interfaces

import android.widget.EditText
import android.widget.TextView
import cenemas.core_enums.sign_up_field_enum

data class IToggleViewPass(val fieldToggle: sign_up_field_enum, val toggleBtnView: TextView, val valueView: EditText) {
}
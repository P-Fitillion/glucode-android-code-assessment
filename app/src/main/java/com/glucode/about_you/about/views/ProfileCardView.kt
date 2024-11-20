package com.glucode.about_you.about.views

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewProfileCardBinding
import com.glucode.about_you.engineers.models.Engineer

class ProfileCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {
    private val binding: ViewProfileCardBinding =
        ViewProfileCardBinding.inflate(LayoutInflater.from(context), this)

    var engineer: Engineer? = null
        set(engineer) {
            field = engineer
            binding.name.text = engineer?.name
            binding.role.text = engineer?.role
            binding.years.text = engineer?.quickStats?.years?.toString(10)
            binding.coffees.text = engineer?.quickStats?.coffees?.toString(10)
            binding.bugs.text = engineer?.quickStats?.bugs?.toString(10)
            handleImage(engineer)
        }

    private fun handleImage(engineer: Engineer?) {
        if (engineer?.defaultImageName != null && engineer.defaultImageName.isNotEmpty()) {
            binding.profileImage.setImageURI(Uri.parse(engineer.defaultImageName))
        }
    }

    fun registerImageClickListener(listener: OnClickListener) {
        binding.profileImage.setOnClickListener(listener)
    }

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        elevation = resources.getDimension(R.dimen.elevation_normal)
    }
}

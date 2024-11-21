package com.glucode.about_you.engineers

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glucode.about_you.data.EngineersDataService
import com.glucode.about_you.data.SortingCriterion
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private val onClick: (Engineer) -> Unit
) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(ItemEngineerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], onClick)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(engineer: Engineer, onClick: (Engineer) -> Unit) {
            binding.name.text = engineer.name
            binding.role.text = engineer.role
            binding.root.setOnClickListener {
                onClick(engineer)
            }

            if (engineer.defaultImageName.isNotEmpty()) {
                binding.profileImage.setImageURI(Uri.parse(engineer.defaultImageName))
            }

            binding.quickStat.text = getStatsText(engineer)
        }

        fun getStatsText(engineer: Engineer): String {
            val currentCriterion = EngineersDataService.currentSortingCriterion
            if (currentCriterion != null) {
                return currentCriterion.let { criterion ->
                    when (currentCriterion) {
                        SortingCriterion.YEARS.name -> "Years: ${engineer.quickStats.years}"
                        SortingCriterion.COFFEES.name -> "Coffees: ${engineer.quickStats.coffees}"
                        SortingCriterion.BUGS.name -> "Bugs: ${engineer.quickStats.bugs}"
                        else -> {"Invalid state"}
                    }.toString()
                }
            }
            return ""
        }
    }
}
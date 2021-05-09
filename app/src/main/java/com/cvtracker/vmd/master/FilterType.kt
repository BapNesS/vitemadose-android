package com.cvtracker.vmd.master

import com.cvtracker.vmd.data.DisplayItem

class FilterType {

    companion object {

        fun getDefault() = mutableListOf(appointmentFilterType)

        const val FILTER_APPOINTMENT = "FILTER_APPOINTMENT"
        const val FILTER_VACCINE_TYPE = "FILTER_VACCINE_TYPE"

        val appointmentFilterType = FilterSection(
            id = FILTER_APPOINTMENT,
            displayTitle = "Créneaux",
            defaultState = false,
            filters = listOf(
                Filter("Chronodoses uniquement", false) {
                    it.isChronodose
                },
                Filter("Centres disponibles uniquement", false) {
                    it.available
                }
            ))
    }

    class FilterSection(
        val id: String,
        val displayTitle: String,
        val defaultState: Boolean,
        val filters: List<Filter>
    ){
        override fun toString(): String {
            return displayTitle + " ==> " + filters.joinToString(separator = ",") { it.displayTitle + "/" + it.enabled }
        }
    }

    class Filter(
        val displayTitle: String,
        var enabled: Boolean,
        val predicate: (DisplayItem.Center) -> Boolean
    )
}
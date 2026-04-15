package d31ruv.pulse.sutra.core.data.chant.model

data class ChantDashboardState(
    val sanctuaryTitle: String,
    val mantraLabel: String,
    val mantraName: String,
    val currentCount: Int,
    val targetCount: Int,
    val primaryActionLabel: String,
    val manualActionLabel: String,
)

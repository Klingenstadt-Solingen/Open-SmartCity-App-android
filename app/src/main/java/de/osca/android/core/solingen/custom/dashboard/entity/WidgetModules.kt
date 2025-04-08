package de.osca.android.core.solingen.custom.dashboard.entity

enum class WidgetModules(val text: String, val skippable: Boolean) {
    JOB(text = "JOB", skippable = false),
    EVENT(text = "EVENT", skippable = false),
    WEATHER(text = "WEATHER", skippable = false),
    WASTE_A(text = "WASTE_A", skippable = true),
    WASTE_B(text = "WASTE_B", skippable = false),
    PRESS_RELEASE(text = "PRESS_RELEASE", skippable = false),
    MAP(text = "MAP", skippable = false),
    SERVICE(text = "SERVICE", skippable = false),
    TOWNHALL(text = "TOWNHALL", skippable = false),
    DISTRICT(text = "DISTRICT", skippable = false),
    ENVIRONMENT(text = "ENVIRONMENT", skippable = false),
}

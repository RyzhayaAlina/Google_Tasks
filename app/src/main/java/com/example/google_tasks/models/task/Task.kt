package com.example.google_tasks.models.task

import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var additInfo: String,
    var isCompleted: Boolean = false,
    var isChosen: Boolean
)
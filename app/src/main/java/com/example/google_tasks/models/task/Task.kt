package com.example.google_tasks.models.task

import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val additInfo: String,
    val isCompleted: Boolean = false,
    val isChosen: Boolean
)
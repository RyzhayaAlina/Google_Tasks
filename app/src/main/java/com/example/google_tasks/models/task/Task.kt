package com.example.google_tasks.models.task

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.material.textfield.TextInputLayout.EndIconMode
import java.util.UUID
@Entity
data class Task(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    var name: String,
    var additInfo: String,
    var isCompleted: Boolean = false,
    var isChosen: Boolean
)
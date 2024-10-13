package com.test.ganggod.network.profile

import kotlinx.serialization.Serializable

@Serializable
data class AiModelsRequest(
    val user_id: String
)

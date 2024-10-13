package com.test.ganggod.constData

import com.test.ganggod.R

object ModelList {
    val items = listOf(
        "GPT-4o",
        "Kimi",
        "GPT-4o-mini",
        "Claude-3.5-sonnet",
        "Claude-3-haiku",
        "GPT-4o-all",
        "AskInternet",
    )

    val modelStore = listOf(
        "GPT-4",
        "Dalle-E-2",
        "Dalle-E-3",
    )

    val modelStoreMap = mapOf(
        "GPT-4o" to "gpt-4o",
        "GPT-4o-mini" to "gpt-4o-mini",
        "GPT-4o-all" to "gpt-4o-all",
        "GPT-4-turbo" to "gpt-4-turbo-preview",
        "GPT-3.5-turbo" to "gpt-3.5-turbo",
        "GPT-4-all" to "gpt-4-all",
        "GPT-4-vision" to "gpt-4-vision-preview",
        "GPT-4-origin" to "gpt-4-origin",
        "Claude-3.5-sonnet" to "claude-3.5-sonnet",
        "Claude-3-haiku" to "claude-3-haiku",
        "Claude-3-opus" to "claude-3-opus",
        "Doubao-pro" to "doubao-pro-32k",
        "DeepSeek-coder" to "deepseek-coder",
        "DeepSeek" to "deepseek-chat",
        "Kimi" to "kimi",
        "AskInternet" to "gpt-ask-internet",
        "Llama3-70b" to "llama3-70b",
        "Gemini" to "gemini",
        "Codellama" to "codellama"
    )

    val describeMap = mapOf(
        "GPT-4" to "OpenAI最新发布的强大模型。在定量问题（数学和物理）、创意写作以及许多其他挑战性任务中，表现比ChatGPT更强。速度更快。强烈建议首选。",
        "Dalle-E-2" to "OpenAI发布的画图模型。可以生成高分辨率的图像，适合用于创意设计、插画、动画等领域。",
        "Dalle-E-3" to "OpenAI发布的画图模型。可以生成高分辨率的图像，适合用于创意设计、插画、动画等领域。",
    )

    val modelPictureMap = mapOf(
        "GPT-4" to R.drawable.gpt4,
        "Dalle-E-2" to R.drawable.gpt4_vision,
        "Dalle-E-3" to R.drawable.gpt4_vision
    )
}
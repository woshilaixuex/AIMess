package com.test.ganggod.network.profile

import kotlinx.serialization.Serializable

//{
//    "code": "200",
//    "msg": "查询成功",
//    "data": {
//        "ai_models": {
//            "AIInfos": [
//                {
//                    "usage_id": 7,
//                    "user_id": "000000000006",
//                    "model_id": 1,
//                    "model_name": "gpt-4",
//                    "query_count": 38
//                },
//                {
//                    "usage_id": 8,
//                    "user_id": "000000000006",
//                    "model_id": 2,
//                    "model_name": "dall-e-2",
//                    "query_count": 60
//                },
//                {
//                    "usage_id": 9,
//                    "user_id": "000000000006",
//                    "model_id": 3,
//                    "model_name": "dall-e-3",
//                    "query_count": 60
//                }
//            ]
//        }
//    }
//}
@Serializable
data class AiModelsResponse(
    val code: Int,
    val msg: String,
    val data: AiModelsResponseData
){
    @Serializable
    data class AiModelsResponseData(
        val ai_infos: List<aIInfos>
    ){
            @Serializable
            data class aIInfos(
                val usage_id: Int,
                val user_id: String,
                val model_id: Int,
                val model_name: String,
                val query_count: Int
            )
    }
}


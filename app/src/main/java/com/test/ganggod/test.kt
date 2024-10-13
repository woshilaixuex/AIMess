package com.test.ganggod

import okhttp3.OkHttpClient
import okhttp3.Request
import kotlinx.coroutines.*
import okhttp3.logging.HttpLoggingInterceptor


fun main() = runBlocking {
    val client = OkHttpClient().newBuilder().addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()

    val request: Request = Request.Builder()
        .url("wss://c.datapipe.top/api/chat")
        .build()

    val response = client.newCall(request).execute()

}
//
//
//
//
//fun main() {
//    val jsonString = """
//        {
//            "token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjI3NDc2NTMsInBhc3N3b3JkIjoiOGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5MiIsInVzZXJuYW1lIjoia290YWhpIn0.tOENNjApIRm8Vlo4HW24o_yL-cGNDqmLeYD3ogHvQQE",
//            "id": 27,
//            "type": "chat",
//            "message": "你可以做什么",
//            "web": false,
//            "model": "gpt-3.5-turbo",
//            "context": 3,
//            "auto_use_coin": true,
//            "ignore_context": false,
//            "max_tokens": 2000,
//            "temperature": 0.6,
//            "top_p": 1.0,
//            "top_k": 5,
//            "presence_penalty": 0.0,
//            "frequency_penalty": 0.0,
//            "repetition_penalty": 1.0
//        }
//    """
//    val chatMessage = Json.decodeFromString<ChatMessage>(jsonString)
//
//    if (chatMessage.type == "chat") {
//        println("这是一个对话消息，内容是: ${chatMessage.message}")
//    } else {
//        println("这不是一个对话消息")
//    }
//}
////{"type":"chat","message":"我刚刚说了什么吗","web":false,"model":"gpt-3.5-turbo","context":3,"auto_use_coin":true,"ignore_context":false,"max_tokens":2000,"temperature":0.6,"top_p":1,"top_k":5,"presence_penalty":0,"frequency_penalty":0,"repetition_penalty":1}
////{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjI3NDc2NTMsInBhc3N3b3JkIjoiOGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5MiIsInVzZXJuYW1lIjoia290YWhpIn0.tOENNjApIRm8Vlo4HW24o_yL-cGNDqmLeYD3ogHvQQE","id":-1}
////{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjI3NDc2NTMsInBhc3N3b3JkIjoiOGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5MiIsInVzZXJuYW1lIjoia290YWhpIn0.tOENNjApIRm8Vlo4HW24o_yL-cGNDqmLeYD3ogHvQQE","id":27}
////{"type":"chat","message":"霓虹","web":false,"model":"gpt-3.5-turbo","context":3,"auto_use_coin":true,"ignore_context":false,"max_tokens":2000,"temperature":0.6,"top_p":1,"top_k":5,"presence_penalty":0,"frequency_penalty":0,"repetition_penalty":1}
////{"type":"chat","message":"//{\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjI3NDc2NTMsInBhc3N3b3JkIjoiOGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5MiIsInVzZXJuYW1lIjoia290YWhpIn0.tOENNjApIRm8Vlo4HW24o_yL-cGNDqmLeYD3ogHvQQE\",\"id\":27}\n//{\"type\":\"chat\",\"message\":\"你可以做什么\",\"web\":false,\"model\":\"gpt-3.5-turbo\",\"context\":3,\"auto_use_coin\":true,\"ignore_context\":false,\"max_tokens\":2000,\"temperature\":0.6,\"top_p\":1,\"top_k\":5,\"presence_penalty\":0,\"frequency_penalty\":0,\"repetition_penalty\":1}\n//{\"token\":\"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjI3NDc2NTMsInBhc3N3b3JkIjoiOGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5MiIsInVzZXJuYW1lIjoia290YWhpIn0.tOENNjApIRm8Vlo4HW24o_yL-cGNDqmLeYD3ogHvQQE\",\"id\":-1}\n//{\"type\":\"chat\",\"message\":\"你好\",\"web\":false,\"model\":\"gpt-3.5-turbo\",\"context\":3,\"auto_use_coin\":true,\"ignore_context\":false,\"max_tokens\":2000,\"temperature\":0.6,\"top_p\":1,\"top_k\":5,\"presence_penalty\":0,\"frequency_penalty\":0,\"repetition_penalty\":1}你可以找出这个是怎么分辨出是一段对话里面的标识吗","web":false,"model":"gpt-4o","context":3,"auto_use_coin":true,"ignore_context":false,"max_tokens":2000,"temperature":0.6,"top_p":1,"top_k":5,"presence_penalty":0,"frequency_penalty":0,"repetition_penalty":1}
////{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjI3NDc2NTMsInBhc3N3b3JkIjoiOGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5MiIsInVzZXJuYW1lIjoia290YWhpIn0.tOENNjApIRm8Vlo4HW24o_yL-cGNDqmLeYD3ogHvQQE","id":27}
////{"type":"chat","message":"你可以做什么","web":false,"model":"gpt-3.5-turbo","context":3,"auto_use_coin":true,"ignore_context":false,"max_tokens":2000,"temperature":0.6,"top_p":1,"top_k":5,"presence_penalty":0,"frequency_penalty":0,"repetition_penalty":1}
////{"token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MjI3NDc2NTMsInBhc3N3b3JkIjoiOGQ5NjllZWY2ZWNhZDNjMjlhM2E2MjkyODBlNjg2Y2YwYzNmNWQ1YTg2YWZmM2NhMTIwMjBjOTIzYWRjNmM5MiIsInVzZXJuYW1lIjoia290YWhpIn0.tOENNjApIRm8Vlo4HW24o_yL-cGNDqmLeYD3ogHvQQE","id":-1}
////{"type":"chat","message":"你好","web":false,"model":"gpt-3.5-turbo","context":3,"auto_use_coin":true,"ignore_context":false,"max_tokens":2000,"temperature":0.6,"top_p":1,"top_k":5,"presence_penalty":0,"frequency_penalty":0,"repetition_penalty":1}

//{"type":"chat","message":"```file\n[[image.png]]\nhttps://s3.datapipe.top/chatnio/20240715/9Zin96/image.png\n```\n\n这张图片你会吗","web":false,"model":"gpt-3.5-turbo","context":3,"auto_use_coin":true,"ignore_context":false,"max_tokens":2000,"temperature":0.6,"top_p":1,"top_k":5,"presence_penalty":0,"frequency_penalty":0,"repetition_penalty":1}


//选择排序（Selection Sort）是一种简单直观的排序算法，它的基本思想是每次从待排序的数据元素中选出最小（或最大）的一个元素，放在序列的起始位置，直到全部待排序的数据元素排完为止。下面是一个简单的选择排序的C++实现示例：
//
//```cpp
//#include <iostream>
//
//void selectionSort(int arr[], int n) {
//    for (int i = 0; i < n - 1; i++) {
//        int minIndex = i;
//        for (int j = i + 1; j < n; j++) {
//            if (arr[j] < arr[minIndex]) {
//                minIndex = j;
//            }
//        }
//        if (minIndex != i) {
//            std::swap(arr[i], arr[minIndex]);
//        }
//    }
//}
//
//int main() {
//    int arr[] = {64, 25, 12, 22, 11};
//    int n = sizeof(arr) / sizeof(arr[0]);
//
//    selectionSort(arr, n);
//
//    std::cout << "Sorted array: ";
//    for (int i = 0; i < n; i++) {
//        std::cout << arr[i] << " ";
//    }
//    std::cout << std::endl;
//
//    return 0;
//}
//```
//
//在这个示例中，`selectionSort`函数实现了选择排序算法，`main`函数中创建了一个整数数组`arr`，然后调用`selectionSort`函数对数组进行排序，并输出排序后的结果。你可以将这段代码复制粘贴到你的C++编译器中运行，以查看选择排序的实际效果。如果你有任何关于选择排序或代码示例的进一步问题，请随时提出。

//{"type":"chat","message":"```file\n[[nginx.txt]]\nserver {\r\n listen 80;\r\n client_max_body_size 20M;\r\n server_name localhost;\r\n\r\n location / {\r\n proxy_pass http://localhost:8080;\r\n proxy_set_header Host $host;\r\n proxy_set_header X-Real-IP $remote_addr;\r\n proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;\r\n proxy_set_header X-Forwarded-Proto $scheme;\r\n }\r\n}\n```\n\n这是什么","web":false,"model":"gpt-4o","context":3,"auto_use_coin":true,"ignore_context":false,"max_tokens":3000,"temperature":0.6,"top_p":1,"top_k":5,"presence_penalty":0,"frequency_penalty":0,"repetition_penalty":1}
package com.test.ganggod

//fun main() {
//    runBlocking {
//        withContext(Dispatchers.IO){
//            try {
//                val file = File("C:\\Users\\liuyi\\Desktop\\新建文本文档.txt")
//                if (!file.exists()) {
//                    throw Exception("File not found: ${file.absolutePath}")
//                }
//
//                val fileExtension = MimeTypeMap.getFileExtensionFromUrl(file.absolutePath)
//                val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)
//                    ?: throw Exception("Unable to determine MIME type for file: ${file.absolutePath}")
//
//                val requestFile: RequestBody = file.asRequestBody(mimeType.toMediaTypeOrNull())
//                val body: MultipartBody.Part = MultipartBody.Part.createFormData("file", file.name, requestFile)
//                val okHttpClient = OkHttpClient.Builder()
//                    .connectTimeout(3000L, TimeUnit.MILLISECONDS)
//                    .writeTimeout(10, TimeUnit.SECONDS)
//                    .retryOnConnectionFailure(true)
//                    .addInterceptor(interceptor = AppModule.loggingInterceptor)
//                    .build()
//                val retrofit = Retrofit.Builder()
//                    .client(okHttpClient)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(FlowCallAdapterFactory.create())
//                    .build()
//                val uploadFileService:UploadFileService = retrofit.create(UploadFileService::class.java)
//                val uploadResponse = uploadFileService.uploadFile(Token.TOKEN, body)
//                println("uploadResponse = ${uploadResponse.status}")
//            }catch (e:Exception){
//                println(e)
//            }
//
//        }
//        delay(5000L)
//    }
//}
fun main()
{
    val s = mutableListOf(1)
    s.add(5,2)
    println(s)
}
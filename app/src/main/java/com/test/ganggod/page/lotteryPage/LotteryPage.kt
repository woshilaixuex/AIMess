package com.test.ganggod.page.lotteryPage

import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.test.ganggod.R
import com.test.ganggod.ui.theme.LightModeColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.pow

@Composable
fun PieChartWithText() {
    val giftList = listOf(
        "全部模型1000次使用次数",
        "增加100次使用",
        "增加10次dall-e-3画图模型",
        "增加10次dall-e-2画图模型",
        "增加10次gpt-4对话模型",
        "全部20次使用",
        "全部10次使用",
        "全部5次使用",
        "随机积分"
    )

}

@Composable
fun LotteryPage(lotteryViewModel: LotteryViewModel = hiltViewModel()){
    val showDialog by lotteryViewModel.showDialog.collectAsState()
    val isRolling by lotteryViewModel.isRolling.collectAsState()
    val lotteryResult by lotteryViewModel.lotteryResult.collectAsState()

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { lotteryViewModel.hideDialog() },
            title = { Text(text = "抽奖结果") },
            text = { Text(text = lotteryResult) },
            confirmButton = {
                Button(
                    onClick = { lotteryViewModel.hideDialog() },
                ) {
                    Text(text = "OK")
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightModeColor.BackGroundColor)
            .statusBarsPadding()
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp)){
            Column (modifier = Modifier.align(Alignment.Center)){
                if (isRolling == 0){
                    Image(painterResource(R.drawable.box), contentDescription = "", modifier = Modifier.size(200.dp).padding(16.dp).align(Alignment.CenterHorizontally))
                }else if(isRolling == 1){
                    GifImage(modifier = Modifier.size(200.dp).padding(16.dp).align(Alignment.CenterHorizontally), imageId = R.drawable.treasure)
                }
                if (isRolling == 3){
                    Image(painterResource(R.drawable.lottery), contentDescription = "", modifier = Modifier.size(200.dp).padding(16.dp).align(Alignment.CenterHorizontally))
                }
                Button(onClick = {lotteryViewModel.startLottery()}, modifier = Modifier.fillMaxWidth()) {
                    Text(text = if (isRolling == 1) "正在抽奖" else "抽奖")
                }
            }
        }
    }
}

@Composable
fun GifImage(modifier: Modifier, imageId : Int) {
    //自己构建图片加载器
    val imageLoader = ImageLoader.Builder(LocalContext.current).components {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()
    Image(
        modifier = modifier,
        painter = //淡出效果
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = imageId)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)//淡出效果
                }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
    )
}


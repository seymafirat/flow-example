package com.example.composeflowexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeflowexample.ui.theme.ComposeFlowExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeFlowExampleTheme {
                val viewModel: MyViewModel by viewModels()
                //CountDownTimerScreen(viewModel = viewModel)
                ComparisonScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun CountDownTimerScreen(viewModel: MyViewModel) {
    val counter = viewModel.countDownTimerFlow.collectAsState(initial = 10)
    Surface(color = MaterialTheme.colors.background) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = counter.value.toString(),
                fontSize = 26.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun ComparisonScreen(viewModel: MyViewModel) {
    val liveDataValue = viewModel.liveData.observeAsState()
    val stateFlowValue = viewModel.stateFlow.collectAsState()
    val sharedFlowValue = viewModel.sharedFlow.collectAsState(initial = "")

    Surface(color = MaterialTheme.colors.background) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.align(Alignment.Center)) {

                Text(
                    text = liveDataValue.value ?: "",
                    fontSize = 26.sp
                )
                Button(onClick = {
                    viewModel.changeLiveDataValue()
                }) {
                    Text(text = "Livedata Button")
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = stateFlowValue.value ?: "",
                    fontSize = 26.sp
                )
                Button(onClick = {
                    viewModel.changeStateFlowValue()
                }) {
                    Text(text = "Stateflow Button")
                }
                Spacer(modifier = Modifier.padding(10.dp))

                Text(
                    text = sharedFlowValue.value ?: "",
                    fontSize = 26.sp
                )
                Button(onClick = {
                    viewModel.changeSharedFlowValue()
                }) {
                    Text(text = "Sharedflow Button")
                }
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

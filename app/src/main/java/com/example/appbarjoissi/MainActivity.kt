package com.example.appbarjoissi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

//
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Screen()
        }

    }
}


//
@Composable
fun Screen() {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "*Tarefas*",
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 62.dp),
                        textAlign = TextAlign.Center,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        CoroutineScope(Dispatchers.Default).launch{
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },

                backgroundColor = Color(233, 30, 99, 255),
            )


        },
//DrawerContent
        drawerContent = {
            Box(
                modifier = Modifier
                    .background(Color.Magenta)
                    .height(16.dp)
            ){
                Text(text = "Opções")
            }
            Column(){
                Text(text = "Opção 1")
            }
        },


//começo content
        content = {
                paddingValues -> Log.i("paddingValues","$paddingValues")
            Column(
                modifier = Modifier
                    .background(Color(241, 176, 241, 255))
                    .fillMaxSize()

            ) {
                Search(modificador = Modifier
                    .fillMaxWidth()
                    .background(Color(241, 176, 241, 255))
                )
                Widget(modificador = Modifier.fillMaxWidth(),
                    taskname = "Terapia",
                    taskdetail = "Para esquecer que eu sou a mãe",
                    deadEndDate = Date(System.currentTimeMillis())
                )
                Widget(modificador = Modifier.fillMaxWidth(),
                    taskname = "Psiquiatra",
                    taskdetail = "Para entender o porque de me considerar louca ",
                    deadEndDate = Date(System.currentTimeMillis())
                )


            }

        }, //fim content

        bottomBar = {
            BottomAppBar(

                content = {
                    Text(
                        text = "*Joissi*",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,


                        )
                },

                backgroundColor = Color(233, 30, 99, 255),
            )
        }


    ) //scaffold
}

@Composable
fun Search(modificador : Modifier){
    TextField(
        value = "", onValueChange = {},
        modifier = modificador,
        placeholder = { Text(text = "")},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon")
        }
    )
}

@Composable
fun Widget(modificador: Modifier,
           taskname: String,
           taskdetail: String,
           deadEndDate: Date
)

{
    val dateFormatter = SimpleDateFormat("EEE, MM dd, yyyy", Locale.getDefault())
    Row(modifier = modificador.padding(top = 15.dp)) {
        Icon(imageVector = Icons.Default.Notifications,
            contentDescription = "Icone.",
            modifier = Modifier
                .padding(end = 7.dp)
                .padding(top = 12.dp)
        )
        Text(
            text = dateFormatter.format(deadEndDate),
            modifier = Modifier
                .padding(end = 16.dp)
                .padding(top = 13.dp)

        )

        Column(modifier = modificador
            .border(1.dp, Color.Black)
            .padding(5.dp)


        ) {
            Text(text = taskname,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic

            )
            Text(text = taskdetail,
                fontSize = 10.sp,
                fontStyle = FontStyle.Italic

            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Screen()
}
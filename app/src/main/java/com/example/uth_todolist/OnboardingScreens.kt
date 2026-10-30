package com.example.uth_todolist

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uth_todolist.ui.theme.UTH_TodoListTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Onboarding.route) { OnboardingScreen(navController) }
        composable(Screen.Home.route) {}
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000L)
        navController.popBackStack()
        navController.navigate(Screen.Onboarding.route)
    }
    Column(
        modifier = Modifier.fillMaxSize().background(PrimaryBlue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        //icon
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_uth_box),
                contentDescription = null,
                tint = PrimaryBlue,
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(Modifier.height(16.dp))
        Text("UTH Todo", color = BackgroundWhite, fontSize = 35.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Việc học, việc nhà - gọn gàng mỗi ngày", color = BackgroundWhite, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text("Phiên bản 2.1.0", color = BackgroundWhite, fontSize = 12.sp)
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(navController: NavController) {
    val pages = OnboardingRepository.getPages()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize().background(BackgroundWhite)) {
        //header
        Row(
            modifier = Modifier.fillMaxWidth().padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(PrimaryBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_uth_box),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))

                Row {
                    Text("UTH ", fontWeight = FontWeight.Bold, color = TextDark, fontSize = 18.sp)
                    Text("Todo", fontWeight = FontWeight.Bold, color = PrimaryBlue, fontSize = 18.sp)
                }
            }

            //bo qua
            Text(
                text = "Bỏ qua",
                color = TextGray,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        //paper
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { position ->
            val page = pages[position]
            Column(
                modifier = Modifier.fillMaxSize().padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(50.dp)
                        .height(300.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color(0xFFF3F5F9)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(page.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    )
                }
                Spacer(Modifier.height(40.dp))
                Text(
                    text = page.title,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = page.description,
                    fontSize = 16.sp,
                    color = TextGray,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        //footer goi cac components
        Column(modifier = Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            UthPageIndicator(pageCount = pages.size, currentPage = pagerState.currentPage)
            UthPrimaryButton(
                text = if (pagerState.currentPage == pages.size - 1) "Bắt đầu ngay" else "Tiếp tục",
                onClick = {
                    if (pagerState.currentPage == pages.size - 1) {
                        navController.navigate(Screen.Home.route) { popUpTo(Screen.Onboarding.route) { inclusive = true } }
                    } else {
                        coroutineScope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    UTH_TodoListTheme {
        OnboardingScreen(navController = rememberNavController())
    }
}
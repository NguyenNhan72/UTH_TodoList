package com.example.uth_todolist

object OnboardingRepository {
    fun getPages(): List<OnboardingPage> = listOf(
        OnboardingPage(
            imageRes = R.drawable.ic_onboarding_1,
            title = "Mọi việc, gọn trong một chỗ",
            description = "Ghi lại bài tập, việc nhà, lịch hẹn. Không còn quên việc nào."
        ),
        OnboardingPage(
            imageRes = R.drawable.ic_onboarding_2,
            title = "Chia theo Học tập và Cuộc sống",
            description = "Mỗi việc có nhóm riêng, nhìn là biết hôm nay cần làm gì."
        ),
        OnboardingPage(
            imageRes = R.drawable.ic_onboarding_3,
            title = "Nhắc đúng lúc",
            description = "Bật thông báo để UTH Todo nhắc bạn trước hạn chót."
        )
    )
}
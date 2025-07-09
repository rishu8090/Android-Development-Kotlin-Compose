package com.example.week9.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoContainerAnimation() =
    slideIntoContainer(
        animationSpec = tween(
            durationMillis = 20,
            easing = EaseIn
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.End
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOfContainerAnimation() =
    slideOutOfContainer(
        animationSpec = tween(
            durationMillis = 20,
            easing = EaseOut
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.Start
    )
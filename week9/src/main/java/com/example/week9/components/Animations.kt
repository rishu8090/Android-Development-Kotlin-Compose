package com.example.week9.components

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideIntoContainerAnimation(
    towards: SlideDirection = SlideDirection.End
) =
    slideIntoContainer(
        animationSpec = tween(
            durationMillis = 400,
            easing = EaseIn
        ),
        towards = towards
    )

fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutOfContainerAnimation(
    towards: SlideDirection = SlideDirection.Start
) =
    slideOutOfContainer(
        animationSpec = tween(
            durationMillis = 400,
            easing = EaseOut
        ),
        towards = towards
    )
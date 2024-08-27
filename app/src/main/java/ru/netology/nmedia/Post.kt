package ru.netology.nmedia

import kotlin.math.min
import kotlin.random.Random

data class Post(
    val id: Int,
    val title: String,
    val subtitle: String,
    val content: String,
    var likes: Int,
    var share: Int,
    var view: Int,
    var likeByMe: Boolean
)

val RANDOM_KF = 1_000

val post = Post(
    id = 1,
    title = "Нетология. Университет интернет-профессий будущего",
    subtitle = "21 мая в 18:36",
    content = " Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
    likes = Random.nextInt(RANDOM_KF),
    share = min(990,Random.nextInt(RANDOM_KF * 2)),
    view = Random.nextInt(RANDOM_KF * RANDOM_KF),
    likeByMe = false
)
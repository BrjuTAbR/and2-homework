package ru.netology.nmedia

import kotlin.math.min
import kotlin.random.Random

const val RANDOM_KF = 1_000
const val LIKE_KF = 3
const val SHARE_KF = LIKE_KF * 4

var idx: Int = 9

var currentNum = Random.nextInt(RANDOM_KF * idx)

val postViewNumArray = Array(idx) { i ->
    if (i > 0) {
        currentNum = min(currentNum, Random.nextInt(RANDOM_KF * (idx - i)))
    }
    currentNum
}


var posts = listOf(
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Освоение новой профессии — это не только открывающиеся возможности и перспективы, но и настоящий вызов самому себе. Приходится выходить из зоны комфорта и перестраивать привычный образ жизни: менять распорядок дня, искать время для занятий, быть готовым к возможным неудачам в начале пути. В блоге рассказали, как избежать стресса на курсах профпереподготовки → http://netolo.gy/fPD",
        subtitle = "23 сентября в 10:12",
        view = postViewNumArray[--idx],
        likes = 9_999,
        share = 999,
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Делиться впечатлениями о любимых фильмах легко, а что если рассказать так, чтобы все заскучали \uD83D\uDE34\n",
        subtitle = "22 сентября в 10:14",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Таймбоксинг — отличный способ навести порядок в своём календаре и разобраться с делами, которые долго откладывали на потом. Его главный принцип — на каждое дело заранее выделяется определённый отрезок времени. В это время вы работаете только над одной задачей, не переключаясь на другие. Собрали советы, которые помогут внедрить таймбоксинг \uD83D\uDC47\uD83C\uDFFB",
        subtitle = "22 сентября в 10:12",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "\uD83D\uDE80 24 сентября стартует новый поток бесплатного курса «Диджитал-старт: первый шаг к востребованной профессии» — за две недели вы попробуете себя в разных профессиях и определите, что подходит именно вам → http://netolo.gy/fQ",
        subtitle = "21 сентября в 10:12",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Диджитал давно стал частью нашей жизни: мы общаемся в социальных сетях и мессенджерах, заказываем еду, такси и оплачиваем счета через приложения.",
        subtitle = "20 сентября в 10:14",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Большая афиша мероприятий осени: конференции, выставки и хакатоны для жителей Москвы, Ульяновска и Новосибирска \uD83D\uDE09",
        subtitle = "19 сентября в 14:12",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Языков программирования много, и выбрать какой-то один бывает нелегко. Собрали подборку статей, которая поможет вам начать, если вы остановили свой выбор на JavaScript.",
        subtitle = "19 сентября в 10:24",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Знаний хватит на всех: на следующей неделе разбираемся с разработкой мобильных приложений, учимся рассказывать истории и составлять PR-стратегию прямо на бесплатных занятиях \uD83D\uDC47",
        subtitle = "18 сентября в 10:12",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    ),
    Post(
        id = idx.toLong(),
        title = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        subtitle = "21 мая в 18:36",
        view = postViewNumArray[--idx],
        likes = Random.nextInt(postViewNumArray[idx] / LIKE_KF),
        share = Random.nextInt(postViewNumArray[idx] / SHARE_KF),
        likeByMe = false
    )
)

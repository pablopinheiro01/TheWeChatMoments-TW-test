import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.ui.model.Comment
import com.tws.moments.ui.model.Image
import com.tws.moments.ui.model.Sender
import com.tws.moments.ui.model.Tweet


val ERROR_DEFAULT = "error"

val userJsmith = UserBean(
    username = "hengzeng",
    nick = "Huan Huan",
    avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar.png",
    profileImage = "https://techops-recsys-lateral-hiring.github"
)

val listTweet = listOf(
    Tweet(
        sender = Sender(
            nick = "Cheng Yao", username = "cyao",
            avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/001.jpeg"
        ),
        content = "First post!",
        images = listOf(
            Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/001.jpeg"),
            Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/002.jpeg"),
            Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/003.jpeg")
        ),
        comments = listOf(
            Comment(
                content = "Good.", sender = Sender(
                    nick = "Lei Huang", username = "leihuang",
                    avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/002.jpeg"
                )
            ),
            Comment(
                content = "Like it too",
                sender = Sender(
                    nick = "WeiDong Gu",
                    username = "weidong",
                    avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/003.jpeg"
                )
            )
        ), error = null
    ),
    Tweet(
        sender = Sender(
            nick = "Xin Ge",
            username = "xinge",
            avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/000.jpeg"
        ),
        content = null, images = null, comments = null, error = null
    ),
    Tweet(
        sender =
        Sender(
            nick = "Yang Luo", username = "yangluo",
            avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/004.jpeg"
        ),
        content = null,
        images = listOf(Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/004.jpeg")),
        comments = null, error = null
    ),
    Tweet(
        sender =
        Sender(
            nick = "Jianing Zheng",
            username = "jianing",
            avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/005.jpeg"
        ),
        content = null,
        images = listOf(Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/005.jpeg")),
        comments = null,
        error = null
    ),
    Tweet(sender = null, content = null, images = null, comments = null, error = "losted"),
    Tweet(
        sender = Sender(
            nick = "Wei Fan",
            username = "weifan",
            avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/006.jpeg"
        ),
        content = "Unlike many proprietary big data processing platform different, Spark is built on a unified abstract RDD, making it possible to deal with different ways consistent with large data processing scenarios, including MapReduce, Streaming, SQL, Machine Learning and Graph so on. To understand the Spark, you have to understand the RDD.",
        images = listOf(Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/006.jpeg")),
        comments = null, error = null
    ),
    Tweet(
        sender = Sender(
            nick = "Xin Guo",
            username = "xinguo",
            avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/007.jpeg"
        ),
        content = "This is the first post on the second page .",
        images = listOf(
            Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/007.jpeg"),
            Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/008.jpeg"),
            Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/009.jpeg"),
            Image(url = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/tweets/010.jpeg")
        ),
        comments = listOf(
            Comment(
                content = "Good.", sender =
                Sender(
                    nick = "Yanzi Li",
                    username = "yanzili",
                    avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/008.jpeg"
                )
            ),
            Comment(
                content = "Like it too",
                sender = Sender(
                    nick = "Jing Zhao",
                    username = "jingzhao",
                    avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/001.jpeg"
                )
            )
        ), error = null
    ),
    Tweet(
        sender = Sender(
            nick = "Niang Niang",
            username = "hengzeng",
            avatar = "https://techops-recsys-lateral-hiring.github.io/moments-data/images/user/avatar/000.jpeg"
        ),
        content = null, images = null, comments = null, error = null
    )
)
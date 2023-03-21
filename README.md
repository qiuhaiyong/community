# community

## 技术栈
* SpringBoot
* SpringMVC
* MyBatis
* SpringSecurity
* Spring Quarzt
* MySQL
* redis
* ElasticSearch
* Kafka
## 功能模块
* 登录注册
* 发帖评论，搜索帖子
使用ElasticSearch存储文章高效搜索符合内容的帖子，并且将关键词加高亮。
* 私信
* 点赞
使用Redis，将用户id存入以帖子id为key的list中，实现点赞和取消点赞。
* 网站数据统计
* 系统通知
使用kafka发送广播给所有用户。
等

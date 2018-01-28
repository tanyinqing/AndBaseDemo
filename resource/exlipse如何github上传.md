
<h1 id="20171228_1">exlipse如何github上传</h1>

- https://tanyinqing.gitbooks.io/xiangmujingyan/content/pei_zhi_eclipse_de_ban_ben_kong_zhi.html
介绍eclipse的github上传
- 先在github上建立一个库
- 把https的地址配置到url = "" 配置文件中
- 建立一个库
	- 项目右键team-share Project 
	- 打钩 use or create repository in parent folder of project
	- 项目打钩  点击 create repository
	- finish
	- 这样就在文件根目录下建立了一个.git文件夹
- 推送到本地 pull
- 提交到库并保存 commit and push  全选
- 把github的配置修改成stuio软件的配置

```
studio配置
[user]
  name = tg
  email = 2@qq.com

exlipse配置
[core]
 repositoryformatversion = 0
 filemode = false
 bare = false
 logallrefupdates = true
 symlinks = false
 ignorecase = true
 hideDotFiles = dotGitOnly
[remote "origin"]
 url = "" 

fetch = +refs/heads/*:refs/remotes/origin/*
[branch "master"]
 remote = origin
 merge = refs/heads/master
[user]
 name = tg
 email = 27@qq.com

```
**运用命令更新github代码库**

- UTF-8可以转化成GBK编码，但GBK转化成UTF-8会出现乱码。
- txt格式要用UTF-8格式，java代码要用java格式

- git 命令 **从远处克隆一个文件到本地**
- 1 必须先在本地建立一个空文件夹。
- git  clone  https://github.com/tanyinqing/Java-Big-Data-Engineer-Training.git   D:\tanyinqing\ceshi

- 1 首先本地下载一个库，然后进入本地.git所在的目录

- cd D:\TanYinQingJavaEEStudy\GeRenXiangMu\tanStaticWebPage

- git add data\txt\build_gradle.txt  添加单个文件
- git add 新建文本文档.txt
- git add data\   添加对文件夹的修改
- git add tanStaticWebPage\
- git add -A  表示提交所有文件

- 2 git commit -m “修改”
- 3 git push   到git服务器
- 4 更新线上代码到本地：git pull

- git --help  帮助文件

- 删除
- 1 git rm 新建文本文档.txt
- 2 git commit -m “修改”
- 3 git push   到git服务器

- 五、分支

- 列出所有本地分支
- $ git branch

- 列出所有远程分支
- $ git branch -r

- 显示有变更的文件
- $ git status

- C:\Program Files\Git  这个是git的安装目录
- C:\Program Files\Git\usr\libexec

- GIT_HOME
- %GIT_HOME%\bin

- 注：“path”中，每个路径之间要以英文输入状态下的分号——“;”作为间隔






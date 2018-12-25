## maven thrift plugin for windows

当在windows上进行thrift编译的时候，出现了“命令太长”的错误，这是windows系统限制所致。

在windows上，命令行参数的最大长度只能是32767，再大了就死。没有任何办法。

因为原来的plugin会把所有依赖jar包中的thrift文件都include进来，所以如果你的项目大量使用thrift

很可能出现这个问题。

注意：我只是在0.1.10的版本上进行了修改。因为我们的thrift暂时都是小于0.7的。。

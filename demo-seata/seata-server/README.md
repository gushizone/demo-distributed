# Seata-Server v1.4.2

- [seata｜releases](https://github.com/seata/seata/releases)
- [seataio/seata-server | dockerHub](https://hub.docker.com/r/seataio/seata-server)
- [seata-samples](https://github.com/seata/seata-samples/blob/master/doc/quick-integration-with-spring-cloud.md)

# 修改配置

下载release版本，并修改配置文件。

- https://github.com/seata/seata/releases
- registry.conf ：设置注册中心和配置中心。registry推荐使用SpringCloud的注册中心，配置中心测试直接使用file即可。
- file.conf ：配置seata-server事务日志持久化位置。推荐使用db方式。

# 启动

```shell
# 启动注册中心

# 启动 seata-server
./bin/seata-server.sh
```


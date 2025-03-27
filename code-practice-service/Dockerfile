# 基于 OpenJDK 8 运行时
FROM eclipse-temurin:8-jre
# 设置工作目录
WORKDIR /apps
# 复制本地的 jar 文件到容器中
COPY target/code-practice-service-1.0.0-SNAPSHOT.jar app.jar
# 暴露应用端口（根据你的配置调整，默认 8080）
EXPOSE 8092
# 启动命令
CMD ["java", "-jar", "app.jar"]
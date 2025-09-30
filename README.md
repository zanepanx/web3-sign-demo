# Web3 Sign Demo (Java, web3j)

一个使用 **web3j** 完成以太坊地址生成、消息签名与验签的最小示例。这类链上基础能力很适合展示 Developer 能力并用于 Legion DEV 分数。

## 功能
- 生成新的私钥与地址
- 对消息 `Hello Legion` 进行签名
- 使用公钥恢复地址以验证签名

## 运行
```bash
# 需要 JDK 17 + Maven
mvn -q -DskipTests package
java -cp target/web3-sign-demo-0.0.1-SNAPSHOT.jar com.example.web3sign.Web3SignDemo
```

运行后将打印：新地址、签名数据以及验签是否通过。

> 注意：示例仅做演示用途，不要把真实私钥放进代码仓库。

package com.example.web3sign;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.math.BigInteger;

public class Web3SignDemo {
    public static void main(String[] args) throws Exception {
        // 1. 生成新的私钥/公钥对
        ECKeyPair keyPair = Keys.createEcKeyPair(new SecureRandom());
        String address = "0x" + Keys.getAddress(keyPair.getPublicKey());
        System.out.println("Generated address: " + address);
        System.out.println("Private key (hex): 0x" + keyPair.getPrivateKey().toString(16));

        // 2. 待签名消息
        String message = "Hello Legion";
        byte[] messageHash = Sign.getEthereumMessageHash(message.getBytes(StandardCharsets.UTF_8));
        System.out.println("Message: " + message);
        System.out.println("Message hash: " + Numeric.toHexString(messageHash));

        // 3. 使用私钥签名
        Sign.SignatureData sig = Sign.signPrefixedMessage(message.getBytes(StandardCharsets.UTF_8), keyPair);
        String r = Numeric.toHexString(sig.getR());
        String s = Numeric.toHexString(sig.getS());
        String v = String.valueOf(sig.getV());
        System.out.println("Signature r: " + r);
        System.out.println("Signature s: " + s);
        System.out.println("Signature v: " + v);

        // 4. 通过签名恢复公钥/地址（验签）
        BigInteger pubKeyRecovered = Sign.signedPrefixedMessageToKey(
                message.getBytes(StandardCharsets.UTF_8), sig);
        String recoveredAddr = "0x" + Keys.getAddress(pubKeyRecovered);
        System.out.println("Recovered address: " + recoveredAddr);
        System.out.println("Verified: " + recoveredAddr.equalsIgnoreCase(address));
    }
}

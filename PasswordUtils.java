// PasswordUtils.java - 集中管理密码操作
package com.example.common;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PasswordUtils {

    private final PasswordEncoder passwordEncoder;

    public PasswordUtils(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 加密密码
     */
    public String encode(String rawPassword) {
        if (!StringUtils.hasText(rawPassword)) {
            throw new IllegalArgumentException("密码不能为空");
        }
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 验证密码
     */
    public boolean verify(String rawPassword, String encodedPassword) {
        if (!StringUtils.hasText(rawPassword) || !StringUtils.hasText(encodedPassword)) {
            return false;
        }
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 判断是否已加密（BCrypt格式检测）
     */
    public boolean isEncrypted(String password) {
        return password != null && password.startsWith("$2a$");
    }

    /**
     * 密码强度校验
     * 要求：8-20位，至少包含字母和数字
     */
    public boolean validateStrength(String password) {
        if (password == null || password.length() < 8 || password.length() > 20) {
            return false;
        }

        // 至少包含一个字母和一个数字
        boolean hasLetter = password.matches(".*[a-zA-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");

        return hasLetter && hasDigit;
    }

    /**
     * 生成随机密码（用于密码重置）
     */
    public String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }
}
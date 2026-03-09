// SecurityConfig.java - 更完整的配置
package com.example.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    /**
     * BCryptPasswordEncoder 配置
     * strength: 加密强度，范围4-31，默认10
     * 强度10表示2^10=1024次哈希迭代
     *
     * 强度建议：
     * - 开发环境：4-6（快速）
     * - 生产环境：10-12（平衡安全与性能）
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);  // 生产环境用10-12
    }

    /**
     * 可选：创建不同的编码器用于不同场景
     */
    @Bean(name = "strongPasswordEncoder")
    public PasswordEncoder strongPasswordEncoder() {
        return new BCryptPasswordEncoder(12);  // 更高安全要求
    }
}
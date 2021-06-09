package io.github.jojoti.grpcstatersb.autoconfigure;

import io.github.jojoti.grpcstatersb.GRpcScopeService;
import io.github.jojoti.grpcstatersb.GRpcServerRunner;
import io.grpc.services.HealthStatusManager;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author JoJo Wang
 * @link github.com/jojoti
 */
@AutoConfigureOrder
@ConditionalOnBean(annotation = {GRpcScopeService.class})
@EnableConfigurationProperties(GRpcServerProperties.class)
public class GRpcAutoConfiguration {

    @Bean
    public GRpcServerRunner grpcServerRunner(GRpcServerProperties gRpcServerProperties, HealthStatusManager healthStatusManager) {
        return new GRpcServerRunner(gRpcServerProperties, healthStatusManager);
    }

    @Bean
    public HealthStatusManager healthStatusManager() {
        return new HealthStatusManager();
    }

}
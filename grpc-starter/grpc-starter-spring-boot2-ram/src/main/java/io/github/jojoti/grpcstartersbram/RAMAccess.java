/*
 * Copyright 2021 JoJo Wang , homepage: https://github.com/jojoti/experiment-jvm.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.jojoti.grpcstartersbram;

import io.github.jojoti.grpcstartersb.GRpcScope;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;

import java.util.List;

/**
 * @author JoJo Wang
 * @link github.com/jojoti
 */
public interface RAMAccess {

    // 启动注册 ram 列表权限
    // 这个可能会执行多次
    default void onRegister(GRpcScope gRpcScope, List<MethodDescriptor<?, ?>> methods) {

    }

    /**
     * @param gRpcScope        标注的注解属于哪个模块
     * @param ramItem          注解标记的属性
     * @param methodDescriptor
     * @return
     */
    default boolean checkSession(GRpcScope gRpcScope, MethodDescriptor<?, ?> methodDescriptor, RAM ramItem, Metadata metadata) {
        // 后续可以根据 不同的 scope 选择不同的实现
        // 默认使用 内置 session 实现
        return !SessionInterceptor.USER_NTS.get().isAnonymous();
    }

    boolean access(GRpcScope gRpcScope, MethodDescriptor<?, ?> methodDescriptor, RAM ramItem, Metadata metadata);

}

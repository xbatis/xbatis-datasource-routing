/*
 *  Copyright (c) 2024-2025, Ai东 (abc-127@live.cn).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License").
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

package cn.xbatis.datasource.routing;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Callable;

/**
 * 数据源挂载
 */
public class DataSourceHolder {

    private static final ThreadLocal<Deque<String>> TL = new ThreadLocal<>();

    /**
     * 追加/切换数据源
     * 记住别忘记remove
     *
     * @param type 数据源分类
     */
    public static void add(String type) {
        Deque<String> list = TL.get();
        if (list == null) {
            list = new ArrayDeque<>();
            TL.set(list);
        }
        list.addLast(type);
    }

    /**
     * 获取当前数据源
     * @return 数据源分类
     */
    public static String getCurrent() {
        Deque<String> list = TL.get();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.getLast();
    }

    /**
     * 移除最近追加/切换的数据源
     */
    public static void remove() {
        Deque<String> deque = TL.get();
        if (deque != null) {
            deque.removeLast();
            if (deque.isEmpty()) {
                TL.remove();
            }
        }
    }

    /**
     * 在指定数据库下执行
     *
     * @param type     数据源分类
     * @param runnable 执行器
     */
    public static void execute(String type, Runnable runnable) {
        try {
            add(type);
            runnable.run();
        } finally {
            remove();
        }
    }

    /**
     * 在指定数据源下执行
     *
     * @param type     数据源分类
     * @param callable 执行器
     * @param <V>      返回值类型
     * @return callable 执行后的返回值
     */
    public static <V> V execute(String type, Callable<V> callable) {
        try {
            add(type);
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            remove();
        }
    }
}

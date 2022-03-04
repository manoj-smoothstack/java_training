/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.redis.test.condition;

import static org.junit.jupiter.api.extension.ConditionEvaluationResult.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

/**
 * {@link ExecutionCondition} for {@link EnabledOnRedisClusterCondition @EnabledOnRedisClusterAvailable}.
 *
 * @author Mark Paluch
 * @author Christoph Strobl
 * @see EnabledOnRedisClusterCondition
 */
class EnabledOnRedisClusterCondition implements ExecutionCondition {

	private static final ConditionEvaluationResult ENABLED_BY_DEFAULT = enabled(
			"@EnabledOnClusterAvailable is not present");

	@Override
	public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

		var optional = AnnotationUtils.findAnnotation(context.getElement(), EnabledOnRedisClusterAvailable.class);

		if (!optional.isPresent()) {
			return ENABLED_BY_DEFAULT;
		}

		var annotation = optional.get();

		try (var socket = new Socket()) {

			socket.connect(new InetSocketAddress(annotation.host(), annotation.port()), 100);

			return enabled(
					String.format("Connection successful to Redis Cluster at %s:%d", annotation.host(), annotation.port()));
		} catch (IOException e) {
			return disabled(
					String.format("Cannot connect to Redis Cluster at %s:%d (%s)", annotation.host(), annotation.port(), e));
		}
	}

}

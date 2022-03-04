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
package example.springdata.mongodb.order;

import example.springdata.mongodb.order.Order.OrderId;

import org.jmolecules.ddd.types.Repository;
import org.jmolecules.spring.AssociationResolver;

/**
 * @author Oliver Drotbohm
 */
public interface Orders extends Repository<Order, OrderId>, AssociationResolver<Order, OrderId> {
	Order save(Order order);
}

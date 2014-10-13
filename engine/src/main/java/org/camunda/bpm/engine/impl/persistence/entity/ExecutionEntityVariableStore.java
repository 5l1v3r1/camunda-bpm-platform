/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.engine.impl.persistence.entity;

import org.camunda.bpm.engine.impl.core.variable.CoreVariableScope;
import org.camunda.bpm.engine.impl.variable.AbstractPersistentVariableStore;

import java.util.List;
import java.util.Map;

/**
 * @author Daniel Meyer
 * @author Roman Smirnov
 * @author Sebastian Menski
 *
 */
public class ExecutionEntityVariableStore extends AbstractPersistentVariableStore {

  private static final long serialVersionUID = 1L;

  protected ExecutionEntity executionEntity;

  public ExecutionEntityVariableStore(ExecutionEntity executionEntity) {
    this.executionEntity = executionEntity;
  }

  protected List<VariableInstanceEntity> loadVariableInstances() {
    return executionEntity.loadVariableInstances();
  }

  protected void initializeVariableInstanceBackPointer(VariableInstanceEntity variableInstance) {
    executionEntity.initializeVariableInstanceBackPointer(variableInstance);
  }

  protected boolean isAutoFireHistoryEvents() {
    return executionEntity.isAutoFireHistoryEvents();
  }

  public Map<String, VariableInstanceEntity> getVariableInstancesWithoutInitialization() {
    return variableInstances;
  }

  protected CoreVariableScope<?> getVariableScope() {
    return executionEntity;
  }

}

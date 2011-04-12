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

package org.activiti.engine.impl.persistence.entity;

import java.util.List;

import org.activiti.engine.impl.persistence.AbstractHistoricManager;
import org.activiti.engine.task.Attachment;


/**
 * @author Tom Baeyens
 */
public class AttachmentManager extends AbstractHistoricManager {

  @SuppressWarnings("unchecked")
  public List<Attachment> findAttachmentsByProcessInstanceId(String processInstanceId) {
    checkHistoryEnabled();
    return getPersistenceSession().selectList("selectAttachmentsByProcessInstanceId", processInstanceId);
  }

  @SuppressWarnings("unchecked")
  public List<Attachment> findAttachmentsByTaskId(String taskId) {
    checkHistoryEnabled();
    return getPersistenceSession().selectList("selectAttachmentsByTaskId", taskId);
  }

  @SuppressWarnings("unchecked")
  public void deleteAttachmentsByTaskId(String taskId) {
    checkHistoryEnabled();
    List<AttachmentEntity> attachments = getPersistenceSession().selectList("selectAttachmentsByTaskId", taskId);
    for (AttachmentEntity attachment: attachments) {
      String contentId = attachment.getContentId();
      if (contentId!=null) {
        getPersistenceSession().delete(ByteArrayEntity.class, contentId);
      }
      getPersistenceSession().delete(AttachmentEntity.class, attachment.getId());
    }
  }
}


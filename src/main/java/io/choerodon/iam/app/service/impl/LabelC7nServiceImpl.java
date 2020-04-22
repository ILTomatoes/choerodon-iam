package io.choerodon.iam.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hzero.iam.domain.entity.Label;
import org.hzero.iam.infra.mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.iam.app.service.LabelC7nService;
import io.choerodon.iam.infra.enums.RoleLabel;

/**
 * @author scp
 * @date 2020/3/31
 * @description
 */
@Service
public class LabelC7nServiceImpl implements LabelC7nService {
    @Autowired
    private LabelMapper labelMapper;

    @Override
    public List<Label> listByOption(Label label) {
        List<Label> labelDTOS = labelMapper.select(label);
        // 组织层过滤organization.gitlab.owner标签
        if (ResourceLevel.ORGANIZATION.value().equals(label.getFdLevel())) {
            labelDTOS = labelDTOS.stream()
                    .filter(labelDTO -> !RoleLabel.ORGANIZATION_GITLAB_OWNER.value().equals(labelDTO.getName()))
                    .collect(Collectors.toList());
        }
        return labelDTOS;
    }
}

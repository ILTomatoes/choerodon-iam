package io.choerodon.iam.infra.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import io.choerodon.iam.api.vo.ProjectMapCategoryVO;
import io.choerodon.iam.infra.dto.ProjectDTO;
import io.choerodon.iam.infra.dto.ProjectMapCategoryDTO;
import io.choerodon.mybatis.common.BaseMapper;

/**
 * @author wuguokai
 */
public interface ProjectMapper extends BaseMapper<ProjectDTO> {

    int countProjectsWithRolesSize(@Param("id") Long id,
                                   @Param("params") String params);

    List<ProjectDTO> selectProjectsWithRoles(
            @Param("id") Long id,
            @Param("start") Integer start,
            @Param("size") Integer size,
            @Param("params") String params);

    List<ProjectDTO> selectUserProjectsUnderOrg(@Param("userId") Long userId,
                                                @Param("orgId") Long orgId,
                                                @Param("isEnabled") Boolean isEnabled);


    List<Long> listUserIds(@Param("projectId") Long projectId);

    Boolean projectEnabled(@Param("sourceId") Long sourceId);

    List<ProjectDTO> selectByProjectIds(@Param("ids") Set<Long> ids);

    /**
     * 获取组织下指定type的项目名
     *
     * @param type  项目类型Code
     * @param orgId 组织Id
     * @return 组织下指定type的项目名List
     */
    List<String> selectProjectNameByType(@Param("type") String type,
                                         @Param("orgId") Long orgId);


    /**
     * 获取组织下没有项目类型的项目名
     *
     * @param orgId 组织Id
     * @return 组织下没有项目类型的项目名List
     */
    List<String> selectProjectNameNoType(@Param("orgId") Long orgId);


    ProjectDTO selectCategoryByPrimaryKey(@Param("projectId") Long projectId);

    List<ProjectDTO> selectByOrgIdAndCategory(@Param("organizationId") Long organizationId, @Param("param") String param);

    List<ProjectDTO> selectByOrgIdAndCategoryEnable(@Param("organizationId") Long organizationId, @Param("agile") String agile, @Param("param") String param);

    /**
     * 获取所有项目Id及其对应类型Id
     *
     * @return 列表
     */
    List<ProjectMapCategoryDTO> selectProjectAndCategoryId();

    /**
     * 查询组织下用户的项目列表.
     * 特殊处理: admin用户或组织管理员可查看组织下所有项目
     *
     * @param userId     用户Id
     * @param projectDTO 项目DTO
     * @param isAdmin    是否为admin用户
     * @param isOrgAdmin 是否为组织管理员
     * @param params     模糊查询字段
     * @return 项目列表
     */
    List<ProjectDTO> selectProjectsByUserIdOrAdmin(@Param("organizationId") Long organizationId,
                                                   @Param("userId") Long userId,
                                                   @Param("projectDTO") ProjectDTO projectDTO,
                                                   @Param("isAdmin") Boolean isAdmin,
                                                   @Param("isOrgAdmin") Boolean isOrgAdmin,
                                                   @Param("params") String params);

    List<ProjectDTO> selectAllProjectsByUserIdOrAdmin(@Param("userId") Long userId,
                                                      @Param("projectDTO") ProjectDTO projectDTO,
                                                      @Param("isAdmin") Boolean isAdmin);

    List<ProjectDTO> selectProjectsByOptions(@Param("organizationId") Long organizationId,
                                             @Param("projectDTO") ProjectDTO projectDTO,
                                             @Param("orderBy") String orderBy,
                                             @Param("params") String params);

    List<ProjectDTO> selectProjectsByUserId(@Param("userId") Long userId,
                                            @Param("projectDTO") ProjectDTO projectDTO);


    List<Long> getProListByName(@Param("name") String name);

    List<ProjectDTO> selectProjectsByOrgIdAndNameWithLimit(@Param("organizationId") Long organizationId,
                                                           @Param("name") String name,
                                                           @Param("limit") Integer limit);

    Set<Long> listUserManagedProjectInOrg(@Param("organizationId") Long organizationId,
                                          @Param("userId") Long userId);

    List<ProjectMapCategoryVO> listProjectCategory(@Param("projectIdList") Set<Long> projectIdList);

    List<ProjectDTO> selectProjectsWithCategoryAndRoleByUserIdOrAdmin(@Param("organizationId") Long organizationId,
                                                                      @Param("userId") Long userId,
                                                                      @Param("projectDTO") ProjectDTO projectDTO,
                                                                      @Param("isAdmin") Boolean isAdmin,
                                                                      @Param("isOrgAdmin") Boolean isOrgAdmin,
                                                                      @Param("params") String params);

    List<ProjectDTO> listOwnedProjects(@Param("organizationId") Long organizationId, @Param("userId") Long userId, @Param("isAdmin") boolean isAdmin, @Param("isOrgAdmin") boolean isOrgAdmin);
}

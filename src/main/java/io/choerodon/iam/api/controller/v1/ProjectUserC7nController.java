package io.choerodon.iam.api.controller.v1;

import java.util.List;
import java.util.Set;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import io.choerodon.core.base.BaseController;
import io.choerodon.core.domain.Page;
import io.choerodon.core.iam.ResourceLevel;
import io.choerodon.iam.app.service.ProjectUserService;
import io.choerodon.iam.infra.config.C7nSwaggerApiConfig;
import io.choerodon.iam.infra.dto.UserDTO;
import io.choerodon.iam.infra.dto.UserWithGitlabIdDTO;
import io.choerodon.mybatis.pagehelper.annotation.SortDefault;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.pagehelper.domain.Sort;
import io.choerodon.swagger.annotation.CustomPageRequest;
import io.choerodon.swagger.annotation.Permission;


@Api(tags = C7nSwaggerApiConfig.CHOERODON_PROJECT_USER)
@RestController
@RequestMapping(value = "/choerodon/v1/projects")
public class ProjectUserC7nController extends BaseController {

    private ProjectUserService userService;

    public ProjectUserC7nController(ProjectUserService userService) {
        this.userService = userService;
    }


    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation(value = "项目层分页查询用户列表（包括用户信息以及所分配的项目角色信息）")
    @GetMapping(value = "/{project_id}/users/search")
    @CustomPageRequest
    public ResponseEntity<Page<UserDTO>> pagingQueryUsersWithRolesOnProjectLevel(
            @ApiParam(value = "项目id", required = true)
            @PathVariable(name = "project_id") Long projectId,
            @ApiIgnore
            @SortDefault(value = "id", direction = Sort.Direction.DESC) PageRequest PageRequest,
            @ApiParam(value = "登录名")
            @RequestParam(required = false) String loginName,
            @ApiParam(value = "用户名")
            @RequestParam(required = false) String realName,
            @ApiParam(value = "角色名")
            @RequestParam(required = false) String roleName,
            @ApiParam(value = "是否启用")
            @RequestParam(required = false) Boolean enabled,
            @ApiParam(value = "查询参数")
            @RequestParam(required = false) String params) {
        return new ResponseEntity<>(userService.pagingQueryUsersWithRolesOnProjectLevel(projectId, PageRequest, loginName, realName, roleName,
                enabled, params), HttpStatus.OK);
    }

    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation(value = "项目层查询用户列表（包括用户信息以及所分配的项目角色信息）排除自己")
    @GetMapping(value = "/{project_id}/users/search/list")
    public ResponseEntity<List<UserDTO>> listUsersWithRolesOnProjectLevel(
            @ApiParam(value = "项目id", required = true)
            @PathVariable(name = "project_id") Long projectId,
            @ApiParam(value = "登录名")
            @RequestParam(required = false) String loginName,
            @ApiParam(value = "用户名")
            @RequestParam(required = false) String realName,
            @ApiParam(value = "角色名")
            @RequestParam(required = false) String roleName,
            @ApiParam(value = "查询参数")
            @RequestParam(required = false) String params) {
        return new ResponseEntity<>(userService.listUsersWithRolesOnProjectLevel(projectId, loginName, realName, roleName, params), HttpStatus.OK);
    }


    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation(value = "根据多个id查询用户（包括用户信息以及所分配的项目角色信息以及GitlabUserId）")
    @PostMapping(value = "/{project_id}/users/list_by_ids")
    public ResponseEntity<List<UserWithGitlabIdDTO>> listUsersWithRolesAndGitlabUserIdByIds(
            @ApiParam(value = "项目id", required = true)
            @PathVariable(name = "project_id") Long projectId,
            @ApiParam(value = "多个用户id", required = true)
            @RequestBody Set<Long> userIds) {
        return new ResponseEntity<>(userService.listUsersWithRolesAndGitlabUserIdByIdsInProject(projectId, userIds), HttpStatus.OK);
    }


    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation(value = "查询项目下指定角色的用户列表")
    @GetMapping(value = "/{project_id}/users/{role_lable}")
    public ResponseEntity<List<UserDTO>> listProjectUsersByProjectIdAndRoleLable(
            @ApiParam(value = "项目id", required = true)
            @PathVariable("project_id") Long projectId,
            @ApiParam(value = "角色标签", required = true)
            @PathVariable("role_lable") String roleLable) {
        return ResponseEntity.ok(userService.listProjectUsersByProjectIdAndRoleLabel(projectId, roleLable));
    }


    /**
     * 根据projectId和param模糊查询loginName和realName两列
     */
    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation(value = "查询项目下的用户列表(根据登录名或真实名称搜索)")
    @GetMapping(value = "/{project_id}/users/search_by_name")
    public ResponseEntity<List<UserDTO>> listUsersByName(@PathVariable(name = "project_id") Long projectId,
                                                         @RequestParam(required = false) String param) {
        return ResponseEntity.ok(userService.listUsersByName(projectId, param));
    }


    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation("根据项目id查询项目下的项目所有者")
    @GetMapping("/{project_id}/owner/list")
    public ResponseEntity<List<UserDTO>> listProjectOwnerById(@PathVariable(name = "project_id") Long projectId) {
        return ResponseEntity.ok(userService.listProjectOwnerById(projectId));
    }


    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation(value = "查询项目下的用户列表，根据真实名称或登录名搜索(限制20个)")
    @GetMapping(value = "/{project_id}/users/search_by_name/with_limit")
    public ResponseEntity<List<UserDTO>> listUsersByNameWithLimit(@PathVariable(name = "project_id") Long projectId,
                                                                  @RequestParam(name = "param", required = false) String param) {
        return ResponseEntity.ok(userService.listUsersByNameWithLimit(projectId, param));
    }


    @Permission(level = ResourceLevel.PROJECT)
    @ApiOperation(value = "检查是否还能创建用户")
    @GetMapping("/{project_id}/users/check_enable_create")
    public ResponseEntity<Boolean> checkEnableCreateUser(@PathVariable(name = "project_id") Long projectId) {
//        return ResponseEntity.ok(userService.checkEnableCreateUser(projectId));
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Permission(level = ResourceLevel.PROJECT, permissionWithin = true)
    @ApiOperation(value = "敏捷分页模糊查询项目下的用户和分配issue的用户接口")
    @PostMapping(value = "/{project_id}/agile_users")
    @CustomPageRequest
    public ResponseEntity<Page<UserDTO>> agileUsers(@PathVariable(name = "project_id") Long id,
                                                    @ApiIgnore
                                                    @SortDefault(value = "id", direction = Sort.Direction.DESC)
                                                            PageRequest pageable,
                                                    @RequestBody Set<Long> userIds,
                                                    @RequestParam(required = false) String param) {
        return new ResponseEntity<>(userService.agileUsers(id, pageable, userIds, param), HttpStatus.OK);
    }

}

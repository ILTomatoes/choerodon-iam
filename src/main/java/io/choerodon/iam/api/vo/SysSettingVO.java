package io.choerodon.iam.api.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import io.choerodon.iam.infra.valitador.SysSettingValidator;
import io.choerodon.mybatis.domain.AuditDomain;

/**
 * @author superlee
 * @since 2019-04-23
 */
public class SysSettingVO extends AuditDomain {

    @ApiModelProperty(value = "平台徽标，非必填字段，图片地址，大小缩放显示")
    private String favicon;

    @ApiModelProperty(value = "平台导航栏图形标，非必填字段，图片，大小缩放")
    private String systemLogo;

    @ApiModelProperty(value = "平台全称，非必填字段，如果此字段为空，则展示平台简称的信息")
    private String systemTitle;

    @ApiModelProperty(value = "平台简称，必填字段，20字符")
    @NotEmpty(message = "error.setting.name.null", groups = {SysSettingValidator.GeneralInfoGroup.class})
    @Length(max = 20, message = "error.setting.name.too.long", groups = {SysSettingValidator.GeneralInfoGroup.class})
    private String systemName;

    @ApiModelProperty(value = "平台默认密码，必填字段，至少6字符，至多15字符，数字或字母")
    @NotEmpty(message = "error.setting.default.password.null", groups = {SysSettingValidator.PasswordPolicyGroup.class})
    @Length(min = 6, max = 15, message = "error.setting.default.password.length.invalid", groups = {SysSettingValidator.PasswordPolicyGroup.class})
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "error.setting.default.password.format.invalid", groups = {SysSettingValidator.PasswordPolicyGroup.class})
    private String defaultPassword;

    @ApiModelProperty(value = "平台默认语言，必填字段")
    @NotEmpty(message = "error.setting.default.language.null", groups = {SysSettingValidator.GeneralInfoGroup.class})
    private String defaultLanguage;

    @ApiModelProperty(value = "不启用组织层密码策略时的密码最小长度，非必填字段，默认6")
    @Range(min = 0, max = 65535, message = "error.minLength")
    private Integer minPasswordLength;

    @ApiModelProperty(value = "不启用组织层密码策略时的密码最大长度, 非必填字段，默认18")
    @Range(min = 0, max = 65535, message = "error.maxLength")
    private Integer maxPasswordLength;

    @ApiModelProperty(value = "是否启用注册")
    private Boolean registerEnabled;

    @ApiModelProperty(value = "注册页面链接")
    private String registerUrl;

    @ApiModelProperty(value = "重置gitlab密码页面链接")
    private String resetGitlabPasswordUrl;

    @ApiModelProperty(value = "平台主题色")
    private String themeColor;
    @ApiModelProperty(value = "是否启用平台层 强制修改默认密码")
    private Boolean forceModifyPassword;

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getSystemLogo() {
        return systemLogo;
    }

    public void setSystemLogo(String systemLogo) {
        this.systemLogo = systemLogo;
    }

    public String getSystemTitle() {
        return systemTitle;
    }

    public void setSystemTitle(String systemTitle) {
        this.systemTitle = systemTitle;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public Integer getMinPasswordLength() {
        return minPasswordLength;
    }

    public void setMinPasswordLength(Integer minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }

    public Integer getMaxPasswordLength() {
        return maxPasswordLength;
    }

    public void setMaxPasswordLength(Integer maxPasswordLength) {
        this.maxPasswordLength = maxPasswordLength;
    }

    public Boolean getRegisterEnabled() {
        return registerEnabled;
    }

    public void setRegisterEnabled(Boolean registerEnabled) {
        this.registerEnabled = registerEnabled;
    }

    public String getRegisterUrl() {
        return registerUrl;
    }

    public void setRegisterUrl(String registerUrl) {
        this.registerUrl = registerUrl;
    }

    public String getResetGitlabPasswordUrl() {
        return resetGitlabPasswordUrl;
    }

    public void setResetGitlabPasswordUrl(String resetGitlabPasswordUrl) {
        this.resetGitlabPasswordUrl = resetGitlabPasswordUrl;
    }

    public String getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(String themeColor) {
        this.themeColor = themeColor;
    }

    public Boolean getForceModifyPassword() {
        return forceModifyPassword;
    }

    public void setForceModifyPassword(Boolean forceModifyPassword) {
        this.forceModifyPassword = forceModifyPassword;
    }
}

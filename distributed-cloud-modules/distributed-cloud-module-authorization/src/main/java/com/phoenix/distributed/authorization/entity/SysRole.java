package com.phoenix.distributed.authorization.entity;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author wjj-phoenix
 * @since 2025-09-22
 */
@Data
@Schema(description = "角色")
@EqualsAndHashCode(callSuper = true)
public class SysRole extends Model<SysRole> {

    private static final long serialVersionUID = 1L;

    @Id(value = "role_id", keyType = KeyType.Auto)
    @Schema(description = "角色编号")
    private Long roleId;

    @NotBlank(message = "角色名称不能为空")
    @Schema(description = "角色名称")
    private String roleName;

    @NotBlank(message = "角色标识不能为空")
    @Schema(description = "角色标识")
    private String roleCode;

    @Schema(description = "角色描述")
    private String roleDesc;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private String updateBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 删除标识（0-正常,1-删除）
     */
    @Schema(description = "删除标记,1:已删除,0:正常")
    private String delFlag;

}